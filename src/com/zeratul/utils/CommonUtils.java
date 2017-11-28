package com.zeratul.utils;

import java.util.UUID;

public class CommonUtils {

	public static String getUUid(){
		return UUID.randomUUID().toString().replaceAll("-", "");
	}
	
}
