package com.wjx.sharding.sharding.domain;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

/**
 * @Author WangJX
 * @Date 2024/3/29 17:12
 * @Description
 */
@TableName("t_org_0")
@Data
public class Org {

  private Integer id;
  private String name;
}
