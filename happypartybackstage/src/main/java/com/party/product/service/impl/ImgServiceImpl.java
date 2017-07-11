package com.party.product.service.impl;

import java.io.File;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import com.party.domain.Img;
import com.party.exception.ExceptionMsgEnum;
import com.party.exception.ServiceException;
import com.party.product.mapper.ImgMapper;
import com.party.product.service.ImgService;
/**
 * 
 * @author Caizhf
 * @date 2017年6月20日下午3:55:27
 * @version v.0.1
 * @email 1115054416@qq.com 
 *
 * <p>Description: </p>
 *
 */
@Service
public class ImgServiceImpl implements ImgService{
	
	@Autowired
	private ImgMapper imgMapper;
	
	@Override
	public void save(String productType, int productId, String realPath,String diskPath) {
		if(StringUtils.isEmpty(productType)||productId==0)
			throw new ServiceException(ExceptionMsgEnum.CLIENT_PARAM_ERROR_MSG);
		if(StringUtils.isEmpty(diskPath))
			throw new ServiceException("图片上传失败,请不要上传过大图片");
		if(imgMapper.save(new Img(productType,productId,realPath,diskPath))==null)
			throw new ServiceException(ExceptionMsgEnum.SERVER_ERROR_MSG);
	}

	@Override
	@Transactional
	public void delete(int iid) {
		Img img = imgMapper.findById(iid);
		File file = new File(img.getDiskUrl());
		//删除图片，并且删除记录
		if(!file.delete()||imgMapper.deleteById(iid)==null)
			throw new ServiceException("删图失败,数据库可能已经没有这张图片了");
	}

	@Override
	public Img get(int iid) {
		Img img = imgMapper.findById(iid);
		return img;
	}

	@Override
	public void update(int iid, String  showPath,String diskPath) {
		Img oldImg = imgMapper.findById(iid);
		File file = new File(oldImg.getDiskUrl());
		if(!file.delete()||imgMapper.updateUrlById(iid,showPath,diskPath)==null)
			throw new ServiceException("更新图片失败");
	}

}
