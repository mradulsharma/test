package com.madiv.cognito;

import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.auth.AWSCredentialsProvider;
import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.cognitoidp.AWSCognitoIdentityProvider;
import com.amazonaws.services.cognitoidp.AWSCognitoIdentityProviderClientBuilder;
import com.amazonaws.services.cognitoidp.model.AdminCreateUserRequest;
import com.amazonaws.services.cognitoidp.model.AdminCreateUserResult;
import com.amazonaws.services.cognitoidp.model.AttributeType;
import com.amazonaws.services.cognitoidp.model.DeliveryMediumType;
import com.amazonaws.services.cognitoidp.model.UserType;

public class CognitoClient {
	private static String userPoolId = "ap-southeast-2_pbR8hTB9U";  // Affirm Software Group : TestBackgroundCheckUserPool 
	private static String clientId=  "7o5q635iqaostji91ik66g5lkt";  // Affirm Software Group : TestBackgroundCheckApplication

	
//	private static String userPoolId = "ap-southeast-2_baURpaCU9";  // Affirm Software Group : TestUserPool
//	private static String clientId=  "4ch7lvs7c52s98mpg2epsq7tk1";  // Affirm Software Group : ConversationClient
	
	
	private static String accessKey= "";
	private static String secretKey= "";
	
	
	public static void main(String[] args) {
		accessKey = args[0];
		secretKey = args[1];
		
		CognitoClient client = new CognitoClient();
		client.createUserInPool("hockt+Apr29a21@affirmsoftware.com.au");
		
		System.out.println("\n\n\nDone !!");

	}

	private void createUserInPool(String userEmailId) {
		
		AWSCognitoIdentityProvider cognitoClient = getAmazonCognitoIdentityClient();
		
		AdminCreateUserRequest cognitoRequest = new AdminCreateUserRequest()
			       .withUserPoolId(userPoolId)
			       .withUsername(userEmailId)
			       .withUserAttributes(
			             new AttributeType().withName("email").withValue(userEmailId),
		                 new AttributeType().withName("email_verified").withValue("true")
			       )
	              .withTemporaryPassword("Test123~")
	              .withMessageAction("SUPPRESS")
	              .withDesiredDeliveryMediums(DeliveryMediumType.EMAIL)
	              .withForceAliasCreation(Boolean.FALSE);
			 
			       AdminCreateUserResult createUserResult =  cognitoClient.adminCreateUser(cognitoRequest);
			       UserType cognitoUser =  createUserResult.getUser();
			                      
			       System.out.println("cognitoUser = ["+cognitoUser+"]");
		
	}

	private static AWSCognitoIdentityProvider getAmazonCognitoIdentityClient() {
		
		AWSCredentials credentials = new BasicAWSCredentials(accessKey, secretKey); 
		
		AWSCredentialsProvider credentialsProvider =  new AWSStaticCredentialsProvider(credentials);
		
		return AWSCognitoIdentityProviderClientBuilder.standard()
					.withCredentials(credentialsProvider)
					.withRegion(Regions.AP_SOUTHEAST_2)
					.build();		
	}
}
