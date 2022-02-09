package com.myself.demo.config;

import com.alibaba.fastjson.JSON;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.List;

/**
 * 日志aop切面
 *
execution(* com.tp.soft.service..*.*(..))

 整个表达式可以分为五个部分：

 1、execution(): 表达式主体。

 2、第一个*号：表示返回类型，*号表示所有的类型。

 3、包名：表示需要拦截的包名，后面的两个句点表示当前包和当前包的所有子包，com.sample.service.impl包、子孙包下所有类的方法。

 4、第二个*号：表示类名，*号表示所有的类。

 5、*(..):最后这个星号表示方法名，*号表示所有的方法，后面括弧里面表示方法的参数，两个句点表示任何参数。
 *
 */

@Aspect
@Component
public class logAOP {

	@Before(value = "execution(* com.myself.demo.controller..*.*(..))")
	public void before(JoinPoint joinPoint) {
		//获取方法名
		String name = joinPoint.getSignature().getName();
		//获取参数
		// 构造参数组集合
		List<Object> argList = new ArrayList<>();
		for (Object  arg : joinPoint.getArgs()) {
			// request/response无法使用toJSON
			if (arg instanceof HttpServletRequest) {
				argList.add("request");
			} else if (arg instanceof HttpServletResponse) {
				argList.add("response");
			} else {
				argList.add(arg);
			}
		}


		System.out.println("AOP——"+"方法名:"+name+"参数:"+argList.toString());
	}

	@After(value = "execution(* com.myself.demo.controller..*.*(..))")
	public void after(JoinPoint joinPoint){
		//获取方法名
		String name = joinPoint.getSignature().getName();
		System.out.println("AOP——"+name+"方法执行完毕");
	}

	@AfterReturning(value = "execution(* com.myself.demo.controller..*.*(..))",returning = "result")
	public void afterReturning(JoinPoint joinPoint,Object result){
		//获取方法名
		String name = joinPoint.getSignature().getName();
		//获取方法返回结果
		String responce = JSON.toJSONString(result);
		System.out.println("AOP——"+"方法名"+name+"返回结果"+responce);
	}

	@AfterThrowing(value = "execution(* com.myself.demo.controller..*.*(..))",throwing = "exception")
	public void afterThrowing(JoinPoint joinPoint,Exception exception){
		//获取方法名
		String name = joinPoint.getSignature().getName();
		System.out.println("AOP——"+name+"方法抛出异常："+exception);
	}

}
