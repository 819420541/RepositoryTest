package d49.ssm.service;

import java.util.List;

import d49.ssm.po.ItemsCustom;
import d49.ssm.po.ItemsQueryVO;

public interface ItemsService {
    
    /**
     * 商品列表查询
     * @param itemsQueryVO
     * @return
     * @throws Exception
     */
    public List<ItemsCustom> findItemsList(ItemsQueryVO itemsQueryVO) throws Exception;
    
    /**
     * 根据"id"主键进行更新数据
     * @param id
     * @param itemsCustom
     * @throws Exception
     */
    public void updateItems(Integer id, ItemsCustom itemsCustom) throws Exception;
    
    /**
     * 根据"id"主键进行查询数据
     * @param id
     * @return
     * @throws Exception
     */
    public ItemsCustom findItemsById(Integer id) throws Exception;

    /**
     * 删除数据
     * @param ids
     * @throws Exception
     */
    public void deleteItems(Integer [] ids) throws Exception;
    
    /**
     * 删除数据
     * @param id
     * @throws Exception
     */
    public  void deleteItem(Integer id) throws Exception;
    
    /**
     * 增加数据
     * @param itemsCustom
     * @throws Exception
     */
    public void addItems(ItemsCustom itemsCustom) throws Exception;

}


