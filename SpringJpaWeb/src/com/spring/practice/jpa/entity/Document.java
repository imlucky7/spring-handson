package com.spring.practice.jpa.entity;

import java.sql.Date;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

@Entity
@Table(name="ZDOCUMENT")
public class Document {
	@Id
	@Column(name="DOCUMENT_ID")
	private String documentId;
	
	@Column(name="TITLE")
	private String title;
	
	@Column(name="CREATE_BY")
	private String crateBy;
	
	@Column(name="CREATE_DATE")
	private Date createDate;
	
	/**
	 * Never user CascadeType.ALL in ManyToMany relationship.
	 * http://vladmihalcea.com/2015/03/05/a-beginners-guide-to-jpa-and-hibernate-cascade-types/
	 */
	@ManyToMany(mappedBy="documents", cascade={CascadeType.PERSIST})
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
