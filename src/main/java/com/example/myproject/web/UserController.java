package com.example.myproject.web;

import com.example.myproject.domain.User;
import com.example.myproject.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.*;

@Data
@AllArgsConstructor
@Api(tags = "用户管理")
@RestController
@RequestMapping(value = "/users")

public class UserController {

  static Map<Long, User> users = Collections.synchronizedMap(new HashMap<Long, User>());

  private UserService userService;


  @GetMapping("/")
  @ApiOperation(value = "获取用户列表")
  public List<User> getUserList() {
    List<User> r = new ArrayList<User>(users.values());
    return r;
  }

  @PostMapping("/")
//  @PostMapping(value = "", produces = "application/json; charset=utf-8")
  @ApiOperation(value = "创建用户", notes = "根据User对象创建用户")
  public String postUser(@Valid @RequestBody User user) {
//    users.put(user.getId(), user);
//    return "success";
    int result = userService.create(user.getName(), user.getAge());
    return String.valueOf(result);
  }

  @GetMapping("/{id}")
  @ApiOperation(value = "获取用户详细信息", notes = "根据url的id来获取用户详细信息")
  public User getUser(@PathVariable Long id) {
    // url中的id可通过@PathVariable绑定到函数的参数中
    return users.get(id);
  }

  @PutMapping(value = "/{id}", produces = "application/json; charset=utf-8")
  @ApiImplicitParam(paramType = "path", dataType = "Long", name = "id", value = "用户编号", required = true, example = "1")
  @ApiOperation(value = "更新用户详细信息", notes = "根据url的id来指定更新对象，并根据传过来的user信息来更新用户详细信息")
  public String putUser (@PathVariable Long id, @RequestBody User user) {
    User u = users.get(id);
    u.setName(user.getName());
    u.setAge(user.getAge());
    users.put(id, u);
    return "success";
  }

  @DeleteMapping("/{id}")
  @ApiOperation(value = "删除用户", notes = "根据url的id来指定删除对象")
  public String deleteUser(@PathVariable Long id) {
    users.remove(id);
    return "success";
  }

}
