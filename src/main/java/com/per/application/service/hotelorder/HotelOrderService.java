package com.per.application.service.hotelorder;

import java.util.List;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageInfo;
import com.per.application.entity.hotelorder.HotelOrder;

/**
 * @author hotel
 * @date 2022-04-21 21:23:44
 */
public interface HotelOrderService {

	public HotelOrder findById(Long id);

	public void signOut(Long id);

	public List<HotelOrder> findBySearch(HotelOrder h);

	public PageInfo<HotelOrder> findByPage(HotelOrder obj, Page<Object> page);

	public int insert(HotelOrder h);

	public int updateById(HotelOrder h) throws Exception;

	public void deleteById(Long id);
}
