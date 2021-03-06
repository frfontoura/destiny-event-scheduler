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

import com.destinyEventScheduler.enums.Rate;
import com.fasterxml.jackson.annotation.JsonGetter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonProperty.Access;
import com.fasterxml.jackson.annotation.JsonSetter;

@Entity
@Table(name = "evaluation", uniqueConstraints = @UniqueConstraint(columnNames = "ID", name = "PK_EVALUATION"))
@SequenceGenerator(name = "EVALUATION_SEQUENCE", sequenceName = "EVALUATION_SEQUENCE", allocationSize = 1, initialValue = 0)
public class Evaluation {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "EVALUATION_SEQUENCE")
	private Long id;

	@JsonProperty(access = Access.WRITE_ONLY)
	@ManyToOne(optional = false)
	@JoinColumn(name = "game_id", insertable = true, updatable = true)
	private Game game;

	@JsonIgnore
	@ManyToOne(optional = false)
	@JoinColumn(name = "member_a_id", insertable = true, updatable = true, foreignKey=@ForeignKey(name="FK_EVALUATION_MEMBER_A_ID"))
	private Member memberA;

	@JsonIgnoreProperties({"platform", "likes", "dislikes","gamesCreated", "gamesPlayed"})
	@ManyToOne(optional = false)
	@JoinColumn(name = "member_b_id", insertable = true, updatable = true, foreignKey=@ForeignKey(name="FK_EVALUATION_MEMBER_B_ID"))
	private Member memberB;

	@Column(name = "rate", nullable = false)
	private Rate rate;

	@JsonGetter("rate")
	public Integer getStatusJson(){
		return rate.getValue();
	}
	
	@JsonSetter("rate")
	public void setStatusJson(Integer value){
		this.rate = Rate.parse(value);
	}
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Game getGame() {
		return game;
	}

	public void setGame(Game game) {
		this.game = game;
	}

	public Member getMemberA() {
		return memberA;
	}

	public void setMemberA(Member memberA) {
		this.memberA = memberA;
	}

	public Member getMemberB() {
		return memberB;
	}

	public void setMemberB(Member memberB) {
		this.memberB = memberB;
	}
	
	public Rate getRate() {
		return rate;
	}

	public void setRate(Rate rate) {
		this.rate = rate;
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
		Evaluation other = (Evaluation) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

}
