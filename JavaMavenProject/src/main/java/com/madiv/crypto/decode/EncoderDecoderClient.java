package com.madiv.crypto.decode;

import java.util.Base64;

public class EncoderDecoderClient {
	public static void main(String[] args) {
		String originalMsg = "This is my test string to check encoding and decoding works in java Base64";
		
		System.out.println("originalMsg = ["+originalMsg+"]");
		EncoderDecoderClient client = new EncoderDecoderClient();
		String encodedMessage = client.encodeMsg(originalMsg);
		System.out.println("encodedMessage = ["+encodedMessage+"]");
		String decodedMessage = client.decodeMsg(encodedMessage);
		System.out.println("decodedMessage = ["+decodedMessage+"]");
		
		
		System.out.println("is original message and decoded are same : " + originalMsg.equals(decodedMessage));
	}

	private String decodeMsg(String encodedMessage) {
		return new String(Base64.getDecoder().decode(encodedMessage));
	}

	private String encodeMsg(String originalMsg) {
		return Base64.getEncoder().encodeToString(originalMsg.getBytes());
	}
}
