package com.communiti.B;

import java.net.URLDecoder;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.communiti.crypt.Crypt;
import com.communiti.crypt.KeyInit;
import com.communiti.crypt.RSA;

@RestController
public class ControllerB {

	Crypt crypt = new Crypt();
	
	private final KeyInit keyInit;
	
	ControllerB(KeyInit init){
		keyInit = init;
	}
	
	@RequestMapping("/sendMessageToA")
	public String sendMsg(String msg) throws Exception {
		RSA userB = keyInit.getUserB();
		String encryptedMsg = crypt.encrypt(msg, userB.getPublicKey());
		String url = "http://localhost:8081/receiveMessageFromB?msg="
				+ URLEncoder.encode(encryptedMsg, StandardCharsets.UTF_8);
		RestTemplate restTemplate = new RestTemplate();
		String res = restTemplate.getForObject(url, String.class);
		return res;
	}
	
	@RequestMapping("/receiveMessageFromA")
	public String receiveMsg(String msg) throws Exception {
		Crypt crypt = new Crypt();
		msg = URLDecoder.decode(msg, StandardCharsets.UTF_8);
		String decryptedMsg = crypt.decrypt(msg, keyInit.getUserB().getPrivateKey());
		System.out.println(decryptedMsg);
		return decryptedMsg;
	}

}
