package com.tedu.cloudnote.dao;

import com.tedu.cloudnote.entity.User;

public interface UserDao {
	public void save(User user);
	public User findByName(String name);

}
