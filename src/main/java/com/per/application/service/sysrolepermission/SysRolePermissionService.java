package com.per.application.service.sysrolepermission;

import java.util.List;

import com.per.application.entity.sysrolepermission.SysRolePermission;

/**
 * @author MYL
 * @date 2022-04-17 13:14:11
 */
public interface SysRolePermissionService {

	public SysRolePermission findById(Long id);

	public List<SysRolePermission> findBySearch(SysRolePermission s);

	public List<SysRolePermission> findByRoleId(Long userId);

	public int insert(SysRolePermission s);

	public int updateById(SysRolePermission s);

	public void deleteById(Long id);
	
	public void deleteByRoleId(Long roleId);
}
