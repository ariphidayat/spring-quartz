package org.arip.job.job;

import lombok.extern.slf4j.Slf4j;
import org.quartz.Job;
import org.quartz.JobDataMap;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

/**
 * Created by Arip Hidayat on 1/3/2018.
 */
@Slf4j
public class MessageJob implements Job {

    @Override
    public void execute(JobExecutionContext context) throws JobExecutionException {
        JobDataMap map = context.getMergedJobDataMap();
        runMessageJob(map);
    }

    private void runMessageJob(JobDataMap map) {
        String content = map.getString("content");
        log.info(content);
    }
}
