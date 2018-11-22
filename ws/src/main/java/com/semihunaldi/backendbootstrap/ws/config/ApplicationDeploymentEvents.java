package com.semihunaldi.backendbootstrap.ws.config;

import org.apache.commons.io.IOUtils;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.event.EventListener;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;

/**
 * Created by semihunaldi on 22.11.2018
 */

@Configuration
public class ApplicationDeploymentEvents {

	@EventListener(ApplicationReadyEvent.class)
	public void applicationReady() {
		printBanner();
	}

	private void printBanner() {
		try{
			ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
			if(classLoader != null){
				InputStream in = ApplicationDeploymentEvents.class.getResourceAsStream("/deploy_banner.txt");
				BufferedReader reader = new BufferedReader(new InputStreamReader(in));
				System.out.println("\n\n" + IOUtils.toString(reader) + "\n\n");
			}
		} catch(Exception e){
			//ignore
		}
	}
}
