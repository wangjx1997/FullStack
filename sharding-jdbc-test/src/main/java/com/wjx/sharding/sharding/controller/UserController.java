package com.wjx.sharding.sharding.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.wjx.sharding.sharding.domain.Org;
import com.wjx.sharding.sharding.domain.User;
import com.wjx.sharding.sharding.mapper.OrgDao;
import com.wjx.sharding.sharding.mapper.UserDao;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

/**
 * @Author WangJX
 * @Date 2024/3/29 16:48
 * @Description
 */
@RestController
@RequestMapping("/user/")
public class UserController {

  @Resource
  private UserDao userDao;

  @Resource
  private OrgDao orgDao;


  @GetMapping("list")
  public List<User> test(){
    return userDao.selectList(new QueryWrapper<User>());
  }

  @PostMapping("add")
  public void add(){
    final User user = new User();
    user.setName("wwww");
    userDao.insert(user);
  }

  @PostMapping("mixed")
  public void mixed(){
    final List<Org> orgs = orgDao.selectList(new QueryWrapper<Org>());
    orgs.forEach(System.out::println);
    final User user = new User();
    user.setName("mixed");
    userDao.insert(user);

    final User user1 = userDao.selectById(user.getId());
    System.out.println(user1);
  }


  @PostMapping("mixedTr")
  @Transactional
  public void mixedTr(){
    final List<Org> orgs = orgDao.selectList(new QueryWrapper<Org>());
    orgs.forEach(System.out::println);
    final User user = new User();
    user.setName("mixed");
    userDao.insert(user);

    final User user1 = userDao.selectById(user.getId());
    System.out.println(user1);
  }

}
