package com.Email.sendingEmail;

import java.io.File;
import java.util.Properties;

import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

public class App {
	public static void main(String[] args) {
		System.out.println("Preparing to send message...");
		String message = "Hello, Dear, this is message for security check";
		String subject = "Code: Confirmation";
		
		String to = "abc@gmail.com";//type another email here you want to send it to
		String from = "xyz@gmail.com";//type your email here

		// sendEmail(message, subject, to, from);

		// sending attachment here..
		sendAttach(message, subject, to, from);

	}

//this is is responsible to send the message with attachment
	private static void sendAttach(String message, String subject, String to, String from) {
		// Variable for gmail
				String host = "smtp.gmail.com";

				// get the system properties
				Properties properties = System.getProperties();
				System.out.println("PROPERTIES " + properties);

				// setting important information to properties object

				// set the host
				properties.put("mail.smtp.host", host);
				properties.put("mail.smtp.port", "465");// google port
				properties.put("mail.smtp.ssl.enable", "true");// secure socket layer
				properties.put("mail.smtp.auth", "true");

				// Step 1: to get the session method

				Session session = Session.getInstance(properties, new Authenticator() {

					@Override
					protected PasswordAuthentication getPasswordAuthentication() {

						return new PasswordAuthentication("xyz@gmail.com", "jcsyxqf");//your email and password here
					}

				});

				session.setDebug(true);

				// Step 2: compose the message[text,multi,media]
				MimeMessage m = new MimeMessage(session);

				try {
					// from email
					m.setFrom(from);
					// adding recipient
					m.addRecipient(Message.RecipientType.TO, new InternetAddress(to));
					// adding suject to message
					m.setSubject(subject);
					
					//attachement...
					
					//file path
					String path="C:\\Users\\91945\\Desktop\\new\\logo.png";
					
					MimeMultipart mimeMultipart = new MimeMultipart();
					//text
					//file
					
					MimeBodyPart textMime = new MimeBodyPart();//this statement for sending text
					
					MimeBodyPart fileMime = new MimeBodyPart();//this statement for sending file
					
					try {
						textMime.setText(message);
						
						File file=new File(path);
						fileMime.attachFile(file);
						
						
						mimeMultipart.addBodyPart(textMime);
						mimeMultipart.addBodyPart(fileMime);
						
					} catch (Exception e) {
						e.printStackTrace();
					}
					
					m.setContent(mimeMultipart);
					
					// adding test to message
					//m.setText(message);

					// send the message
					// Step 3: send to message using Transport class
					Transport.send(m);
					System.out.println("Sent successfully.....");

				} catch (Exception e) {
					e.printStackTrace();
				}

	}

//this message to responsible to send email...
	private static void sendEmail(String message, String subject, String to, String from) {
		// Variable for gmail
		String host = "smtp.gmail.com";

		// get the system properties
		Properties properties = System.getProperties();
		System.out.println("PROPERTIES " + properties);

		// setting important information to properties object

		// set the host
		properties.put("mail.smtp.host", host);
		properties.put("mail.smtp.port", "465");// google port
		properties.put("mail.smtp.ssl.enable", "true");// secure socket layer
		properties.put("mail.smtp.auth", "true");

		// Step 1: to get the session method

		Session session = Session.getInstance(properties, new Authenticator() {

			@Override
			protected PasswordAuthentication getPasswordAuthentication() {

				return new PasswordAuthentication("xyz@gmail.com", "jcsyxqf");//your email and password here
			}

		});

		session.setDebug(true);

		// Step 2: compose the message[text,multi,media]
		MimeMessage m = new MimeMessage(session);

		try {
			// from email
			m.setFrom(from);
			// adding recipient
			m.addRecipient(Message.RecipientType.TO, new InternetAddress(to));
			// adding suject to message
			m.setSubject(subject);
			// adding test to message
			m.setText(message);

			// send the message
			// Step 3: send to message using Transport class
			Transport.send(m);
			System.out.println("Sent successfuly.....");

		} catch (Exception e) {
			e.printStackTrace();
		}

	}
}
