package com.zhidisoft.until;

import java.util.HashMap;
import java.util.Map;

import org.apache.shiro.crypto.SecureRandomNumberGenerator;
import org.apache.shiro.crypto.hash.SimpleHash;

/**  
* 
* @author tkc  
* @date 2018年9月18日  
*/ 
public class CreatePassWordUtil {

	public static Map enctPwd(String username,String password){
		String algorithmName = "md5"; // 算法名称
		String salt1 = username;
		String salt2 = new SecureRandomNumberGenerator().nextBytes().toHex();
	
		int hashIterations = 2;  //散列迭代次数
		// algorithmName: 加密算法的名称 ；password：明文密码   ； salt: 盐值  ； hashIterations：算法迭代次数
		SimpleHash hash = new SimpleHash(algorithmName, password, salt1 + salt2, hashIterations);
		String encodedPassword = hash.toHex();  
		Map map = new HashMap();
		map.put("pwd", encodedPassword);
		map.put("salt", salt1 + salt2);
		
		return map;
	}
	

}
