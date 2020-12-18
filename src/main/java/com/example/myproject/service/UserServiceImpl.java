package com.example.myproject.service;

import com.example.myproject.domain.User;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Title: UserServiceImpl
 * @Descrption: TODO
 * @Author: SunJun
 * @Date: Created in 2020/12/18 11:35 下午
 * @version: V1.0
 * @Modified By:
 */

@Service
public class UserServiceImpl implements UserService {
  private JdbcTemplate jdbcTemplate;

  UserServiceImpl(JdbcTemplate jdbcTemplate) {
    this.jdbcTemplate = jdbcTemplate;
  }

  @Override
  public int create(String name, Integer age) {
    return jdbcTemplate.update("insert into USER(NAME, AGE) values(?, ?)", name, age);
  }

  @Override
  public List<User> getByName(String name) {
    List<User> users = jdbcTemplate.query("select NAME, AGE from USER where NAME = ?", (resultSet, i) -> {
      User user = new User();
      user.setName(resultSet.getString("NAME"));
      user.setAge(resultSet.getInt("AGE"));
      return user;
    }, name);
    return users;
  }

  @Override
  public int deleteByName(String name) {
    return jdbcTemplate.update("delete from USER where NAME = ?", name);
  }

  @Override
  public int getAllUsers() {
    return jdbcTemplate.queryForObject("select count(1) from USER", Integer.class);
  }

  @Override
  public int deleteAllUsers() {
    return jdbcTemplate.update("delete from USER");
  }
}
