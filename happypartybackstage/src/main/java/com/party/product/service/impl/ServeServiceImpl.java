package com.party.product.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import com.party.domain.Serve;
import com.party.domain.ServeCategory;
import com.party.dto.Page;
import com.party.exception.ExceptionMsgEnum;
import com.party.exception.ServiceException;
import com.party.product.mapper.ServeMapper;
import com.party.product.mapper.ServeCategoryMapper;
import com.party.product.service.ServeService;
/**
 * 
 * @author Caizhf
 * @date 2017年6月11日下午2:48:19
 * @version v.0.1
 * @email 1115054416@qq.com 
 *
 * <p>Description: 二级服务的实现类,功能介绍全部都在接口当中</p>
 *
 */
@Service
public class ServeServiceImpl implements ServeService{
	
	@Autowired
	private ServeMapper serveMapper;
	@Autowired
	private ServeCategoryMapper serveCategoryMapper;
	
	@Override
	public Page listServe(int pageNum, String type,Integer adminId) {
		//如果类型为空，抛出异常
		if(StringUtils.isEmpty(type))
			throw new ServiceException(ExceptionMsgEnum.CLIENT_PARAM_ERROR_MSG);
		
		int totleRecord = serveMapper.countList(type,adminId);
		Page page = null;
		if(pageNum==0)
			page = new Page(totleRecord,1);
		else
			page = new Page(totleRecord,pageNum);
		List<Serve> serves = serveMapper.list(type,page.getStartindex(),page.getPAGESIZE(),adminId);
		page.setList(serves);
		return page;
	}

	
	@Override
	public Page listServeByCategory(int pageNum, String type, int categoryId) {
		if(StringUtils.isEmpty(type)||categoryId==0)
			throw new ServiceException(ExceptionMsgEnum.CLIENT_PARAM_ERROR_MSG);
		int totleRecord = serveMapper.countListByCategory(type,categoryId);
		Page page = null;
		if(pageNum==0)
			page = new Page(totleRecord,1);
		else
			page = new Page(totleRecord,pageNum);
		List<Serve> serves = serveMapper.listByCategoryId(type,categoryId,page.getStartindex(),page.getPAGESIZE());
		page.setList(serves);
		return page;
	}

	
	@Override
	public Serve getServe(int sid) {
		if(sid==0)
			throw new ServiceException(ExceptionMsgEnum.CLIENT_PARAM_ERROR_MSG);
		//映射，把所有的图片都映射拿出来
		Serve s = serveMapper.findById(sid);
		if(s==null)
			throw new ServiceException(ExceptionMsgEnum.SERVER_DB_NOTEXIT);
		return s;
	}

	
	@Override
	public List<ServeCategory> listCategory(String type) {
		if(StringUtils.isEmpty(type))
			throw new ServiceException(ExceptionMsgEnum.CLIENT_PARAM_ERROR_MSG);
		//不需要映射
		List<ServeCategory> categorys = serveCategoryMapper.listCategoryByServeType(type);
		if(categorys==null||categorys.size()==0)
			throw new ServiceException("当前数据库中还没有任何关于这个分类的记录");
		return categorys;
	}


	@Override
	public Page listLikeByTitle(int pageNum, String type, int pageSize, String key) {
		int totleRecord = serveMapper.countListLikeByTitle(type,key);
		Page page = null;
		if(pageNum==0)
			page = new Page(totleRecord,1);
		else
			page = new Page(totleRecord, pageNum,pageSize);
		List<Serve> serves = serveMapper.listLikeByTitle(key,type,page.getStartindex(),pageSize);
		page.setList(serves);
		return page;
	}


	@Override
	@Transactional
	public void update(Serve serve) {
		if(serveMapper.updateById(serve)==null)
			throw new ServiceException(ExceptionMsgEnum.SERVER_ERROR_MSG);
	}


	@Override
	@Transactional
	public Integer save(Serve serve) {
		if(serveMapper.save(serve)==null)
			throw new ServiceException(ExceptionMsgEnum.SERVER_ERROR_MSG);
		return serve.getSid();
	}


	@Override
	@Transactional
	public void deleteById(int sid) {
		if(serveMapper.deleteById(sid)==null)
			throw new ServiceException(ExceptionMsgEnum.SERVER_DB_NOTEXIT);
	}


	@Override
	@Transactional
	public void saveServeCategory(ServeCategory sc) {
		if(serveCategoryMapper.save(sc)==null)
			throw new ServiceException(ExceptionMsgEnum.SERVER_ERROR_MSG);
	}


	@Override
	public ServeCategory findServeCategoryByScid(int scid) {
		if(scid==0)
			throw new ServiceException(ExceptionMsgEnum.CLIENT_PARAM_ERROR_MSG);
		ServeCategory sc = serveCategoryMapper.findById(scid);
		if(sc==null)
			throw new ServiceException(ExceptionMsgEnum.SERVER_DB_NOTEXIT);
		return sc;
	}

	/*
	 * 删除需谨慎，如果没有把商品都删除，就不能删除这个分类
	 */
	@Override
	@Transactional
	public void deleteServeCategoryByScid(int scid) {
		if(scid==0)
			throw new ServiceException(ExceptionMsgEnum.CLIENT_PARAM_ERROR_MSG);
		if(serveCategoryMapper.deleteById(scid)==null)
			throw new ServiceException(ExceptionMsgEnum.SERVER_DB_NOTEXIT);
	}


	@Override
	@Transactional
	public void updateServeCategoryByScid(ServeCategory serveCategory) {
		if(serveCategoryMapper.updateById(serveCategory)==null)
			throw new ServiceException(ExceptionMsgEnum.SERVER_ERROR_MSG);
	}


	@Override
	public void toTop(int sid, int sequence) {
		if(serveMapper.updateSequenceById(sid,sequence)==null)
			throw new ServiceException(ExceptionMsgEnum.SERVER_ERROR_MSG);
	}

}
