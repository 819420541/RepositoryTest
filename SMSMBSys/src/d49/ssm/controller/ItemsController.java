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
	 * ��Ʒ����  
	 * ͨ��@ModelAttribute������ҳ���л�ȡ�������
	 */
	@ModelAttribute("itemsType")
		public Map<String, String> getItemsType(){
		Map<String, String> itemsType=new HashMap<>();
		itemsType.put("101", "������");
		itemsType.put("102", "ĸӤ��");
		itemsType.put("103", "�ֻ���");
		return itemsType;
	}
    
	/**
	 * �޸���Ʒ
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
            throw new CustomException("�޸ĵ���Ʒ��Ϣ������....");
        }
        
    	model.addAttribute("items", itemsCustom);
    	return "items/editItems";
    }
    
    /**
     * �޸���Ʒ
     * @param request
     * @param model
     * @param id
     * @param itemsCustom
     * @param bindingResult
     * @param pic
     * @return "forward:queryItems.action" ҳ��ת��
     * @throws Exception
     */
    @RequestMapping("/editItemsSubmit")
//  public String editItemsSubmit(Model model,Integer id, @ModelAttribute("items")@Validated(value= {ValidGroupUpdate.class}) ItemsCustom itemsCustom,BindingResult bindingResult) throws Exception{
    public String editItemsSubmit(HttpServletRequest request, Model model,Integer id, 
    		@Validated(value= { ValidGroupUpdate.class }) ItemsCustom itemsCustom, BindingResult bindingResult, 
    		MultipartFile pic) throws Exception{
    	//��ȡУ�������Ϣ
		if(bindingResult.hasErrors()) {
			//���������Ϣ
			List<ObjectError> allErrors=bindingResult.getAllErrors();
			//��������Ϣ���ݵ�ҳ��
			model.addAttribute("allErrors", allErrors);
			
			//ͨ��model�����ٴδ�������
			model.addAttribute("items", itemsCustom);
			
			//ת������Ʒ�޸�ҳ��
			return "items/editItems";
		}
    	
		/*
		 * �ϴ�ͼƬ��Ϣ
		 * orininalFilename	�ļ���
		 * pic_path			�洢·����Ϣ
		 * newFileName		������ɵ��ļ���
		 * newFile			�ļ��洢��λ��
		 */
		String orininalFilename=pic.getOriginalFilename(); 
		if(pic!=null && orininalFilename!=null && orininalFilename.length()>0) {

			String pic_path=request.getSession().getServletContext().getRealPath("/uploadfile/");
			String newFileName=UUID.randomUUID().toString()+"_"+orininalFilename;
			File newFile=new File(pic_path+newFileName);
			//ֱ�Ӱ�����д��
			pic.transferTo(newFile);
			itemsCustom.setPic(newFileName);
		}
        
    	itemsService.updateItems(id, itemsCustom);
    	//ҳ��ת��
        return "forward:queryItems.action";
    	
//		//�ض�����Ʒ�б�ҳ�桢
//		return "redirect:queryItems.action";
//		//ת����jspҳ��
//		return "success";
    }

    /**
     * ��Ʒ�Ĳ�ѯ
     * @return ModelAndView
     * @throws Exception
     */
    @RequestMapping("/queryItems.action")
    public ModelAndView queryItems(HttpServletRequest request,ItemsQueryVO itemsQueryVO) throws Exception{
		
		System.out.println("��������ת��  ���ض�����Ƿ���request"+request.getParameter("id"));
		
		List<ItemsCustom> itemsList =itemsService.findItemsList(itemsQueryVO);
		ModelAndView modelAndView =new ModelAndView();
		modelAndView.addObject("itemsList",itemsList);
		modelAndView.setViewName("items/itemsList");
		return modelAndView;
    }
    
	/**
	 * ����ɾ��
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
     * ���
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


