package com.destinyEventScheduler.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

@Entity
@Table(name = "notice", uniqueConstraints = @UniqueConstraint(columnNames = "ID", name = "PK_NOTICE"))
public class Notice {

	@Id
	private Long id;

	@Column(name = "url", nullable = false, length = 255)
	private String url;

	@Column(name = "message", nullable = false, length = 255)
	private String message;

	@Column(name = "version_code", nullable = false)
	private Long versionCode;

	@Column(name = "force_update", nullable = false)
	private boolean forceUpdate;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public boolean isForceUpdate() {
		return forceUpdate;
	}

	public void setForceUpdate(boolean forceUpdate) {
		this.forceUpdate = forceUpdate;
	}

	public Long getVersionCode() {
		return versionCode;
	}

	public void setVersionCode(Long versionCode) {
		this.versionCode = versionCode;
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Notice other = (Notice) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

}
