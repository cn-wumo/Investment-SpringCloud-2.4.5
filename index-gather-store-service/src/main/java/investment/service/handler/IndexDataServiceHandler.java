package investment.service.handler;

import cn.hutool.core.collection.CollectionUtil;
import investment.pojo.Index;
import investment.pojo.IndexData;

import java.util.List;

public class IndexDataServiceHandler {
    public List<IndexData> third_part_not_connected(String code){
        System.out.println("third_part_not_connected()");
        IndexData index= new IndexData();
        index.setClosePoint(0);
        index.setDate("n/a");
        return CollectionUtil.toList(index);
    }
}
