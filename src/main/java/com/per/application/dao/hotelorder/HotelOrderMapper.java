package com.per.application.dao.hotelorder;

import java.util.List;

import com.per.application.entity.hotelorder.HotelOrder;

/**
 * @author hotel
 * @date 2022-04-21 21:23:44
 */ 
public interface HotelOrderMapper{

	public HotelOrder findById(Long id);

	public List<HotelOrder> findBySearch(HotelOrder h);

	public int insert(HotelOrder h);

	public int updateById(HotelOrder h);

	public void deleteById(Long id);
}

