package com.chat.qs.utils;

import com.chat.qs.enums.SocketMessageType;

/**
 * Created by Administrator on 2020/4/17 0017.
 */
public class SocketMessage {

    private Integer code;

    private String message;

    private Object data;

    private SocketMessageType type;

    public SocketMessage() {
    }

    public SocketMessage(Integer code, String message, Object data, SocketMessageType type) {
        this.code = code;
        this.message = message;
        this.data = data;
        this.type = type;
    }

    public static SocketMessage download(Object data,String messgae){
        return new SocketMessage(200,messgae,data,SocketMessageType.DOWNLOAD);
    }
    public static SocketMessage text(Object data){
        return new SocketMessage(200,null,data,SocketMessageType.TEXT);
    }
    public static SocketMessage image(Object data) {
        return new SocketMessage(200,null,data,SocketMessageType.IMAGE);
    }
    public static SocketMessage link(Object data){
        return new SocketMessage(200,null,data,SocketMessageType.LINK);
    }
    public static SocketMessage weather(Object data,String message){
        return new SocketMessage(200,message,data,SocketMessageType.WEATHER);
    }

    public static SocketMessage fail(){
        return new SocketMessage(500,null,null,SocketMessageType.NONE);
    }

    public static SocketMessage fail(String message){
        return new SocketMessage(500,message,null,SocketMessageType.NONE);
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

}
