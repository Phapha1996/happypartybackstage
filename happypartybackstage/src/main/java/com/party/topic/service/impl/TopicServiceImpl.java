package com.party.topic.service.impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import com.party.domain.Reply;
import com.party.domain.Topic;
import com.party.dto.Page;
import com.party.exception.ExceptionMsgEnum;
import com.party.exception.ServiceException;
import com.party.topic.mapper.ReplyMapper;
import com.party.topic.mapper.TopicMapper;
import com.party.topic.service.TopicService;
/**
 * 
 * @author Caizhf
 * @date 2017年6月10日下午10:20:38
 * @version v.0.1
 * @email 1115054416@qq.com 
 *
 * <p>Description: 帖子、回复接口业务实现类</p>
 *
 */
@Service
public class TopicServiceImpl implements TopicService{
	@Autowired
	private TopicMapper topicMapper;
	@Autowired
	private ReplyMapper replyMapper;
	private static final int PAGESIZE = 10;
	
	@Override
	@Transactional(propagation=Propagation.REQUIRED)
	public int saveTopic(Topic t) {
		t.setClickLike("");
		t.setcTime(new Date());
		if(StringUtils.isEmpty(t.getContent())||StringUtils.isEmpty(t.getTitle()))
			throw new ServiceException(ExceptionMsgEnum.CLIENT_PARAM_ERROR_MSG);
		if(topicMapper.save(t)==null||t.getTid()==null)
			throw new ServiceException(ExceptionMsgEnum.SERVER_ERROR_MSG);
		return t.getTid();
	}
	
	//如果没有数据了，就把空值传回去，前端看到null就显示没有更多数据了
	@Override
	public Page listTopic(int pageNum) {
		int totleRecord = countListTopic();
		Page page = null;
		if(pageNum==0)
			page = new Page(totleRecord,1);
		else
			//如果不为空，那么就按照想看的页码进行
			page = new Page(totleRecord,pageNum);
		List<Topic> topics = topicMapper.list(page.getStartindex(),PAGESIZE);
		page.setList(topics);
		return page;
	}

	
	@Override
	public Topic getTopic(int tid) {
		if(tid==0)
			throw new ServiceException(ExceptionMsgEnum.CLIENT_PARAM_ERROR_MSG);
		Topic t = topicMapper.findById(tid);
		if(t==null)
			throw new ServiceException(ExceptionMsgEnum.SERVER_DB_NOTEXIT);
		return t;
	}

	
	@Override
	@Transactional
	public void deleteTopic(int tid) {
		if(tid==0)
			throw new ServiceException(ExceptionMsgEnum.CLIENT_PARAM_ERROR_MSG);
		if(topicMapper.deleteById(tid) == null)
			throw new ServiceException(ExceptionMsgEnum.SERVER_ERROR_MSG);
	}

	//如果没有数据了，就把空值传回去，前端看到null就显示没有更多数据了
	@Override
	public Page listReplyByTopicId(int tid,int pageNum) {
		int totleRecord = replyMapper.countListByTopicId(tid);
		Page page = null;
		if(pageNum==0)
			page = new Page(totleRecord,1);
		else
			page = new Page(totleRecord,pageNum);
		List<Reply> replies = replyMapper.listByTopicId(tid,page.getStartindex(),page.getPAGESIZE());
		page.setList(replies);
		return page;
	}

	
	@Override
	public int countListTopic() {
		int totleRecord = topicMapper.countList();
		return totleRecord;
	}


	@Override
	public Reply getReply(int rid) {
		if(rid==0)
			throw new ServiceException(ExceptionMsgEnum.CLIENT_PARAM_ERROR_MSG);
		Reply r = replyMapper.findById(rid);
		if(r==null)
			throw new ServiceException(ExceptionMsgEnum.SERVER_ERROR_MSG);
		return r;
	}

	//置顶功能
	@Override
	@Transactional
	public void updateSequenceById(int tid, int sequence) {
		if(tid==0||sequence==0)
			throw new ServiceException(ExceptionMsgEnum.CLIENT_PARAM_ERROR_MSG);
		if(topicMapper.updateSequenceById(tid,sequence)==null)
			throw new ServiceException(ExceptionMsgEnum.SERVER_ERROR_MSG);
	}


	@Override
	public Page listLikeByTitle(String key,int pageNum,int pageSize) {
		int totleRecord = topicMapper.countLikeByTitle(key);
		Page page = null;
		if(pageNum==0)
			page = new Page(totleRecord,1);
		else
			page = new Page(totleRecord, pageNum,pageSize);
		List<Topic> topics = topicMapper.listLikeByTitle(key,page.getStartindex(), pageSize);
		page.setList(topics);
		return page;
	}


	@Override
	public void deleteReply(int rid) {
		if(replyMapper.deleteById(rid)==null)
			throw new ServiceException(ExceptionMsgEnum.SERVER_ERROR_MSG);
	}
	
}
