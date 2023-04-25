package com.per.application.dao.hotelroom;

import java.util.List;

import com.per.application.entity.hotelroom.HotelRoom;

/**
 * @author hotel
 * @date 2022-04-21 21:23:44
 */ 
public interface HotelRoomMapper{

	public HotelRoom findById(Long id);

	public List<HotelRoom> findBySearch(HotelRoom h);

	public int insert(HotelRoom h);

	public int updateById(HotelRoom h);

	public void deleteById(Long id);
}

