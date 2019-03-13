package com.madiv.crypto.keypair;

import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
import java.security.PrivateKey;
import java.security.PublicKey;

import org.bouncycastle.util.encoders.Hex;


/*
 * Author : Maddy
 * 
 * 
 * Read more usage of public and private key at : https://www.tutorialspoint.com/java_cryptography/java_cryptography_keypairgenerator.htm
 * 
 */



public class PublicPrivateKeyClient {
	private KeyPairGenerator keyGen;
	private KeyPair pair;
	private PrivateKey privateKey;
	private PublicKey publicKey;
	
	public PublicPrivateKeyClient(int keyLength) throws NoSuchAlgorithmException, NoSuchProviderException {
		this.keyGen = KeyPairGenerator.getInstance("RSA");
		this.keyGen.initialize(keyLength);
	}
	
	public void createKeys() {
		this.pair = this.keyGen.generateKeyPair();
		this.privateKey = pair.getPrivate();
		this.publicKey = pair.getPublic();
	}	
	
	public PrivateKey getPrivateKey() {
		return this.privateKey;
	}

	public PublicKey getPublicKey() {
		return this.publicKey;
	}	
	
	public static void main(String[] args) {
		PublicPrivateKeyClient client;
		try {
			client = new PublicPrivateKeyClient(1024);
			client.createKeys();
			
			
			System.out.println("Public Key : ["+Hex.toHexString(client.getPublicKey().getEncoded())+"] ");
			System.out.println("Private Key : ["+Hex.toHexString(client.getPrivateKey().getEncoded())+"] ");
			
			
		} catch (NoSuchAlgorithmException | NoSuchProviderException e) {
			System.err.println(e.getMessage());
		}
	}
}
