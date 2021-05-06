package investment.service;

import cn.hutool.core.collection.CollUtil;
import investment.pojo.Index;
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
import java.util.List;
import java.util.Map;

@Service
@CacheConfig(cacheNames="indexes")
public class IndexService {

    @Resource
    RestTemplate restTemplate;

    @CacheEvict(allEntries=true)
    public void remove(){
    }

    @Cacheable(key="'all_codes'")
    public List<Index> get(){
        return CollUtil.toList();
    }

    @CachePut(key = "'all_codes'",unless = "#result[0].code=='000000'")
    public List<Index> fresh() {
        return fetch_indexes_from_third_part();
    }

    public List<Index> fetch_indexes_from_third_part(){
        List<Map<String,String>> temp=
                restTemplate.exchange(
                        "http://127.0.0.1:8090/indexes/codes.json",
                        HttpMethod.GET, null,
                        new ParameterizedTypeReference<List<Map<String,String>>>() {}
                ).getBody();
        if(temp!=null){
            return map2Index(temp);
        }else
            return null;
    }

    private List<Index> map2Index(List<Map<String,String>> temp) {
        List<Index> indexes = new ArrayList<>();
        for (Map<String,String> map : temp) {
            String code = map.get("code");
            String name = map.get("name");
            Index index= new Index();
            index.setCode(code);
            index.setName(name);
            indexes.add(index);
        }
        return indexes;
    }

}