package org.junyoung.springbootredis.crudrepository;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory;
import org.springframework.data.redis.core.RedisKeyValueAdapter;
import org.springframework.data.redis.repository.configuration.EnableRedisRepositories;

@Configuration
// @EnableRedisRepositories 어노테이션은 Redis와 상호작용하는 데 필요한 레포지토리 인터페이스를 활성화하는데 사용
// enableKeyspaceEvents 속성은 Redis의 Keyspace에 대한 이벤트를 활성화할지 여부를 설정하는 데 사용
// ON_STARTUP은 어플리케이션 시작 시 Redis Keyspace 이벤트를 활성화
// 따라서 아래 코드는 Keyspace의 Event를 어플리케이션이 시작될 때부터 활성화시킨다는 코드
// 기존에는 자동으로 keysapce 내의 키들이 ttl 이후에도 삭제되지 않았지만, 아래 코드를 통해 keyspace event를 탐지하여 index keyspace가 정리되도록 함
// 아래 코드가 실행되는 경우 데이터를 생성할 때 ~~:phantom이 함께 생성되는데, 이는 ttl이 만료되어 이벤트가 발생될 때 이용되는 시스템 데이터
@EnableRedisRepositories(enableKeyspaceEvents = RedisKeyValueAdapter.EnableKeyspaceEvents.ON_STARTUP)
public class RedisConfig {
    @Value("${spring.data.redis.host}")
    private String host;

    @Value("${spring.data.redis.port}")
    private int port;

    @Bean
    public RedisConnectionFactory redisConnectionFactory() {
        return new LettuceConnectionFactory(host, port);
    }
}
