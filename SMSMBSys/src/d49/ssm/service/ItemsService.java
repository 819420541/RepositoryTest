package d49.ssm.service;

import java.util.List;

import d49.ssm.po.ItemsCustom;
import d49.ssm.po.ItemsQueryVO;

public interface ItemsService {
    
    /**
     * ��Ʒ�б��ѯ
     * @param itemsQueryVO
     * @return
     * @throws Exception
     */
    public List<ItemsCustom> findItemsList(ItemsQueryVO itemsQueryVO) throws Exception;
    
    /**
     * ����"id"�������и�������
     * @param id
     * @param itemsCustom
     * @throws Exception
     */
    public void updateItems(Integer id, ItemsCustom itemsCustom) throws Exception;
    
    /**
     * ����"id"�������в�ѯ����
     * @param id
     * @return
     * @throws Exception
     */
    public ItemsCustom findItemsById(Integer id) throws Exception;

    /**
     * ɾ������
     * @param ids
     * @throws Exception
     */
    public void deleteItems(Integer [] ids) throws Exception;
    
    /**
     * ɾ������
     * @param id
     * @throws Exception
     */
    public  void deleteItem(Integer id) throws Exception;
    
    /**
     * ��������
     * @param itemsCustom
     * @throws Exception
     */
    public void addItems(ItemsCustom itemsCustom) throws Exception;

}


