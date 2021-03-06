package com.yinzhiwu.shiro.entity;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ForeignKey;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
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
	private String parentIds;
	
	private Boolean available = Boolean.TRUE;
	
	@OneToMany(mappedBy="parent")
	private List<Organization> childs = new ArrayList<>();

	public String getName() {
		return name;
	}

	public List<Organization> getChilds() {
		return childs;
	}
	
	/**
	 * 
	 * @return 所有的子孙节点
	 */
	public List<Organization> getAllChilds(){
		List<Organization> allChilds = new ArrayList<>();
		if(childs != null || childs.size()>0){
			allChilds.addAll(childs);
			for (Organization org : childs) {
				allChilds.addAll(org.getAllChilds());
			}
		}
		return allChilds;
	}

	public void setChilds(List<Organization> childs) {
		this.childs = childs;
	}

	public Organization getParent() {
		return parent;
	}

	public String getParentIds() {
		return parentIds;
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

	public void setParentIds(String praent_ids) {
		this.parentIds = praent_ids;
	}

	public void setAvailable(Boolean available) {
		this.available = available;
	}

	public String makeSelfAsParentIds() {
		return   getParentIds() + getId() + "/";
	}
	
	
}
