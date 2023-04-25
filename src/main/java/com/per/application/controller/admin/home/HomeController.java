package com.per.application.controller.admin.home;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.per.application.sys.responseEntity.ServerResponse;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

/**
 * @author MYL
 * @date 2021-11-18 14:13:30
 */
@Api(tags = "首页管理")
@RequestMapping(value = "/admin/home/")
@Controller
public class HomeController {
	@ApiOperation(value = "数据测试", notes = "")
	@GetMapping("/data")
	@ResponseBody
	public ServerResponse data(HttpServletRequest request) throws Exception {
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("value", 500);
		map.put("name", "A1");
		Map<String, Object> map2 = new HashMap<String, Object>();
		map2.put("value", 400);
		map2.put("name", "A2");
		Map<String, Object> map3 = new HashMap<String, Object>();
		map3.put("value", 300);
		map3.put("name", "A3");
		Map<String, Object> map4 = new HashMap<String, Object>();
		map4.put("value", 200);
		map4.put("name", "A4");
		list.add(map);
		list.add(map2);
		list.add(map3);
		list.add(map4);
		return ServerResponse.ok(list);
	}
}
