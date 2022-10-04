package com.communiti.A;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class Controller {
	
	@RequestMapping("/sendMessage")
	public static String receiveMsg(String msg) {
		
		return msg;
	}

}
