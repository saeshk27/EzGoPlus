package com.ezgo.web.pojo;

import java.io.Serializable;

public abstract class BaseKeyedObject implements Serializable {

	private static final long serialVersionUID = 1L;

	public abstract String getId();

	public abstract void setId(String id);

	@Override
	public boolean equals(Object obj) {
		if (obj == this) {
			return true;
		} else if (obj == null) {
			return false;
		} else if (!(obj instanceof BaseKeyedObject)) {
			return false;
		}

		BaseKeyedObject other = (BaseKeyedObject) obj;
		if (this.getId() == null) {
			if (other.getId() != null) {
				return false;
			}
		} else if (!this.getId().equals(other.getId())) {
			return false;
		}
		return true;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + (getId() == null ? super.hashCode() : getId().hashCode());
		return result;
	}
	
	@Override
	public String toString() {
		return "BaseKeyedObject [id=" + getId() + "]";
	}

}