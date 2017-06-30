package com.yinzhiwu.shiro.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ForeignKey;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="sys_organization")
public class Organization extends BaseEntity {

	/**
	 * 
	 */
	private static final long serialVersionUID = -6273813604164820923L;
	
	@Column(length=32)
	private String name;
	
	@ManyToOne
	@JoinColumn(foreignKey=@ForeignKey(name="fk_organization_parent_id"))
	private Organization parent;
	
	@Column(name="parent_ids", length=128)
	private String praent_ids;
	
	private Boolean available = Boolean.TRUE;

	public String getName() {
		return name;
	}

	public Organization getParent() {
		return parent;
	}

	public String getPraent_ids() {
		return praent_ids;
	}

	public Boolean getAvailable() {
		return available;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setParent(Organization parent) {
		this.parent = parent;
	}

	public void setPraent_ids(String praent_ids) {
		this.praent_ids = praent_ids;
	}

	public void setAvailable(Boolean available) {
		this.available = available;
	}
	
	
}
