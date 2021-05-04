package investment.service;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.collection.CollectionUtil;
import cn.hutool.core.convert.Convert;
import investment.pojo.IndexData;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@CacheConfig(cacheNames="index_data_list")
public class IndexDataService {
    private final Map<String, List<IndexData>> indexDataList = new HashMap<>();

    @Resource
    RestTemplate restTemplate;

    @CachePut(key="'indexData-code-'+ #code",unless = "#result[0].closePoint==0")
    public List<IndexData> fresh(String code) {
        return fetch_indexes_from_third_part(code);
    }

    @CacheEvict(key="'indexData-code-'+ #code")
    public void remove(String code){
    }

    @Cacheable(key="'indexData-code-'+ #code")
    public List<IndexData> get(String code){
        return CollUtil.toList();
    }

    public List<IndexData> fetch_indexes_from_third_part(String code){
        List<Map<String,String>> temp=
                restTemplate.exchange(
                        "http://127.0.0.1:8090/indexes/"+code+".json",
                        HttpMethod.GET, null,
                        new ParameterizedTypeReference<List<Map<String,String>>>() {}
                ).getBody();
                restTemplate.getForObject("http://127.0.0.1:8090/indexes/"+code+".json",List.class);
        if(temp!=null){
            return map2IndexData(temp);
        }else
            return null;
    }

    private List<IndexData> map2IndexData(List<Map<String,String>> temp) {
        List<IndexData> indexDataList = new ArrayList<>();
        for (Map<String,String> map : temp) {
            String date = map.get("date");
            float closePoint = Convert.toFloat(map.get("closePoint"));
            IndexData indexData = new IndexData();

            indexData.setDate(date);
            indexData.setClosePoint(closePoint);
            indexDataList.add(indexData);
        }

        return indexDataList;
    }
}