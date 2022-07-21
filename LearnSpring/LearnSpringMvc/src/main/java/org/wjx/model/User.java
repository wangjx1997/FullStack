package org.wjx.model;

import org.wjx.validgroup.ValidationGroup1;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

/**
 * @Author WangJX
 * @Date 2022/3/10 16:14
 * @Description
 */
public class User {
//    @NotNull(groups = { ValidationGroup2.class})
    @NotNull
    private Integer id;
//    @NotEmpty(groups = {ValidationGroup1.class, ValidationGroup2.class})
    @NotEmpty
    private String username;

//    @NotBlank(groups = {ValidationGroup1.class, ValidationGroup2.class})
    private String address;
    @Email(groups = ValidationGroup1.class)
    private String email;
//    @NotNull(groups = {ValidationGroup1.class, ValidationGroup2.class})
//    @NotEmpty
@NotNull
    private Integer age;

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", address='" + address + '\'' +
                ", email='" + email + '\'' +
                ", age=" + age +
                '}';
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }
}
