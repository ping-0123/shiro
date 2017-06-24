package com.yinzhiwu.shiro.entity;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;

@Entity
@Table(name="shiro_role")
public class Role extends BaseEntity {
	private static final long serialVersionUID = 8453032007727952230L;
	
	private String name;
	@OneToMany(mappedBy="role")
	private List<Permission> pemissions = new ArrayList<>();
	@ManyToMany
	@JoinTable(name="shiro_user_role",
		joinColumns={@JoinColumn(name="role_id")},
		inverseJoinColumns={@JoinColumn(name="user_id")})
	private List<User> users = new ArrayList<>();
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public List<Permission> getPemissions() {
		return pemissions;
	}
	public void setPemissions(List<Permission> pemissions) {
		this.pemissions = pemissions;
	}
	public List<User> getUsers() {
		return users;
	}
	public void setUsers(List<User> users) {
		this.users = users;
	}
	
	  @Transient  
	    public List<String> getPermissionsName(){  
	        List<String> list=new ArrayList<String>();  
	        List<Permission> perlist=getPemissions();  
	        for (Permission per : perlist) {  
	            list.add(per.getName());  
	        }  
	        return list;  
	    }  
}
