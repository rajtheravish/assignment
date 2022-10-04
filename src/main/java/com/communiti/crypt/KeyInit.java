package com.communiti.crypt;

//Initialization of two user A and B
public class KeyInit {
	public static RSA UserA = new RSA();
	public static RSA UserB = new RSA();

	public KeyInit() {
		UserA = new RSA();
		UserB = new RSA();
	}

	public RSA getUserA() {
		return UserA;
	}

	public void setUserA(RSA userA) {
		UserA = userA;
	}

	public RSA getUserB() {
		return UserB;
	}

	public void setUserB(RSA userB) {
		UserB = userB;
	}

}
