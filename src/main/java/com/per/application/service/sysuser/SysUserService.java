package com.per.application.service.sysuser;

import java.util.List;
import java.util.Map;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageInfo;
import com.per.application.entity.sysuser.SysUser;

/**
 * @author MYL
 * @date 2021-11-22 20:30:45
 */
public interface SysUserService {

	public SysUser findById(Long id);

	public List<SysUser> findBySearch(SysUser s);

	public SysUser login(SysUser s);

	public int insert(SysUser s) throws Exception;

	public int updateById(SysUser s);

	public void deleteById(Long id);

	public List<Map<String, Object>> findMoreByUserId(Map<String, Object> map);

	public PageInfo<SysUser> findByPage(SysUser obj, Page<Object> page);
}
