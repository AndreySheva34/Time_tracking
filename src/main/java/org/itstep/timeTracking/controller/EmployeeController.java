package org.itstep.timeTracking.controller;

import lombok.RequiredArgsConstructor;
import org.itstep.timeTracking.command.EmployeeCommand;
import org.itstep.timeTracking.entity.Department;
import org.itstep.timeTracking.entity.Employee;
import org.itstep.timeTracking.repository.DepartmentRepository;
import org.itstep.timeTracking.repository.EmployeeRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Collections;
import java.util.Optional;

@Controller
@RequestMapping("/emploee")
@RequiredArgsConstructor
public class EmployeeController {
    private final EmployeeRepository employeeRepository;
    private final DepartmentRepository departmentRepository;
    @GetMapping
    String index(Model model){
        model.addAttribute("employees", employeeRepository.findAll());
        model.addAttribute("departments", departmentRepository.findAll());
        return "emploee";
    }

    @PostMapping
    String create(EmployeeCommand employeeCommand ){

        Optional<Department> optionalDepartment = departmentRepository.findById(employeeCommand.departmentId());
        optionalDepartment.ifPresent(department -> {
            Employee employee = Employee.fromEmployee(employeeCommand);
            employee.setDepartment(department);
            employeeRepository.save(employee);
        });
        return "redirect:/emploee";

    }
}
