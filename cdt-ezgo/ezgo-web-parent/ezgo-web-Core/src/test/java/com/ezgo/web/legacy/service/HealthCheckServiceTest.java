package com.ezgo.web.legacy.service;

import static org.junit.Assert.*;
//import static org.mockito.Mockito.*;

import org.junit.Before;
import org.junit.Test;

import com.ezgo.web.legacy.dao.DBHealthCheckLegacyDao;
import com.ezgo.web.service.HealthCheckService;

public class HealthCheckServiceTest {

	HealthCheckService healthCheckService;

	DBHealthCheckLegacyDao dbHealthCheckLegacyDao;

	@Before
	public void setup() {
		dbHealthCheckLegacyDao = new DBHealthCheckLegacyDao();
		healthCheckService = new HealthCheckService();
		healthCheckService.setDbHealthCheckLegacyDao(dbHealthCheckLegacyDao);
	}

	@Test
	public void shouldCheckHealth() {
		boolean health = healthCheckService.checkAppHealth();
		assertTrue(health);
	}

}
