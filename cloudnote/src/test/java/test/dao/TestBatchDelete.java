package test.dao;

import org.junit.Test;

import com.tedu.cloudnote.dao.NoteDao;

import test.service.BaseTest;

public class TestBatchDelete extends BaseTest {
	@Test
	public void test(){
		NoteDao noteDao = ac.getBean("noteDao",NoteDao.class);
		String [] ids = {
			"9187ffd3-4c1e-4768-9f2f-c600e835b823",
			"ebd65da6-3f90-45f9-b045-782928a5e2c0",
			"fed920a0-573c-46c8-ae4e-368397846efd"};
		int rows = noteDao.batchDelete(ids);
		System.out.println("删除的记录行数："+rows);
	}

}
