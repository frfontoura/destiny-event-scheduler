package com.destinyEventScheduler.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ForeignKey;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@Entity
@Table(name = "event", uniqueConstraints = @UniqueConstraint(columnNames = "ID", name = "PK_EVENT"))
@SequenceGenerator(name = "EVENT_SEQUENCE", sequenceName = "EVENT_SEQUENCE", allocationSize = 1, initialValue = 0)
public class Event {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "EVENT_SEQUENCE")
	private Long id;

	@JsonIgnore
	@Column(name = "name", nullable = false)
	private String name;
	
	@JsonProperty("en")
	@Column(name = "name_en")
	private String nameEn;
	
	@JsonProperty("pt")
	@Column(name = "name_pt", nullable = false)
	private String namePt;
	
	@JsonProperty("es")
	@Column(name = "name_es", nullable = false)
	private String nameEs;

	@Column(name = "icon", nullable = false)
	private String icon;

	@Column(name = "min_light", nullable = false)
	@Min(value = 0)
	private Integer minLight;

	@Column(name = "max_guardians", nullable = false)
	@Min(value = 0)
	@Max(value = 6)
	private Integer maxGuardians;

	@JsonIgnoreProperties(value = {"events"})
	@ManyToOne(optional = false)
	@JoinColumn(name = "event_type_id", nullable = false, updatable = false, foreignKey=@ForeignKey(name="FK_EVENT_EVENT_TYPE_ID"))
	private EventType eventType;
	
	public Event(){
		
	}
	
	public Event(long id) {
		this.id = id;
	}

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
	
	public String getNameEn() {
		return nameEn;
	}

	public void setNameEn(String nameEn) {
		this.nameEn = nameEn;
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
