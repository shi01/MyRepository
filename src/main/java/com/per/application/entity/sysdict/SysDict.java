package com.per.application.entity.sysdict;

/**
 * @author MYL
 * @date 2021-11-24 17:02:24
 */ 
public class SysDict{

	private Long id;//主键
	private String type;//类别
	private String val;//键值
	private String label;
	private String describe;//描述
	private Integer sort;//序号
	private Long parentId;//父节点id
	private String state;//状态0-禁用,1-启用

	public SysDict(){
		super();
	}
	public void setId(Long id){
		this.id=id;
	}
	public Long getId(){
		return id;
	}
	public void setType(String type){
		this.type=type;
	}
	public String getType(){
		return type;
	}
	public void setVal(String val){
		this.val=val;
	}
	public String getVal(){
		return val;
	}
	public void setLabel(String label){
		this.label=label;
	}
	public String getLabel(){
		return label;
	}
	public void setDescribe(String describe){
		this.describe=describe;
	}
	public String getDescribe(){
		return describe;
	}
	public void setSort(Integer sort){
		this.sort=sort;
	}
	public Integer getSort(){
		return sort;
	}
	public void setParentId(Long parentId){
		this.parentId=parentId;
	}
	public Long getParentId(){
		return parentId;
	}
	public void setState(String state){
		this.state=state;
	}
	public String getState(){
		return state;
	}
	@Override
	public String toString(){
		return "SysDict[id="+id+",type="+type+",val="+val+",label="+label+",describe="+describe+",sort="+sort+",parentId="+parentId+",state="+state+"]";
	}
}

