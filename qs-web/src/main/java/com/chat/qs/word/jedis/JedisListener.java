package com.chat.qs.word.jedis;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

/**
 * Created by Administrator on 2020/4/15 0015.
 */
public class JedisListener implements ServletContextListener {
    private static final Logger LOGGER = LoggerFactory.getLogger(JedisListener.class);
    private JedisPool jedisPool;

    @Override
    public void contextInitialized(ServletContextEvent sce) {
        ServletContext sc = sce.getServletContext();
        String redisHost = sc.getInitParameter("redis.host");
        String redisPort = sc.getInitParameter("redis.port");
        LOGGER.info("redis.host: "+redisHost);
        LOGGER.info("redis.port: "+redisPort);
        LOGGER.info("开始初始化JedisPool");
        try{
            JedisPoolConfig jedispool_config = new JedisPoolConfig();
            jedisPool = new JedisPool(jedispool_config, redisHost, Integer.parseInt(redisPort));
            ResourceChangeNotifier.setJedisPool(jedisPool);
            LOGGER.info("初始化JedisPool成功");
        }catch(Exception e){
            LOGGER.error("初始化JedisPool失败", e);
        }
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        jedisPool.destroy();
        LOGGER.info("关闭JedisPool");
    }
}