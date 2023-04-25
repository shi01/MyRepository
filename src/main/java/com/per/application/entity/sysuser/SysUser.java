package com.per.application.entity.sysuser;

/**
 * @author MYL
 * @date 2021-11-22 20:30:45
 */ 
public class SysUser{

	private Long id;//用户id
	private String name;//用户名
	private String username;//用户名
	private String password;
	private Long roleId;//角色id

	public SysUser(){
		super();
	}
	public void setId(Long id){
		this.id=id;
	}
	public Long getId(){
		return id;
	}
	public void setName(String name){
		this.name=name;
	}
	public String getName(){
		return name;
	}
	public void setUsername(String username){
		this.username=username;
	}
	public String getUsername(){
		return username;
	}
	public void setPassword(String password){
		this.password=password;
	}
	public String getPassword(){
		return password;
	}
	public void setRoleId(Long roleId){
		this.roleId=roleId;
	}
	public Long getRoleId(){
		return roleId;
	}
	@Override
	public String toString(){
		return "SysUser[id="+id+",name="+name+",username="+username+",password="+password+",roleId="+roleId+"]";
	}
}

