package com.party.domain;
/**
 * 
 * @author Caizhf
 * @date 2017年6月21日下午1:41:43
 * @version v.0.1
 * @email 1115054416@qq.com 
 *
 * <p>Description: 布置主题</p>
 *
 */
public class Theme {
	private int thid;						//主键
	private String themeName;				//主题名
	private Integer sequence;
	private Img img;
	
	public int getThid() {
		return thid;
	}
	public void setThid(int thid) {
		this.thid = thid;
	}
	public String getThemeName() {
		return themeName;
	}
	public void setThemeName(String themeName) {
		this.themeName = themeName;
	}
	public Img getImg() {
		return img;
	}
	public void setImg(Img img) {
		this.img = img;
	}
	public Integer getSequence() {
		return sequence;
	}
	public void setSequence(Integer sequence) {
		this.sequence = sequence;
	}
	
}
