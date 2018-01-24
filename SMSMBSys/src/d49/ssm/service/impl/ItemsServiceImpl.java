package d49.ssm.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import d49.ssm.exception.CustomException;
import d49.ssm.mapper.ItemsMapper;
import d49.ssm.mapper.ItemsMapperCustom;
import d49.ssm.po.Items;
import d49.ssm.po.ItemsCustom;
import d49.ssm.po.ItemsExample;
import d49.ssm.po.ItemsQueryVO;
import d49.ssm.service.ItemsService;

@Service("itemsService")
@Transactional
public class ItemsServiceImpl implements ItemsService {
    
    @Autowired
    private ItemsMapperCustom itemsMapperCustom;
    
    @Autowired
    private ItemsMapper itemsMapper;

    @Override
    public List<ItemsCustom> findItemsList(ItemsQueryVO itemsQueryVO) throws Exception {
        return itemsMapperCustom.findItemsList(itemsQueryVO);
    }


	@Override
	public void updateItems(Integer id, ItemsCustom itemsCustom) throws Exception {
		// 需指定"id"，否在若"id"为空那么将会报错
		itemsCustom.setId(id);
		itemsMapper.updateByPrimaryKeyWithBLOBs(itemsCustom);
	}


	@Override
	public ItemsCustom findItemsById(Integer id) throws Exception {
		Items items = itemsMapper.selectByPrimaryKey(id);
		
		if(items == null) {
			throw new CustomException("修改的商品信息不存在...");
		}
		ItemsCustom itemsCustom = new ItemsCustom();
		//Spring 的方式实现属性的拷贝
		BeanUtils.copyProperties(items, itemsCustom);
		return itemsCustom;
	}


	@Override
	public void deleteItems(Integer[] ids) throws Exception {
		List<Integer> list =new ArrayList<>();
		
		for (Integer id : ids) {
		    list.add(id);
		}
		
		ItemsExample itemsExample =new ItemsExample();
		itemsExample.createCriteria().andIdIn(list);
		
		itemsMapper.deleteByExample(itemsExample);
	}



	@Override
	public void deleteItem(Integer id) throws Exception {
		itemsMapper.deleteByPrimaryKey(id);
	}
    
	


	@Override
	public void addItems(ItemsCustom itemsCustom) throws Exception {
		itemsMapper.insert(itemsCustom);
	}
}


