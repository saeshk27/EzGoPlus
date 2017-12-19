package com.ezgo.web.pojo;

import java.io.Serializable;

public class DBHealthCheck implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private boolean connectionAvailable;

	public boolean isConnectionAvailable() {
		return connectionAvailable;
	}

	public void setConnectionAvailable(boolean connectionAvailable) {
		this.connectionAvailable = connectionAvailable;
	}
}
