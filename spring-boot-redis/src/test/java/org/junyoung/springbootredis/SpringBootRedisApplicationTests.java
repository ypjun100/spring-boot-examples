package org.junyoung.springbootredis;

import org.junit.jupiter.api.Test;
import org.junyoung.springbootredis.entity.LoginUser;
import org.junyoung.springbootredis.repository.LoginUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class SpringBootRedisApplicationTests {
    @Autowired
    LoginUserRepository loginUserRepository;

    @Test
    void contextLoads() {
        System.out.println(loginUserRepository.count());

        LoginUser userA = new LoginUser("ypjun100");
        loginUserRepository.save(userA);

        LoginUser userB = new LoginUser("ypjun101");
        loginUserRepository.save(userB);

        System.out.println(loginUserRepository.count());

        if (loginUserRepository.findById(userB.getId()).isPresent()) {
            LoginUser user = loginUserRepository.findById(userB.getId()).get();
            loginUserRepository.save(new LoginUser(user));
        }
    }

}
