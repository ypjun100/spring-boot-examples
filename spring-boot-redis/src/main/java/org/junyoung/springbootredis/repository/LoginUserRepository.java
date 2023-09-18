package org.junyoung.springbootredis.repository;

import org.junyoung.springbootredis.entity.LoginUser;
import org.springframework.data.repository.CrudRepository;

public interface LoginUserRepository extends CrudRepository<LoginUser, String> {
}