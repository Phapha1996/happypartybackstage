package com.party.decoration.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import com.party.decoration.mapper.DecorationMapper;
import com.party.decoration.mapper.DecorationProductCategoryMapper;
import com.party.decoration.mapper.DecorationProductMapper;
import com.party.decoration.mapper.ThemeMapper;
import com.party.decoration.service.DecorationService;
import com.party.domain.Decoration;
import com.party.domain.DecorationProduct;
import com.party.domain.DecorationProductCategory;
import com.party.domain.Theme;
import com.party.dto.Page;
import com.party.exception.ExceptionMsgEnum;
import com.party.exception.ServiceException;
/**
 * 
 * @author Caizhf
 * @date 2017年6月21日下午2:07:28
 * @version v.0.1
 * @email 1115054416@qq.com 
 *
 * <p>Description: 场地布置下所有一切的业务逻辑,注释请看接口</p>
 *
 */
@Service
public class DecorationServiceImpl implements DecorationService {
	@Autowired
	private ThemeMapper themeMapper;
	@Autowired
	private DecorationMapper decMapper;
	@Autowired
	private DecorationProductCategoryMapper decProCatMapper;
	@Autowired
	private DecorationProductMapper decProMapper;
	
//-----------------------------------------------Theme Start-------------------------------------//
	@Transactional
	@Override
	public int saveTheme(Theme theme) {
		if(theme==null||StringUtils.isEmpty(theme.getThemeName()))
			throw new ServiceException(ExceptionMsgEnum.CLIENT_PARAM_ERROR_MSG);
		if(themeMapper.save(theme)==null)
			throw new ServiceException(ExceptionMsgEnum.SERVER_ERROR_MSG);
		return theme.getThid();
	}
	
	@Override
	public void updateTheme(Theme theme) {
		if(themeMapper.updateById(theme)==null)
			throw new ServiceException(ExceptionMsgEnum.SERVER_ERROR_MSG);
	}

	@Override
	public void themeToTop(int thid, int sequence) {
		if(themeMapper.updateSequenceById(thid,sequence)==null)
			throw new ServiceException(ExceptionMsgEnum.SERVER_ERROR_MSG);
	}
	
	@Override
	public Theme getTheme(int thid) {
		Theme t = themeMapper.findById(thid);
		return t;
	}

	//先留着，做了场地布置再做这个
	@Transactional
	@Override
	public void deleteTheme(int thid) {
		if(thid==0)
			throw new ServiceException(ExceptionMsgEnum.CLIENT_PARAM_ERROR_MSG);
		//先拿出当前布置主题下所有的场地布置
		List<Decoration> decs = decMapper.listDecorationByThemeId(thid, null, null);
		//将场地布置的主题外键全部设空
		if(decs!=null&&decs.size()!=0)
		for(Decoration dec:decs){
			decMapper.removeThemeForeignKeyById(dec.getDid(),null);
		}
		//删除当前主题
		if(themeMapper.deleteById(thid)==null)
			throw new ServiceException(ExceptionMsgEnum.SERVER_DB_NOTEXIT);
	}
	
	@Override
	public Page listTheme(int pageNum, int pageSize) {
		int totleRecord = themeMapper.countList();
		Page page = null;
		if(pageNum==0)
			page = new Page(totleRecord,1);
		else
			page = new Page(totleRecord, pageNum,pageSize);
		List<Theme> list = themeMapper.list(page.getStartindex(),pageSize);
		page.setList(list);
		return page;
	}

	@Override
	public Page listThemeLikeByName(String key, int pageNum, int pageSize) {
		int totleRecord = themeMapper.countLikeByName(key);
		Page page = null;
		if(pageNum==0)
			page = new Page(totleRecord,1);
		else
			page = new Page(totleRecord, pageNum,pageSize);
		List<Theme> list = themeMapper.listLikeByName(key,page.getStartindex(),pageSize);
		page.setList(list);
		return page;
		
	}
//-----------------------------------------------Decoration Start-------------------------------------//
	@Override
	@Transactional
	public int saveDecoration(Decoration dec) {
		if(dec==null||dec.getAdmin()==null||dec.getAdmin().getAid()==0)
			throw new ServiceException(ExceptionMsgEnum.CLIENT_PARAM_ERROR_MSG);
		if(decMapper.save(dec)==null)
			throw new ServiceException(ExceptionMsgEnum.SERVER_ERROR_MSG);
		return dec.getDid();
	}

	@Override
	@Transactional(propagation=Propagation.REQUIRED)
	public void deleteDecoration(int did) {
		List<DecorationProduct> products = decProMapper.listDecorationProductByDecorationId(did, null, null);
		//移除全部外键
		if(products!=null&&products.size()!=0)
		for(DecorationProduct p:products){
			decProMapper.removeForeignKeyOnDecorationById(p.getDpid(),null);
		}
		//然后在删除
		if(decMapper.deleteById(did)==null)
			throw new ServiceException(ExceptionMsgEnum.SERVER_ERROR_MSG);
	}
	
	@Override
	public void updateDecoration(Decoration d) {
		if(decMapper.updateById(d)==null)
			throw new ServiceException(ExceptionMsgEnum.SERVER_ERROR_MSG);
	}


	@Override
	public Decoration getDecoration(int did) {
		if(did==0)
			throw new ServiceException(ExceptionMsgEnum.CLIENT_PARAM_ERROR_MSG);
		Decoration dec = decMapper.findById(did);
		if(dec==null)
			throw new ServiceException(ExceptionMsgEnum.SERVER_DB_NOTEXIT);
		return dec;
	}

	@Override
	public Page listDecorationByThemeId(int thid, int pageNum, int pageSize) {
		int totleRecord = decMapper.countListDecorationByThemeId(thid);
		Page page = null;
		if(pageNum==0)
			page = new Page(totleRecord,1);
		else
			page = new Page(totleRecord, pageNum,pageSize);
		List<Decoration> decs = decMapper.listDecorationByThemeId(thid,page.getStartindex(),pageSize);
		page.setList(decs);
		return page;
	}

	@Override
	public Page listDecoration(int pageNum, int pageSize,Integer adminId) {
		int totleRecord = decMapper.countList(adminId);
		Page page = null;
		if(pageNum==0)
			page = new Page(totleRecord,1);
		else
			page = new Page(totleRecord, pageNum,pageSize);
		List<Decoration> decs = decMapper.list(page.getStartindex(), pageSize,adminId);
		page.setList(decs);
		return page;
	}

	@Override
	public Page listDecorationLikeByTitle(int pageNum, int pageSize, String key,Integer adminId) {
		int totleRecord = decMapper.countListDecorationLikeByTitle(key,adminId);
		Page page = null;
		if(pageNum==0)
			page = new Page(totleRecord,1);
		else
			page = new Page(totleRecord, pageNum,pageSize);
		List<Decoration> decs = decMapper.listDecorationLikeByTitle(key,page.getStartindex(),pageSize,adminId);
		page.setList(decs);
		return page;
	}
	
	@Override
	public void decorationToTop(int did, int sequence){
		if(decMapper.updateSequenceById(did,sequence)==null)
			throw new ServiceException(ExceptionMsgEnum.SERVER_ERROR_MSG);
	}
//-----------------------------------------------DecorationProductCategory Start-------------------------------------//	

	@Override
	public void saveDecorationProductCategory(DecorationProductCategory decProCat) {
		if(decProCat==null)
			throw new ServiceException(ExceptionMsgEnum.CLIENT_PARAM_ERROR_MSG);
		if(decProCatMapper.save(decProCat)==null)
			throw new ServiceException(ExceptionMsgEnum.SERVER_ERROR_MSG);
	}

	@Override
	public void deleteDecorationProductCategory(int dcid) {
		if(dcid==0)
			throw new ServiceException(ExceptionMsgEnum.CLIENT_PARAM_ERROR_MSG);
		if(decProCatMapper.deleteById(dcid)==null)
			throw new ServiceException(ExceptionMsgEnum.SERVER_ERROR_MSG);
	}

	@Override
	public void update(DecorationProductCategory decProCat) {
		if(decProCat==null)
			throw new ServiceException(ExceptionMsgEnum.CLIENT_PARAM_ERROR_MSG);
		if(decProCatMapper.updateById(decProCat)==null)
			throw new ServiceException(ExceptionMsgEnum.SERVER_ERROR_MSG);
	}

	@Override
	public Page listDecorationProductCategory(int pageNum, int pageSize) {
		int totleRecord = decProCatMapper.countList();
		Page page = null;
		if(pageNum==0)
			page = new Page(totleRecord,1);
		else
			page = new Page(totleRecord, pageNum,pageSize);
		List<DecorationProductCategory> list = decProCatMapper.list(page.getStartindex(),pageSize);
		page.setList(list);
		return page;
	}

	@Override
	public DecorationProductCategory findDecorationProductCategoryById(int dcid) {
		if(dcid==0)
			throw new ServiceException(ExceptionMsgEnum.CLIENT_PARAM_ERROR_MSG);
		DecorationProductCategory dpc = decProCatMapper.findById(dcid);
		return dpc;
	}
//-----------------------------------------------DecorationProduct Start-------------------------------------//	
	@Override
	public int saveDecorationProduct(DecorationProduct dp) {
		if(dp==null)
			throw new ServiceException(ExceptionMsgEnum.CLIENT_PARAM_ERROR_MSG);
		if(decProMapper.save(dp)==null)
			throw new ServiceException(ExceptionMsgEnum.SERVER_ERROR_MSG);
		return dp.getDpid();
	}

	@Override
	public void deleteDecorationProduct(int dpid) {
		if(dpid==0)
			throw new ServiceException(ExceptionMsgEnum.CLIENT_PARAM_ERROR_MSG);
		if(decProMapper.deleteById(dpid)==null)
			throw new ServiceException(ExceptionMsgEnum.SERVER_DB_NOTEXIT);
	}

	@Override
	public DecorationProduct findDecorationProductById(int dpid) {
		if(dpid==0)
			throw new ServiceException(ExceptionMsgEnum.CLIENT_PARAM_ERROR_MSG);
		DecorationProduct decProduct = decProMapper.findById(dpid);
		if(decProduct==null)
			throw new ServiceException(ExceptionMsgEnum.SERVER_DB_NOTFOUND);
		return decProduct;
	}

	@Override
	public void updateDecorationProduct(DecorationProduct dp) {
		if(dp==null||dp.getDpid()==null)
			throw new ServiceException(ExceptionMsgEnum.CLIENT_PARAM_ERROR_MSG);
		if(decProMapper.updateById(dp)==null)
			throw new ServiceException(ExceptionMsgEnum.SERVER_ERROR_MSG);
	}

	@Override
	public Page listDecorationProductByDecorationId(int did, int pageNum, int pageSize) {
		int totleRecord = decProMapper.countListDecorationProductByDecorationId(did);
		Page page = null;
		if(pageNum==0)
			page = new Page(totleRecord,1);
		else
			page = new Page(totleRecord, pageNum,pageSize);
		List<DecorationProduct> list = decProMapper.listDecorationProductByDecorationId(did,page.getStartindex(),pageSize);
		page.setList(list);
		return page;
	}

	@Override
	public Page listDecorationProductByCategoryId(int dcid, int pageNum, int pageSize) {
		int totleRecord = decProMapper.countListDecorationProductByCategoryId(dcid);
		Page page = null;
		if(pageNum==0)
			page = new Page(totleRecord,1);
		else
			page = new Page(totleRecord, pageNum,pageSize);
		List<DecorationProduct> list = decProMapper.listDecorationProductByCategoryId(dcid,page.getStartindex(),pageSize);
		page.setList(list);
		return page;
	}

	@Override
	public Page listDecorationProductOrByLikeTitle(String key, int pageNum, int pageSize) {
		int totleRecord = decProMapper.countList(key);
		Page page = null;
		if(pageNum==0)
			page = new Page(totleRecord,1);
		else
			page = new Page(totleRecord, pageNum,pageSize);
		List<DecorationProduct> list = decProMapper.listDecorationProductOrByLikeTitle(key,page.getStartindex(),pageSize);
		page.setList(list);
		return page;
	}




}
