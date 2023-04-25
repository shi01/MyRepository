package com.per.application.service.sysrole;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageInfo;
import com.per.application.entity.sysrole.SysRole;

/**
 * @author MYL
 * @date 2022-04-15 15:40:03
 */
public interface SysRoleService {

	public SysRole findById(Long id);

	public List<SysRole> findBySearch(SysRole s);

	PageInfo<SysRole> findByPage(SysRole c, Page<Object> page);

	public int insert(SysRole s) throws Exception;

	public int updateById(SysRole s);

	public void deleteById(Long id);

	public void save(SysRole s, HttpServletRequest request);
}
