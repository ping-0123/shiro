package com.yinzhiwu.shiro.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ForeignKey;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="sys_resource")
public class Resource extends BaseEntity {

	/**
	 * 
	 */
	private static final long serialVersionUID = 8689302420444286023L;

	@Column(length=32)
	private String name;
	
	@Column(length=32)
	private String type;
	
	@Column(length=32)
	private String url;
	
	@ManyToOne
	@JoinColumn(foreignKey=@ForeignKey(name="fk_resource_parent_id"))
	private Resource parent;
	
	@Column(length=32, name="parent_ids")
	private String parentIds;
	
	@Column(length=128)
	private String permission;
	
	private Boolean available = Boolean.TRUE;

	public String getName() {
		return name;
	}

	public String getType() {
		return type;
	}

	public String getUrl() {
		return url;
	}

	public Resource getParent() {
		return parent;
	}

	public String getParentIds() {
		return parentIds;
	}

	public String getPermission() {
		return permission;
	}

	public Boolean getAvailable() {
		return available;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setType(String type) {
		this.type = type;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public void setParent(Resource parent) {
		this.parent = parent;
	}

	public void setParentIds(String parentIds) {
		this.parentIds = parentIds;
	}

	public void setPermission(String permission) {
		this.permission = permission;
	}

	public void setAvailable(Boolean available) {
		this.available = available;
	}
	
	
}
