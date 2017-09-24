package com.spring.practice.jpa.entity;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@NamedQueries({
	@NamedQuery(name = "Group.findByUserid", 
			query = "select u.group from User u where u.userId = :userId"),
	@NamedQuery(name = "Group.updateName",
			query = "update Group g set g.groupName = :groupName where g.groupId = :groupId"),
	@NamedQuery(name = "Group.updateDesc",
			query = "update Group g set g.description = :groupDesc where g.groupId = :groupId"),
	@NamedQuery(name = "Group.deleteGroup",
			query = "delete from Group g where g.groupId = :groupId")
})		
@Table(name="ZGROUP")
public class Group {
	@Id
	@Column(name="GROUP_ID")
	private String groupId;
	
	@Column(name="GROUP_NAME")
	private String groupName;
	
	@Column(name="DESCRIPTION")
	private String description;
	
	/**
	 * Never user CascadeType.ALL in ManyToMany relationship.
	 * http://vladmihalcea.com/2015/03/05/a-beginners-guide-to-jpa-and-hibernate-cascade-types/
	 */
	@ManyToMany(cascade= {CascadeType.PERSIST, CascadeType.MERGE}, fetch=FetchType.EAGER)
	@JoinTable(name="Z_DOCUMENT_GROUP",
				joinColumns={@JoinColumn(name="GRP_ID")},
				inverseJoinColumns={@JoinColumn(name="DOC_ID")})
	private Set<Document> documents;
	
	@OneToMany(mappedBy="group", cascade=CascadeType.ALL)
	private Set<User> users;
	
	public String getGroupId() {
		return groupId;
	}
	public void setGroupId(String groupId) {
		this.groupId = groupId;
	}
	public String getGroupName() {
		return groupName;
	}
	public void setGroupName(String groupName) {
		this.groupName = groupName;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public Set<Document> getDocuments() {
		return documents;
	}
	public void setDocuments(Set<Document> documents) {
		this.documents = documents;
	}
	public Set<User> getUsers() {
		return users;
	}
	public void setUsers(Set<User> users) {
		this.users = users;
	}
}