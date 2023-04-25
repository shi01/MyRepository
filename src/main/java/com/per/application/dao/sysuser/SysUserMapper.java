package com.per.application.dao.sysuser;

import java.util.List;
import java.util.Map;

import com.per.application.entity.sysuser.SysUser;

/**
 * @author MYL
 * @date 2021-11-22 20:30:45
 */
public interface SysUserMapper {

	public SysUser findById(Long id);

	public SysUser findByAccount(SysUser s);

	public List<SysUser> findBySearch(SysUser s);

	public int insert(SysUser s);

	public int updateById(SysUser s);

	public void deleteById(Long id);

	public List<Map<String, Object>> findMoreByUserId(Map<String, Object> map);
}
