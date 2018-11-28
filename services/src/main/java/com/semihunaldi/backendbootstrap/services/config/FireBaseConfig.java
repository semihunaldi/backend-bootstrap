package com.semihunaldi.backendbootstrap.services.config;

import com.google.auth.oauth2.GoogleCredentials;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.context.event.EventListener;

import java.io.FileInputStream;

/**
 * Created by semihunaldi on 23.11.2018
 */

@Configuration
@Profile("firebase")
public class FireBaseConfig {

	protected final Logger logger = LoggerFactory.getLogger(this.getClass());

	@Autowired
	private ApplicationContext context;

	@EventListener(ApplicationReadyEvent.class)
	public void initializeFirebaseApp() {
		try{
			FileInputStream serviceAccount = new FileInputStream("path/to/serviceAccountKey.json");
			FirebaseOptions options = new FirebaseOptions.Builder()
					.setCredentials(GoogleCredentials.fromStream(serviceAccount))
					.build();
			FirebaseApp.initializeApp(options);
		} catch(Exception e){
			logger.error("Firebase initialization error", e);
			exitApplication();
		}
	}

	private void exitApplication() {
		int exitCode = SpringApplication.exit(context, () -> -99);
		System.exit(exitCode);
	}
}
