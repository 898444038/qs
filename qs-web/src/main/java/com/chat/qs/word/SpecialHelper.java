package com.chat.qs.word;

import com.chat.qs.config.redis.RedissonService;
import com.chat.qs.constant.RedisConstant;
import com.chat.qs.entity.City;
import com.chat.qs.entity.Question;
import com.chat.qs.enums.CommonLanguage;
import com.chat.qs.enums.QuestionType;
import com.chat.qs.entity.WeatherType;
import com.chat.qs.pojo.weather.Forecast;
import com.chat.qs.pojo.weather.ResultMsg;
import com.chat.qs.pojo.weather.Weather;
import com.chat.qs.pojo.weather.Yesterday;
import com.chat.qs.utils.HttpClient;
import com.chat.qs.utils.SocketMessage;
import com.chat.qs.utils.SpringBeanUtil;
import com.chat.qs.utils.StringUtils;
import com.google.gson.Gson;
import org.apdplat.word.WordSegmenter;
import org.apdplat.word.segmentation.Word;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * 特殊问题处理
 */
public class SpecialHelper {

    private volatile static SpecialHelper instance = null;

    private SpecialHelper(){}

    public static SpecialHelper getInstance()   {
        if (instance == null)  {
            synchronized (SpecialHelper.class) {
                if (instance== null)  {
                    instance= new SpecialHelper();
                }
            }
        }
        return instance;
    }

    //处理特殊问题
    public SocketMessage handle(Question question,String text) {
        Integer type = question.getType();
        if(QuestionType.weather.getCode().equals(type)){
            return handleWeather(text);
        }else if(QuestionType.deposit_change.getCode().equals(type)){
            return handleDepositChange(text);
        }else if(QuestionType.today_deposit.getCode().equals(type)){
            return handleTodayDeposit(text);
        }else if(QuestionType.card_quota.getCode().equals(type)){
            return handleCardQuota(text);
        }
        return SocketMessage.fail();
    }

    private SocketMessage handleCardQuota(String text) {
        return SocketMessage.download("/static/question/部门信用卡完成数据.xlsx","部门信用卡完成数据.xlsx");
    }

    private SocketMessage handleTodayDeposit(String text) {
        return SocketMessage.image("/static/question/存款数据日报表.jpg");
    }

    private SocketMessage handleDepositChange(String text) {
        return SocketMessage.image("/static/question/myyeji.PNG");
    }

    //处理天气
    private SocketMessage handleWeather(String text) {
        List<Word> lists = WordSegmenter.segWithStopWords(text);
        RedissonService redissonService = SpringBeanUtil.getBean(RedissonService.class);
        List<City> cityList = redissonService.getRBucket(RedisConstant.CITY_LIST_KEY);
        List<WeatherType> weatherTypeList = redissonService.getRBucket(RedisConstant.WEATHER_LIST_KEY);
        City city = null;
        String w = null;
        for (Word word : lists){
            w = word.getText().replaceAll("天气","");
            for(City c : cityList){
                if(c.getName().equals(w)){
                    city = c;
                }
            }
        }
        if(city!=null){
            //String cityNameUrl = "http://wthrcdn.etouch.cn/weather_mini?city="+city.getName();
            String cityKeyUrl = "http://wthrcdn.etouch.cn/weather_mini?citykey="+city.getId();
            String str = HttpClient.requestByGetMethod(cityKeyUrl);
            Gson gson = new Gson();
            ResultMsg resultMsg = gson.fromJson(str, ResultMsg.class);
            if(resultMsg!=null && resultMsg.getStatus().equals(1000)){
                Weather weather = resultMsg.getData();
                StringBuffer sb = new StringBuffer();
                sb.append("城市：").append(weather.getCity()).append("<br>");
                sb.append("温度：").append(weather.getWendu()).append("℃<br>");
                sb.append("提示：").append(weather.getGanmao()).append("<br>");
                sb.append("<div class='card-deck'>");
                Yesterday yesterday = weather.getYesterday();
                WeatherType wt = null;
                for(WeatherType weatherType : weatherTypeList){
                    if(weatherType.getName().equals(yesterday.getType())){
                        wt = weatherType;
                    }
                }
                sb.append("<div class=\"card\" data-type='"+wt.getType()+"'>");
                sb.append("<div class=\"card-header\"><i class='"+wt.getDayIcon()+"'></i></div>");
                sb.append("<div class=\"card-body\">");
                sb.append("<h4 class=\"card-title\">"+yesterday.getDate()+"</h4>");
                sb.append("<h6 class=\"card-subtitle\"><i class=\"zwicon-sun\"></i>"+yesterday.getType()+" "+yesterday.getLow().split(" ")[1]+"~"+yesterday.getHigh().split(" ")[1]+"</h6>");
                sb.append("<p class=\"card-text\">"+yesterday.getFx()+" "+StringUtils.getCDATA(yesterday.getFl())+"</p>");
                sb.append("</div>");
                sb.append("</div>");

                wt=null;
                List<Forecast> forecastList = weather.getForecast();
                AtomicInteger i = new AtomicInteger();
                for (Forecast forecast : forecastList){
                    for(WeatherType weatherType : weatherTypeList){
                        if(weatherType.getName().equals(forecast.getType())){
                            wt = weatherType;
                        }
                    }

                    String selected = "";
                    if(i.incrementAndGet() == 1){
                        selected = "price-table__item--popular-selected";
                    }
                    sb.append("<div class='card "+selected+"' data-type='"+wt.getType()+"'>");
                    sb.append("<div class=\"card-header\"><i class='"+wt.getDayIcon()+"'></i></div>");
                    sb.append("<div class=\"card-body\">");
                    sb.append("<h4 class=\"card-title\">"+forecast.getDate()+"</h4>");
                    sb.append("<h6 class=\"card-subtitle\"><i class='"+wt.getDayIcon()+"'></i>"+forecast.getType()+" "+forecast.getLow().split(" ")[1]+"~"+yesterday.getHigh().split(" ")[1]+"</h6>");
                    sb.append("<p class=\"card-text\">"+forecast.getFengxiang()+" "+StringUtils.getCDATA(forecast.getFengli())+"</p>");
                    sb.append("</div>");
                    sb.append("</div>");
                }
                //sb.append("提示：").append(weather.getGanmao()).append("<br>");

                String type = "none";
                for(WeatherType weatherType : weatherTypeList){
                    if(weatherType.getName().equals(forecastList.get(0).getType())){
                        type = weatherType.getType();
                    }
                }
                sb.append("</div>");
                return SocketMessage.weather(sb.toString(),type,0);
            }
        }else{
            return SocketMessage.weather("天气","您要查询哪个城市天气呢?",1);
        }
        return SocketMessage.text(CommonLanguage.NOT_FOUND_QUESTION.getDesc());
    }
}
