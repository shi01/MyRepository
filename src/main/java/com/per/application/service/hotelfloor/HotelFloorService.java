package com.per.application.service.hotelfloor;

import java.util.List;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageInfo;
import com.per.application.entity.hotelfloor.HotelFloor;

/**
 * @author hotel
 * @date 2022-04-21 21:23:44
 */
public interface HotelFloorService {

	public HotelFloor findById(Long id);

	public List<HotelFloor> findBySearch(HotelFloor h);

	public PageInfo<HotelFloor> findByPage(HotelFloor obj, Page<Object> page);

	public int insert(HotelFloor h);

	public int updateById(HotelFloor h);

	public void deleteById(Long id);
}
