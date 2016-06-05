package com.destinyEventScheduler.model;

import java.time.LocalDate;

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

@Entity
@Table(name = "entry", uniqueConstraints = @UniqueConstraint(columnNames = "ID", name = "PK_ENTRY"))
@SequenceGenerator(name="ENTRY_SEQUENCE", sequenceName="ENTRY_SEQUENCE", allocationSize=1, initialValue=0)
public class Entry {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator="ENTRY_SEQUENCE")
	private Long id;

	@ManyToOne(optional = false)
	@JoinColumn(name = "member_id", nullable = false, updatable = false, foreignKey=@ForeignKey(name="FK_ENTRY_MEMBER_ID"))
	private Member member;

	@ManyToOne(optional = false)
	@JoinColumn(name = "game_id", nullable = false, updatable = false, foreignKey=@ForeignKey(name="FK_ENTRY_GAME_ID"))
	private Game game;

	@Column(name = "time", nullable = false)
	private LocalDate time;

	public Long getId() {
		return id;
	}
	
	public void setId(Long id) {
		this.id = id;
	}
	
	public Member getMember() {
		return member;
	}

	public void setMember(Member member) {
		this.member = member;
	}

	public Game getGame() {
		return game;
	}

	public void setGame(Game game) {
		this.game = game;
	}

	public LocalDate getTime() {
		return time;
	}

	public void setTime(LocalDate time) {
		this.time = time;
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
		Entry other = (Entry) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

}
