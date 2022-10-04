package com.communiti.A;

import java.net.URLDecoder;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.communiti.crypt.Crypt;
import com.communiti.crypt.KeyInit;

@RestController
public class ControllerA {

	Crypt crypt = new Crypt();

	private final KeyInit keyInit;

	ControllerA(KeyInit init) {
		keyInit = init;
	}

	// Receive Normal message for user B
	@RequestMapping("/sendMessageToB")
	public String sendMsg(String msg) throws Exception {
		// Encrypt normal message using public key
		String encryptedMsg = crypt.encrypt(msg, keyInit.getUserB().getPublicKey());
		// pass encrypted message to user A, if this message is received by any one else
		// also he can't understand the message
		String url = "http://localhost:8081/receiveMessageFromA?msg="
				+ URLEncoder.encode(encryptedMsg, StandardCharsets.UTF_8);
		RestTemplate restTemplate = new RestTemplate();
		// Send encrypted message to other user.
		String res = restTemplate.getForObject(url, String.class);
		return res;
	}

	//Receive encrypted message from other user and return decrypted message.
	@RequestMapping("/receiveMessageFromB")
	public String receiveMsg(String msg) throws Exception {
		Crypt crypt = new Crypt();
		msg = URLDecoder.decode(msg, StandardCharsets.UTF_8);
		//Decrypt the message using user's private key
		String decryptedMsg = crypt.decrypt(msg, keyInit.getUserB().getPrivateKey());
		return decryptedMsg;
	}

}
