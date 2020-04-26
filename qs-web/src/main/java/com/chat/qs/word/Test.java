package com.chat.qs.word;

import com.chat.qs.pojo.weather.ResultMsg;
import com.chat.qs.pojo.weather.Weather;
import com.chat.qs.utils.HttpClient;
import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

/**
 * Created by Administrator on 2020/4/23 0023.
 */
public class Test {
    public static void main(String[] args) {
        String str = HttpClient.requestByGetMethod("http://wthrcdn.etouch.cn/weather_mini?city=哈尔滨");


        //String jsonstr = "{\"data\":{\"yesterday\":{\"date\":\"22日星期三\",\"high\":\"高温 5℃\",\"fx\":\"西北风\",\"low\":\"低温 -2℃\",\"fl\":\"<![CDATA[4-5级]]>\",\"type\":\"雨夹雪\"},\"city\":\"哈尔滨\",\"forecast\":[{\"date\":\"23日星期四\",\"high\":\"高温 9℃\",\"fengli\":\"<![CDATA[3-4级]]>\",\"low\":\"低温 -2℃\",\"fengxiang\":\"西北风\",\"type\":\"晴\"},{\"date\":\"24日星期五\",\"high\":\"高温 12℃\",\"fengli\":\"<![CDATA[3-4级]]>\",\"low\":\"低温 0℃\",\"fengxiang\":\"南风\",\"type\":\"晴\"},{\"date\":\"25日星期六\",\"high\":\"高温 11℃\",\"fengli\":\"<![CDATA[3-4级]]>\",\"low\":\"低温 0℃\",\"fengxiang\":\"北风\",\"type\":\"多云\"},{\"date\":\"26日星期天\",\"high\":\"高温 13℃\",\"fengli\":\"<![CDATA[<3级]]>\",\"low\":\"低温 1℃\",\"fengxiang\":\"北风\",\"type\":\"多云\"},{\"date\":\"27日星期一\",\"high\":\"高温 15℃\",\"fengli\":\"<![CDATA[<3级]]>\",\"low\":\"低温 4℃\",\"fengxiang\":\"西风\",\"type\":\"多云\"}],\"ganmao\":\"天气较凉，较易发生感冒，请适当增加衣服。体质较弱的朋友尤其应该注意防护。\",\"wendu\":\"6\"},\"status\":1000,\"desc\":\"OK\"}";
        //String json = "{\"yesterday\":{\"date\":\"22日星期三\",\"high\":\"高温 5℃\",\"fx\":\"西北风\",\"low\":\"低温 -2℃\",\"fl\":\"<![CDATA[4-5级]]>\",\"type\":\"雨夹雪\"},\"city\":\"哈尔滨\",\"forecast\":[{\"date\":\"23日星期四\",\"high\":\"高温 9℃\",\"fengli\":\"<![CDATA[3-4级]]>\",\"low\":\"低温 -2℃\",\"fengxiang\":\"西北风\",\"type\":\"晴\"},{\"date\":\"24日星期五\",\"high\":\"高温 12℃\",\"fengli\":\"<![CDATA[3-4级]]>\",\"low\":\"低温 0℃\",\"fengxiang\":\"南风\",\"type\":\"晴\"},{\"date\":\"25日星期六\",\"high\":\"高温 11℃\",\"fengli\":\"<![CDATA[3-4级]]>\",\"low\":\"低温 0℃\",\"fengxiang\":\"北风\",\"type\":\"多云\"},{\"date\":\"26日星期天\",\"high\":\"高温 13℃\",\"fengli\":\"<![CDATA[<3级]]>\",\"low\":\"低温 1℃\",\"fengxiang\":\"北风\",\"type\":\"多云\"},{\"date\":\"27日星期一\",\"high\":\"高温 15℃\",\"fengli\":\"<![CDATA[<3级]]>\",\"low\":\"低温 4℃\",\"fengxiang\":\"西风\",\"type\":\"多云\"}],\"ganmao\":\"天气较凉，较易发生感冒，请适当增加衣服。体质较弱的朋友尤其应该注意防护。\",\"wendu\":\"6\"}";

        Gson gson = new Gson();
        ResultMsg resultMsg = gson.fromJson(str, ResultMsg.class);
        System.out.println(resultMsg);

//        JsonParser parser = new JsonParser();
//        JsonElement element = parser.parse(json);
//        JsonObject root = element.getAsJsonObject();
//        String resultCode = root.get("data").getAsString();
    }
}
