package com.spring.practice.hibernate.model;

import java.util.Date;
import java.util.Set;

public class Document {
	private String documentId;
	private String title;
	private String crateBy;
	private Date createDate;
	Set<Group> groups;
	
	public String getDocumentId() {
		return documentId;
	}
	public void setDocumentId(String documentId) {
		this.documentId = documentId;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getCrateBy() {
		return crateBy;
	}
	public void setCrateBy(String crateBy) {
		this.crateBy = crateBy;
	}
	public Date getCreateDate() {
		return createDate;
	}
	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}
	public Set<Group> getGroups() {
		return groups;
	}
	public void setGroups(Set<Group> groups) {
		this.groups = groups;
	}
}
