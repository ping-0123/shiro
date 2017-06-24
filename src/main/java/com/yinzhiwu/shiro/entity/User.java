package com.yinzhiwu.shiro.entity;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.hibernate.validator.constraints.NotEmpty;

@Entity
@Table(name="shiro_user")
public class User extends BaseEntity{
	private static final long serialVersionUID = -6757432250580429608L;
	
	@NotEmpty(message="用户名不能为空")
	private String name;
	@NotEmpty(message="密码不能为空")
	private String password;
	@ManyToMany
	@JoinTable(name="shiro_user_role", 
		joinColumns={@JoinColumn(name="user_id")},
		inverseJoinColumns={@JoinColumn(name="role_id")})
	private List<Role> roles = new ArrayList<>();
	
	
	public String getName() {
		return name;
	}
	public void setName(String username) {
		this.name = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public List<Role> getRoles() {
		return roles;
	}
	public void setRoles(List<Role> roles) {
		this.roles = roles;
	}
	
	 @Transient  
    public Set<String> getRolesName(){  
        List<Role> roles=getRoles();  
        Set<String> set=new HashSet<String>();  
        for (Role role : roles) {  
            set.add(role.getName());  
        }  
        return set;  
    }  
	
	
}
