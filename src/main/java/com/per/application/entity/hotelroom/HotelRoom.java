package com.per.application.entity.hotelroom;

import java.math.BigDecimal;
/**
 * @author hotel
 * @date 2022-04-21 21:23:44
 */ 
public class HotelRoom{

	private Long id;//主键
	private String roomNum;//房间号
	private String type;//房间类型
	private String bed;//床型
	private BigDecimal price;//标准价
	private BigDecimal vipPrice;//会员价
	private Long floorId;//楼层编号
	private String state;//状态

	public HotelRoom(){
		super();
	}
	public void setId(Long id){
		this.id=id;
	}
	public Long getId(){
		return id;
	}
	public void setRoomNum(String roomNum){
		this.roomNum=roomNum;
	}
	public String getRoomNum(){
		return roomNum;
	}
	public void setType(String type){
		this.type=type;
	}
	public String getType(){
		return type;
	}
	public void setBed(String bed){
		this.bed=bed;
	}
	public String getBed(){
		return bed;
	}
	public void setPrice(BigDecimal price){
		this.price=price;
	}
	public BigDecimal getPrice(){
		return price;
	}
	public void setVipPrice(BigDecimal vipPrice){
		this.vipPrice=vipPrice;
	}
	public BigDecimal getVipPrice(){
		return vipPrice;
	}
	public void setFloorId(Long floorId){
		this.floorId=floorId;
	}
	public Long getFloorId(){
		return floorId;
	}
	public void setState(String state){
		this.state=state;
	}
	public String getState(){
		return state;
	}
	@Override
	public String toString(){
		return "HotelRoom[id="+id+",roomNum="+roomNum+",type="+type+",bed="+bed+",price="+price+",vipPrice="+vipPrice+",floorId="+floorId+",state="+state+"]";
	}
}

