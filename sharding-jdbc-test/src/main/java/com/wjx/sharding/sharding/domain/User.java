package com.wjx.sharding.sharding.domain;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

/**
 * @Author WangJX
 * @Date 2024/3/29 16:46
 * @Description
 */
@TableName("t_user_0")
@Data
public class User {

  private Integer id;
  private String name;
}
