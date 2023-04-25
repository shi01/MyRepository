package com.per.application.controller.sys.sysrolepermission;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.per.application.entity.sysmenus.SysMenus;
import com.per.application.entity.sysrolepermission.SysRolePermission;
import com.per.application.service.sysmenus.SysMenusService;
import com.per.application.service.sysrolepermission.SysRolePermissionService;
import com.per.application.sys.responseEntity.ServerResponse;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

/**
 * @author MYL
 */
@Api(tags = "权限信息管理")
@RequestMapping(value = "/admin/permission/")
@Controller
public class SysRolePermissionController {

	@Autowired
	private SysRolePermissionService tempService;
	@Autowired
	private SysMenusService sysMenusService;

	@ApiOperation("列表")
	@GetMapping("list")
	@ResponseBody
	public ServerResponse list(Long roleId) {
		List<SysMenus> list = sysMenusService.getMeuns();
		return ServerResponse.ok(tempService.findByRoleId(roleId)).extra(list);
	}

	@ApiOperation("详情")
	@GetMapping("detail")
	@ResponseBody
	public ServerResponse detail(Long id) {
		return ServerResponse.ok(tempService.findById(id));
	}

	@PostMapping("add")
	@ResponseBody
	public ServerResponse add(SysRolePermission obj) {
		try {
			tempService.insert(obj);
		} catch (Exception e) {
			return ServerResponse.fail();
		}
		return ServerResponse.ok();
	}

	@PostMapping("update")
	@ResponseBody
	public ServerResponse update(SysRolePermission obj) {
		try {
			tempService.updateById(obj);
		} catch (Exception e) {
			return ServerResponse.fail();
		}
		return ServerResponse.ok();
	}

	@PostMapping("delete")
	@ResponseBody
	public ServerResponse delete(Long id) {
		try {
			tempService.deleteById(id);
		} catch (Exception e) {
			return ServerResponse.fail();
		}
		return ServerResponse.ok();
	}
}