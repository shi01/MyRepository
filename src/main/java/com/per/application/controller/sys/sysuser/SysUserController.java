package com.per.application.controller.sys.sysuser;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.github.pagehelper.Page;
import com.per.application.entity.sysuser.SysUser;
import com.per.application.service.sysuser.SysUserService;
import com.per.application.sys.responseEntity.ServerResponse;
import com.per.application.utils.AlgorithmUtils;
import com.per.application.utils.ValidationMethods;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

/**
 * @author MYL
 */
@Api(tags = "用户信息管理")
@RequestMapping(value = "/admin/user/")
@Controller
public class SysUserController {

	@Autowired
	private SysUserService tempService;

	@ApiOperation("列表分页")
	@GetMapping("list")
	@ResponseBody
	public ServerResponse list(SysUser obj, Page<Object> page) {
		page.setOrderBy("id DESC");
		return ServerResponse.ok(tempService.findByPage(obj, page));
	}

	@ApiOperation("详情")
	@GetMapping("detail")
	@ResponseBody
	public ServerResponse detail(Long id) {
		SysUser user = tempService.findById(id);
		user.setPassword(null);
		return ServerResponse.ok(user);
	}

	@PostMapping("add")
	@ResponseBody
	public ServerResponse add(SysUser obj) {
		if (ValidationMethods.isEmpty(obj) || ValidationMethods.isEmpty(obj.getUsername())
				|| ValidationMethods.isEmpty(obj.getRoleId())) {
			return ServerResponse.fail();
		}
		try {
			// TODO 默认密码123456
			obj.setPassword(AlgorithmUtils.encryption("123456"));
			tempService.insert(obj);
		} catch (Exception e) {
			return ServerResponse.fail(e.getMessage());
		}
		return ServerResponse.ok();
	}

	@PostMapping("update")
	@ResponseBody
	public ServerResponse update(SysUser obj) {
		try {
			tempService.updateById(obj);
		} catch (Exception e) {
			return ServerResponse.fail();
		}
		return ServerResponse.ok();
	}

	@PostMapping("pwd")
	@ResponseBody
	public ServerResponse pwd(String password, String old, HttpServletRequest request) {
		if (ValidationMethods.isEmpty(old) || ValidationMethods.isEmpty(password)) {
			return ServerResponse.fail();
		}
		try {
			Long userId = AlgorithmUtils.getUserId(request);
			if (AlgorithmUtils.encryption(old).equals(tempService.findById(userId).getPassword())) {
				SysUser user = new SysUser();
				user.setId(userId);
				user.setPassword(AlgorithmUtils.encryption(password));
				tempService.updateById(user);
			} else {
				return ServerResponse.fail().message("保存失败:密码错误!");
			}
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