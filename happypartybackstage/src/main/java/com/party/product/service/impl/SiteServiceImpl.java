package com.party.product.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.party.domain.Img;
import com.party.domain.Site;
import com.party.dto.Page;
import com.party.exception.ExceptionMsgEnum;
import com.party.exception.ServiceException;
import com.party.product.mapper.SiteMapper;
import com.party.product.service.ImgService;
import com.party.product.service.SiteService;

/**
 * 
 * @author Caizhf
 * @date 2017年6月20日下午5:54:08
 * @version v.0.1
 * @email 1115054416@qq.com
 *
 *        <p>
 * 		Description:
 *        </p>
 *
 */
@Service
public class SiteServiceImpl implements SiteService {

	@Autowired
	private SiteMapper siteMapper;
	@Autowired
	private ImgService imgService;

	@Override
	@Transactional
	public Integer save(Site site) {
		if (siteMapper.save(site)==null)
			throw new ServiceException(ExceptionMsgEnum.SERVER_ERROR_MSG);
		return site.getSiid();
	}

	@Override
	//所有内部事务停止，用当前事务
	@Transactional(propagation=Propagation.REQUIRED)
	// 删除的时候需要级联把图片一起删除了
	public void deleteById(int siid) {
		Site site = siteMapper.findById(siid);
		if (site != null) {
			List<Img> imgs = site.getImgs();
			// 如果这个场地有图片，那么循环删除图片
			if (imgs.size() != 0)
				for (Img i : imgs) {
					imgService.delete(i.getIid());
				}
		}
		if (siteMapper.deleteById(siid) == null)
			throw new ServiceException(ExceptionMsgEnum.SERVER_DB_NOTEXIT);
	}

	@Override
	@Transactional
	public void updateById(Site site) {
		if (siteMapper.updateById(site) == null)
			throw new ServiceException(ExceptionMsgEnum.SERVER_ERROR_MSG);
	}

	@Override
	public Site findById(int siid) {
		Site site = siteMapper.findById(siid);
		if (site == null)
			throw new ServiceException(ExceptionMsgEnum.SERVER_DB_NOTEXIT);
		return site;
	}

	@Override
	public Page listLikeByTitle(String key, int pageNum, int pageSize) {
		int totleRecord = siteMapper.countLikeByTitle(key);
		Page page = null;
		if (pageNum == 0)
			page = new Page(totleRecord, 1);
		else
			// 如果不为空，那么就按照想看的页码进行
			page = new Page(totleRecord, pageNum,pageSize);
		List<Site> sites = siteMapper.listLikeByTitle(key, page.getStartindex(), pageSize);
		page.setList(sites);
		return page;
	}

	@Override
	public Page list(int pageNum, int pageSize, Integer adminId) {
		int totleRecord = siteMapper.countList(adminId);
		Page page = null;
		if (pageNum == 0)
			page = new Page(totleRecord, 1);
		else
			// 如果不为空，那么就按照想看的页码进行
			page = new Page(totleRecord, pageNum,pageSize);
		List<Site> sites = siteMapper.list(page.getStartindex(), pageSize, adminId);
		page.setList(sites);
		return page;
	}

	@Override
	public void toTop(int siid, int sequence) {
		if (siteMapper.updateSequenceById(siid, sequence) == null)
			throw new ServiceException(ExceptionMsgEnum.SERVER_DB_NOTEXIT);
	}

}
