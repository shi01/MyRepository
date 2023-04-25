package com.per.application.service.sysuser;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.per.application.dao.sysuser.SysUserMapper;
import com.per.application.entity.sysuser.SysUser;
import com.per.application.service.common.CommonService;

/**
 * @author MYL
 * @date 2021-11-22 20:30:45
 */
@Service
@Transactional
public class SysUserServiceImpl implements SysUserService {

	@Autowired
	private SysUserMapper mapper;
	@Autowired
	private CommonService commonService;

	@Override
	public SysUser findById(Long id) {
		return mapper.findById(id);
	}

	public List<SysUser> findBySearch(SysUser s) {
		return mapper.findBySearch(s);
	}

	public int insert(SysUser s) throws Exception {
		Long count = commonService.count("SELECT count(1) FROM `sys_user` WHERE username='" + s.getUsername() + "'");
		if (count > 0) {
			throw new Exception("用户名已存在！");
		}
		return mapper.insert(s);
	}

	public int updateById(SysUser s) {
		return mapper.updateById(s);
	}

	public void deleteById(Long id) {
		mapper.deleteById(id);
	}

	@Override
	public List<Map<String, Object>> findMoreByUserId(Map<String, Object> map) {
		return mapper.findMoreByUserId(map);
	}

	@Override
	public SysUser login(SysUser s) {
		return mapper.findByAccount(s);
	}

	@Override
	public PageInfo<SysUser> findByPage(SysUser obj, Page<Object> page) {
		PageHelper.startPage(page.getPageNum(), page.getPageSize(), page.getOrderBy());
		List<SysUser> list = mapper.findBySearch(obj);
		PageInfo<SysUser> pageInfo = new PageInfo<SysUser>(list);
		return pageInfo;
	}
}
