package com.zeratul.service;

import java.sql.SQLException;
import java.util.List;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.zeratul.bean.Category;
import com.zeratul.dao.CategoryDao;
import com.zeratul.utils.JedisPoolUtils;

import redis.clients.jedis.Jedis;



public class CategoryService {


	public List<Category> getAllCategory() throws SQLException {
		
		List<Category> categories=null;
		
		Jedis jedis=JedisPoolUtils.getJedis();
		
		String categoryJson= jedis.get("CategoryList");
//		System.out.println(categoryJson);
		Gson gson=new Gson();
		categories=gson.fromJson(categoryJson, new TypeToken<List<Category>>() {}.getType());
		if(categoryJson==null){
			CategoryDao dao=new CategoryDao();
			categories= dao.queryCategorys();
			jedis.set("CategoryList", gson.toJson(categories));
		}
		return categories;
		
		

	}

	public Category getCategory(String cid) throws SQLException {
		CategoryDao dao=new CategoryDao();
		return dao.getCategory(cid);
		
	}
}
