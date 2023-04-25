package com.per.application.dao.sysrole;

import java.util.List;

import com.per.application.entity.sysrole.SysRole;

/**
 * @author MYL
 * @date 2022-04-15 15:40:03
 */ 
public interface SysRoleMapper{

	public SysRole findById(Long id);

	public List<SysRole> findBySearch(SysRole s);

	public int insert(SysRole s);

	public int updateById(SysRole s);

	public void deleteById(Long id);
}

