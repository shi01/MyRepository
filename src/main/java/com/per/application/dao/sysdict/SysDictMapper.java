package com.per.application.dao.sysdict;

import java.util.List;

import com.per.application.entity.sysdict.SysDict;

/**
 * @author MYL
 * @date 2021-11-24 17:02:24
 */ 
public interface SysDictMapper{

	public SysDict findById(Long id);

	public List<SysDict> findBySearch(SysDict s);

	public int insert(SysDict s);

	public int updateById(SysDict s);

	public void deleteById(Long id);
}

