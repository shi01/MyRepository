package com.per.application.controller.sys.menu;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.per.application.entity.sysmenus.SysMenus;
import com.per.application.service.sysmenus.SysMenusService;
import com.per.application.service.sysuser.SysUserService;
import com.per.application.sys.responseEntity.ServerResponse;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

/**
 * @author MYL
 * @date 2021-11-22 17:34:10
 */
@Api(tags = "菜单管理")
@RequestMapping(value = "/admin/menus/")
@Controller
public class SysMenusController {

	@Autowired
	private SysMenusService sysMenusService;
	@Autowired
	private SysUserService sysUserService;

	@ApiOperation("服务列表")
	@GetMapping("list")
	@ResponseBody
	public ServerResponse list(Long userId) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("userId", userId);
		map.put("type", 1);
		// 一级菜单
		List<Map<String, Object>> list1 = sysUserService.findMoreByUserId(map);
		map.put("type", 2);
		// 二级菜单
		List<Map<String, Object>> list2 = sysUserService.findMoreByUserId(map);
		return ServerResponse.ok(list1).extra(list2);
	}

	@GetMapping("add")
	public String add(SysMenus s) {
		return sysMenusService.insert(s) + "";
	}

	@GetMapping("update")
	public String update(SysMenus s) {
		return sysMenusService.updateById(s) + "";
	}

	@GetMapping("delete")
	public String delete(Long id) {
		sysMenusService.deleteById(id);
		return "";
	}
}
