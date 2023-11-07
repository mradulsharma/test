package com.madiv.sqs;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

import com.amazonaws.services.sqs.AmazonSQS;
import com.amazonaws.services.sqs.AmazonSQSClientBuilder;
import com.amazonaws.services.sqs.model.DeleteMessageRequest;
import com.amazonaws.services.sqs.model.Message;
import com.amazonaws.services.sqs.model.MessageAttributeValue;
import com.amazonaws.services.sqs.model.ReceiveMessageRequest;
import com.amazonaws.services.sqs.model.SendMessageRequest;
import com.amazonaws.services.sqs.model.SetQueueAttributesRequest;

/*
 *
 * We need to run this client from EC2 instance, where IAM role is created for EC2 to communicate to SQS.
 *
 * Argument 01 : First argument would be queue name e.g. "revolve-staging-integration"
 * Argument 02 : Second argument would be Y/N depending on if we have to run producer to push data to SQS.
 * Argument 03 : Third argument would be Y/N depending on if we have to run consumer to pull data from queue and display.
 *
 *
 *
 * This code tries to read upto 10 messages in one pull,
 * but SQS does not guarantee that it will alwasy give you upto 10 messages if more than 1 messages are there in queue.
 * So for this piece of code, probably we need to run it twice to read queue to receive both the messages from queue.
 *
 *
 */
public class SQSSendClient {

    public static void main(String[] args) {

        System.out.println("Arguments : ["+Arrays.toString(args)+"]");

        SQSSendClient client = new SQSSendClient();
        if ("Y".equals(args[1])) {
            client.sendTestMsg(args[0]);
        }

        if ("Y".equals(args[2])) {
            client.receiveTestMsg(args[0]);
        }

        System.out.println("\n\n\nFinished !!!");
    }

    private void receiveTestMsg(String queueName) {


        AmazonSQS sqs = AmazonSQSClientBuilder.defaultClient();
        final String queueUrl = sqs.getQueueUrl(queueName).getQueueUrl();

        System.out.println("queueUrl = ["+queueUrl+"]");
        final SetQueueAttributesRequest setQueueAttributesRequest = new SetQueueAttributesRequest()
                .withQueueUrl(queueUrl)
                .addAttributesEntry("ReceiveMessageWaitTimeSeconds", "10");
        sqs.setQueueAttributes(setQueueAttributesRequest);


        final ReceiveMessageRequest receiveMessageRequest = new ReceiveMessageRequest(queueUrl);
        // Telling SQS to deliver upto 10 messages, but it is not guarantied.
        receiveMessageRequest.setMaxNumberOfMessages(10);
        final List<Message> messages = sqs.receiveMessage(receiveMessageRequest.withMessageAttributeNames("All")).getMessages();


        //Iterate over each message.
        for(final Message message : messages) {
            //All message attributes can be accessed like this.
            Map<String, MessageAttributeValue> messageAttributes = message.getMessageAttributes();
            System.out.println("Msg Attribute all : "+messageAttributes);
            System.out.println("Individual Message Value : "+messageAttributes.get("Key-01").getStringValue());
            System.out.println("Individual Message Value : "+messageAttributes.get("Key-02").getStringValue());


            //Message Body
            System.out.println("Message Body:"+message.getBody());

            //If successful, delete message from queue.
            final String messageReceiptHandle = message.getReceiptHandle();
            final String messageId = message.getMessageId();
            sqs.deleteMessage(new DeleteMessageRequest(queueUrl, messageReceiptHandle));
            System.out.println("Message Deleted having message id = " + messageId);

        }

    } // End : receiveTestMsg

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

            //Set message attribute
            final Map<String, MessageAttributeValue> messageAttributes = new HashMap<>();
            messageAttributes.put("Key-01", new MessageAttributeValue()
                    .withDataType("String")
                    .withStringValue("Value-01a"));
            messageAttributes.put("Key-02", new MessageAttributeValue()
                    .withDataType("String")
                    .withStringValue("Value-02b"));
            sendMessageRequest.setMessageAttributes(messageAttributes);

            //Set message body
            sendMessageRequest.setMessageBody(message.toString());

            //Send Message
            sqs.sendMessage(sendMessageRequest);

            delay += 10; // next message delayed for 10 seconds more
        }

        System.out.println("First Message Sent !!... enter any key to continue.");
        keyboard.nextLine();

        {
            StringBuilder message = new StringBuilder("This is test Message-02\n").append("Line-01 : Another line\n");
            SendMessageRequest sendMessageRequest = new SendMessageRequest().withQueueUrl(queueUrl);
            sendMessageRequest.setDelaySeconds(delay);

            //Set message attribute
            final Map<String, MessageAttributeValue> messageAttributes = new HashMap<>();
            messageAttributes.put("Key-01", new MessageAttributeValue()
                    .withDataType("String")
                    .withStringValue("Value-01c"));
            messageAttributes.put("Key-02", new MessageAttributeValue()
                    .withDataType("String")
                    .withStringValue("Value-02d"));
            sendMessageRequest.setMessageAttributes(messageAttributes);

            //Set message body
            sendMessageRequest.setMessageBody(message.toString());


            //Send Message
            sqs.sendMessage(sendMessageRequest);

        }

        System.out.println("Second Message Sent !!");

    } // End : sendTestMsg
}
