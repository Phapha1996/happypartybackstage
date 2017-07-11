package com.party.product.controller;



import java.io.File;
import java.net.URL;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.party.dto.ImageDto;
import com.party.dto.Response;
import com.party.product.service.ImgService;
import com.party.tool.UploadUtils;
/**
 * 
 * @author Caizhf
 * @date 2017年6月20日下午2:46:14
 * @version v.0.1
 * @email 1115054416@qq.com 
 *
 * <p>Description: 图片控制器</p>
 *
 */
@RestController
@RequestMapping("/image")
public class ImgController {
	private static final Logger logger = LoggerFactory.getLogger(ImgController.class);
	
	@Autowired
	private ImgService imgService;
	
	@Value("${image.path}")
	private String imagePath;
	
	@Value("${image.tomcat.uri}")
	private String imageUri;
	/*private final ResourceLoader resourceLoader;  
	
	@Autowired
	public ImgController(ResourceLoader resourceLoader) {  
        this.resourceLoader = resourceLoader;  
    }  */
	
	/**
	 * 
	 * @param image 图片文件
	 * @param productType 产品类型
	 * @param productId 产品id
	 * @return
	 * @throws Exception
	 */
	//详情上传请参考127.0.0.1:8090/upload    上半部分的表单
	@RequestMapping("/add")
	@Transactional
	public Response save(@RequestParam("image")MultipartFile image,@RequestParam("productType")String productType,
			@RequestParam("productId")int productId,HttpServletRequest req) throws Exception{
		if(image.isEmpty())
			return Response.failure("图片不能为空");
		//上传
		ImageDto imd = UploadUtils.MvcUpload(image, imagePath);
		//得到硬盘路径
		String diskPath = imd.getImageServerPath();
		String reqUrl = req.getRequestURL().toString();
		String reqUri = req.getRequestURI();
		//得到显示路径
		String showUrl = reqUrl.replaceAll(reqUri, imageUri+imd.getImageName());
		
		
		logger.info("图片："+image.getName()+"<--存储路径为-->"+diskPath);
		logger.info("图片："+image.getName()+"<--显示的存储路径为-->"+showUrl);
		imgService.save(productType, productId, showUrl,diskPath);
		return Response.success();
	}
	
/*	*//**
	 * 功能：普通上传，不存储入数据库
	 * @param image
	 * @return
	 * @throws Exception
	 *//*
	//127.0.0.1:8090/simple/upload
	@RequestMapping("/simple/upload")
	public String upload(@RequestParam("file")MultipartFile image,HttpServletRequest request) throws Exception{
		if(image.isEmpty())
			return "{\"code\": 1 ,\"msg\": \"上传失败请重试\" ,\"data\": {\"src\":\"\"}}";
//			return Response.success("图片不能为空");
		String serverPath = UploadUtils.MvcUpload(image, simpleImageSavePath).replace("src/main/resources/static", "");
		//return Response.success(new Img(serverPath));
		return "{\"code\": 0 ,\"msg\": \"success\" ,\"data\": {\"src\":\"http://localhost:8090/admin"+serverPath+"\"}}";
	}*/
	
	
	/**
	 * 功能：普通上传，不存储入数据库
	 * @param image
	 * @return
	 * @throws Exception
	 */
	//127.0.0.1:8090/simple/upload
	@RequestMapping("/simple/upload")
	public  String upload(@RequestParam("file")MultipartFile image,HttpServletRequest req) throws Exception{
		if(image.isEmpty())
			return "{\"code\": 1 ,\"msg\": \"上传失败请重试\" ,\"data\": {\"src\":\"\"}}";
		ImageDto imd = UploadUtils.MvcUpload(image, imagePath);
		String reqUrl = req.getRequestURL().toString();
		String reqUri = req.getRequestURI();
		String showUrl = reqUrl.replaceAll(reqUri, imageUri+imd.getImageName());
		return "{\"code\": 0 ,\"msg\": \"success\" ,\"data\": {\"src\":\""+showUrl+"\"}}";
	}
	
	
	
	/**
	 * 功能：删除图片
	 * 注意：删除图片路径的同时要把本地上的图片删除，然后删除数据库中的记录
	 * @param iid
	 * @return
	 */
	//127.0.0.1/image/delete/*
	@RequestMapping("/delete/{iid}")
	public Response delete(@PathVariable int iid){
		imgService.delete(iid);
		return Response.success();
	}
	
	//127.0.0.1/image/get/*
	@RequestMapping("/get/{iid}")
	public Response get(@PathVariable int iid){
		return Response.success(imgService.get(iid));	
	}
	
	/**
	 * 功能：更新图片
	 * 注意：保存图片的同时需要把原来的图片删除，并且在数据库中存入新的图片地址
	 * @param image
	 * @param iid
	 * @return
	 * @throws Exception 
	 */
	//详情上传请参考127.0.0.1:8090/upload    下半部分的表单
	@RequestMapping("/update")
	@Transactional
	public Response update(@RequestParam("image")MultipartFile image,@RequestParam("iid") int iid,HttpServletRequest req) throws Exception{
		if(image.isEmpty())
			return Response.failure("图片不能为空");
		ImageDto imd= UploadUtils.MvcUpload(image, imagePath);
		logger.info("图片："+image.getName()+"<--存储路径为-->"+imd.getImageServerPath());
		String reqUrl = req.getRequestURL().toString();
		String reqUri = req.getRequestURI();
		String showUrl = reqUrl.replaceAll(reqUri, imageUri+imd.getImageName());
		imgService.update(iid,showUrl,imd.getImageServerPath());
		return Response.success();
	}
	
	
	
	
	
	
	/*//显示图片的方法关键 匹配路径像 localhost:8080/b7c76eb3-5a67-4d41-ae5c-1642af3f8746.png  
    @RequestMapping(method = RequestMethod.GET, value = "/product/{filename:.+}")  
    @ResponseBody  
    public ResponseEntity<?> getFile(@PathVariable String filename) {  
        try {  
        	logger.info(resourceLoader.getResource("file:" + Paths.get(filePath, filename).toString())+"");
            return ResponseEntity.ok(resourceLoader.getResource("file:" + Paths.get(filePath, filename).toString()));  
        } catch (Exception e) {  
            return ResponseEntity.notFound().build();  
        }  
    }  */
	
	
/*	@RequestMapping("/test")
	public Response testUrl(HttpServletRequest request){
		//类加载根路径
		String classPath = this.getClass().getResource("/").getPath();
		
		StringBuffer uri = request.getRequestURL();
		
		StringBuffer url = request.getRequestURL();
		
		String s = request.getServletContext().getContextPath();
		//类加载根路径
		URL xmlPath = this.getClass().getClassLoader().getResource("");

		//类所在工程根路径
		String proClassPath = this.getClass().getResource("").getPath();

		//项目服务器脚本文件路径
		String proPath = System.getProperty("user.dir");

		// 获取所有的类路径 包括jar包的路径
		String allClassPath = System.getProperty("java.class.path");

		//项目部署的路径
		String path = request.getSession().getServletContext().getRealPath("/");
		return Response.success();
	}*/
	
}
