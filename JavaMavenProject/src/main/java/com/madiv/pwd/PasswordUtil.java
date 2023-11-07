package com.madiv.pwd;

import org.bouncycastle.jce.provider.BouncyCastleProvider;
import org.jasypt.encryption.pbe.StandardPBEStringEncryptor;
import org.jasypt.encryption.pbe.config.SimplePBEConfig;
import org.jasypt.properties.PropertyValueEncryptionUtils;

/*
 * This program demonstrates hwo we can use jasypt library to encrypt and decrypt password using some encryption key.
 * 
 *  NOTE : Every time we encrypt same password with same key, this util gives different encrypted value.
 *  e.g. 1.> key=ayaan, password=M@n2ABC01, encrpted value comes first go 	=> ENC(QZH1tm1SJdse6NGjsw3gS4iXkUPhnxcgurokHXFkWY4=)
 *       2.> key=ayaan, password=M@n2ABC01, encrpted value comes second go 	=> ENC(CVJW/7/hIVjp9x9vxUF/41grtREoObEDGLNB0fi+jyI=)
 *       3.> key=ayaan, password=M@n2ABC01, encrpted value comes third go 	=> ENC(Mn9ZE031s44QYs5WBc9c3Gu6oOp/HbPikYTnD+gFxus=)
 *       
 *       And when decrypt all abvoe three using smae key "ayaan" every times gives same original password = "M@n2ABC01" 
 */
public class PasswordUtil {
	
	private String encryptionKey = "ayaan";
	private String encryptedPassword = "ENC(QZH1tm1SJdse6NGjsw3gS4iXkUPhnxcgurokHXFkWY4=)";
	private String originalPassword = "M@n2ABC01";
	
	private StandardPBEStringEncryptor stringEncryptor;
	
	
    private void setUpStringEncryptor(String encryptionKey) {
        SimplePBEConfig conf = new SimplePBEConfig();
        conf.setAlgorithm("PBEWithSHA256And128BitAES-CBC-BC");
        conf.setPassword(encryptionKey);
        conf.setProvider(new BouncyCastleProvider());

        stringEncryptor = new StandardPBEStringEncryptor();
        stringEncryptor.setConfig(conf);
    }
	
	public static void main(String[] args) {
		
		
		PasswordUtil util = new PasswordUtil();
		util.decryptPassword();
		
		util.encryptedPassword();
		
		
	}

	private void encryptedPassword() {
		setUpStringEncryptor(this.encryptionKey);
		String encValue = PropertyValueEncryptionUtils.encrypt(this.originalPassword, this.stringEncryptor);
		System.out.println("Encrypted Value ["+encValue+"]");
	}

	private void decryptPassword() {
		setUpStringEncryptor(this.encryptionKey);
		
		String password = null;
		
		if(PropertyValueEncryptionUtils.isEncryptedValue(this.encryptedPassword)) {
			System.out.println("password is encrypted");
			password = PropertyValueEncryptionUtils.decrypt(this.encryptedPassword, this.stringEncryptor);
		} else {
			System.out.println("password is NOT encrypted");
			password = this.encryptedPassword;
		}
		
		
		System.out.println("Pasword =["+password+"]. Compare with orignial =["+password.equals(this.originalPassword)+"]");
	}
}
