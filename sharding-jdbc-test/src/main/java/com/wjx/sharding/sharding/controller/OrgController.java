package com.wjx.sharding.sharding.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.wjx.sharding.sharding.domain.Org;
import com.wjx.sharding.sharding.mapper.OrgDao;
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
@RequestMapping("/org/")
public class OrgController {


  @Resource
  private OrgDao orgDao;



  @GetMapping("list")
  public List<Org> test(){
    return orgDao.selectList(new QueryWrapper<Org>());
  }

  @PostMapping("add")
  public void add(){
    final Org org = new Org();
    org.setName("jigou");
    orgDao.insert(org);
  }


}
