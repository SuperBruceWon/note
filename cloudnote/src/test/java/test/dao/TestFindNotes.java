package test.dao;

import java.sql.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.Test;

import com.tedu.cloudnote.dao.NoteDao;
import com.tedu.cloudnote.entity.Note;

import test.service.BaseTest;

public class TestFindNotes extends BaseTest {
	@Test
	public void test1(){
		NoteDao noteDao =
			ac.getBean("NoteDao",NoteDao.class);
		//创建查询条件params
		Map<String, Object> params =
				new HashMap<String, Object>();
		//params.put("title", "%测试%");
		params.put("status", "2");
		String s1 = "2016-07-01";
		//SimpleDateFormat sdf = new SimpleDateFormat(s1);
		Date beginDate = Date.valueOf(s1);
		Long begin = beginDate.getTime();
		params.put("begin", begin);
		//执行组合查询
		List<Note> list =
			noteDao.findNotes(params);
		for(Note note:list){
			System.out.println(
				note.getCn_note_title());
		}
		System.out.println("记录数： "+list.size());
	}

}
