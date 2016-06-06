package com.destinyEventScheduler.dto;

public class EventDTO {

	private Long id;
	private String name;
	private String icon;
	private Integer minLight;
	private Integer maxGuardians;

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

	public String getIcon() {
		return icon;
	}

	public void setIcon(String icon) {
		this.icon = icon;
	}

	public Integer getMinLight() {
		return minLight;
	}

	public void setMinLight(Integer minLight) {
		this.minLight = minLight;
	}

	public Integer getMaxGuardians() {
		return maxGuardians;
	}

	public void setMaxGuardians(Integer maxGuardians) {
		this.maxGuardians = maxGuardians;
	}
}
