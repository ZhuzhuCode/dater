package com.zjr.dater.business.service.Impl;

import com.zjr.dater.business.dao.ScheduleMapper;
import com.zjr.dater.business.domain.ScheduleOption;
import com.zjr.dater.business.model.Schedule;
import com.zjr.dater.business.model.User;
import com.zjr.dater.business.service.ScheduleService;
import com.zjr.dater.common.utils.SessionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

/**
 * Created by zhujr on 2018/9/21.
 */
@Service
@Transactional
public class ScheduleServiceImpl implements ScheduleService {

    @Autowired
    private ScheduleMapper scheduleMapper;

    /**
     * 分页查询
     * @param scheduleOption
     * @param pageNum
     * @param pageSize
     * @return
     */
    public List<Schedule> queryScheduleList(ScheduleOption scheduleOption, int pageNum, int pageSize) {
        scheduleOption.setCount(pageSize);
        scheduleOption.setStart((pageNum-1)*pageSize);
        scheduleOption.setEndrow(pageNum * pageSize);
        return scheduleMapper.queryScheduleList(scheduleOption);
    }

    @Override
    public int selectCount() {
        return scheduleMapper.selectCount();
    }

    /**
     * 获取所以待办事项
     * @return
     */
    public List<Schedule> findAllSchedule(ScheduleOption scheduleOption) {
        return scheduleMapper.findAllSchedule(scheduleOption);
    }

    /**
     * 获取待办事项详情
     * @param id
     * @return
     */
    public Schedule getScheduleById(long id) {
        return scheduleMapper.selectByPrimaryKey(id);
    }

    /**
     * 新增待办事项
     * @param schedule
     * @return
     */
    public int addSchedule(Schedule schedule) {
        schedule.setCreatetime(new Date());
        //获取当前登录用户
        User user = SessionUtils.getUser();
        schedule.setUserid(user.getId());
        return scheduleMapper.insertSelective(schedule);
    }

    /**
     * 更新待办事项
     * @param schedule
     * @return
     */
    public int updateSchedule(Schedule schedule) {
        return scheduleMapper.updateByPrimaryKeySelective(schedule);
    }

    /**
     * 删除特定事项
     * @param id
     * @return
     */
    public int deleteById(long id) {
        return scheduleMapper.deleteByPrimaryKey(id);
    }
}
