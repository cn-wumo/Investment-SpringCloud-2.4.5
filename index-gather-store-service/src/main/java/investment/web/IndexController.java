package investment.web;

import com.alibaba.csp.sentinel.annotation.SentinelResource;
import investment.pojo.Index;
import investment.service.IndexService;
import investment.service.handler.IndexServiceHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

@RestController
public class IndexController {
    @Resource
    IndexService indexService;

    @RequestMapping(value = "/getCodes", method = RequestMethod.GET)
    @SentinelResource(value = "getCodes", fallback="third_part_not_connected", fallbackClass = IndexServiceHandler.class)
    public List<Index> get(){
        return indexService.get();
    }

    @RequestMapping(value = "/freshCodes", method = RequestMethod.GET)
    @SentinelResource(value = "freshCodes", fallback="third_part_not_connected", fallbackClass = IndexServiceHandler.class)
    public List<Index> fresh(){
        return indexService.fresh();
    }
    @RequestMapping(value = "/removeCodes", method = RequestMethod.GET)
    public String remove(){
        indexService.remove();
        return "remove codes successfully";
    }


}