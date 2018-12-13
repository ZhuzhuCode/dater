package com.zjr.dater.scheduled.task;

import com.zjr.dater.business.domain.ScheduleOption;
import com.zjr.dater.business.model.Schedule;
import com.zjr.dater.business.model.User;
import com.zjr.dater.business.service.Impl.ScheduleServiceImpl;
import com.zjr.dater.common.utils.DateUtils;
import com.zjr.dater.common.utils.SessionUtils;
import com.zjr.dater.common.utils.SpringUtils;
import com.zjr.dater.websocket.WebSocketServer;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.List;

/**
 * Created by zhujr on 2018/10/16.
 * 定时推送任务 只要是在设定的一分钟内都会提醒
 */
@Component
public class ScheduleTask {
    private static Logger log = LoggerFactory.getLogger(ScheduleTask.class);
    private static final String scheduleTime = "*/10 * * * * ?";//每秒钟都执行一次
    private static ScheduleServiceImpl scheduleService = SpringUtils.getBean(ScheduleServiceImpl.class);


    @Scheduled(cron = scheduleTime)
    public static void scheduleTimer() throws IOException {
        //获取当前登录用户
        User user = new User();
        user.setId(Long.valueOf(4));
        log.info("获取当前登录用户：" + user.getName());
        if (user != null) {
            ScheduleOption scheduleOption = new ScheduleOption();
            scheduleOption.setUserId(user.getId());
            List<Schedule> schedules = scheduleService.findAllSchedule(scheduleOption);
            if (schedules != null && schedules.size() > 0) {
                for (int i = 0; i < schedules.size(); i++) {
                    Schedule s = schedules.get(i);
                    //1.先判断是否是今日任务
                    if (StringUtils.equals(DateUtils.getStringDateShort(), DateUtils.dateToStr(s.getContentstart()))) {
                        //2.获取当前时间,任务提醒只精确到分钟
                        double timeLag = (s.getContentstart().getTime()-DateUtils.getNow().getTime())/60000;
                        if(timeLag<=1&&timeLag>=-1){
                            WebSocketServer.sendInfo(s.getContent(),null);
                        }
                    }
                }
            }
        }
    }
}
