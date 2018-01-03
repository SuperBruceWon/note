package com.tedu.cloudnote.aop;

import java.io.FileWriter;
import java.io.PrintWriter;

import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

/**
 * 切面：将异常信息写入文件
 *
 */
@Component//扫描
@Aspect//指定为切面
public class ExceptionBean {
	//指定异常通知和切入点表达式
	@AfterThrowing(throwing="e",
			pointcut="within(com.tedu.cloudnote.controller..*)")
	public void execute(Exception e){
		//e就是目标方法抛出的异常对象
//		System.out.println(
//				"将异常信息写入文件"+e);
		try{
			//将e对象信息写入note_error.log文件
		FileWriter fw =
			new FileWriter("D:\\note_error.txt",true);
		PrintWriter pw = new PrintWriter(fw);
		e.printStackTrace(pw);
		pw.close();
		fw.close();
		}catch(Exception ex){
			ex.printStackTrace();
		}
	}

}
