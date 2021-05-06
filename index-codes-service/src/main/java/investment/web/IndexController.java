package investment.web;

import investment.config.IpConfiguration;
import investment.pojo.Index;
import investment.service.IndexService;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

@RestController
public class IndexController {
    @Resource
    IndexService indexService;
    @Resource
    IpConfiguration ipConfiguration;

    @RequestMapping(value = "/codes", method = RequestMethod.GET)
    @CrossOrigin
    public List<Index> codes(){
        return indexService.get();
    }
}