package com.party.domain;

import java.util.List;
/**
 * 
 * @author Caizhf
 * @date 2017年6月21日下午2:45:58
 * @version v.0.1
 * @email 1115054416@qq.com 
 *
 * <p>Description: 场地布置</p>
 *
 */
public class Decoration {
	private int did; // 主键
	private String title; // 标题
	private String city; // 城市
	private String tags; // 标签
	private String details; // 详细内容
	private int sequence; // 排序
	private Double price;		//原价
	private Double bottomPrice;	//最低价
	private Admin admin;
	private Theme theme;
	private List<Img> imgs;
	
	public int getDid() {
		return did;
	}
	public void setDid(int did) {
		this.did = did;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public String getTags() {
		return tags;
	}
	public void setTags(String tags) {
		this.tags = tags;
	}
	public String getDetails() {
		return details;
	}
	public void setDetails(String details) {
		this.details = details;
	}
	public int getSequence() {
		return sequence;
	}
	public void setSequence(int sequence) {
		this.sequence = sequence;
	}
	public Admin getAdmin() {
		return admin;
	}
	public void setAdmin(Admin admin) {
		this.admin = admin;
	}
	public Theme getTheme() {
		return theme;
	}
	public void setTheme(Theme theme) {
		this.theme = theme;
	}
	public List<Img> getImgs() {
		return imgs;
	}
	public void setImgs(List<Img> imgs) {
		this.imgs = imgs;
	}
	public Double getPrice() {
		return price;
	}
	public void setPrice(Double price) {
		this.price = price;
	}
	public Double getBottomPrice() {
		return bottomPrice;
	}
	public void setBottomPrice(Double bottomPrice) {
		this.bottomPrice = bottomPrice;
	}
}
