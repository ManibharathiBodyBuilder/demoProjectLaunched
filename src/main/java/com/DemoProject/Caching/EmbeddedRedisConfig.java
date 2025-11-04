/*package com.DemoProject.Caching;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import redis.embedded.RedisServer;
import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import java.io.IOException;

@Configuration
public class EmbeddedRedisConfig {

    private RedisServer redisServer;

    @PostConstruct
    public void startRedis() throws IOException {
        redisServer = new RedisServer(6379);
        redisServer.start();
        System.out.println("✅ Embedded Redis started on port 6379");
    }

    @PreDestroy
    public void stopRedis() {
        if (redisServer != null) {
            redisServer.stop();
            System.out.println("🛑 Embedded Redis stopped");
        }
    }
}

*/