package com.per.application.service.sysmenus;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.per.application.dao.sysmenus.SysMenusMapper;
import com.per.application.entity.sysmenus.SysMenus;

/**
 * @author MYL
 * @date 2021-11-22 17:34:10
 */
@Service
@Transactional
public class SysMenusServiceImpl implements SysMenusService {

	@Autowired
	private SysMenusMapper mapper;

	@Override
	public SysMenus findById(Long id) {
		return mapper.findById(id);
	}

	public List<SysMenus> findBySearch(SysMenus s) {
		return mapper.findBySearch(s);
	}

	public int insert(SysMenus s) {
		return mapper.insert(s);
	}

	public int updateById(SysMenus s) {
		return mapper.updateById(s);
	}

	public void deleteById(Long id) {
		mapper.deleteById(id);
	}

	@Override
	public List<SysMenus> getMeuns() {
		return mapper.getMeuns();
	}
}
