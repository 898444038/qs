package com.chat.qs.config.socket;

import com.alibaba.fastjson.JSON;
import com.chat.qs.enums.SocketMessageType;
import com.chat.qs.utils.SocketMessage;
import com.chat.qs.word.WordHelper;
import com.chat.qs.word.qa.QuestionAnswerHelper;
import com.google.gson.Gson;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelId;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.handler.codec.http.FullHttpRequest;
import io.netty.handler.codec.http.websocketx.TextWebSocketFrame;

import java.net.InetSocketAddress;
import java.util.HashMap;
import java.util.Map;

/**
 * socket = new WebSocket("ws://127.0.0.1:12345/ws?uid=666&gid=777");
 * channelActive与客户端建立连接
 * channelInactive与客户端断开连接
 * channelRead0客户端发送消息处理
 */
public class MyWebSocketHandler extends SimpleChannelInboundHandler<TextWebSocketFrame> {

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        System.out.println("与客户端建立连接，通道开启！");
        //添加到channelGroup通道组
        MyChannelHandlerPool.channelGroup.add(ctx.channel());
    }

    @Override
    public void channelInactive(ChannelHandlerContext ctx) throws Exception {
        System.out.println("与客户端断开连接，通道关闭！");
        //添加到channelGroup 通道组
        MyChannelHandlerPool.channelGroup.remove(ctx.channel());
    }

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        //首次连接是FullHttpRequest，处理参数
        if (null != msg && msg instanceof FullHttpRequest) {
            FullHttpRequest request = (FullHttpRequest) msg;
            String uri = request.uri();

            Map paramMap=getUrlParams(uri);
            System.out.println("接收到的参数是："+JSON.toJSONString(paramMap));
            //如果url包含参数，需要处理
            if(uri.contains("?")){
                String newUri=uri.substring(0,uri.indexOf("?"));
                System.out.println(newUri);
                request.setUri(newUri);
            }

            //客户端Ip
            String clientIP = request.headers().get("X-Forwarded-For");
            if (clientIP == null) {
                InetSocketAddress insocket = (InetSocketAddress) ctx.channel().remoteAddress();
                clientIP = insocket.getAddress().getHostAddress();
            }
            System.out.println("接收到的参数是："+JSON.toJSONString(paramMap)+",clientIP:"+clientIP);
        }else if(msg instanceof TextWebSocketFrame){
            Gson gson = new Gson();
            ChannelId channelId = ctx.channel().id();
            //正常的TEXT消息类型
            TextWebSocketFrame frame=(TextWebSocketFrame)msg;
            String text = frame.text();
            System.out.println("客户端收到服务器数据：" + text);
            SocketMessage receiveMessage = gson.fromJson(text,SocketMessage.class);
            SocketMessage message = null;
            if(receiveMessage.getType().equals(SocketMessageType.LABEL)){
                message = WordHelper.getInstance().label(Long.valueOf(receiveMessage.getData().toString()));
            }else if(receiveMessage.getType().equals(SocketMessageType.CATE)){
                message = WordHelper.getInstance().cate(Long.valueOf(receiveMessage.getData().toString()));
            }else{
                message = WordHelper.getInstance().seg(receiveMessage);
            }
            sendMessage(channelId,gson.toJson(message));
            //todo 保存会话、记录日志
        }
        super.channelRead(ctx,msg);
    }

    @Override
    protected void channelRead0(ChannelHandlerContext channelHandlerContext, TextWebSocketFrame textWebSocketFrame) throws Exception {

    }

    //收到信息后，群发给所有channel
    private void sendAllMessage(String message){
        MyChannelHandlerPool.channelGroup.writeAndFlush(new TextWebSocketFrame(message));
    }

    //收到信息后，单发给指定的channelId
    private void sendMessage(ChannelId channelId, String message){
        MyChannelHandlerPool.channelGroup.find(channelId).writeAndFlush(new TextWebSocketFrame(message));
    }

    private static Map getUrlParams(String url){
        Map<String,String> map = new HashMap<>();
        url = url.replace("?",";");
        if (!url.contains(";")){
            return map;
        }
        if (url.split(";").length > 0){
            String[] arr = url.split(";")[1].split("&");
            for (String s : arr){
                String key = s.split("=")[0];
                String value = s.split("=")[1];
                map.put(key,value);
            }
            return  map;

        }else{
            return map;
        }
    }
}
