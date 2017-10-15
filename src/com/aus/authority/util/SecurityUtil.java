package com.aus.authority.util;

import java.security.SecureRandom;

import org.apache.commons.codec.DecoderException;
import org.apache.commons.codec.binary.Hex;
import org.springside.modules.security.utils.Digests;
import org.springside.modules.utils.Encodes;

import com.aus.authority.model.UserDto;
import com.aus.user.util.Constant;

public class SecurityUtil {
	
	private static SecureRandom random = new SecureRandom();
	
	/**
	 * 生成盐值
	 * @param numBytes
	 * @return
	 */
	public static String generateSalt(int numBytes) {
		
		byte[] bytes = new byte[numBytes];
		
		random.nextBytes(bytes);
		
		return Hex.encodeHexString(bytes);
	}
	
	/**
	 * 设定安全的密码，生成随机的salt并经过1024次 sha-1 hash
	 * @throws DecoderException 
	 */
	public static String entryptPassword(String password,String salt) throws DecoderException {
		
		byte[] hashPassword = Digests.sha1(password.getBytes(), Hex.decodeHex(salt.toCharArray()), Constant.HASH_INTERATIONS);
		
		return Encodes.encodeHex(hashPassword);
	}
	
	
	
	/**
	 * 设定安全的密码，生成随机的salt并经过1024次 sha-1 hash
	 */
	public static void entryptPassword(UserDto userDto) {
		
		byte[] salt = Digests.generateSalt(Constant.SALT_SIZE);
		
		userDto.setSalt(Encodes.encodeHex(salt));

		byte[] hashPassword = Digests.sha1(userDto.getPassword().getBytes(), salt, Constant.HASH_INTERATIONS);
		
		userDto.setPassword(Encodes.encodeHex(hashPassword));
	}

	
	public static void main(String[] args) throws DecoderException {
		String salt = "c8fb3fc479a8883d";
		String password = "1qaz!QAZ";
		String str  = SecurityUtil.entryptPassword("123456","c8fb3fc479a8883d");
		String str1  = SecurityUtil.entryptPassword("123456","c8fb3fc479a8883d");
		System.out.println(" str ="+str);
		System.out.println(" str1 ="+str1);
	}

}
