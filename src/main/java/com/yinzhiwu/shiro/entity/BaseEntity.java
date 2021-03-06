package com.yinzhiwu.shiro.entity;


import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonFormat;  
  
/**
 * name rule
 * foreign key:  			 fk_{table name}_{foreign key column name}
 * foreign key column name:  {parent table name}_{referenced column name}
 * unique key: 				 uk_{table name}_{unique key column name}
 */

@MappedSuperclass  
public abstract class BaseEntity implements Serializable{  
  
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	/** 
     * ID 
     */  
	
	@Id  
    @Column(nullable = false)  
	@GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long id;  
    /** 
     * �������� 
     */  
    private Integer createUserId;
    
    
    @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")  
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")  
    @Column(updatable = false)  
    private Date createDate;  
    /** 
     * �޸����� 
     */  
    
    private Integer lastModifiedUserId;
    
    
    @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")  
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone = "GMT+8") 
    @Column(insertable=true,updatable=true)
    protected Date lastModifiedDate;  
    
      
    public BaseEntity() {
	}


	public void init() {
		Date date = new Date();
    	this.createDate = date;
    	this.lastModifiedDate=date;
    	this.createUserId=1;
    	this.lastModifiedUserId=1;
	}


    public Long getId() {  
        return id;  
    }  
  
    public void setId(long id) {  
        this.id = id;  
    }  
  

    public Date getCreateDate() {  
        return createDate;  
    }  
  

    public void setCreateDate(Date createDate) {  
        this.createDate = createDate;  
    }  
  

    public Date getLastModifiedDate() {  
        return lastModifiedDate;  
    }  
  
    public void setLastModifiedDate(Date modifyDate) {  
        this.lastModifiedDate = modifyDate;  
    }  
  
    @Override  
    public int hashCode() {  
        return id == null ? System.identityHashCode(this) : id.hashCode();  
    }  
  
    @Override  
    public boolean equals(Object obj) {  
        if (this == obj) {  
            return true;  
        }  
        if (obj == null) {  
            return false;  
        }  
        if (getClass().getPackage() != obj.getClass().getPackage()) {  
            return false;  
        }  
        final BaseEntity other = (BaseEntity) obj;  
        if (id == null) {  
            if (other.getId() != null) {  
                return false;  
            }  
        } else if (!id.equals(other.getId())) {  
            return false;  
        }  
        return true;  
    }

	public  Integer getCreateUserId() {
		return createUserId;
	}

	public  void setCreateUserId(int createUserId) {
		this.createUserId = createUserId;
	}

	public  Integer getLastModifiedUserId() {
		return lastModifiedUserId;
	}

	public  void setLastModifiedUserId(int moddifiedUserId) {
		this.lastModifiedUserId = moddifiedUserId;
	}


}  