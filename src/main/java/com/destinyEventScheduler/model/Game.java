package com.destinyEventScheduler.model;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

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
import javax.persistence.OrderBy;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.Min;

import com.destinyEventScheduler.enums.Status;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonGetter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonProperty.Access;
import com.fasterxml.jackson.annotation.JsonSetter;
import com.google.common.collect.Ordering;

@Entity
@Table(name = "game", uniqueConstraints = @UniqueConstraint(columnNames = "ID", name = "PK_GAME"))
@SequenceGenerator(name = "GAME_SEQUENCE", sequenceName = "GAME_SEQUENCE", allocationSize = 1, initialValue = 0)
public class Game {
	
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "GAME_SEQUENCE")
	private Long id;

	@JsonIgnoreProperties({"clan", "icon", "platform", "likes", "dislikes", "gamesCreated", "gamesPlayed", "xp", "favoriteEvent"})
	@ManyToOne(optional = false)
	@JoinColumn(name = "membership", nullable = false, foreignKey=@ForeignKey(name="FK_GAME_MEMBER_ID"))
	private Member creator;

	@ManyToOne(optional = false)
	@JoinColumn(name = "event_id", nullable = false, foreignKey=@ForeignKey(name="FK_GAME_EVENT_ID"))
	private Event event;
	
	@JsonProperty(access = Access.WRITE_ONLY)
	@JsonFormat(pattern="yyyy-MM-dd'T'HH:mm:ss")
	@Column(name = "time", nullable = false)
	private LocalDateTime time;

	@Column(name = "light", nullable = false)
	@Min(value = 0)
	private int light;
	
	@Enumerated(EnumType.STRING)
	@Column(name = "status", nullable = false)
	private Status status;
	
	@Column(name = "comment", length = 60)
	private String comment;
	
	@JsonIgnore
	@OrderBy("id")
	@OneToMany(mappedBy = "game", cascade = CascadeType.ALL, fetch = FetchType.LAZY, orphanRemoval = true)
	private List<Entry> entries;
	
	@JsonIgnore
	@OrderBy("id")
	@OneToMany(mappedBy = "game", cascade = CascadeType.ALL, fetch = FetchType.LAZY, orphanRemoval = true)
	private List<Evaluation> evaluations;
	
	@JsonProperty(value = "joined", access = Access.READ_ONLY)
	@Transient
	private boolean memberJoined;
	
	@JsonProperty(value = "evaluated", access = Access.READ_ONLY)
	@Transient
	private boolean evaluated;
	
	@JsonProperty(access = Access.READ_ONLY)
	@Transient
	private LocalDateTime timeJson;

	public Game(){
		
	}
	
	public Game(Long gameId) {
		this.id = gameId;
	}

	@JsonGetter("status")
	public Integer getStatusJson(){
		return status.getValue();
	}
	
	@JsonSetter("status")
	public void setStatusJson(Integer value){
		this.status = Status.parse(value);
	}
	
	@JsonIgnore
	public boolean hasMemberEntry(Member member){
		for(Entry e: getEntries()){
			if(e.getMember().equals(member)){
				return true;
			}
		}
		return false;
	}
	
	@JsonIgnore
	public Entry getEntryByMember(Member member){
		for(Entry entry: getEntries()){
			if(entry.getMember().equals(member)){
				return entry;
			}
		}
		return null;
	}
	
	@JsonIgnore
	public List<Evaluation> getEvaluationsByMember(Member member){
		Ordering<Evaluation> orderByEntries = Ordering.explicit(getMembershipOrderByEntry()).onResultOf(e -> e.getMemberB().getMembership());
		List<Evaluation> evaluations = getEvaluations().stream().filter(e -> e.getMemberA().equals(member)).collect(Collectors.toList());
		return orderByEntries.immutableSortedCopy(evaluations);
	}
	
	@JsonIgnore
	public List<Long> getMembershipOrderByEntry(){
		List<Long> membershipOrderByEntryTime = getEntries()
				.stream()
				.sorted((e1, e2) -> Long.compare(e1.getId(), e2.getId()))
				.map(e -> e.getMember().getMembership())
				.collect(Collectors.toList());
		return membershipOrderByEntryTime;
	}
	
	@JsonGetter("time")
	public LocalDateTime getTimeJson() {
		return timeJson;
	}

	public void setTimeJson(LocalDateTime timeJson) {
		this.timeJson = timeJson;
	}
	
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

	public boolean isMemberJoined() {
		return memberJoined;
	}

	public void setMemberJoined(boolean memberRegistered) {
		this.memberJoined = memberRegistered;
	}
	
	public List<Evaluation> getEvaluations() {
		if(evaluations == null){
			evaluations = new ArrayList<>();
		}
		return evaluations;
	}

	public void setEvaluations(List<Evaluation> evaluations) {
		this.evaluations = evaluations;
	}
	
	public boolean isEvaluated() {
		return evaluated;
	}

	public void setEvaluated(boolean evaluated) {
		this.evaluated = evaluated;
	}
	
	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
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
