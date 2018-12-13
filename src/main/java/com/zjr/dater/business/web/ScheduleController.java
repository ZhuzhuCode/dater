package com.zjr.dater.business.web;

import com.zjr.dater.business.domain.ScheduleOption;
import com.zjr.dater.business.model.Schedule;
import com.zjr.dater.business.service.ScheduleService;
import com.zjr.dater.common.ResultObj;
import com.zjr.dater.common.utils.DateUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Date;
import java.util.List;

/**
 * Created by zhujr on 2018/10/12.
 * 待办事项list
 */
@Controller
@RequestMapping(value = "/schedule")
public class ScheduleController {
    @Autowired
    private ScheduleService scheduleService;

    /**
     * 待办事项list
     * @param userId
     * @param content
     * @param page
     * @param limit
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "scheduleList")
    public ResultObj scheduleList(@RequestParam(required = false, name = "userId") String userId,
                                  @RequestParam(required = false, name = "content")String content,
                                  int page, int limit) {
        ScheduleOption scheduleOption = new ScheduleOption();
        if (StringUtils.isNotBlank(content)){
            scheduleOption.setContent(content);
        }
        if(StringUtils.isNotBlank(userId)){
            scheduleOption.setUserId(Long.valueOf(userId));
        }
        List<Schedule> schedules = scheduleService.queryScheduleList(scheduleOption,page,limit);
        int count = scheduleService.selectCount();
        ResultObj re = new ResultObj(count,schedules);
        return re;
    }

    /**
     * 跳转到新增/编辑页面
     * @param operate
     * @param id
     * @param model
     * @return
     */
    @RequestMapping(value = "toEditSchedule")
    public String toEditSchedule(@RequestParam(required = false,name = "operate") String operate,
                                 @RequestParam(required = false,name = "id")String id, Model model){
        Schedule schedule = new Schedule();
        if(StringUtils.equals(operate,"1") && StringUtils.isNotBlank(id)){
            schedule = scheduleService.getScheduleById(Long.valueOf(id));
        }
        model.addAttribute("schedule",schedule);
        return "business/schedule-edit";
    }

    /**
     * 新增/修改待办事项
     * @param schedule
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "editSchedule")
    public ResultObj editSchedule(Schedule schedule,String createtimeCN,String contentstartCN){
        ResultObj re = new ResultObj();
        int result = 0;
        String msg = "";
        Date createTime = DateUtils.strToDateLong(createtimeCN);
        Date contentStart = DateUtils.strToDateLong(contentstartCN);
        schedule.setCreatetime(createTime);
        schedule.setContentstart(contentStart);
        if (schedule.getId() != null) {
            msg += "修改";
            result = scheduleService.updateSchedule(schedule);
        } else {
            msg += "新增";
            result = scheduleService.addSchedule(schedule);
        }
        if (result > 0) {
            re.setCode(ResultObj.CodeC.SUCCESS);
            re.setMsg(msg + "待办事项成功");
        } else {
            re.setCode(ResultObj.CodeC.FAIL);
            re.setMsg(msg + "待办事项失败");
        }
        return re;
    }


    /**
     * 删除待办事项
     * @param id
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "deleteById")
    public ResultObj deleteById(String id){
        ResultObj re = new ResultObj();
        if(StringUtils.isNotBlank(id)){
            int result = scheduleService.deleteById(Long.valueOf(id));
            if (result > 0) {
                re.setCode(ResultObj.CodeC.SUCCESS);
                re.setMsg("已删除该待办事项");
            } else {
                re.setCode(ResultObj.CodeC.FAIL);
                re.setMsg("删除待办事项失败");
            }
        }else {
            re.setCode(ResultObj.CodeC.FAIL);
            re.setMsg("未获取到待办事项ID");
        }
        return re;
    }
}
