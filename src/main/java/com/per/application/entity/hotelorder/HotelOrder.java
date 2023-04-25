package com.per.application.entity.hotelorder;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonFormat;

/**
 * @author hotel
 * @date 2022-04-21 21:23:44
 */
public class HotelOrder {

	private Long id;// 主键
	private String orderNum;// 订单编号
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	@JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
	private Date startDate;// 入住日期
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	@JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
	private Date endDate;// 退房日期
	private String customerName;// 预定人
	private String phone;// 手机号码
	private Integer num;// 同住人数
	private Long roomId;// 房间id
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
	private Date createTime;// 下单时间
	private String orderType;// 订单状态
	private String state;// 状态

	public HotelOrder() {
		super();
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getId() {
		return id;
	}

	public void setOrderNum(String orderNum) {
		this.orderNum = orderNum;
	}

	public String getOrderNum() {
		return orderNum;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	public Date getStartDate() {
		return startDate;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}

	public Date getEndDate() {
		return endDate;
	}

	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}

	public String getCustomerName() {
		return customerName;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getPhone() {
		return phone;
	}

	public void setNum(Integer num) {
		this.num = num;
	}

	public Integer getNum() {
		return num;
	}

	public void setRoomId(Long roomId) {
		this.roomId = roomId;
	}

	public Long getRoomId() {
		return roomId;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setOrderType(String orderType) {
		this.orderType = orderType;
	}

	public String getOrderType() {
		return orderType;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getState() {
		return state;
	}

	@Override
	public String toString() {
		return "HotelOrder[id=" + id + ",orderNum=" + orderNum + ",startDate=" + startDate + ",endDate=" + endDate
				+ ",customerName=" + customerName + ",phone=" + phone + ",num=" + num + ",roomId=" + roomId
				+ ",createTime=" + createTime + ",orderType=" + orderType + ",state=" + state + "]";
	}
}
