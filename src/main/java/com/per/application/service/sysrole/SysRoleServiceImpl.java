package com.per.application.service.sysrole;

import java.util.ArrayList;
import java.util.List;
import java.util.Map.Entry;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.per.application.dao.sysrole.SysRoleMapper;
import com.per.application.entity.sysrole.SysRole;
import com.per.application.entity.sysrolepermission.SysRolePermission;
import com.per.application.service.common.CommonService;
import com.per.application.service.sysrolepermission.SysRolePermissionService;

/**
 * @author MYL
 * @date 2022-04-15 15:40:03
 */
@Service
@Transactional
public class SysRoleServiceImpl implements SysRoleService {

	@Autowired
	private SysRoleMapper mapper;
	@Autowired
	private CommonService commonService;
	@Autowired
	private SysRolePermissionService sysRolePermissionService;

	@Override
	public SysRole findById(Long id) {
		return mapper.findById(id);
	}

	public List<SysRole> findBySearch(SysRole s) {
		return mapper.findBySearch(s);
	}

	public int insert(SysRole s) throws Exception {
		Long count = commonService.count("SELECT count(1) FROM `sys_role` WHERE role_name='"+s.getRoleName()+"' OR role_value='"+s.getRoleValue()+"'");
		if (count > 0) {
			throw new Exception("角色名称或角色值已存在");
		}
		return mapper.insert(s);
	}

	public int updateById(SysRole s) {
		return mapper.updateById(s);
	}

	public void deleteById(Long id) {
		mapper.deleteById(id);
	}

	@Override
	public PageInfo<SysRole> findByPage(SysRole c, Page<Object> page) {
		PageHelper.startPage(page.getPageNum(), page.getPageSize(), page.getOrderBy());
		List<SysRole> lists = mapper.findBySearch(c);
		PageInfo<SysRole> pageInfo = new PageInfo<SysRole>(lists);
		return pageInfo;
	}

	@Override
	public void save(SysRole s, HttpServletRequest request) {
		if (s.getId() == null) {
			mapper.insert(s);
		} else {
			mapper.updateById(s);
			sysRolePermissionService.deleteByRoleId(s.getId());
		}
		// 批量新增权限
		List<SysRolePermission> list = getMapFromRequest(request);
		for (SysRolePermission srp : list) {
			srp.setRoleId(s.getId());
			sysRolePermissionService.insert(srp);
		}
	}

	// 将request中的参数转换成Map
	public static List<SysRolePermission> getMapFromRequest(HttpServletRequest request) {
		List<SysRolePermission> list = new ArrayList<SysRolePermission>();
		SysRolePermission sysRolePermission;
		Set<Entry<String, String[]>> entrySet = request.getParameterMap().entrySet();
		for (Entry<String, String[]> entry : entrySet) {
			String name = entry.getKey();
			if (!name.contains("_menu_")) {
				continue;
			}
			sysRolePermission = new SysRolePermission();
			sysRolePermission.setMenuId(Long.valueOf(name.replace("_menu_", "")));
			sysRolePermission.setPower(request.getParameter(name));
			list.add(sysRolePermission);
		}
		return list;
	}
}
