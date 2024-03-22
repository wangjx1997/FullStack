package org.wjx.properties;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

import java.util.List;
import java.util.Map;

/**
 * @Author WangJX
 * @Date 2024/3/22 10:59
 * @Description
 */
@Data
@ConfigurationProperties(prefix = "testymal")
public class YmlProperties {


    private String configName;

    private UserInfo userInfo;

    private List<String> lists;

    private List<UserInfo> listUserInfo;

    private List<List<UserInfo>> listUserInfos;

    private Map<String, String> maps;

    private Map<String, List<String>> mapList;

    private Map<String, UserInfo> mapUser;

    private Map<String, List<UserInfo>> mapUserList;

    private Map<String, Map<String, String>> ttl;

}
