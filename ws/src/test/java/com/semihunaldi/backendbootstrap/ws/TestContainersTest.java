package com.semihunaldi.backendbootstrap.ws;

import com.mongodb.MongoClient;
import com.mongodb.MongoClientOptions;
import com.mongodb.MongoCredential;
import com.mongodb.ServerAddress;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import org.apache.commons.lang3.StringUtils;
import org.bson.Document;
import org.junit.Assert;
import org.junit.ClassRule;
import org.junit.Test;
import org.testcontainers.containers.DockerComposeContainer;

import java.io.File;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;

/**
 * Created by semihunaldi on 30.11.2018
 */
public class TestContainersTest {

	private static String dockerComposeFile = "docker-compose-db-testing.yml";

	static {
		URL resource = TestContainersTest.class.getClassLoader().getResource(dockerComposeFile);
		if(resource != null){
			dockerComposeFile = resource.getFile();
		}
	}

	@ClassRule
	public static DockerComposeContainer compose =
			new DockerComposeContainer(
					new File(dockerComposeFile))
					.withExposedService("postgres", 5432)
					.withExposedService("mongo", 27017)
					.waitingFor("mongo", new MongoWaitStrategy("localhost", 27017, "backendbootstrapdb", "backend-bootstrap-user", "password"))
					.waitingFor("postgres", new PostgresWaitStrategy("jdbc:postgresql://localhost:5432/backendbootstrapdb", "backend-bootstrap-user", "password"));

	@Test
	public void testMongo() {
		ServerAddress serverAddress = new ServerAddress("localhost", 27017);
		MongoCredential credential = MongoCredential.createScramSha1Credential("backend-bootstrap-user", "admin", "password".toCharArray());
		MongoClientOptions mongoClientOptions = MongoClientOptions.builder().legacyDefaults().build();
		MongoClient mongoClient = new MongoClient(serverAddress, credential, mongoClientOptions);
		mongoClient.getDatabase("backendbootstrapdb").createCollection("testCollection");
		MongoCollection<Document> collection = mongoClient.getDatabase("backendbootstrapdb").getCollection("testCollection");
		Document document = new Document("name", "test");
		collection.insertOne(document);
		FindIterable<Document> documents = collection.find(document);
		Assert.assertNotNull(documents);
		Assert.assertNotNull(documents.iterator().next());
	}

	@Test
	public void testPostgres() throws Exception {
		Class.forName("org.postgresql.Driver");
		Connection connection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/backendbootstrapdb", "backend-bootstrap-user", "password");
		ResultSet resultSet = connection.prepareStatement("select * from backendbootstrapdb.public.t_user;").executeQuery();
		Assert.assertNotNull(resultSet);
		while(resultSet.next()){
			String string = resultSet.getString(1);
			Assert.assertTrue(StringUtils.isNotBlank(string));
		}
		connection.close();
	}
}
