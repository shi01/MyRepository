package com.per.application.controller.admin.hotelfloor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.github.pagehelper.Page;
import com.per.application.entity.hotelfloor.HotelFloor;
import com.per.application.service.common.CommonService;
import com.per.application.service.hotelfloor.HotelFloorService;
import com.per.application.sys.responseEntity.ServerResponse;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@Api(tags = "楼层信息管理")
@RequestMapping(value = "/admin/floor/")
@Controller
public class HotelFloorController {

	@Autowired
	private HotelFloorService tempService;
	@Autowired
	private CommonService commonService;

	@ApiOperation("列表分页")
	@GetMapping("list")
	@ResponseBody
	public ServerResponse list(HotelFloor obj, Page<Object> page) {
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
	public ServerResponse add(HotelFloor obj) {
		try {
			tempService.insert(obj);
		} catch (Exception e) {
			return ServerResponse.fail();
		}
		return ServerResponse.ok();
	}

	@PostMapping("update")
	@ResponseBody
	public ServerResponse update(HotelFloor obj) {
		try {
			tempService.updateById(obj);
		} catch (Exception e) {
			return ServerResponse.fail();
		}
		return ServerResponse.ok();
	}

	@PostMapping("save")
	@ResponseBody
	public ServerResponse save(HotelFloor obj) {
		if (obj == null) {
			return ServerResponse.fail();
		}
		String sql = "SELECT count(1) FROM `hotel_floor` WHERE floor_name='" + obj.getFloorName() + "' ";
		Long count;
		String msg = "楼层已存在";
		try {
			if (obj.getId() == null) {
				count = commonService.count(sql);
				if (count > 0) {
					return ServerResponse.fail().message(msg);
				}
				tempService.insert(obj);
			} else {
				count = commonService.count(sql + " AND id!=" + obj.getId());
				if (count > 0) {
					return ServerResponse.fail().message(msg);
				}
				tempService.updateById(obj);
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