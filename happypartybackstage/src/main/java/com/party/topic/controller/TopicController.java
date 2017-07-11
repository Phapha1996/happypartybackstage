package com.party.topic.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.party.domain.Admin;
import com.party.domain.Topic;
import com.party.dto.Response;
import com.party.topic.service.TopicService;
/**
 * 
 * @author Caizhf
 * @date 2017年6月10日下午10:19:20
 * @version v.0.1
 * @email 1115054416@qq.com 
 *
 * <p>Description: 帖子与回复的控制器</p>
 *
 */
@RestController
@RequestMapping("/topic")
public class TopicController {
	
	@Autowired
	private TopicService topicService;
	
	//127.0.0.1:8080/topic/add?title=*&content=*
	@RequestMapping("/add")
	public Response saveTopic(Topic topic){
		Admin admin = (Admin) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		topic.setAdmin(admin);
		return Response.success(topicService.saveTopic(topic));
	}
	
	
	/**
	 * 获取帖子列表
	 * @return
	 */
	//访问路径：127.0.0.1:8090/topic/list
	@RequestMapping("/list/{pageNum}")
	public Response listTopic(@PathVariable int pageNum){
		return Response.success(topicService.listTopic(pageNum));
	}
	
	/**
	 * 获取单个帖子的信息
	 * @param tid
	 * @return
	 */
	//访问路径：127.0.0.1:8090/topic/get/*
	@RequestMapping("/get/{tid}")
	public Response getTopic(@PathVariable int tid){
		return Response.success(topicService.getTopic(tid));
	}
	
	/**
	 * 删除帖子
	 * @return
	 */
	//访问路径：127.0.0.1:8090/topic/delete/*
	@RequestMapping("/delete/{tid}")
	public Response deleteTopic(@PathVariable int tid){
		topicService.deleteTopic(tid);
		return Response.success();
	}
	
	/**
	 * 根据帖子id查所有的回复
	 * @param tid
	 * @return
	 */
	//访问路径：127.0.0.1::8090/topic/reply/list/*?pageNum=*
	@RequestMapping("/reply/list/{tid}")
	public Response listReplyByTopicId(@PathVariable int tid,@RequestParam("pageNum")int pageNum){
		return Response.success(topicService.listReplyByTopicId(tid,pageNum));
	}
	
	/**
	 * 获取回复详情
	 * @param rid
	 * @return
	 */
	//访问路径：127.0.0.1:8090/topic/reply/get/*
	@RequestMapping("/reply/get/{rid}")
	public Response getReply(@PathVariable int rid){
		return Response.success(topicService.getReply(rid));
	}
	
	
	/**
	 * 置顶功能
	 * @param tid	帖子id号码
	 * @param sequence 置顶级别
	 * @return
	 */
	//127.0.0.1:8090/topic/top/*?sequence=?
	@RequestMapping("/top/{tid}")
	public Response toTop(@PathVariable int tid, @RequestParam("sequence")int sequence){
		topicService.updateSequenceById(tid,sequence);
		return Response.success();
	}
	
	/**
	 * 根据title模糊查询
	 * @param key
	 * @return
	 */
	//127.0.0.1:8090/topic/like/*?key=*&pageSize=*
	@RequestMapping("/like/{pageNum}")
	public Response findLikeByTitle(@RequestParam("key")String key,
			@PathVariable int pageNum,@RequestParam("pageSize") int pageSize){
		return Response.success(topicService.listLikeByTitle(key,pageNum,pageSize));
	}
	
	@RequestMapping("/delete/reply/{rid}")
	public Response deleteReply(@PathVariable int rid){
		topicService.deleteReply(rid);
		return Response.success();
	}
	
}
