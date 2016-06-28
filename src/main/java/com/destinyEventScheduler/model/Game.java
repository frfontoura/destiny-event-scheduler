package com.destinyEventScheduler.model;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.ForeignKey;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.Min;

import com.destinyEventScheduler.enums.Status;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

@Entity
@Table(name = "game", uniqueConstraints = @UniqueConstraint(columnNames = "ID", name = "PK_GAME"))
@SequenceGenerator(name = "GAME_SEQUENCE", sequenceName = "GAME_SEQUENCE", allocationSize = 1, initialValue = 0)
@JsonIdentityInfo(generator=ObjectIdGenerators.PropertyGenerator.class, property="id")
public class Game {
	
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "GAME_SEQUENCE")
	private Long id;

	@JsonIgnoreProperties({"clan"})
	@ManyToOne(optional = false)
	@JoinColumn(name = "member_id", nullable = false, updatable = false, foreignKey=@ForeignKey(name="FK_GAME_MEMBER_ID"))
	private Member creator;

	@ManyToOne(optional = false)
	@JoinColumn(name = "event_id", nullable = false, updatable = false, foreignKey=@ForeignKey(name="FK_GAME_EVENT_ID"))
	private Event event;
	
	@Column(name = "time", nullable = false)
	private LocalDateTime time;

	@Column(name = "light", nullable = false)
	@Min(value = 0)
	private int light;
	
	@Enumerated(EnumType.STRING)
	@Column(name = "status", nullable = false)
	private Status status;
	
	@JsonIgnoreProperties(value = {"game"})
	@OneToMany(mappedBy = "game", cascade = CascadeType.ALL, fetch = FetchType.LAZY, orphanRemoval = true)
	private List<Entry> entries;
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Member getCreator() {
		return creator;
	}

	public void setCreator(Member creator) {
		this.creator = creator;
	}

	public Event getEvent() {
		return event;
	}

	public void setEvent(Event event) {
		this.event = event;
	}

	public int getLight() {
		return light;
	}

	public void setLight(int light) {
		this.light = light;
	}

	public Status getStatus() {
		return status;
	}

	public void setStatus(Status status) {
		this.status = status;
	}

	public LocalDateTime getTime() {
		return time;
	}

	public void setTime(LocalDateTime time) {
		this.time = time;
	}
	
	public List<Entry> getEntries() {
		if(entries == null){
			entries = new ArrayList<>(); 
		}
		return entries;
	}

	public void setEntries(List<Entry> entries) {
		this.entries = entries;
	}
	
	public int getInscriptions() {
		return getEntries().size();
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
		Game other = (Game) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

}
