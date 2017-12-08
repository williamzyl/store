package com.zeratul.service;

import java.sql.SQLException;

import com.zeratul.bean.User;
import com.zeratul.dao.UserDao;

public class UserService {

	public boolean register(User user) {
		UserDao dao=new UserDao();
		try {
			return dao.register(user);
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
		
	}

	public void activeUser(String activeCode) throws SQLException {
		UserDao dao=new UserDao();
		dao.active(activeCode);
	}

	public boolean checkName(String username) {
		UserDao dao=new UserDao();
		Long isExist = 0L;
		try {
			isExist=dao.checkName(username);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return isExist>0?true:false;
	}
	
	

	public User login(String username, String password) {
		UserDao dao=new UserDao();
		User user =null;
		try {
			user=dao.login(username,password);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return user;
		
		
	}
	
	
	
}
