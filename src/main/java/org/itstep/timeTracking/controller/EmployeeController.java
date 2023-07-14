package org.itstep.timeTracking.controller;

import lombok.RequiredArgsConstructor;
import org.itstep.timeTracking.command.EmployeeCommand;
import org.itstep.timeTracking.entity.Employee;
import org.itstep.timeTracking.repository.DepartmentRepository;
import org.itstep.timeTracking.repository.EmployeeRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/emploee")
@RequiredArgsConstructor
public class EmployeeController {
    private final EmployeeRepository repository;
    private final DepartmentRepository repositoryDepartment;
    @GetMapping
    String index(Model model){
        model.addAttribute("employees", repository.findAll());
        model.addAttribute("departments", repositoryDepartment.findAll());
        return "emploee";
    }

    @PostMapping
    String create(EmployeeCommand command){
        Employee employee = Employee.fromEmployee(command);
        repository.save(employee);
        return "redirect:/emploee";

    }
}
