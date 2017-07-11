package com.party.topic.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.party.domain.Topic;
/**
 * 
 * @author Caizhf
 * @date 2017年6月10日下午10:32:35
 * @version v.0.1
 * @email 1115054416@qq.com 
 *
 * <p>Description: 发帖数据访问接口</p>
 *
 */
public interface TopicMapper {

	
	Integer save(Topic t);
	/**
	 * 统计帖子的记录数
	 * @return
	 */
	Integer countList();
	
	/**
	 * 分页拿出所有的帖子
	 * @param startindex
	 * @param pagesize
	 * @return
	 */
	List<Topic> list(@Param("startIndex")int startIndex,@Param("pageSize")int pageSize);
	
	/**
	 * 通过id查询帖子
	 * @param tid
	 * @return
	 */
	Topic findById(int tid);

	/**
	 * 通过id删帖
	 * @param tid
	 * @return
	 */
	Integer deleteById(int tid);

	/**
	 * 置顶
	 * @param tid
	 * @param sequence
	 * @return
	 */
	Integer updateSequenceById(@Param("tid")int tid, @Param("sequence")int sequence);

	/**
	 * 模糊查询，根据title
	 * @param key
	 * @return
	 */
	List<Topic> listLikeByTitle(@Param("key")String key, 
			@Param("startIndex")int startIndex, @Param("pageSize")int pageSize);
	
	Integer countLikeByTitle(String key);

}
