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

    @GetMapping("/number-of-users")
    public long getNumberOfUsers() {
        return loginUserRepository.count();
    }

    @PostMapping("/user")
    public String addUser(@RequestBody String name) {
        LoginUser user = new LoginUser(name);
        loginUserRepository.save(user);
        return user.getId();
    }

    @PutMapping("/user")
    public void updateUser(@RequestBody String id) {
        if (loginUserRepository.findById(id).isPresent()) {
            LoginUser user = loginUserRepository.findById(id).get();
            loginUserRepository.save(new LoginUser(user));
        }
    }
}
