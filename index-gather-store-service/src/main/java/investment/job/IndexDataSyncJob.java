package investment.job;

import cn.hutool.core.date.DateUtil;
import investment.pojo.Index;
import investment.service.IndexDataService;
import investment.service.IndexService;
import org.jetbrains.annotations.NotNull;
import org.quartz.JobExecutionContext;
import org.springframework.scheduling.quartz.QuartzJobBean;

import javax.annotation.Resource;
import java.util.List;

public class IndexDataSyncJob extends QuartzJobBean {

    @Resource
    private IndexService indexService;

    @Resource
    private IndexDataService indexDataService;

    @Override
    protected void executeInternal(@NotNull JobExecutionContext context){
        System.out.println("定时启动：" + DateUtil.now());
        List<Index> indexes = indexService.fresh();
        for (Index index : indexes) {
            indexDataService.fresh(index.getCode());
        }
        System.out.println("定时结束：" + DateUtil.now());
    }
}