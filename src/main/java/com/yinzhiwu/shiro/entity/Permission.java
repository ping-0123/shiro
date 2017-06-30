package com.yinzhiwu.shiro.entity;

import javax.persistence.Entity;
import javax.persistence.ForeignKey;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="sys_permission")
public class Permission extends BaseEntity{
	private static final long serialVersionUID = 7525405111409749914L;
	
	private String name;
	@ManyToOne
	@JoinColumn(foreignKey=@ForeignKey(name="fk_permission_role_id"))
	private Role role;
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Role getRole() {
		return role;
	}
	public void setRole(Role role) {
		this.role = role;
	}
	
	
}
