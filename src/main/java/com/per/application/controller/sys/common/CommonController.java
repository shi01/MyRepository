package com.per.application.controller.sys.common;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.per.application.service.common.CommonService;
import com.per.application.sys.responseEntity.ServerResponse;
import com.per.application.utils.ValidationMethods;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

/**
 * @author MYL
 * @date 2022-04-18 17:02:24
 */
@Api(tags = "通用查询")
@RequestMapping(value = "/admin/common/")
@Controller
public class CommonController {

	@Autowired
	private CommonService CommonService;

	@ApiOperation("任取三个字段")
	@GetMapping("list")
	@ResponseBody
	public ServerResponse list(String c1, String c2, String c3, String t) {
		if (ValidationMethods.isEmpty(t) || ValidationMethods.isEmpty(c1) || ValidationMethods.isEmpty(c2)
				|| ValidationMethods.isEmpty(c3)) {
			return ServerResponse.fail();
		}
		return ServerResponse.ok(CommonService.find2Select(c1, c2, c3, t));
	}

	@ApiOperation("任取三个字段加自定义条件")
	@GetMapping("list2")
	@ResponseBody
	public ServerResponse list(String c1, String c2, String c3, String t, String w) {
		if (ValidationMethods.isEmpty(t) || ValidationMethods.isEmpty(c1) || ValidationMethods.isEmpty(c2)
				|| ValidationMethods.isEmpty(c3) || ValidationMethods.isEmpty(w)) {
			return ServerResponse.fail();
		}
		return ServerResponse.ok(CommonService.find2SelectBySearch(c1, c2, c3, t, w));
	}
}
