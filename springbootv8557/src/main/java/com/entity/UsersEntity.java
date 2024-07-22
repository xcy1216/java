package com.entity;

import java.io.Serializable;
import java.lang.reflect.InvocationTargetException;
import java.util.Date;

import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import com.baomidou.mybatisplus.enums.IdType;
import org.apache.commons.beanutils.BeanUtils;

/** 
 * 用户
 */
@TableName("users")
public class UsersEntity<T> implements Serializable {
	private static final long serialVersionUID = 1L;

	public UsersEntity() {

	}

	public UsersEntity(String username,String password,String role,String phonenumber) {
		super();
		this.username=username;
		this.password=password;
		this.role=role;
		this.phonenumber=phonenumber;

	}

	@TableId(type = IdType.AUTO)
	private Long id;
	
	/**
	 * 用户账号
	 */
	private String username;
	
	/**
	 * 密码
	 */
	private String password;
	
	/**
	 * 用户类型
	 */
	private String role;
	
	private Date addtime;



	private String phonenumber;

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public Date getAddtime() {
		return addtime;
	}

	public void setAddtime(Date addtime) {
		this.addtime = addtime;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getPhonenumber() {
		return phonenumber;
	}

	public void setPhonenumber(String phonenumber) {
		this.phonenumber = phonenumber;
	}

}
