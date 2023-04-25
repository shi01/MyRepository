package com.per.application.entity.hotelfloor;

/**
 * @author hotel
 * @date 2022-04-21 21:23:44
 */ 
public class HotelFloor{

	private Long id;//主键
	private String floorName;//楼层
	private String state;//状态0-停用，1-启用

	public HotelFloor(){
		super();
	}
	public void setId(Long id){
		this.id=id;
	}
	public Long getId(){
		return id;
	}
	public void setFloorName(String floorName){
		this.floorName=floorName;
	}
	public String getFloorName(){
		return floorName;
	}
	public void setState(String state){
		this.state=state;
	}
	public String getState(){
		return state;
	}
	@Override
	public String toString(){
		return "HotelFloor[id="+id+",floorName="+floorName+",state="+state+"]";
	}
}

