package com.per.application.service.hotelfloor;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.per.application.dao.hotelfloor.HotelFloorMapper;
import com.per.application.entity.hotelfloor.HotelFloor;

/**
 * @author hotel
 * @date 2022-04-21 21:23:44
 */
@Service
@Transactional
public class HotelFloorServiceImpl implements HotelFloorService {

	@Autowired
	private HotelFloorMapper mapper;

	@Override
	public HotelFloor findById(Long id) {
		return mapper.findById(id);
	}

	public List<HotelFloor> findBySearch(HotelFloor h) {
		return mapper.findBySearch(h);
	}

	@Override
	public PageInfo<HotelFloor> findByPage(HotelFloor obj, Page<Object> page) {
		PageHelper.startPage(page.getPageNum(), page.getPageSize(), page.getOrderBy());
		List<HotelFloor> list = mapper.findBySearch(obj);
		PageInfo<HotelFloor> pageInfo = new PageInfo<HotelFloor>(list);
		return pageInfo;
	}

	public int insert(HotelFloor h) {
		return mapper.insert(h);
	}

	public int updateById(HotelFloor h) {
		return mapper.updateById(h);
	}

	public void deleteById(Long id) {
		mapper.deleteById(id);
	}
}
