package com.party.domain;
/**
 * 
 * @author Caizhf
 * @date 2017年6月22日下午1:36:17
 * @version v.0.1
 * @email 1115054416@qq.com 
 *
 * <p>Description: 场地布置下的商品</p>
 *
 */
public class DecorationProductCategory {
	
	private int dcid; // 主键
	private String catName; // 分类名
	public int getDcid() {
		return dcid;
	}
	public void setDcid(int dcid) {
		this.dcid = dcid;
	}
	public String getCatName() {
		return catName;
	}
	public void setCatName(String catName) {
		this.catName = catName;
	}
	
}
