package com.zjr.dater.business.dao;

import com.zjr.dater.business.domain.ScheduleOption;
import com.zjr.dater.business.model.Schedule;
import com.zjr.dater.business.model.ScheduleExample;
import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface ScheduleMapper {
    long countByExample(ScheduleExample example);

    int deleteByExample(ScheduleExample example);

    int deleteByPrimaryKey(Long id);

    int insert(Schedule record);

    int insertSelective(Schedule record);

    List<Schedule> selectByExample(ScheduleExample example);

    Schedule selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") Schedule record, @Param("example") ScheduleExample example);

    int updateByExample(@Param("record") Schedule record, @Param("example") ScheduleExample example);

    int updateByPrimaryKeySelective(Schedule record);

    int updateByPrimaryKey(Schedule record);

    List<Schedule> findAllSchedule(ScheduleOption scheduleOption);

    List<Schedule> queryScheduleList(ScheduleOption scheduleOption);

    int selectCount();
}