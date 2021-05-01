package investment.service.handler;

import cn.hutool.core.collection.CollectionUtil;
import investment.pojo.Index;

import java.util.List;

public class IndexServiceHandler {
    public static List<Index> third_part_not_connected(){
        Index index= new Index();
        index.setCode("000000");
        index.setName("无效指数代码");
        return CollectionUtil.toList(index);
    }
}
