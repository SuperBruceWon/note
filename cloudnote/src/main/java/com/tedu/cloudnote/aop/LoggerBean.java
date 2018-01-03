package com.tedu.cloudnote.aop;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

/**
 * ��װ��׮�����߼�
 * ����spring-aop.jar,aspectjweavar.jar
 * aopalliance.jar
 */
@Component//ɨ��
@Aspect//ָ��Ϊ����
public class LoggerBean {
	//ָ��֪ͨ����/�������ʽ
	@Before("within(com.tedu.cloudnote.controller..*)")
	public void LoggerController(){
		System.out.println("����Controller�������");
	}

}
