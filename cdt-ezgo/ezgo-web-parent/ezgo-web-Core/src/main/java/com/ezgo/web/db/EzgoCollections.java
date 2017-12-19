package com.ezgo.web.db;

public enum EzgoCollections {
	
	DUAL("Dual"),
	HEALTH_CHECK("HealthCheck"),
	USER("User");
	
	private final String ezgoCollection;
	
	private EzgoCollections(String ezgoCollection) {
		this.ezgoCollection = ezgoCollection;
	}
	
	public String getCollection() {
		return this.ezgoCollection;
	}
}
