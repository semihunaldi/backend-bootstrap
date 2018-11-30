package com.semihunaldi.backendbootstrap.ws;

import com.mongodb.MongoClient;
import com.mongodb.MongoClientOptions;
import com.mongodb.MongoCredential;
import com.mongodb.ServerAddress;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.junit.Test;
import org.rnorth.ducttape.TimeoutException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testcontainers.containers.ContainerLaunchException;
import org.testcontainers.containers.wait.strategy.AbstractWaitStrategy;

import java.util.concurrent.TimeUnit;

import static org.rnorth.ducttape.unreliables.Unreliables.retryUntilSuccess;

/**
 * Created by semihunaldi on 30.11.2018
 */

@EqualsAndHashCode(callSuper = false)
@Data
public class MongoWaitStrategy extends AbstractWaitStrategy {

	private static final Logger log = LoggerFactory.getLogger(MongoWaitStrategy.class);

	private String host;
	private int port;
	private String database;
	private String userName;
	private String password;

	public MongoWaitStrategy(String host, int port, String database, String userName, String password) {
		this.host = host;
		this.port = port;
		this.database = database;
		this.userName = userName;
		this.password = password;
	}

	@Override
	protected void waitUntilReady() {
		ServerAddress serverAddress = new ServerAddress(host, port);
		MongoCredential credential = MongoCredential.createCredential(userName, database, password.toCharArray());
		MongoClientOptions mongoClientOptions = MongoClientOptions.builder().legacyDefaults().build();
		try{
			retryUntilSuccess((int) startupTimeout.getSeconds(), TimeUnit.SECONDS, () -> {
				getRateLimiter().doWhenReady(() -> {
					try{
						MongoClient mongoClient = new MongoClient(serverAddress, credential, mongoClientOptions);
						mongoClient.listDatabases();
						mongoClient.close();
					} catch(Exception e){
						throw new RuntimeException(e);
					}
				});
				return true;
			});
		} catch(TimeoutException e){
			throw new ContainerLaunchException("Timed out waiting for mongo to be accessible : " + serverAddress.getHost() + ":" + serverAddress.getPort());
		}
	}

	@Test
	public void testMongo() {

	}
}
