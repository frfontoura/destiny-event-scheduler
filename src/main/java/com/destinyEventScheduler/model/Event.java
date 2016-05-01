package com.destinyEventScheduler.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;

@Entity
@Table(name = "event")
@SequenceGenerator(name = "EVENT_SEQUENCE", sequenceName = "EVENT_SEQUENCE", allocationSize = 1, initialValue = 0)
public class Event {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "EVENT_SEQUENCE")
	private Long id;

	@Column(name = "name", nullable = false)
	private String name;

	@Column(name = "icon", nullable = false)
	private String icon;

	@Column(name = "type", nullable = false)
	private String type;

	@Column(name = "min_light", nullable = false)
	@Min(value = 0)
	private Integer minLight;

	@Column(name = "max_guardians", nullable = false)
	@Min(value = 0)
	@Max(value = 6)
	private Integer maxGuardians;

	@ManyToOne(optional = false)
	@JoinColumn(name = "event_type_id", nullable = false, updatable = false)
	private EventType eventType;
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getIcon() {
		return icon;
	}

	public void setIcon(String icon) {
		this.icon = icon;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public Integer getMinLight() {
		return minLight;
	}

	public void setMinLight(Integer minLight) {
		this.minLight = minLight;
	}

	public Integer getMaxGuardians() {
		return maxGuardians;
	}

	public void setMaxGuardians(Integer maxGuardians) {
		this.maxGuardians = maxGuardians;
	}

	public EventType getEventType() {
		return eventType;
	}

	public void setEventType(EventType eventType) {
		this.eventType = eventType;
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
		Event other = (Event) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

}
