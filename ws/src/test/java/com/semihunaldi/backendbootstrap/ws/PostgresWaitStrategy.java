package com.semihunaldi.backendbootstrap.ws;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.rnorth.ducttape.TimeoutException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testcontainers.containers.ContainerLaunchException;
import org.testcontainers.containers.wait.strategy.AbstractWaitStrategy;

import java.sql.Connection;
import java.sql.DriverManager;
import java.util.concurrent.TimeUnit;

import static org.rnorth.ducttape.unreliables.Unreliables.retryUntilSuccess;

/**
 * Created by semihunaldi on 30.11.2018
 */

@EqualsAndHashCode(callSuper = false)
@Data
public class PostgresWaitStrategy extends AbstractWaitStrategy {

	private static final Logger log = LoggerFactory.getLogger(PostgresWaitStrategy.class);

	private String connectionUrl;
	private String userName;
	private String password;

	public PostgresWaitStrategy(String connectionUrl, String userName, String password) {
		this.connectionUrl = connectionUrl;
		this.userName = userName;
		this.password = password;
	}

	@Override
	protected void waitUntilReady() {
		try{
			retryUntilSuccess((int) startupTimeout.getSeconds(), TimeUnit.SECONDS, () -> {
				getRateLimiter().doWhenReady(() -> {
					try{
						Class.forName("org.postgresql.Driver");
						Connection connection = DriverManager.getConnection(connectionUrl, userName, password);
						connection.close();
					} catch(Exception e){
						log.debug("waiting for postgres");
						throw new RuntimeException(e);
					}
				});
				return true;
			});
		} catch(TimeoutException e){
			throw new ContainerLaunchException("Timed out waiting for postgres to be accessible : " + connectionUrl);
		}
	}
}
