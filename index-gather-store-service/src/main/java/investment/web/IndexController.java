package investment.web;

import investment.pojo.Index;
import investment.service.IndexService;
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
    public List<Index> get(){
        return indexService.fetch_indexes_from_third_part();
    }
}