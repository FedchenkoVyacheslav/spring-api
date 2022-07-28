package com.example.spring_api.entity;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
public class PostEntity {
	public PostEntity() {
	}

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	private String tabletPhotoUrl;
	private String mobilePhotoUrl;
	private String mobile2xPhotoUrl;
	private String tablet2xPhotoUrl;
	private String desktopPhotoUrl;
	private String desktop2xPhotoUrl;
	private String text;
	private String title;
	private int commentsCount;
	private Date date = new Date(System.currentTimeMillis());
	private int views;
	private Date updatedAt = new Date(System.currentTimeMillis());
	private Date createdAt = new Date(System.currentTimeMillis());
	private Date deletedAt;
	@OneToMany
	private List<TagEntity> tags;

	public String toJson() {
		String res = "";
		for (TagEntity tag : tags) {
			res += tag.toJson() + ",";
		}
		return String.format("{\"id\":%d,\"desktopPhotoUrl\":\"%s\",\"desktop2xPhotoUrl\":\"%s\",\"tabletPhotoUrl\":\"%s\",\"tablet2xPhotoUrl\":\"%s\"," +
						"\"mobilePhotoUrl\":\"%s\",\"mobile2xPhotoUrl\":\"%s\",\"title\":\"%s\",\"text\":\"%s\",\"commentsCount\":%d,\"date\":\"%s\"," +
						"\"views\":%d,\"createdAt\":\"%s\",\"updatedAt\":\"%s\",\"deletedAt\":\"%s\",\"tags\": [%s]}",
				getId(), getDesktopPhotoUrl(), getDesktop2xPhotoUrl(), getTabletPhotoUrl(), getTablet2xPhotoUrl(), getMobilePhotoUrl(),
				getMobile2xPhotoUrl(), getTitle(), getText(), getCommentsCount(), getDate(), getViews(), getCreatedAt(),
				getUpdatedAt(), getDeletedAt(), res.substring(0, res.length() - 1));
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public int getCommentsCount() {
		return commentsCount;
	}

	public void setCommentsCount(int commentsCount) {
		this.commentsCount = commentsCount;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public int getViews() {
		return views;
	}

	public void setViews(int views) {
		this.views = views;
	}

	public List<TagEntity> getTags() {
		return tags;
	}

	public void setTags(List<TagEntity> tags) {
		this.tags = tags;
	}

	public String getTabletPhotoUrl() {
		return tabletPhotoUrl;
	}

	public void setTabletPhotoUrl(String tabletPhotoUrl) {
		this.tabletPhotoUrl = tabletPhotoUrl;
	}

	public String getMobilePhotoUrl() {
		return mobilePhotoUrl;
	}

	public void setMobilePhotoUrl(String mobilePhotoUrl) {
		this.mobilePhotoUrl = mobilePhotoUrl;
	}

	public String getMobile2xPhotoUrl() {
		return mobile2xPhotoUrl;
	}

	public void setMobile2xPhotoUrl(String mobile2xPhotoUrl) {
		this.mobile2xPhotoUrl = mobile2xPhotoUrl;
	}

	public String getTablet2xPhotoUrl() {
		return tablet2xPhotoUrl;
	}

	public void setTablet2xPhotoUrl(String tablet2xPhotoUrl) {
		this.tablet2xPhotoUrl = tablet2xPhotoUrl;
	}

	public String getDesktopPhotoUrl() {
		return desktopPhotoUrl;
	}

	public void setDesktopPhotoUrl(String desktopPhotoUrl) {
		this.desktopPhotoUrl = desktopPhotoUrl;
	}

	public String getDesktop2xPhotoUrl() {
		return desktop2xPhotoUrl;
	}

	public void setDesktop2xPhotoUrl(String desktop2xPhotoUrl) {
		this.desktop2xPhotoUrl = desktop2xPhotoUrl;
	}

	public Date getUpdatedAt() {
		return updatedAt;
	}

	public void setUpdatedAt(Date updatedAt) {
		this.updatedAt = updatedAt;
	}

	public Date getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(Date createdAt) {
		this.createdAt = createdAt;
	}

	public Date getDeletedAt() {
		return deletedAt;
	}

	public void setDeletedAt(Date deletedAt) {
		this.deletedAt = deletedAt;
	}
}