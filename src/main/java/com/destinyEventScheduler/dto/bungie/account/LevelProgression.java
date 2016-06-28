package com.destinyEventScheduler.dto.bungie.account;

public class LevelProgression {
	
	private String dailyProgress;
	private String currentProgress;
	private String level;
	private String progressToNextLevel;
	private String weeklyProgress;
	private String progressionHash;
	private String step;
	private String nextLevelAt;

	public String getDailyProgress() {
		return dailyProgress;
	}

	public void setDailyProgress(String dailyProgress) {
		this.dailyProgress = dailyProgress;
	}

	public String getCurrentProgress() {
		return currentProgress;
	}

	public void setCurrentProgress(String currentProgress) {
		this.currentProgress = currentProgress;
	}

	public String getLevel() {
		return level;
	}

	public void setLevel(String level) {
		this.level = level;
	}

	public String getProgressToNextLevel() {
		return progressToNextLevel;
	}

	public void setProgressToNextLevel(String progressToNextLevel) {
		this.progressToNextLevel = progressToNextLevel;
	}

	public String getWeeklyProgress() {
		return weeklyProgress;
	}

	public void setWeeklyProgress(String weeklyProgress) {
		this.weeklyProgress = weeklyProgress;
	}

	public String getProgressionHash() {
		return progressionHash;
	}

	public void setProgressionHash(String progressionHash) {
		this.progressionHash = progressionHash;
	}

	public String getStep() {
		return step;
	}

	public void setStep(String step) {
		this.step = step;
	}

	public String getNextLevelAt() {
		return nextLevelAt;
	}

	public void setNextLevelAt(String nextLevelAt) {
		this.nextLevelAt = nextLevelAt;
	}

}