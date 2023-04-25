package com.per.application.service.sysdict;

import java.util.List;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageInfo;
import com.per.application.entity.sysdict.SysDict;

/**
 * @author MYL
 * @date 2021-11-24 17:02:24
 */
public interface SysDictService {

	public SysDict findById(Long id);

	public List<SysDict> findBySearch(SysDict s);

	PageInfo<SysDict> findByPage(SysDict c, Page<Object> page);

	public int insert(SysDict s);

	public int updateById(SysDict s);

	public void deleteById(Long id);
}
