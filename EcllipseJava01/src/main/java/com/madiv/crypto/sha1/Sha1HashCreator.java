package com.madiv.crypto.sha1;

import java.io.UnsupportedEncodingException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;

import org.apache.commons.codec.DecoderException;
import org.apache.commons.codec.binary.Base64;
import org.apache.commons.codec.binary.Hex;

public class Sha1HashCreator {
    private static final String HMAC_SHA1_ALGORITHM = "HmacSHA1";
	
	
	public static void main(String[] args) throws NoSuchAlgorithmException, InvalidKeyException, IllegalStateException, UnsupportedEncodingException {
		Sha1HashCreator creator = new Sha1HashCreator();
		String testPayload = "This is my test payload, and its HmacSHA1 has would be generated in this code..";
		String hashKey = "b4ee6662023a2b99537a3e75e6fbfa5f98988836";
		String signature = creator.generateSignature(testPayload, hashKey);
		System.out.println("signature for payload is ["+signature+"]");
	}

	private String generateSignature(String testPayload, String hashKey) throws NoSuchAlgorithmException, InvalidKeyException, IllegalStateException, UnsupportedEncodingException {
		String retValue = null;
		
		byte[] key;
        try {
            key = Hex.decodeHex(hashKey.toCharArray());
        } catch (DecoderException e) {
            throw new SecurityException("Invalid HEX key", e);
        }
		
		
        
        // get an hmac_sha1 key from the raw key bytes
        SecretKeySpec signingKey = new SecretKeySpec(key, HMAC_SHA1_ALGORITHM);   
        
        // get an hmac_sha1 Mac instance and initialise with the signing key
        Mac mac = Mac.getInstance(HMAC_SHA1_ALGORITHM);
        mac.init(signingKey);
        
        // compute the hmac on input data bytes
        byte[] rawHmac = mac.doFinal(testPayload.getBytes("UTF-8"));
        
        // base64-encode the hmac
        retValue = Base64.encodeBase64String(rawHmac);
        
		return retValue;
	}
}
