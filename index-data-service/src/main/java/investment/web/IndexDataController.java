package investment.web;

import investment.config.IpConfiguration;
import investment.pojo.IndexData;
import investment.service.IndexDataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

@RestController
public class IndexDataController {
    @Resource
    IndexDataService indexDataService;
    @Resource
    IpConfiguration ipConfiguration;

    @RequestMapping(value = "/data/{code}", method = RequestMethod.GET)
    public List<IndexData> get(@PathVariable("code") String code){
        return indexDataService.get(code);
    }
}