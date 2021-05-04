package investment.web;

import com.alibaba.csp.sentinel.annotation.SentinelResource;
import investment.pojo.IndexData;
import investment.service.IndexDataService;
import investment.service.handler.IndexDataServiceHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

@RestController
public class IndexDataController {
    @Resource
    IndexDataService indexDataService;

    @RequestMapping(value = "/freshIndexData/{code}", method = RequestMethod.GET)
    @SentinelResource(value = "freshIndexData", fallbackClass = IndexDataServiceHandler.class, fallback = "third_part_not_connected")
    public String fresh(@PathVariable("code") String code){
        indexDataService.fresh(code);
        return "fresh index data successfully";
    }

    @RequestMapping(value = "/getIndexData/{code}", method = RequestMethod.GET)
    public List<IndexData> get(@PathVariable("code") String code){
        return indexDataService.get(code);
    }

    @RequestMapping(value = "/removeIndexData/{code}", method = RequestMethod.GET)
    public String remove(@PathVariable("code") String code){
        indexDataService.remove(code);
        return "remove index data successfully";
    }
}