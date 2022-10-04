package com.communiti.crypt;

import java.security.PrivateKey;
import java.security.PublicKey;
import java.util.Base64;

import javax.crypto.Cipher;

public class Crypt {
	
	//Encrypt a message using public key
	public String encrypt(String msg, PublicKey publicKey) throws Exception {
		byte[] msgToByte = msg.getBytes();
		Cipher cipher = Cipher.getInstance("RSA/ECB/PKCS1Padding");
		cipher.init(Cipher.ENCRYPT_MODE, publicKey);
		byte[] encryptedBytes = cipher.doFinal(msgToByte);
		return encode(encryptedBytes);
	}
	
	public String encode(byte[] data) {
		return Base64.getEncoder().encodeToString(data);
	}
	
	//Decrypt a message using public key
	public String decrypt(String encryptedMsg, PrivateKey privateKey) throws Exception {
		byte[] msgToByte = decode(encryptedMsg);
		Cipher cipher = Cipher.getInstance("RSA/ECB/PKCS1Padding");
		cipher.init(Cipher.DECRYPT_MODE, privateKey);
		byte[] decryptedBytes = cipher.doFinal(msgToByte);
		return new String(decryptedBytes, "UTF8");
	}
	
	public byte[] decode(String data) {
		return Base64.getDecoder().decode(data);
	}
}
