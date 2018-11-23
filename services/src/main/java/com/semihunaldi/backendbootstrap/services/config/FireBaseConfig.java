package com.semihunaldi.backendbootstrap.services.config;

import com.google.auth.oauth2.GoogleCredentials;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import javax.annotation.PostConstruct;
import java.io.FileInputStream;
import java.io.IOException;

/**
 * Created by semihunaldi on 23.11.2018
 */

@Configuration
@Profile("firebase")
public class FireBaseConfig {
	protected final Logger logger = LoggerFactory.getLogger(this.getClass());

	@PostConstruct
	public void init() {
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
