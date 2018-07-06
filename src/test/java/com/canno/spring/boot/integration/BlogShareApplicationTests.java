package com.canno.spring.boot.integration;

import com.canno.spring.boot.integration.mq.active.DestinationName;
import com.canno.spring.boot.integration.mq.active.Producer;
import com.canno.spring.boot.integration.mq.active.Publisher;
import com.canno.spring.boot.integration.redis.RedisClient;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;

@RunWith(SpringRunner.class)
@SpringBootTest
public class BlogShareApplicationTests {
    @Resource
    Producer producer;
    @Resource
    Publisher publisher;
    @Autowired
    RedisClient redisClient;
    Logger logger = LoggerFactory.getLogger(this.getClass());

    @Test
    public void contextLoads() throws InterruptedException {

        for(int i = 0; i < 10; i ++){
            producer.sendMessage(DestinationName.PRODUCER_MODEL, "第" + i + "条消息！");
            Thread.sleep(3000L);
            publisher.publish(DestinationName.PUBLISHER_MODEL,"第" + i + "条消息！");
        }
        Thread.sleep(200000L);
    }

    @Test
    public void redisTest(){
        System.out.println("___________________________________________________________________");
        String key = "canno";
        redisClient.addOrUpdate(key,"enjoy nice");
        logger.info("redis -> {}: {}",key, redisClient.load("canno"));
    }
}
