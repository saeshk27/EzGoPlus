package com.ezgo.web.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ezgo.web.legacy.dao.DBHealthCheckLegacyDao;

@Service
public class HealthCheckService {

	private static final Logger LOGGER = LoggerFactory.getLogger(HealthCheckService.class);

	@Autowired
	private DBHealthCheckLegacyDao dbHealthCheckLegacyDao;

	public void setDbHealthCheckLegacyDao(DBHealthCheckLegacyDao dbHealthCheckLegacyDao) {
		this.dbHealthCheckLegacyDao = dbHealthCheckLegacyDao;
	}

	public boolean checkAppHealth() {
		boolean appHealth = true;
		if (!checkDBHealth()) {
			appHealth = false;
			LOGGER.error("checkAppHealth() | DB health check failed");
		}
		if (!checkOtherServiceHealth()) {
			appHealth = false;
			LOGGER.error("checkAppHealth() | Other service health check failed");
		}
		if (appHealth) {
			LOGGER.info("checkAppHealth() | Application health is good");
		}
		return appHealth;
	}

	private boolean checkDBHealth() {
		return dbHealthCheckLegacyDao.checkHealth();
	}

	private boolean checkOtherServiceHealth() {
		/**
		 * Placeholder to check health of other services that will be introduced
		 * in the future
		 */
		return true;
	}

}
