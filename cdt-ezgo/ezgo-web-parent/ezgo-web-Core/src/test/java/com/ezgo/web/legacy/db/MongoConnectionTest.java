package com.ezgo.web.legacy.db;

import static org.junit.Assert.*;

import java.util.Arrays;

import org.junit.Test;

import com.ezgo.web.db.EzgoCollections;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.MongoIterable;

public class MongoConnectionTest {

	@Test
	public void testGetConnectionClient() {
		MongoDatabase database = EzgoMongoConnection.getDevDatabase();

		MongoIterable<String> allCollections = database.listCollectionNames();
		for (String collection : allCollections) {
			assertTrue(Arrays.stream(EzgoCollections.values()).anyMatch((t) -> t.getCollection().equals(collection)));
		}
	}

}
