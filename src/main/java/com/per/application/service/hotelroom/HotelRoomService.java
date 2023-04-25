package com.per.application.service.hotelroom;

import java.util.List;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageInfo;
import com.per.application.entity.hotelroom.HotelRoom;

/**
 * @author hotel
 * @date 2022-04-21 21:23:44
 */
public interface HotelRoomService {

	public HotelRoom findById(Long id);

	public List<HotelRoom> findBySearch(HotelRoom h);

	public PageInfo<HotelRoom> findByPage(HotelRoom obj, Page<Object> page);

	public int insert(HotelRoom h);

	public int updateById(HotelRoom h);

	public int updateById2(HotelRoom h) throws Exception;

	public void deleteById(Long id);
}
