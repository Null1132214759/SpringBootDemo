package com.edu.controller;

import com.edu.entity.Department;
import com.edu.service.DepartmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class DepartmentController {
    
    @Autowired
    DepartmentService departmentService;

    @GetMapping("/depts")
    public String list(Model model) {
        List<Department> departments = departmentService.list();
        model.addAttribute("depts", departments);
        return "dept/list";
    }
}
