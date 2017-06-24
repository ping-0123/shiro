package com.yinzhiwu.shiro.dao;

import java.io.Serializable;
import java.util.List;

import com.yinzhiwu.shiro.entity.PageBean;
import com.yinzhiwu.shiro.exception.DataNotFoundException;


public interface BaseDao<T ,PK extends Serializable> {

		public T get(PK id);
		public PK save(T entity);
		public void update(T entity);
		public void saveOrUpdate(T entity);
		public void delete(T entit);
		public void delete(PK id);
		public List<T> findAll();
		public int findCount();
		public List<T> findByProperty(String propertyName, Object value);
		public int findCountByProperty(String propertyName, Object value);
		public List<T> findByProperties(String[] propertyNames, Object[] values);
		public int findCountByProperties(String[] propertyNames, Object[] values);
		
		/**
		 * 如果T中的某一成员变量的class为@Entity注解, 则查询时忽略该属性， 即查询语句没有表的关联
		 * 
		 * @param entity
		 * @return
		 * @throws DataNotFoundException
		 */
		public List<T> findByExample(T entity);
		PageBean<T> findPageByHql(String hql, int pageNum, int pageSize);
		
		

}
