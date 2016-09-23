package com.destinyEventScheduler.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.ForeignKey;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.Min;

import com.destinyEventScheduler.enums.Platform;
import com.destinyEventScheduler.utils.ResourceBundleUtils;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonProperty.Access;

@Entity
@Table(name = "member", uniqueConstraints = @UniqueConstraint(columnNames = "membership", name = "PK_MEMBER"))
public class Member {

	private static final int LIKE_MODIFIER = 16;
	private static final int CREATOR_MODIFIER = 64;
	private static final int PLAYED_MODIFIER = 48;
	private static final int DISLIKE_MODIFIER = 16;
	private static final int EXP_CONSTANT = 8;

	@Id
	@Column(name = "membership", nullable = false)
	private Long membership;

	@Column(name = "name", nullable = false)
	private String name;

	@JsonIgnore
	@Column(name = "password", nullable = false) 
	private String password;
	
	@JsonIgnore
	@ManyToOne(cascade = CascadeType.ALL, optional = false)
	@JoinColumn(name = "group_id", nullable = false, updatable = false, foreignKey=@ForeignKey(name="FK_MEMBER_GROUP_ID"))
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
	
	@JsonIgnore
	@ManyToOne
	@JoinColumn(name = "event_id", nullable = true, foreignKey=@ForeignKey(name="FK_MEMBER_EVENT_ID"))
	private Event favoriteEvent;

	@JsonIgnore
	@JoinTable(name = "MEMBER_ROLES", joinColumns = {
	        @JoinColumn(name = "MEMBER_ID", referencedColumnName = "MEMBERSHIP", nullable = false)}, inverseJoinColumns = {
			@JoinColumn(name = "ROLE_ID", referencedColumnName = "ID", nullable = false)})
	@ManyToMany
	private List<Role> roles;
	
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
		if(evaluationsA == null){
			evaluationsA = new ArrayList<>();
		}
		return evaluationsA;
	}

	public void setEvaluationsA(List<Evaluation> evaluationsA) {
		this.evaluationsA = evaluationsA;
	}
	
	public List<Evaluation> getEvaluationsB() {
		if(evaluationsB == null){
			evaluationsB = new ArrayList<>();
		}
		return evaluationsB;
	}

	public void setEvaluationsB(List<Evaluation> evaluationsB) {
		this.evaluationsB = evaluationsB;
	}

	public Event getFavoriteEvent() {
		return favoriteEvent;
	}

	public void setFavoriteEvent(Event favoriteEvent) {
		this.favoriteEvent = favoriteEvent;
	}

	public List<Role> getRoles() {
		return roles;
	}

	public void setRoles(List<Role> roles) {
		this.roles = roles;
	}
	
	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	
    public Map<String, String> getMemberTitle(){
        if (favoriteEvent == null){
            return null;
        } 
        Map<String, String> titles = new HashMap<>();
        String memberLevelKey = "member.title.level." + getLevelTitle();
        String eventKey = "member.title.event." + favoriteEvent.getName();
        titles.put("en", ResourceBundleUtils.getStringEn(eventKey, ResourceBundleUtils.getStringEn(memberLevelKey)));
        titles.put("pt", ResourceBundleUtils.getStringPt(eventKey, ResourceBundleUtils.getStringPt(memberLevelKey)));
        titles.put("es", ResourceBundleUtils.getStringEs(eventKey, ResourceBundleUtils.getStringEs(memberLevelKey)));
        return titles;
    }
    
    @JsonIgnore
	public int getMemberLevel(int exp) {
		double lvl = Math.sqrt(exp / EXP_CONSTANT);
		int inteiro = (int) lvl;
		double resto = lvl - inteiro;
		if (resto > 0){
			inteiro++;
		}
		return inteiro;
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
	
	private int getMemberXP() {
		int likesFator = (likes * LIKE_MODIFIER);
		int createdFator = (gamesCreated * CREATOR_MODIFIER);
		int playedFator = (gamesPlayed * PLAYED_MODIFIER);
		int dislikeFator = (dislikes * DISLIKE_MODIFIER);
		int result = (likesFator + createdFator + playedFator) - dislikeFator;
		return result;
	}
	
	private int getLevelTitle() {
		int lvl = getMemberLevel(getMemberXP());
		if (lvl <= 10) {
			return 1;
		} else if (lvl <= 20) {
			return 2;
		} else if (lvl <= 30) {
			return 3;
		} else if (lvl <= 40) {
			return 4;
		} else if (lvl <= 50) {
			return 5;
		} else if (lvl <= 60) {
			return 6;
		} else if (lvl <= 70) {
			return 7;
		} else if (lvl <= 80) {
			return 8;
		} else if (lvl <= 90) {
			return 9;
		} else {
			return 1;
		}
	}

}
