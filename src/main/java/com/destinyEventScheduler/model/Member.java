package com.destinyEventScheduler.model;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.ForeignKey;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.Min;

import com.destinyEventScheduler.enums.Platform;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonProperty.Access;

//TODO RETIRAR Math.random

@Entity
@Table(name = "member", uniqueConstraints = @UniqueConstraint(columnNames = "membership", name = "PK_MEMBER"))
public class Member {

	@Id
	@Column(name = "membership", nullable = false)
	private Long membership;

	@Column(name = "name", nullable = false)
	private String name;

	@JsonIgnore
	@ManyToOne(cascade = CascadeType.ALL, optional = false)
	@JoinColumn(name = "clan_id", nullable = false, updatable = false, foreignKey=@ForeignKey(name="FK_MEMBER_CLAN_ID"))
	private Clan clan;

	@Column(name = "icon", nullable = false)
	private String icon;

	@Enumerated(EnumType.STRING)
	@Column(name = "platform", nullable = false)
	private Platform platform;

	@Column(name = "likes")
	@Min(value = 0)
	private int likes;

	@Column(name = "dislikes")
	@Min(value = 0)
	private int dislikes;

	@Column(name = "games_created")
	@Min(value = 0)
	private int gamesCreated;
	
	@Column(name = "games_played")
	@Min(value = 0)
	private int gamesPlayed;
	
	@JsonIgnore
	@OneToMany(mappedBy = "memberA", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	private List<Evaluation> evaluationsA;
	
	@JsonIgnore
	@OneToMany(mappedBy = "memberB", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	private List<Evaluation> evaluationsB;

	public Member(){
		
	}
	
	public Member(Long membership) {
		this.membership = membership;
	}

	@JsonProperty(value = "membership", access = Access.READ_ONLY)
	public String getMembershipJson(){
		return String.valueOf(membership);
	}
	
	@JsonProperty(value = "platform", access = Access.READ_ONLY)
	public Integer getPlatformJson(){
		return platform.getValue();
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Long getMembership() {
		return membership;
	}

	public void setMembership(Long membership) {
		this.membership = membership;
	}

	public Clan getClan() {
		return clan;
	}

	public void setClan(Clan clan) {
		this.clan = clan;
	}

	public String getIcon() {
		return icon;
	}

	public void setIcon(String icon) {
		this.icon = icon;
	}

	public Platform getPlatform() {
		return platform;
	}

	public void setPlatform(Platform platform) {
		this.platform = platform;
	}

	public int getDislikes() {
		dislikes = (int)(Math.random()*100);
		return dislikes;
	}

	public void setDislikes(int dislikes) {
		this.dislikes = dislikes;
	}

	public int getGamesCreated() {
		gamesCreated = (int)(Math.random()*100);
		return gamesCreated;
	}

	public void setGamesCreated(int gamesCreated) {
		this.gamesCreated = gamesCreated;
	}

	public int getGamesPlayed() {
		gamesPlayed = (int)(Math.random()*100);
		return gamesPlayed;
	}

	public void setGamesPlayed(int gamesPlayed) {
		this.gamesPlayed = gamesPlayed;
	}
	
	public int getLikes() {
		likes = (int)(Math.random()*100);
		return likes;
	}

	public void setLikes(int likes) {
		this.likes = likes;
	}
	
	public List<Evaluation> getEvaluationsA() {
		return evaluationsA;
	}

	public void setEvaluationsA(List<Evaluation> evaluationsA) {
		this.evaluationsA = evaluationsA;
	}
	
	public List<Evaluation> getEvaluationsB() {
		return evaluationsB;
	}

	public void setEvaluationsB(List<Evaluation> evaluationsB) {
		this.evaluationsB = evaluationsB;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((membership == null) ? 0 : membership.hashCode());
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
		Member other = (Member) obj;
		if (membership == null) {
			if (other.membership != null)
				return false;
		} else if (!membership.equals(other.membership))
			return false;
		return true;
	}

}
