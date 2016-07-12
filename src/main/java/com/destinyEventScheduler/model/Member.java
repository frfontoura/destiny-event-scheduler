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
import com.destinyEventScheduler.jsonview.MemberView;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonView;
import com.fasterxml.jackson.annotation.JsonProperty.Access;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

@Entity
@Table(name = "member", uniqueConstraints = @UniqueConstraint(columnNames = "membership", name = "PK_MEMBER"))
@JsonIdentityInfo(generator=ObjectIdGenerators.PropertyGenerator.class, property="membership")
public class Member {

	@Id
	@Column(name = "membership", nullable = false)
	private Long membership;

	@JsonView({MemberView.MemberBasic.class})
	@Column(name = "name", nullable = false)
	private String name;

	@JsonIgnoreProperties({"members"})
	@ManyToOne(cascade = CascadeType.ALL, optional = false)
	@JoinColumn(name = "clan_id", nullable = false, updatable = false, foreignKey=@ForeignKey(name="FK_MEMBER_CLAN_ID"))
	private Clan clan;

	@JsonView({MemberView.MemberBasic.class})
	@Column(name = "icon", nullable = false)
	private String icon;

	@Enumerated(EnumType.ORDINAL)
	@Column(name = "platform", nullable = false)
	private Platform platform;

	@JsonView({MemberView.MemberBasic.class})
	@Column(name = "likes")
	@Min(value = 0)
	private int likes;

	@JsonView({MemberView.MemberBasic.class})
	@Column(name = "dislikes")
	@Min(value = 0)
	private int dislikes;

	@JsonView({MemberView.MemberBasic.class})
	@Column(name = "games_created")
	@Min(value = 0)
	private int gamesCreated;
	
	@JsonView({MemberView.MemberBasic.class})
	@Column(name = "games_played")
	@Min(value = 0)
	private int gamesPlayed;
	
	@JsonIgnore
	@OneToMany(mappedBy = "memberA", fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
	private List<Evaluation> evaluationsA;
	
	@JsonIgnore
	@OneToMany(mappedBy = "memberB", fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
	private List<Evaluation> evaluationsB;

	public Member(){
		
	}
	
	public Member(Long membership) {
		this.membership = membership;
	}

	@JsonView({MemberView.MemberBasic.class})
	@JsonProperty(value = "membership", access = Access.READ_ONLY)
	public String getMembershipJson(){
		return String.valueOf(membership);
	}
	
	@JsonView({MemberView.MemberBasic.class})
	@JsonProperty(value = "platform", access = Access.READ_ONLY)
	public Integer getPlatformJson(){
		return platform.getValue();
	}
	
	public int getXp(){
		//TODO CALCULO XP
		return 0;
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
		return dislikes;
	}

	public void setDislikes(int dislikes) {
		this.dislikes = dislikes;
	}

	public int getGamesCreated() {
		return gamesCreated;
	}

	public void setGamesCreated(int gamesCreated) {
		this.gamesCreated = gamesCreated;
	}

	public int getGamesPlayed() {
		return gamesPlayed;
	}

	public void setGamesPlayed(int gamesPlayed) {
		this.gamesPlayed = gamesPlayed;
	}
	
	public int getLikes() {
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
