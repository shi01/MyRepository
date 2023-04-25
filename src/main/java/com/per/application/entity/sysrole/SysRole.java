package com.per.application.entity.sysrole;

/**
 * @author MYL
 * @date 2022-04-15 15:40:03
 */ 
public class SysRole{

	private Long id;//主键
	private String roleName;//角色名
	private String roleValue;//角色值

	public SysRole(){
		super();
	}
	public void setId(Long id){
		this.id=id;
	}
	public Long getId(){
		return id;
	}
	public void setRoleName(String roleName){
		this.roleName=roleName;
	}
	public String getRoleName(){
		return roleName;
	}
	public void setRoleValue(String roleValue){
		this.roleValue=roleValue;
	}
	public String getRoleValue(){
		return roleValue;
	}
	@Override
	public String toString(){
		return "SysRole[id="+id+",roleName="+roleName+",roleValue="+roleValue+"]";
	}
}

