package com.per.application.controller.sys.sysrole;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.github.pagehelper.Page;
import com.per.application.entity.sysrole.SysRole;
import com.per.application.service.sysrole.SysRoleService;
import com.per.application.sys.responseEntity.ServerResponse;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

/**
 * @author MYL
 * @date 2022-04-15 15:40:03
 */
@Api(tags = "角色信息管理")
@RequestMapping(value = "/admin/role/")
@Controller
public class SysRoleController {

	@Autowired
	private SysRoleService tempService;

	@ApiOperation("列表分页")
	@GetMapping("list")
	@ResponseBody
	public ServerResponse list(SysRole obj, Page<Object> page) {
		page.setOrderBy("id DESC");
		return ServerResponse.ok(tempService.findByPage(obj, page));
	}

	@ApiOperation("详情")
	@GetMapping("detail")
	@ResponseBody
	public ServerResponse detail(Long id) {
		return ServerResponse.ok(tempService.findById(id));
	}

	@PostMapping("add")
	@ResponseBody
	public ServerResponse add(SysRole obj) {
		try {
			tempService.insert(obj);
		} catch (Exception e) {
			return ServerResponse.fail(e.getMessage());
		}
		return ServerResponse.ok();
	}

	@PostMapping("update")
	@ResponseBody
	public ServerResponse update(SysRole obj) {
		try {
			tempService.updateById(obj);
		} catch (Exception e) {
			return ServerResponse.fail();
		}
		return ServerResponse.ok();
	}

	@PostMapping("save")
	@ResponseBody
	public ServerResponse save(SysRole obj, HttpServletRequest request) {
		try {
			tempService.save(obj, request);
		} catch (Exception e) {
			return ServerResponse.fail();
		}
		return ServerResponse.ok();
	}

	@GetMapping("delete")
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
