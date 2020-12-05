package io.github.darryring.learn.extend;

/**
 * 彈窗消息
 */
public class PopupMessage extends Message {

	/**
	 * 业务ID，用户弹窗回调
	 */
	private String bizId;

	/**
	 * 是否立即通知, true: 立即通知 false: 按计划通知
	 */
	private Boolean immediatelyNotice;

	/**
	 * APP端计划通知时间，cron 表达式，如：0/30 * * * *
	 */
	private String schedule = "0/30 * * * *";

	/**
	 * 通知开始时间，毫秒
	 * 默认当前时间
	 */
	private Long startTime = System.currentTimeMillis();

	/**
	 * 通知结束时间，毫秒
	 * 默认 当前时间 + 7天
	 */
	private Long endTime = (System.currentTimeMillis() + 7 * 24 * 3600 * 1000);

	/**
	 * 通知是否有详情：
	 * (缺省)0-无详情 1-有详情
	 */
	private Integer actionCount = 1;

	/**
	 * 是否通过应用的公共号通道发送一条提醒消息
	 * 允许发送公告号消息: true
	 * 禁止发送公告号消息: false
	 */
	private Boolean pubAccMsgEnable = true;

	public String getBizId() {
		return bizId;
	}

	public void setBizId(String bizId) {
		this.bizId = bizId;
	}

	public Boolean getImmediatelyNotice() {
		return immediatelyNotice;
	}

	public void setImmediatelyNotice(Boolean immediatelyNotice) {
		this.immediatelyNotice = immediatelyNotice;
	}

	public String getSchedule() {
		return schedule;
	}

	public void setSchedule(String schedule) {
		this.schedule = schedule;
	}

	public Long getStartTime() {
		return startTime;
	}

	public void setStartTime(Long startTime) {
		this.startTime = startTime;
	}

	public Long getEndTime() {
		return endTime;
	}

	public void setEndTime(Long endTime) {
		this.endTime = endTime;
	}

	public Integer getActionCount() {
		return actionCount;
	}

	public void setActionCount(Integer actionCount) {
		this.actionCount = actionCount;
	}

	public Boolean getPubAccMsgEnable() {
		return pubAccMsgEnable;
	}

	public void setPubAccMsgEnable(Boolean pubAccMsgEnable) {
		this.pubAccMsgEnable = pubAccMsgEnable;
	}
}
