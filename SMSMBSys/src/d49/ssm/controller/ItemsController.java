package d49.ssm.controller;

import java.io.File;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import d49.ssm.exception.CustomException;
import d49.ssm.po.ItemsCustom;
import d49.ssm.po.ItemsQueryVO;
import d49.ssm.service.ItemsService;
import d49.ssm.validation.ValidGroupAdd;
import d49.ssm.validation.ValidGroupUpdate;


@Controller
@RequestMapping("/items")
public class ItemsController {

	@Autowired
    @Qualifier("itemsService")
    private ItemsService itemsService;
    
	/**
	 * 商品分类  
	 * 通过@ModelAttribute可以在页面中获取这个数据
	 */
	@ModelAttribute("itemsType")
		public Map<String, String> getItemsType(){
		Map<String, String> itemsType=new HashMap<>();
		itemsType.put("101", "数码类");
		itemsType.put("102", "母婴类");
		itemsType.put("103", "手机类");
		return itemsType;
	}
    
	/**
	 * 修改商品
	 * @param model
	 * @param items_id
	 * @return
	 * @throws Exception
	 */
    @RequestMapping(value="/editItems",method= { RequestMethod.POST,RequestMethod.GET })   
    public String editItems(Model model,
    		@RequestParam(value="id",required=true)Integer items_id
    		) throws Exception{
    	ItemsCustom itemsCustom = itemsService.findItemsById(items_id);
    	
    	if(itemsCustom==null) {
            throw new CustomException("修改的商品信息不存在....");
        }
        
    	model.addAttribute("items", itemsCustom);
    	return "items/editItems";
    }
    
    /**
     * 修改商品
     * @param request
     * @param model
     * @param id
     * @param itemsCustom
     * @param bindingResult
     * @param pic
     * @return "forward:queryItems.action" 页面转发
     * @throws Exception
     */
    @RequestMapping("/editItemsSubmit")
//  public String editItemsSubmit(Model model,Integer id, @ModelAttribute("items")@Validated(value= {ValidGroupUpdate.class}) ItemsCustom itemsCustom,BindingResult bindingResult) throws Exception{
    public String editItemsSubmit(HttpServletRequest request, Model model,Integer id, 
    		@Validated(value= { ValidGroupUpdate.class }) ItemsCustom itemsCustom, BindingResult bindingResult, 
    		MultipartFile pic) throws Exception{
    	//获取校验错误信息
		if(bindingResult.hasErrors()) {
			//输出错误信息
			List<ObjectError> allErrors=bindingResult.getAllErrors();
			//将错误信息传递到页面
			model.addAttribute("allErrors", allErrors);
			
			//通过model对象再次传递数据
			model.addAttribute("items", itemsCustom);
			
			//转发到商品修改页面
			return "items/editItems";
		}
    	
		/*
		 * 上传图片信息
		 * orininalFilename	文件名
		 * pic_path			存储路径信息
		 * newFileName		随机生成的文件名
		 * newFile			文件存储的位置
		 */
		String orininalFilename=pic.getOriginalFilename(); 
		if(pic!=null && orininalFilename!=null && orininalFilename.length()>0) {

			String pic_path=request.getSession().getServletContext().getRealPath("/uploadfile/");
			String newFileName=UUID.randomUUID().toString()+"_"+orininalFilename;
			File newFile=new File(pic_path+newFileName);
			//直接把数据写出
			pic.transferTo(newFile);
			itemsCustom.setPic(newFileName);
		}
        
    	itemsService.updateItems(id, itemsCustom);
    	//页面转发
        return "forward:queryItems.action";
    	
//		//重定向到商品列表页面、
//		return "redirect:queryItems.action";
//		//转发到jsp页面
//		return "success";
    }

    /**
     * 商品的查询
     * @return ModelAndView
     * @throws Exception
     */
    @RequestMapping("/queryItems.action")
    public ModelAndView queryItems(HttpServletRequest request,ItemsQueryVO itemsQueryVO) throws Exception{
		
		System.out.println("测试请求转发  和重定向后是否共享request"+request.getParameter("id"));
		
		List<ItemsCustom> itemsList =itemsService.findItemsList(itemsQueryVO);
		ModelAndView modelAndView =new ModelAndView();
		modelAndView.addObject("itemsList",itemsList);
		modelAndView.setViewName("items/itemsList");
		return modelAndView;
    }
    
	/**
	 * 批量删除
	 * @param items_id
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/deleteItems")
	public String deleteItems( Integer [] items_id) throws Exception{
	    
		itemsService.deleteItems(items_id);
	    return "forward:queryItems.action";
	}
    
	/**
	 * 
	 * @param id
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/deleteItem")
	public String delItems( Integer id) throws Exception{
		itemsService.deleteItem(id);
		
		return "forward:queryItems.action";
	    
	}
    /**
     * 添加
     * @param itemsCustom
     * @param bindingResult
     * @return
     * @throws Exception
     */
    @RequestMapping("/addItems")
	public String  addItems(
			@Validated(value= {ValidGroupAdd.class}) ItemsCustom itemsCustom,
			BindingResult bindingResult) throws Exception{
    	return "forward:queryItems.action";
	}
    
    @RequestMapping("/addItemsSubmit")
    public String addItemsSubmit(ItemsCustom itemsCustom) throws Exception{      
      itemsService.addItems(itemsCustom);
      return "forward:queryItems.action";
  }
    
}


