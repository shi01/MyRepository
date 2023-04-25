package com.per.application.controller.admin;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.per.application.entity.sysuser.SysUser;
import com.per.application.service.sysuser.SysUserService;
import com.per.application.sys.responseEntity.ServerResponse;
import com.per.application.utils.AlgorithmUtils;
import com.per.application.utils.ValidationMethods;

/**
 * @author MYL
 * @date 2021年11月26日 14:13:30
 */
@Controller
public class DefaultController {
	@Autowired
	private SysUserService sysUserService;

	@RequestMapping("/")
	public String toLogin() {
		return "login";
	}

	@RequestMapping("/tologin")
	@ResponseBody
	public ServerResponse tologin() {
		return ServerResponse.unauthorized();
	}

	@PostMapping("/login")
	@ResponseBody
	public ServerResponse login(SysUser user) {
		if (ValidationMethods.isEmpty(user.getUsername()) || ValidationMethods.isEmpty(user.getPassword())) {
			return ServerResponse.fail("用户名或密码不能为空！");
		}
		user.setPassword(AlgorithmUtils.encryption(user.getPassword()));
		SysUser loginUser = sysUserService.login(user);
		if (ValidationMethods.isEmpty(loginUser)) {
			return ServerResponse.fail("用户名或密码错误！");
		}
		String token = AlgorithmUtils.getToken(loginUser.getName(), loginUser.getId());
		return ServerResponse.ok(loginUser).extra(token);
	}

	@PostMapping("/person")
	@ResponseBody
	public ServerResponse person(HttpServletRequest request) {
		Long userId = AlgorithmUtils.getUserId(request);
		if (userId == null) {
			return ServerResponse.unauthorized();
		}
		SysUser user = sysUserService.findById(userId);
		user.setPassword(null);
		return ServerResponse.ok(user);
	}
}
