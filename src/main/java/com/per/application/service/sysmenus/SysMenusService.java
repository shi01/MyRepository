package com.per.application.service.sysmenus;

import java.util.List;

import com.per.application.entity.sysmenus.SysMenus;

/**
 * @author MYL
 * @date 2021-11-22 17:34:10
 */
public interface SysMenusService {

	public SysMenus findById(Long id);

	public List<SysMenus> findBySearch(SysMenus s);

	public List<SysMenus> getMeuns();

	public int insert(SysMenus s);

	public int updateById(SysMenus s);

	public void deleteById(Long id);
}
