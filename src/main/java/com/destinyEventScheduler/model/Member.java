package com.destinyEventScheduler.model;

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

import com.destinyEventScheduler.enums.Platform;

@Entity
@Table(name = "member", uniqueConstraints = @UniqueConstraint(columnNames = "ID", name = "PK_MEMBER"))
@SequenceGenerator(name = "MEMBER_SEQUENCE", sequenceName = "MEMBER_SEQUENCE", allocationSize = 1, initialValue = 0)
public class Member {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "MEMBER_SEQUENCE")
	private Long id;

	@Column(name = "name", nullable = false)
	private String name;

	@Column(name = "membership", nullable = false)
	private String membership;

	@ManyToOne(optional = false)
	@JoinColumn(name = "clan_id", nullable = false, updatable = false, foreignKey=@ForeignKey(name="FK_MEMBER_CLAN_ID"))
	private Clan clan;

	@Column(name = "icon", nullable = false)
	private String icon;

	@Enumerated(EnumType.ORDINAL)
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
	
	@Column(name = "member_since", nullable = false)
	private String memberSince;
	
	@OneToMany(mappedBy = "memberA", fetch = FetchType.LAZY, cascade =CascadeType.ALL, orphanRemoval = true)
	private List<Evaluation> evaluationsA;
	
	@OneToMany(mappedBy = "memberB", fetch = FetchType.LAZY, cascade =CascadeType.ALL, orphanRemoval = true)
	private List<Evaluation> evaluationsB;
	
	public int getXp(){
		//TODO CALCULO XP
		return 0;
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

	public String getMembership() {
		return membership;
	}

	public void setMembership(String membership) {
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

	public String getMemberSince() {
		return memberSince;
	}

	public void setMemberSince(String memberSince) {
		this.memberSince = memberSince;
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
		Member other = (Member) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

}
