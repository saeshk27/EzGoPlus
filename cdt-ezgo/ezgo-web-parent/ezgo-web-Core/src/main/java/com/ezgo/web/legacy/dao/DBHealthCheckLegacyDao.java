package com.ezgo.web.legacy.dao;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import com.ezgo.web.db.EzgoCollections;
import com.ezgo.web.legacy.db.EzgoMongoConnection;
import com.ezgo.web.pojo.DBHealthCheck;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;

@Repository
public class DBHealthCheckLegacyDao {

	private static final Logger LOGGER = LoggerFactory.getLogger(DBHealthCheckLegacyDao.class);

	public boolean checkHealth() {
		try {
			MongoDatabase database = EzgoMongoConnection.getCodecRegisteredDatabase();
			MongoCollection<DBHealthCheck> healthCheckCollection = database.getCollection(EzgoCollections.HEALTH_CHECK.getCollection(), DBHealthCheck.class);
			DBHealthCheck dbHealthCheck = healthCheckCollection.find().first();
			return dbHealthCheck.isConnectionAvailable();
		} catch (Exception ex) {
			LOGGER.error("checkHealth() | Exception caught during db health check", ex);
			return false;
		}
	}
}
