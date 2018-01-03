package com.tedu.cloudnote.aop;

import java.io.FileWriter;
import java.io.PrintWriter;

import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

/**
 * ���棺���쳣��Ϣд���ļ�
 *
 */
@Component//ɨ��
@Aspect//ָ��Ϊ����
public class ExceptionBean {
	//ָ���쳣֪ͨ���������ʽ
	@AfterThrowing(throwing="e",
			pointcut="within(com.tedu.cloudnote.controller..*)")
	public void execute(Exception e){
		//e����Ŀ�귽���׳����쳣����
//		System.out.println(
//				"���쳣��Ϣд���ļ�"+e);
		try{
			//��e������Ϣд��note_error.log�ļ�
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
