package com.tedu.cloudnote.service;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.tedu.cloudnote.dao.UserDao;
import com.tedu.cloudnote.entity.User;
import com.tedu.cloudnote.util.NoteException;
import com.tedu.cloudnote.util.NoteResult;
import com.tedu.cloudnote.util.NoteUtil;
@Service("userService")
@Transactional
public class UserServiceImpl implements UserService {

	@Resource
	private UserDao userdao;
	@Transactional(readOnly=true)
	public NoteResult checkLogin(String name, String password) {
		NoteResult result = new NoteResult();
		User user = userdao.findByName(name);
		//�û���Ϊ��
		if(user==null){
			result.setStatus(1);
			result.setMsg("�û�������");
			return result;
		}
		//�ж�����
		//���û���������ļ���
		try {
			String md5_pwd = NoteUtil.md5(password);
		    if(!user.getCn_user_password().equals(md5_pwd)){
			result.setStatus(2);
			result.setMsg("�������");
			return result;
		    }
		} catch (Exception e) {
			throw new NoteException("��������쳣", e);
		}
		//��½�ɹ�
		result.setStatus(0);
		result.setMsg("��½�ɹ�");
		user.setCn_user_password("");//����������
		result.setData(user);//����user��Ϣ��
		return result;
	}
    @Transactional 
	public NoteResult addUser(
		String name, String nick, String password) {
		NoteResult result = new NoteResult();
		try{
		//����Ƿ�����
		User has_user = userdao.findByName(name);
		if(has_user != null){
			result.setStatus(1);
			result.setMsg("�û�����ռ��");
			return result;
		}
		//ִ���û�ע��	
		User user = new User();
		user.setCn_user_name(name);//�����û���
		user.setCn_user_nick(nick);//�����ǳ�
		String md5_pwd = NoteUtil.md5(password);
		user.setCn_user_password(md5_pwd);//��������
		String userId = NoteUtil.createId();
		user.setCn_user_id(userId);//�����û�ID
		userdao.save(user);
		//�������ؽ��
		result.setStatus(0);
		result.setMsg("ע��ɹ�");
		//ģ���쳣
		String s = null;
		s.length();
		//�׳�һ����ָ���쳣
		return result;
		}catch(Exception e){
			throw new NoteException("�û�ע���쳣", e);
		}
	}
}