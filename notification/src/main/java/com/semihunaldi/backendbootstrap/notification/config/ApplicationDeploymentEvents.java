package com.semihunaldi.backendbootstrap.notification.config;

import com.google.auth.oauth2.GoogleCredentials;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.event.EventListener;

import java.io.FileInputStream;
import java.io.IOException;

/**
 * Created by semihunaldi on 22.11.2018
 */

@Configuration
public class ApplicationDeploymentEvents {
	protected final Logger logger = LoggerFactory.getLogger(this.getClass());

	@EventListener(ApplicationReadyEvent.class)
	public void applicationReady() {
		initializeFirebaseApp();
	}

	private void initializeFirebaseApp() {
		try{
			FileInputStream serviceAccount = new FileInputStream("path/toToken/serviceAccountKey.json");
			FirebaseOptions options = new FirebaseOptions.Builder()
					.setCredentials(GoogleCredentials.fromStream(serviceAccount))
					.build();
			FirebaseApp.initializeApp(options);
		} catch(IOException e){
			//TODO: handle this error as an application startup failure
			logger.error("Firebase initialization error",e);
		}
	}
}
