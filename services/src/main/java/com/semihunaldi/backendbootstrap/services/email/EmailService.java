package com.semihunaldi.backendbootstrap.services.email;

import com.semihunaldi.backendbootstrap.services.BaseService;
import org.springframework.mail.SimpleMailMessage;

import javax.mail.MessagingException;
import java.io.File;

/**
 * Created by semihunaldi on 2.12.2018
 */
public interface EmailService extends BaseService {

	void sendSimpleMessage(SimpleMailMessage message);
	void sendSimpleMessage(String to, String subject, String text);
	void sendMessageWithAttachment(String to, String subject, String text, String attachmentFileName, File file) throws MessagingException;
}
