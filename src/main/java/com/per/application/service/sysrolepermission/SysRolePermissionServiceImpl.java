package com.per.application.service.sysrolepermission;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.per.application.dao.sysrolepermission.SysRolePermissionMapper;
import com.per.application.entity.sysrolepermission.SysRolePermission;

/**
 * @author MYL
 * @date 2022-04-17 13:14:11
 */
@Service
@Transactional
public class SysRolePermissionServiceImpl implements SysRolePermissionService {

	@Autowired
	private SysRolePermissionMapper mapper;

	@Override
	public SysRolePermission findById(Long id) {
		return mapper.findById(id);
	}

	public List<SysRolePermission> findBySearch(SysRolePermission s) {
		return mapper.findBySearch(s);
	}

	public int insert(SysRolePermission s) {
		return mapper.insert(s);
	}

	public int updateById(SysRolePermission s) {
		return mapper.updateById(s);
	}

	public void deleteById(Long id) {
		mapper.deleteById(id);
	}

	@Override
	public List<SysRolePermission> findByRoleId(Long roleId) {
		return mapper.findByRoleId(roleId);
	}

	@Override
	public void deleteByRoleId(Long roleId) {
		mapper.deleteByRoleId(roleId);
	}
}
