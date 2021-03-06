package com.destinyEventScheduler.model;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

@Entity
@Table(name = "event_type", uniqueConstraints = @UniqueConstraint(columnNames = "ID", name = "PK_EVENT_TYPE"))
@SequenceGenerator(name = "EVENT_TYPE_SEQUENCE", sequenceName = "EVENT_TYPE_SEQUENCE", allocationSize = 1, initialValue = 0)
public class EventType {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "EVENT_TYPE_SEQUENCE")
	private Long id;

	@JsonIgnore
	@Column(name = "name", nullable = false)
	private String name;
	
	@JsonProperty("en")
	@Column(name = "name_en", nullable = false)
	private String nameEn;
	
	@JsonProperty("pt")
	@Column(name = "name_pt", nullable = false)
	private String namePt;
	
	@JsonProperty("es")
	@Column(name = "name_es", nullable = false)
	private String nameEs;

	@Column(name = "icon", nullable = false)
	private String icon;
	
	@OneToMany(mappedBy = "eventType", cascade = CascadeType.ALL, fetch = FetchType.LAZY, orphanRemoval = true)
	private List<Event> events;

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
	
	public List<Event> getEvents() {
		return events;
	}

	public void setEvents(List<Event> events) {
		this.events = events;
	}

	public String getNamePt() {
		return namePt;
	}

	public void setNamePt(String namePt) {
		this.namePt = namePt;
	}

	public String getNameEs() {
		return nameEs;
	}

	public void setNameEs(String nameEs) {
		this.nameEs = nameEs;
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
		EventType other = (EventType) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

	public String getNameEn() {
		return nameEn;
	}

	public void setNameEn(String nameEn) {
		this.nameEn = nameEn;
	}

}
