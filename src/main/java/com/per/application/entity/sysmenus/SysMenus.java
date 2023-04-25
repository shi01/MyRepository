package com.per.application.entity.sysmenus;

import java.util.List;

/**
 * @author MYL
 * @date 2021-11-22 17:34:10
 */
public class SysMenus {

	private Long id;// 主键
	private String describe;// 描述
	private String url;// 地址
	private String power;// 权限
	private Integer sort;// 排序
	private Long parentId;// 父节点
	private List<SysMenus> sonList;

	public SysMenus() {
		super();
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getId() {
		return id;
	}

	public void setDescribe(String describe) {
		this.describe = describe;
	}

	public String getDescribe() {
		return describe;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getUrl() {
		return url;
	}

	public void setPower(String power) {
		this.power = power;
	}

	public String getPower() {
		return power;
	}

	public void setSort(Integer sort) {
		this.sort = sort;
	}

	public Integer getSort() {
		return sort;
	}

	public void setParentId(Long parentId) {
		this.parentId = parentId;
	}

	public Long getParentId() {
		return parentId;
	}

	public List<SysMenus> getSonList() {
		return sonList;
	}

	public void setSonList(List<SysMenus> sonList) {
		this.sonList = sonList;
	}

	@Override
	public String toString() {
		return "SysMenus[id=" + id + ",describe=" + describe + ",url=" + url + ",power=" + power + ",sort=" + sort
				+ ",parentId=" + parentId + "]";
	}
}
