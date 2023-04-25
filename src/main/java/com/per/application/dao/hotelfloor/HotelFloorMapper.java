package com.per.application.dao.hotelfloor;

import java.util.List;

import com.per.application.entity.hotelfloor.HotelFloor;

/**
 * @author hotel
 * @date 2022-04-21 21:23:44
 */ 
public interface HotelFloorMapper{

	public HotelFloor findById(Long id);

	public List<HotelFloor> findBySearch(HotelFloor h);

	public int insert(HotelFloor h);

	public int updateById(HotelFloor h);

	public void deleteById(Long id);
}

