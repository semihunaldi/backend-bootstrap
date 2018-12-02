package com.semihunaldi.backendbootstrap.services.email;

import com.semihunaldi.backendbootstrap.services.BaseServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.io.File;

/**
 * Created by semihunaldi on 2.12.2018
 */

@Component
public class EmailServiceImpl extends BaseServiceImpl implements EmailService {

	@Autowired
	public JavaMailSender emailSender;

	@Override
	public void sendSimpleMessage(SimpleMailMessage message) {
		emailSender.send(message);
	}

	@Override
	public void sendSimpleMessage(String to, String subject, String text) {
		SimpleMailMessage message = new SimpleMailMessage();
		message.setTo(to);
		message.setSubject(subject);
		message.setText(text);
		sendSimpleMessage(message);
	}

	@Override
	public void sendMessageWithAttachment(String to, String subject, String text, String attachmentFileName, File file) throws MessagingException {
		MimeMessage message = emailSender.createMimeMessage();
		MimeMessageHelper helper = new MimeMessageHelper(message, true);
		helper.setTo(to);
		helper.setSubject(subject);
		helper.setText(text);
		helper.addAttachment(attachmentFileName, file);
		emailSender.send(message);
	}
}
