package com.itheima.service.impl;

import com.itheima.mapper.EmpMapper;
import com.itheima.pojo.JobOption;
import com.itheima.service.ReportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
public class ReportServiceImpl implements ReportService {
    @Autowired
    private EmpMapper empMapper;

    @Override
    public JobOption getEmpJobData() {
        List<Map<String, Object>> jobData = empMapper.getEmpJobData();

        List<Object> jobList = jobData.stream().map(dataMap -> dataMap.get("jobName")).toList();
        List<Object> dataList = jobData.stream().map(item ->  item.get("num")).toList();
        return new JobOption(jobList,dataList);
    }
}