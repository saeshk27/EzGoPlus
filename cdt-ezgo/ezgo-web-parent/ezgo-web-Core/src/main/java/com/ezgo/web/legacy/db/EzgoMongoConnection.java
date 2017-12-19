package com.ezgo.web.legacy.db;

import static org.bson.codecs.configuration.CodecRegistries.fromProviders;
import static org.bson.codecs.configuration.CodecRegistries.fromRegistries;

import org.bson.codecs.configuration.CodecRegistry;
import org.bson.codecs.pojo.PojoCodecProvider;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.mongodb.MongoClient;
import com.mongodb.MongoCredential;
import com.mongodb.ServerAddress;
import com.mongodb.client.MongoDatabase;

public class EzgoMongoConnection {

	private static final Logger LOGGER = LoggerFactory.getLogger(EzgoMongoConnection.class);

	private static MongoClient dbClient;

	private static final String DEV_SCHEMA = "ezgo-np-devdb";
	private static final String UAT_SCHEMA = "ezgo-np-uatdb";
	private static final String PROD_SCHEMA = "ezgo-proddb";

	private static final String USER_NAME = "ezgo_app_user";
	private static final String PASSWORD = "ezgo";

	private static final String DEV_SERVER = "localhost";
	private static final int DEV_PORT = 27017;

	private EzgoMongoConnection() {

	}

	public static MongoClient getClient() {
		if (null == dbClient) {
			// dbClient = new MongoClient(getServerAddress(), getCredential(), MongoClientOptions.builder().build());
			dbClient = new MongoClient(DEV_SERVER, DEV_PORT);
			LOGGER.info("New db client created on server: {}, port: {}", DEV_SERVER, DEV_PORT);
		}
		return dbClient;
	}

	/**
	 * TODO: Currently the authentication of db is not working.
	 */
	@SuppressWarnings("unused")
	private static MongoCredential getCredential() {
		return MongoCredential.createPlainCredential(USER_NAME, DEV_SCHEMA, PASSWORD.toCharArray());
	}

	/**
	 * TODO: Currently the authentication of db is not working.
	 */
	@SuppressWarnings("unused")
	private static ServerAddress getServerAddress() {
		return new ServerAddress(DEV_SERVER, DEV_PORT);
	}
	
	public static MongoDatabase getCodecRegisteredDatabase() {
		return getDatabase().withCodecRegistry(getCodecRegistry());
	}
	
	public static MongoDatabase getDatabase() {
		return getDatabase(DEV_SCHEMA);
	}
	
	public static MongoDatabase getDevDatabase() {
		return getDatabase(DEV_SCHEMA);
	}

	public static MongoDatabase getUatDatabase() {
		return getDatabase(UAT_SCHEMA);
	}

	public static MongoDatabase getProdDatabase() {
		return getDatabase(PROD_SCHEMA);
	}

	private static MongoDatabase getDatabase(String schema) {
		return getClient().getDatabase(schema);
	}

	public static CodecRegistry getCodecRegistry() {
		return fromRegistries(MongoClient.getDefaultCodecRegistry(),
				fromProviders(PojoCodecProvider.builder().automatic(true).build()));
	}
}
