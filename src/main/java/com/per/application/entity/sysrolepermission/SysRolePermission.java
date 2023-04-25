package com.per.application.entity.sysrolepermission;

/**
 * @author MYL
 * @date 2022-04-17 13:14:11
 */ 
public class SysRolePermission{

	private Long id;//主键
	private Long roleId;
	private Long menuId;
	private String power;//功能权限

	public SysRolePermission(){
		super();
	}
	public void setId(Long id){
		this.id=id;
	}
	public Long getId(){
		return id;
	}
	public void setRoleId(Long roleId){
		this.roleId=roleId;
	}
	public Long getRoleId(){
		return roleId;
	}
	public void setMenuId(Long menuId){
		this.menuId=menuId;
	}
	public Long getMenuId(){
		return menuId;
	}
	public void setPower(String power){
		this.power=power;
	}
	public String getPower(){
		return power;
	}
	@Override
	public String toString(){
		return "SysRolePermission[id="+id+",roleId="+roleId+",menuId="+menuId+",power="+power+"]";
	}
}

