package com.per.application.controller.admin.hotelorder;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.github.pagehelper.Page;
import com.per.application.entity.hotelorder.HotelOrder;
import com.per.application.service.hotelorder.HotelOrderService;
import com.per.application.sys.responseEntity.ServerResponse;
import com.per.application.utils.DateUtils;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@Api(tags = "订单信息管理")
@RequestMapping(value = "/admin/order/")
@Controller
public class HotelOrderController {

	@Autowired
	private HotelOrderService tempService;

	@ApiOperation("列表分页")
	@GetMapping("list")
	@ResponseBody
	public ServerResponse list(HotelOrder obj, Page<Object> page) {
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
	public ServerResponse add(HotelOrder obj) {
		try {
			tempService.insert(obj);
		} catch (Exception e) {
			return ServerResponse.fail(e.getMessage());
		}
		return ServerResponse.ok();
	}

	@PostMapping("update")
	@ResponseBody
	public ServerResponse update(HotelOrder obj) {
		try {
			tempService.updateById(obj);
		} catch (Exception e) {
			return ServerResponse.fail(e.getMessage());
		}
		return ServerResponse.ok();
	}
	
	@PostMapping("state")
	@ResponseBody
	public ServerResponse state(Long id) {
		try {
			tempService.signOut(id);
		} catch (Exception e) {
			return ServerResponse.fail(e.getMessage());
		}
		return ServerResponse.ok();
	}

	@PostMapping("save")
	@ResponseBody
	public ServerResponse save(HotelOrder obj) {
		if (obj == null) {
			return ServerResponse.fail();
		}
		try {
			if (obj.getId() == null) {
				obj.setCreateTime(new Date());
				obj.setOrderNum(getOrderNum());
				tempService.insert(obj);
			} else {
				tempService.updateById(obj);
			}
		} catch (Exception e) {
			return ServerResponse.fail(e.getMessage());
		}
		return ServerResponse.ok();
	}

	public static synchronized String getOrderNum() {
//		return UUID.randomUUID().toString().replace("-", "");
		return DateUtils.getCurrentYear() + "" + new Date().getTime();
	}

	@PostMapping("delete")
	@ResponseBody
	public ServerResponse delete(Long id) {
		try {
			tempService.deleteById(id);
		} catch (Exception e) {
			return ServerResponse.fail(e.getMessage());
		}
		return ServerResponse.ok();
	}
}