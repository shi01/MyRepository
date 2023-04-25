package com.per.application.service.sysdict;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.per.application.dao.sysdict.SysDictMapper;
import com.per.application.entity.sysdict.SysDict;

/**
 * @author MYL
 * @date 2021-11-24 17:02:24
 */ 
@Service
@Transactional
public class SysDictServiceImpl implements SysDictService{

	@Autowired
	private SysDictMapper mapper;

	@Override
	public SysDict findById(Long id){
		return mapper.findById(id);
	}

	public List<SysDict> findBySearch(SysDict s){
	return mapper.findBySearch(s);
	}

	public int insert(SysDict s){
		return mapper.insert(s);
	}

	public int updateById(SysDict s){
		return mapper.updateById(s);
	}

	public void deleteById(Long id){
		mapper.deleteById(id);
	}

	@Override
	public PageInfo<SysDict> findByPage(SysDict c, Page<Object> page) {
		PageHelper.startPage(page.getPageNum(), page.getPageSize(), page.getOrderBy());
		List<SysDict> lists = mapper.findBySearch(c);
		PageInfo<SysDict> pageInfo = new PageInfo<SysDict>(lists);
		return pageInfo;
	}
}

