package com.zeratul.dao;

import java.sql.SQLException;


import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;

import com.zeratul.bean.User;
import com.zeratul.utils.DataSourceUtils;

public class UserDao {

	public boolean register(User user) throws SQLException {
		QueryRunner runner=new QueryRunner(DataSourceUtils.getDataSource());
	
          String sql="insert into user values(?,?,?,?,?,?,?,?,?,?);";
		
		
		int result= runner.update(sql,user.getUid(),user.getUsername(),user.getPassword(),user.getName(),user.getEmail(),user.getTelephone()
				,user.getBirthday(),user.getSex(),user.getState(),user.getCode());
		
		return result>0?true:false;
		
	}

	public void active(String activeCode) throws SQLException {
		QueryRunner runner=new QueryRunner(DataSourceUtils.getDataSource());
		
        String sql="update  user set state=1  where code = ?";
		
		runner.update(sql,activeCode);
		
	}

	public Long checkName(String username) throws SQLException {
        QueryRunner runner=new QueryRunner(DataSourceUtils.getDataSource());
		
        String sql="select count(*) from user where username=?";
		
		Long query = (Long) runner.query(sql, new ScalarHandler(), username);
		return query;
		
	}

	public User login(String username, String password) throws SQLException {
        QueryRunner runner=new QueryRunner(DataSourceUtils.getDataSource());
		
        String sql="select * from user where username=? and password=?";
		
        User user = (User) runner.query(sql, new BeanHandler<User>(User.class), username,password);
		return user;
	}

}
