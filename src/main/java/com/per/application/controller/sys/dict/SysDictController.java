package com.per.application.controller.sys.dict;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.github.pagehelper.Page;
import com.per.application.entity.sysdict.SysDict;
import com.per.application.service.sysdict.SysDictService;
import com.per.application.sys.responseEntity.ServerResponse;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

/**
 * @author MYL
 * @date 2021-11-24 17:02:24
 */
@Api(tags = "字典管理")
@RequestMapping(value = "/admin/dict/")
@Controller
public class SysDictController {

	@Autowired
	private SysDictService sysDictService;

	@ApiOperation("服务列表")
	@GetMapping("list")
	@ResponseBody
	public ServerResponse list(SysDict s) {
		return ServerResponse.ok(sysDictService.findBySearch(s));
	}

	@ApiOperation("详情")
	@GetMapping("detail")
	@ResponseBody
	public ServerResponse detail(Long id) {
		return ServerResponse.ok(sysDictService.findById(id));
	}

	@ApiOperation("分页")
	@GetMapping("pagelist")
	@ResponseBody
	public ServerResponse list(SysDict c, Page<Object> page) {
		page.setOrderBy("type,sort");
		return ServerResponse.ok(sysDictService.findByPage(c, page));
	}

	@PostMapping("add")
	@ResponseBody
	public ServerResponse add(SysDict c) {
		try {
			c.setVal(c.getVal().replaceAll("，", ","));
			sysDictService.insert(c);
		} catch (Exception e) {
			return ServerResponse.fail(e.getMessage());
		}
		return ServerResponse.ok();
	}

	@ApiOperation("分页")
	@PostMapping("update")
	@ResponseBody
	public ServerResponse update(SysDict c) {
		try {
			c.setVal(c.getVal().replaceAll("，", ","));
			sysDictService.updateById(c);
		} catch (Exception e) {
			return ServerResponse.fail(e.getMessage());
		}
		return ServerResponse.ok();
	}

	@PostMapping("state")
	@ResponseBody
	public ServerResponse updateState(SysDict c) {
		try {
			String state = c.getState().equals("0") ? "1" : "0";
			c.setState(state);
			sysDictService.updateById(c);
		} catch (Exception e) {
			return ServerResponse.fail(e.getMessage());
		}
		return ServerResponse.ok();
	}

	@GetMapping("delete")
	public String delete(Long id) {
		sysDictService.deleteById(id);
		return "";
	}
}
