/**
 * 
 */
package org.wjx.handler;

import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.validation.BindException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.wjx.util.ResultUtil;
import org.wjx.vo.ResultVo;

import javax.validation.ConstraintViolationException;
import java.util.stream.Collectors;

/**
 * @author Administrator
 *
 */
@RestControllerAdvice
public class GlobalExceptionHandler {

	// 处理请求参数格式错误 @RequestParam上validate失败后抛出的异常
	@ExceptionHandler(value = ConstraintViolationException.class)
	public ResultVo handler(ConstraintViolationException e){
		StringBuilder str = new StringBuilder();
		e.getConstraintViolations().forEach(violation -> str.append(violation.getMessage()).append(","));
		return ResultUtil.fail(str.toString());
	}

	// 使用@Valid、@validate  验证路径中请求实体校验失败后抛出的异常   表单请求
	@ExceptionHandler(value = BindException.class)
	public ResultVo handler(BindException e){
		StringBuilder str = new StringBuilder();
		e.getBindingResult().getFieldErrors().forEach(fieldError ->
				str.append(fieldError.getField()).append(":").append(fieldError.getDefaultMessage()).append(",")
		);
		return ResultUtil.fail(str.toString());
	}


	//处理请求参数格式错误 @RequestBody 上@validate失败后抛出的异常。  json传参
	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResultVo MethodArgumentNotValidExceptionHandler(MethodArgumentNotValidException e) {
		String message = e.getBindingResult().getAllErrors().stream().map(DefaultMessageSourceResolvable::getDefaultMessage).collect(Collectors.joining());
		return ResultUtil.fail(message);
	}
}
