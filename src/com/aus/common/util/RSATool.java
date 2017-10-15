package com.aus.common.util;

import java.security.Key;
import java.security.KeyFactory;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.SecureRandom;
import java.security.Signature;
import java.security.interfaces.RSAPrivateKey;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;

import javax.crypto.Cipher;

public class RSATool {

	private static final char[] bcdLookup = { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'a', 'b', 'c', 'd',
			'e', 'f' };

	/**
	 * 将字节数组转换为16进制字符串的形式.
	 */
	public static final String bytesToHexStr(byte[] bcd) {
		StringBuilder s = new StringBuilder(bcd.length * 2);

		for (int i = 0; i < bcd.length; i++) {
			s.append(bcdLookup[(bcd[i] >>> 4) & 0x0f]);
			s.append(bcdLookup[bcd[i] & 0x0f]);
		}

		return s.toString();
	}

	/**
	 * 将16进制字符串还原为字节数组.
	 */
	public static final byte[] hexStrToBytes(String s) {
		byte[] bytes;

		bytes = new byte[s.length() / 2];

		for (int i = 0; i < bytes.length; i++) {
			bytes[i] = (byte) Integer.parseInt(s.substring(2 * i, 2 * i + 2), 16);
		}

		return bytes;
	}

	/*
	 * 产生RSA公私钥对
	 */
	public static void genRSAKeyPair() {
		KeyPairGenerator rsaKeyGen = null;
		KeyPair rsaKeyPair = null;
		try {
			System.out.println("Generating a pair of RSA key ... ");
			rsaKeyGen = KeyPairGenerator.getInstance("RSA");
			SecureRandom random = new SecureRandom();
			random.setSeed(System.currentTimeMillis());

			rsaKeyGen.initialize(1024, random);
			rsaKeyPair = rsaKeyGen.genKeyPair();
			PublicKey rsaPublic = rsaKeyPair.getPublic();
			PrivateKey rsaPrivate = rsaKeyPair.getPrivate();

			System.out.println("公钥：" + bytesToHexStr(rsaPublic.getEncoded()));
			System.out.println("私钥：" + bytesToHexStr(rsaPrivate.getEncoded()));
			System.out.println("1024-bit RSA key GENERATED.");
		} catch (Exception e) {
			System.out.println("Exception genRSAKeyPair：" + e);
		}
	}

	/*
	 * 产生签名
	 */
	public static String generateSHA1withRSASigature(String src, String priKey) {
		try {

			Signature sigEng = Signature.getInstance("SHA1withRSA");

			byte[] pribyte = hexStrToBytes(priKey.trim());

			PKCS8EncodedKeySpec keySpec = new PKCS8EncodedKeySpec(pribyte);

			KeyFactory fac = KeyFactory.getInstance("RSA");
			// RSAPublicKey pubKey = (RSAPublicKey)fac.generatePublic(keySpec);
			RSAPrivateKey privateKey = (RSAPrivateKey) fac.generatePrivate(keySpec);
			sigEng.initSign(privateKey);
			sigEng.update(src.getBytes());

			byte[] signature = sigEng.sign();
			return bytesToHexStr(signature);
		} catch (Exception e) {
			System.err.println(e);
			e.printStackTrace(System.err);
			return null;
		}
	}

	/**
	 * 使用公钥解密
	 * 
	 * @param enc
	 * @param priKey
	 * @return by jinshan Feb 1, 2010
	 */
	public static String decryptWithPubKey(String enc, String pubKey) {
		try {
			byte[] pubbyte = hexStrToBytes(pubKey.trim());

			X509EncodedKeySpec keySpec = new X509EncodedKeySpec(pubbyte);
			KeyFactory fac = KeyFactory.getInstance("RSA");
			Key publicKey = fac.generatePublic(keySpec);

			Cipher cipher = Cipher.getInstance("RSA");
			cipher.init(Cipher.DECRYPT_MODE, publicKey);

			byte[] forumcookie = hexStrToBytes(enc);

			byte[] plainText = cipher.doFinal(forumcookie);

			return new String(plainText);

		} catch (Exception e) {
			System.err.println(e);
			e.printStackTrace(System.err);
			return null;
		}
	}

	/**
	 * @param args
	 *            by jinshan Dec 15, 2009
	 */
	public static void main(String[] args) {

		genRSAKeyPair();

	}

}
