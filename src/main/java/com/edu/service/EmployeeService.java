package com.edu.service;


import com.edu.entity.Employee;
import org.springframework.cache.annotation.Cacheable;

import java.util.List;

public interface EmployeeService {
    List<Employee> list();
    
    Employee select(Integer id);

    void insert(Employee employee);

    void delete(Integer id);

    void update(Employee employee);
}
