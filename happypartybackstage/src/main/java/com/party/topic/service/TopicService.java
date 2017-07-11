package com.party.topic.service;

import java.util.List;

import com.party.domain.Reply;
import com.party.domain.Topic;
import com.party.dto.Page;
/**
 * 
 * @author Caizhf
 * @date 2017年6月10日下午9:59:03
 * @version v.0.1
 * @email 1115054416@qq.com 
 *
 * <p>Description: 帖子业务层</p>
 *
 */
public interface TopicService {

	
	int saveTopic(Topic t);
	/**
	 * 得到所有的帖子列表，分页显示
	 * @param pageNum 
	 * @return
	 */
	Page listTopic(int pageNum);
	
	/**
	 * 获取单个帖子的详情
	 * @param tid
	 * @return
	 */
	Topic getTopic(int tid);
	
	/**
	 * 根据id删帖
	 * @param tid
	 */
	void deleteTopic(int tid);
	
	/**
	 * 查看某个帖子下所有的回复
	 * @return
	 */
	Page listReplyByTopicId(int tid,int pageNum);
	
	/**
	 * 帖子所有记录的总数
	 * @return
	 */
	int countListTopic();

	/**
	 * 查看回复的详情
	 * @param rid
	 * @return
	 */
	Reply getReply(int rid);
	
	/**
	 * 置顶功能
	 * @param tid
	 * @param sequence
	 */
	void updateSequenceById(int tid, int sequence);

	/**
	 * 根据标题模糊查询
	 * @param key
	 * @return
	 */
	Page listLikeByTitle(String key,int pageNum,int pageSize);

	void deleteReply(int rid);

}
