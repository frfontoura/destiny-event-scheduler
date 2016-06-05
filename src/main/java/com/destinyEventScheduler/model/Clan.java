package com.destinyEventScheduler.model;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

@Entity
@Table(name = "clan", uniqueConstraints = @UniqueConstraint(columnNames = "ID", name = "PK_CLAN"))
@SequenceGenerator(name="CLAN_SEQUENCE", sequenceName="CLAN_SEQUENCE", allocationSize=1, initialValue=0)
public class Clan {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator="CLAN_SEQUENCE")
	private Long id;

	@Column(name = "bungie_id", nullable = false)
	private String bungieId;
	
	@Column(name = "name", nullable = false)
	private String name;
	
	@Column(name = "icon", nullable = false)
	private String icon;
	
	@Column(name = "background", nullable = false)
	private String background;
	
	@Column(name = "description", nullable = false)
	private String description;
	
	@OneToMany(mappedBy = "clan")
	private List<Member> members;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getBungieId() {
		return bungieId;
	}

	public void setBungieId(String bungieId) {
		this.bungieId = bungieId;
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

	public String getBackground() {
		return background;
	}

	public void setBackground(String background) {
		this.background = background;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public List<Member> getMembers() {
		return members;
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
		Clan other = (Clan) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

	
	
}
