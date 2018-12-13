package com.zjr.dater.business.service;

import com.zjr.dater.business.domain.ScheduleOption;
import com.zjr.dater.business.model.Schedule;

import java.util.List;

/**
 * Created by zhujr on 2018/9/21.
 */
public interface ScheduleService {
    public List<Schedule> queryScheduleList(ScheduleOption scheduleOption, int pageNum, int pageSize);

    public int selectCount();

    public List<Schedule> findAllSchedule(ScheduleOption scheduleOption);

    public Schedule getScheduleById(long id);

    public int addSchedule(Schedule schedule);

    public int updateSchedule(Schedule schedule);

    public int deleteById(long id);

}
