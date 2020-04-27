package com.chat.qs.utils;

import com.chat.qs.enums.SocketMessageType;

/**
 * Created by Administrator on 2020/4/17 0017.
 */
public class SocketMessage {

    private Integer code;

    private String message;

    private Object data;

    private Integer mode = 0;//0：问答模式 1：互动模式

    private SocketMessageType type;

    public SocketMessage() {
    }

    public SocketMessage(Integer code, String message, Object data,Integer mode, SocketMessageType type) {
        this.code = code;
        this.message = message;
        this.data = data;
        this.mode = mode;
        this.type = type;
    }

    public static SocketMessage download(Object data,String messgae){
        return new SocketMessage(200,messgae,data,0,SocketMessageType.DOWNLOAD);
    }
    public static SocketMessage text(Object data){
        return new SocketMessage(200,null,data,0,SocketMessageType.TEXT);
    }
    public static SocketMessage image(Object data) {
        return new SocketMessage(200,null,data,0,SocketMessageType.IMAGE);
    }
    public static SocketMessage link(Object data){
        return new SocketMessage(200,null,data,0,SocketMessageType.LINK);
    }
    public static SocketMessage weather(Object data,String message,Integer mode){
        return new SocketMessage(200,message,data,mode,SocketMessageType.WEATHER);
    }

    public static SocketMessage fail(){
        return new SocketMessage(500,null,null,0,SocketMessageType.NONE);
    }

    public static SocketMessage fail(String message){
        return new SocketMessage(500,message,null,0,SocketMessageType.NONE);
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    public SocketMessageType getType() {
        return type;
    }

    public void setType(SocketMessageType type) {
        this.type = type;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Integer getMode() {
        return mode;
    }

    public void setMode(Integer mode) {
        this.mode = mode;
    }
}
