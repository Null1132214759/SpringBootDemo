package com.edu.service;

import com.edu.entity.Department;

import java.util.List;

public interface DepartmentService {
    
    List<Department> list();
    
    Department select(Integer id);
    
    void insert(Department department);
    
    void delete(Integer id);
    
    void update(Department department);
}
