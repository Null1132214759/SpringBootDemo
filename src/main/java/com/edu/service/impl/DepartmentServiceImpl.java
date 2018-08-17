package com.edu.service.impl;

import com.edu.dao.DepartmentMapper;
import com.edu.entity.Department;
import com.edu.service.DepartmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DepartmentServiceImpl implements DepartmentService {
    
    @Autowired
    DepartmentMapper departmentMapper;

    @Override
    public List<Department> list() {
        List<Department> departments = departmentMapper.selectByExample(null);
        return departments;
    }

    @Override
    public Department select(Integer id) {
        Department department = departmentMapper.selectByPrimaryKey(id);
        return department;
    }

    @Override
    public void insert(Department department) {
        departmentMapper.insert(department);
    }

    @Override
    public void delete(Integer id) {
        departmentMapper.deleteByPrimaryKey(id);
    }

    @Override
    public void update(Department department) {
        departmentMapper.updateByPrimaryKeySelective(department);
    }
}
