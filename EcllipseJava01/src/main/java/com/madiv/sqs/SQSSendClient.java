package com.madiv.sqs;

import java.util.Scanner;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageConsumer;
import javax.jms.Session;
import javax.jms.TextMessage;

import com.amazon.sqs.javamessaging.AmazonSQSMessagingClientWrapper;
import com.amazon.sqs.javamessaging.ProviderConfiguration;
import com.amazon.sqs.javamessaging.SQSConnection;
import com.amazon.sqs.javamessaging.SQSConnectionFactory;
import com.amazonaws.services.sqs.AmazonSQS;
import com.amazonaws.services.sqs.AmazonSQSClientBuilder;
import com.amazonaws.services.sqs.model.SendMessageRequest;

/*
 * 
 * We need to run this client from EC2 instance, where IAM role is created for EC2 to communicate to SQS.
 * 
 * Argument 01 : First argument would be queue name e.g. "revolve-staging-expr3ss"
 * Argument 02 : Second argument would be Y/N depending on if we have to run producer to push data to SQS.
 * Argument 03 : Third argument would be Y/N depending on if we have to run consumer to pull data from queue and display.
 * 
 */
public class SQSSendClient {
	public static void main(String[] args) {
		SQSSendClient client = new SQSSendClient();
		if ("Y".equals(args[1])) {
			client.sendTestMsg(args[0]);
		}

		if ("Y".equals(args[2])) {
			client.receiveTestMsg(args[0]);
		}
	}

	private void receiveTestMsg(String queueName) {

		AmazonSQS sqs = AmazonSQSClientBuilder.defaultClient();
		SQSConnectionFactory connectionFactory = new SQSConnectionFactory(new ProviderConfiguration(), sqs);
		Session session = null;
		SQSConnection connection = null;

		try {

			connection = connectionFactory.createConnection();
			AmazonSQSMessagingClientWrapper client = connection.getWrappedAmazonSQSClient();

			// Create the nontransacted session with AUTO_ACKNOWLEDGE mode
			session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);

			MessageConsumer consumer = session.createConsumer(session.createQueue(queueName));

			connection.start();

			Message message = null;
			// 1000 is time of waiting needs to be long enough otherwise it misses messages
			while ((message = consumer.receive(1000)) != null) {
				TextMessage txtMessage = (TextMessage) message;
				String content = txtMessage.getText();

				System.out.println("content = [" + content + "]");
			}

		} catch (JMSException e) {
			System.out.println("JMSException : [" + e.getMessage() + "]");
			e.printStackTrace();
		} finally {
			try {
				if (session != null)
					session.close();
				if (connection != null)
					connection.close();
			} catch (JMSException e) {
				System.out.println("JMSException : [" + e.getMessage() + "]");
				e.printStackTrace();
			}
		}

	}

	private void sendTestMsg(String queueName) {

		System.out.println("Queue name : [" + queueName + "]");

		final AmazonSQS sqs = AmazonSQSClientBuilder.defaultClient();
		final String queueUrl = sqs.getQueueUrl(queueName).getQueueUrl();

		int delay = 0; // initial delay

		System.out.println("Starting now.... enter any key to continue.");
		Scanner keyboard = new Scanner(System.in);
		keyboard.nextLine();
		{
			StringBuilder message = new StringBuilder("This is test Message-01\n").append("Line-01 : Another line\n");
			SendMessageRequest sendMessageRequest = new SendMessageRequest().withQueueUrl(queueUrl);
			sendMessageRequest.setDelaySeconds(delay);
			sendMessageRequest.setMessageBody(message.toString());
			sqs.sendMessage(sendMessageRequest);
			delay += 10; // next message delayed for 10 seconds more
		}

		System.out.println("First Message Sent !!... enter any key to continue.");
		keyboard.nextLine();

		{
			StringBuilder message = new StringBuilder("This is test Message-02\n").append("Line-01 : Another line\n");
			SendMessageRequest sendMessageRequest = new SendMessageRequest().withQueueUrl(queueUrl);
			sendMessageRequest.setDelaySeconds(delay);
			sendMessageRequest.setMessageBody(message.toString());
			sqs.sendMessage(sendMessageRequest);
		}

		System.out.println("Second Message Sent !!");

	}
}
