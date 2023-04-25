package com.per.application.service.hotelroom;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.per.application.dao.hotelroom.HotelRoomMapper;
import com.per.application.entity.hotelfloor.HotelFloor;
import com.per.application.entity.hotelroom.HotelRoom;
import com.per.application.service.hotelfloor.HotelFloorService;

/**
 * @author hotel
 * @date 2022-04-21 21:23:44
 */
@Service
@Transactional
public class HotelRoomServiceImpl implements HotelRoomService {

	@Autowired
	private HotelRoomMapper mapper;
	@Autowired
	private HotelFloorService hotelFloorService;

	@Override
	public HotelRoom findById(Long id) {
		return mapper.findById(id);
	}

	@Override
	public PageInfo<HotelRoom> findByPage(HotelRoom obj, Page<Object> page) {
		PageHelper.startPage(page.getPageNum(), page.getPageSize(), page.getOrderBy());
		List<HotelRoom> list = mapper.findBySearch(obj);
		PageInfo<HotelRoom> pageInfo = new PageInfo<HotelRoom>(list);
		return pageInfo;
	}

	public List<HotelRoom> findBySearch(HotelRoom h) {
		return mapper.findBySearch(h);
	}

	public int insert(HotelRoom h) {
		return mapper.insert(h);
	}

	public int updateById(HotelRoom h) {
		return mapper.updateById(h);
	}

	public void deleteById(Long id) {
		mapper.deleteById(id);
	}

	@Override
	public int updateById2(HotelRoom h) throws Exception {
		HotelRoom old = mapper.findById(h.getId());
		Long floorId = old.getFloorId();
		if (!floorId.equals(h.getFloorId())) {
			HotelFloor hotelFloor = hotelFloorService.findById(h.getFloorId());
			if (hotelFloor.getState().equals("0")) {
				throw new Exception("该楼层已禁用！");
			}
		}
		mapper.updateById(h);
		return 0;
	}
}
