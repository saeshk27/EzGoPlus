package com.ezgo.web.legacy.dao;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class DBHealthCheckLegacyDaoTest {

	// @InjectMocks
	DBHealthCheckLegacyDao dbHealthCheckLegacyDao;

	@Before
	public void setup() {
		dbHealthCheckLegacyDao = new DBHealthCheckLegacyDao();
	}

	@Test
	public void shouldCheckHealth() {
		boolean health = dbHealthCheckLegacyDao.checkHealth();
		assertTrue(health);
	}
}
