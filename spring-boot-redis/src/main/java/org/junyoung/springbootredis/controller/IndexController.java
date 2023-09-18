package org.junyoung.springbootredis.controller;

import lombok.RequiredArgsConstructor;
import org.junyoung.springbootredis.entity.LoginUser;
import org.junyoung.springbootredis.repository.LoginUserRepository;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/")
@RequiredArgsConstructor
public class IndexController {
    private final LoginUserRepository loginUserRepository;

    @PostMapping("/login")
    public String addUser(@RequestBody String name) {
        LoginUser user = new LoginUser(name);
        loginUserRepository.save(user);
        return user.getId();
    }
}
