package d49.ssm.mapper;

import java.util.List;

import d49.ssm.po.ItemsCustom;
import d49.ssm.po.ItemsQueryVO;

public interface ItemsMapperCustom {
    
    //商品查询列表
    public List<ItemsCustom> findItemsList(ItemsQueryVO itemsQueryVO) throws Exception;
}


