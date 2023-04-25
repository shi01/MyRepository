package com.per.application.service.hotelorder;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.per.application.dao.hotelorder.HotelOrderMapper;
import com.per.application.entity.hotelorder.HotelOrder;
import com.per.application.entity.hotelroom.HotelRoom;
import com.per.application.service.hotelroom.HotelRoomService;

/**
 * @author hotel
 * @date 2022-04-21 21:23:44
 */
@Service
@Transactional
public class HotelOrderServiceImpl implements HotelOrderService {

	@Autowired
	private HotelOrderMapper mapper;
	@Autowired
	private HotelRoomService hotelRoomService;

	@Override
	public HotelOrder findById(Long id) {
		return mapper.findById(id);
	}

	public List<HotelOrder> findBySearch(HotelOrder h) {
		return mapper.findBySearch(h);
	}

	@Override
	public PageInfo<HotelOrder> findByPage(HotelOrder obj, Page<Object> page) {
		PageHelper.startPage(page.getPageNum(), page.getPageSize(), page.getOrderBy());
		List<HotelOrder> list = mapper.findBySearch(obj);
		PageInfo<HotelOrder> pageInfo = new PageInfo<HotelOrder>(list);
		return pageInfo;
	}

	public int insert(HotelOrder h) {
		updateRoomState(h.getRoomId());
		return mapper.insert(h);
	}

	public int updateById(HotelOrder h) throws Exception {
		HotelOrder old = mapper.findById(h.getId());
		Long oldRoomId = old.getRoomId();
		if (!oldRoomId.equals(h.getRoomId())) {
			HotelRoom room = hotelRoomService.findById(h.getRoomId());
			if (room.getState().equals("1")) {
				throw new Exception("该房间占用中！");
			}
		}
		updateRoomState(h.getRoomId());
		return mapper.updateById(h);
	}

	public void deleteById(Long id) {
		mapper.deleteById(id);
	}

	/**
	 * 更新房间状态
	 * 
	 * @param roomId
	 */
	private void updateRoomState(Long roomId) {
		HotelRoom hotelRoom = new HotelRoom();
		hotelRoom.setId(roomId);
		hotelRoom.setState("1");
		hotelRoomService.updateById(hotelRoom);
	}

	/**
	 * 退房
	 */
	@Override
	public void signOut(Long id) {
		HotelOrder old = mapper.findById(id);
		HotelRoom room = hotelRoomService.findById(old.getRoomId());
		room.setState("0");
		hotelRoomService.updateById(room);
		old.setEndDate(new Date());
		mapper.updateById(old);
	}
}
