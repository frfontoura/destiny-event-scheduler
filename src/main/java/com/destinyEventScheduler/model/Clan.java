package com.destinyEventScheduler.model;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

@Entity
@Table(name = "clan", uniqueConstraints = @UniqueConstraint(columnNames = "group_id", name = "PK_CLAN"))
@JsonIdentityInfo(generator=ObjectIdGenerators.PropertyGenerator.class, property="groupId")
public class Clan {

	@Id
	@Column(name = "group_id", nullable = false)
	private Long groupId;
	
	@Column(name = "name", nullable = false)
	private String name;
	
	@Column(name = "icon", nullable = false)
	private String icon;
	
	@Column(name = "background", nullable = false)
	private String background;
	
	@Column(name = "description", nullable = false)
	private String description;
	
	@JsonIgnore
	@OneToMany(mappedBy = "clan")
	private List<Member> members;

	public Clan(){
		
	}
	
	public Clan(Long groupId) {
		this.groupId = groupId;
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

	public Long getGroupId() {
		return groupId;
	}

	public void setGroupId(Long groupId) {
		this.groupId = groupId;
	}

	public void setMembers(List<Member> members) {
		this.members = members;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((groupId == null) ? 0 : groupId.hashCode());
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
		if (groupId == null) {
			if (other.groupId != null)
				return false;
		} else if (!groupId.equals(other.groupId))
			return false;
		return true;
	}

}
