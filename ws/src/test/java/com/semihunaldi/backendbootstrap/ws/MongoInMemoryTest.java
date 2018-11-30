package com.semihunaldi.backendbootstrap.ws;

import com.mongodb.BasicDBObjectBuilder;
import com.mongodb.DBObject;
import com.mongodb.MongoClient;
import de.flapdoodle.embed.mongo.MongodExecutable;
import de.flapdoodle.embed.mongo.MongodStarter;
import de.flapdoodle.embed.mongo.config.IMongodConfig;
import de.flapdoodle.embed.mongo.config.MongodConfigBuilder;
import de.flapdoodle.embed.mongo.config.Net;
import de.flapdoodle.embed.mongo.distribution.Version;
import de.flapdoodle.embed.process.runtime.Network;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.data.mongodb.core.MongoTemplate;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Created by semihunaldi on 30.11.2018
 */

public class MongoInMemoryTest {

	private static MongodExecutable mongodExecutable;
	private static MongoTemplate mongoTemplate;

	private static final String ip = "localhost";
	private static final int port = 27017;

	@AfterClass
	public static void clean() {
		mongodExecutable.stop();
	}

	@BeforeClass
	public static void init() throws Exception {

		IMongodConfig mongodConfig = new MongodConfigBuilder().version(Version.Main.PRODUCTION)
				.net(new Net(ip, port, Network.localhostIsIPv6()))
				.build();

		MongodStarter starter = MongodStarter.getDefaultInstance();
		mongodExecutable = starter.prepare(mongodConfig);
		mongodExecutable.start();
		mongoTemplate = new MongoTemplate(new MongoClient(ip, port), "test");
	}

	@Test
	public void test() {
		DBObject objectToSave = BasicDBObjectBuilder.start()
				.add("key", "value")
				.get();
		mongoTemplate.save(objectToSave, "collection");
		assertThat(mongoTemplate.findAll(DBObject.class, "collection")).extracting("key").containsOnly("value");
	}
}
