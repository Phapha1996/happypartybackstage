package com.party.domain;

/**
 * 
 * @author Caizhf
 * @date 2017年6月11日下午2:18:34
 * @version v.0.1
 * @email 1115054416@qq.com
 *
 *        <p>
 * 		Description: 商品的图片
 *        </p>
 *
 */
public class Img {
	private Integer iid;						
	private String productType;				//商品类型
	private Integer productId;			//商品id
	private String imgUrl;					//图片显示路径
	private String diskUrl;				//图片硬盘存储路径

	public Img(String productType, Integer productId, String imgUrl,String diskUrl) {
		super();
		this.productType = productType;
		this.productId = productId;
		this.imgUrl = imgUrl;
		this.diskUrl = diskUrl;
	}

	
	public Img() {
		super();
	}
	
	public Img(String imgUrl){
		this.imgUrl = imgUrl;
	}

	public Integer getIid() {
		return iid;
	}

	public void setIid(Integer iid) {
		this.iid = iid;
	}

	public String getProductType() {
		return productType;
	}

	public void setProductType(String productType) {
		this.productType = productType;
	}

	public String getImgUrl() {
		return imgUrl;
	}

	public void setImgUrl(String imgUrl) {
		this.imgUrl = imgUrl;
	}

	public Integer getProductId() {
		return productId;
	}

	public void setProductId(Integer productId) {
		this.productId = productId;
	}

	public String getDiskUrl() {
		return diskUrl;
	}

	public void setDiskUrl(String diskUrl) {
		this.diskUrl = diskUrl;
	}

}
