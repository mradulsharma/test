package com.madiv.crypto.sha1;

import java.io.UnsupportedEncodingException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;

public class Sha1HashCreator {
    private static final String HMAC_SHA1_ALGORITHM = "HmacSHA1";
	
	
	public static void main(String[] args) throws NoSuchAlgorithmException, InvalidKeyException, IllegalStateException, UnsupportedEncodingException {
		Sha1HashCreator creator = new Sha1HashCreator();
		String testPayload = "This is simple message which will be hashed...";
		String hashKey = "b4ee6662023a2b99537a3e75e6fbfa5f98988836";
		String signature = creator.generateSignature(testPayload, hashKey);
		System.out.println("signature for payload is ["+signature+"]");
	}

	private String generateSignature(String testPayload, String hashKey) throws NoSuchAlgorithmException, InvalidKeyException, IllegalStateException, UnsupportedEncodingException {
		
		byte[] key;
		
        key = hashKey.getBytes("UTF-8");
        
        // get an hmac_sha1 key from the raw key bytes
        SecretKeySpec signingKey = new SecretKeySpec(key, HMAC_SHA1_ALGORITHM);   
        
        // get an hmac_sha1 Mac instance and initialise with the signing key
        Mac mac = Mac.getInstance(HMAC_SHA1_ALGORITHM);
        mac.init(signingKey);
        
        // compute the hmac on input data bytes
        byte[] rawHmac = mac.doFinal(testPayload.getBytes("UTF-8"));
        
        // Each byte is hex value, so generate string having hex formated bytes sequence.
        StringBuilder builder = new StringBuilder(100);
        for (byte b : rawHmac) {
            builder.append(String.format("%02x", b));
        }
        
		return builder.toString();
	}
}
