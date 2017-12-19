package com.ezgo.web.legacy.db;

import static com.mongodb.client.model.Filters.eq;

import org.bson.Document;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.ezgo.web.db.EzgoCollections;
import com.ezgo.web.pojo.User;
import com.ezgo.web.pojo.group.UserStatus;
import com.ezgo.web.pojo.group.UserType;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.MongoDatabase;

public class DBSlave {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(DBSlave.class);
	
	private MongoDatabase database;
	
	public DBSlave() {
		database = EzgoMongoConnection.getDevDatabase();
	}

	public void accessUserTable() {
		MongoCollection<Document> userCollection = database.getCollection(EzgoCollections.USER.getCollection());
		
		LOGGER.info("Total records in user collection before insertion: {}", userCollection.count());
		Document myDoc = userCollection.find(eq("id", "1")).first();
		if(null == myDoc || myDoc.isEmpty()) {
			userCollection.insertOne(createUserDocument(getNo1User()));
			LOGGER.info("Insertion of number 1 user");
		}
		LOGGER.info("Total records in user collection after insertion: {}", userCollection.count());
		
		try(MongoCursor<Document> cursor = userCollection.find().iterator();) {
			StringBuilder allDocuments = new StringBuilder();
		    while (cursor.hasNext()) {
		    	allDocuments.append("\n");
		    	allDocuments.append(cursor.next().toJson());
		    }
		    LOGGER.info("All User Documents: {}", allDocuments.toString());
		}
	}
	
	public void accessHealthCheckTable() {
		MongoCollection<Document> healthCheckCollection = database.getCollection(EzgoCollections.HEALTH_CHECK.getCollection());
		
		LOGGER.info("Total records in health check collection before insertion: {}", healthCheckCollection.count());
		Document myDoc = healthCheckCollection.find().first();
		if(null == myDoc || myDoc.isEmpty()) {
			healthCheckCollection.insertOne(createHealthCheckDocument());
			LOGGER.info("Insertion of number 1 user");
		}
		LOGGER.info("Total records in health check collection after insertion: {}", healthCheckCollection.count());
		
		try(MongoCursor<Document> cursor = healthCheckCollection.find().iterator();) {
			StringBuilder allDocuments = new StringBuilder();
		    while (cursor.hasNext()) {
		    	allDocuments.append("\n");
		    	allDocuments.append(cursor.next().toJson());
		    }
		    LOGGER.info("All Health Check Documents: {}", allDocuments.toString());
		}
	}
	
	private static Document createHealthCheckDocument() {
		Document doc = new Document();
		doc.put("connectionAvailable", true);
		return doc;
	}

	private static Document createUserDocument(User user) {
		Document doc = new Document();
		doc.put("id", user.getId());
		doc.put("firstName", user.getFirstName());
		doc.put("middleNameInitials", user.getMiddleNameInitials());
		doc.put("lastName", user.getLastName());
		doc.put("email", user.getEmail());
		doc.put("mobile", user.getMobileNumber());
		doc.put("login", user.getLogin());
		doc.put("password", user.getPassword());
		doc.put("status", user.getStatus().name());
		doc.put("type", user.getType().name());
		return doc;
	}

	private User getNo1User() {
		User user = new User();
		user.setId("1");
		user.setFirstName("Satesh Kumar");
		user.setLastName("Hariharan");
		user.setEmail("saeshk27@gmail.com");
		user.setMobileNumber("9944503803");
		user.setLogin("saeshk27");
		user.setPassword("password");
		user.setStatus(UserStatus.ACTIVE);
		user.setType(UserType.SUPER_USER);
		return user;
	}

	@SuppressWarnings("unused")
	private User createUserByBuilder() {
		UserBuilder userBuilder = new UserBuilder()
				.withId("2")
				.withFirstName("Satz")
				.withStatus(UserStatus.ACTIVE)
				.withType(UserType.ADMIN);
		return createUser(userBuilder);
	}
	
	private User createUser(UserBuilder userBuilder) {
		User user = new User();
		user.setId(userBuilder.id);
		user.setFirstName(userBuilder.firstName);
		user.setMiddleNameInitials(userBuilder.middleNameInitials);
		user.setLastName(userBuilder.lastName);
		user.setMobileNumber(userBuilder.mobileNumber);
		user.setEmail(userBuilder.email);
		user.setLogin(userBuilder.login);
		user.setPassword(userBuilder.password);
		user.setStatus(userBuilder.status);
		user.setType(userBuilder.type);
		return user;
	}

	@SuppressWarnings("unused")
	private final static class UserBuilder {
		private String id;
		private String firstName;
		private String lastName;
		private String middleNameInitials;
		private String mobileNumber;
		private String email;
		private String login;
		private String password;
		private UserStatus status;
		private UserType type;

		private UserBuilder withId(String id) {
			this.id = id;
			return this;
		}

		private UserBuilder withFirstName(String firstName) {
			this.firstName = firstName;
			return this;
		}

		private UserBuilder withLastName(String lastName) {
			this.lastName = lastName;
			return this;
		}

		private UserBuilder withMiddleNameInitials(String middleNameInitials) {
			this.middleNameInitials = middleNameInitials;
			return this;
		}

		private UserBuilder withMobileNumber(String mobileNumber) {
			this.mobileNumber = mobileNumber;
			return this;
		}

		private UserBuilder withEmail(String email) {
			this.email = email;
			return this;
		}

		private UserBuilder withLogin(String login) {
			this.login = login;
			return this;
		}

		private UserBuilder withPassword(String password) {
			this.password = password;
			return this;
		}
		
		private UserBuilder withStatus(UserStatus status) {
			this.status = status;
			return this;
		}

		private UserBuilder withType(UserType type) {
			this.type = type;
			return this;
		}
	}
	
	public static void main(String[] args) {
		DBSlave dbSlave = new DBSlave();
		System.out.println("Hello World");
		dbSlave.accessUserTable();
		dbSlave.accessHealthCheckTable();
		System.out.println("End of the program");
	}
}
