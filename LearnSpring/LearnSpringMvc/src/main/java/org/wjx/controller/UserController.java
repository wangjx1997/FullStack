package org.wjx.controller;

import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.wjx.model.User;
import org.wjx.util.ResultUtil;
import org.wjx.validgroup.ValidationGroup2;
import org.wjx.vo.ResultVo;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @Author WangJX
 * @Date 2022/3/17 20:43
 * @Description
 */
@Controller
@RequestMapping("/user/")
@Validated
public class UserController {

    private static Map<Integer, User> userMap = new ConcurrentHashMap<>();

    private static AtomicInteger atomicInteger = new AtomicInteger(0);

    @GetMapping(value = "add")
    public String addUser() {
        return "user";
    }

    @GetMapping(value = "add1")
    public String addUser1() {
        return "user1";
    }

    @GetMapping(value = "add2")
    public String addUser2() {
        return "user2";
    }

    @GetMapping(value = "update")
    public String update() {
        return "userUpdate";
    }

    @ResponseBody
    @PostMapping(value = "doAdd")
    public ResultVo addUser(@Valid User user, BindingResult result) {
        if (result.hasErrors()) {
            //校验未通过，获取所有的异常信息并展示出来
            List<FieldError> fieldErrors = result.getFieldErrors();
            StringBuilder str = new StringBuilder();
            for (FieldError fieldError : fieldErrors) {
                str.append(fieldError.getField()).append(":").append(fieldError.getDefaultMessage());
            }
            return ResultUtil.fail(str.toString());
        }
        user.setId(atomicInteger.incrementAndGet());
        userMap.put(user.getId(), user);
        return ResultUtil.success(user);
    }

    @ResponseBody
    @PostMapping(value = "doAdd1")
    public ResultVo addUser1(@Valid @RequestBody User user) {
        user.setId(atomicInteger.incrementAndGet());
        userMap.put(user.getId(), user);
        return ResultUtil.success(user);
    }

    @ResponseBody
    @PostMapping(value = "doAdd2")
    public ResultVo addUser2(@Validated(ValidationGroup2.class) User user) {
        user.setId(atomicInteger.incrementAndGet());
        userMap.put(user.getId(), user);
        return ResultUtil.success(user);
    }

    @ResponseBody
    @GetMapping(value = "info")
    public ResultVo info(@NotNull(message = "不能为空") @RequestParam("id") Integer id) {
        return ResultUtil.success(userMap.get(id));
    }


    @ResponseBody
    @PostMapping(value = "updateUser")
    public ResultVo updateUser(@Validated User user) {
        if (null == userMap.get(user.getId())) {
            return ResultUtil.fail("不存在此用户");
        }
        userMap.put(user.getId(), user);
        return ResultUtil.success(userMap.get(user.getId()));
    }
}
