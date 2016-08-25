package com.destinyEventScheduler.model;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;

@Entity
@Table(name = "log_app", uniqueConstraints = @UniqueConstraint(columnNames = "id", name = "PK_LOG_APP"))
@SequenceGenerator(name="LOG_APP_SEQUENCE", sequenceName="LOG_APP_SEQUENCE", allocationSize=1, initialValue=0)
public class LogApp {

	@Id
	@Column(name = "id", nullable = false)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator="LOG_APP_SEQUENCE")
	private Long id;

	@Column(name = "membership", nullable = true)
	private Long membership;

	@Column(name = "exception", nullable = false)
	private String exception;

	@JsonProperty("class")
	@Column(name = "class", nullable = false)
	private String classException;
	
	@JsonFormat(pattern="yyyy-MM-dd'T'HH:mm:ss")
	@Column(name = "time", nullable = false)
	private LocalDateTime time;
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getMembership() {
		return membership;
	}

	public void setMembership(Long membership) {
		this.membership = membership;
	}

	public String getException() {
		return exception;
	}

	public void setException(String exception) {
		this.exception = exception;
	}

	public LocalDateTime getTime() {
		return time;
	}

	public void setTime(LocalDateTime time) {
		this.time = time;
	}
	
	public String getClassException() {
		return classException;
	}

	public void setClassException(String classException) {
		this.classException = classException;
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
		LogApp other = (LogApp) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

}
