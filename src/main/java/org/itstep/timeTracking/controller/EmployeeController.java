package org.itstep.timeTracking.controller;

import lombok.RequiredArgsConstructor;
import org.itstep.timeTracking.command.EmployeeCommand;
import org.itstep.timeTracking.entity.Employee;
import org.itstep.timeTracking.repository.DepartmentRepository;
import org.itstep.timeTracking.repository.EmployeeRepository;
import org.itstep.timeTracking.service.EmployeeService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Optional;

@Controller
@RequestMapping("/employees")
@RequiredArgsConstructor
public class EmployeeController {
    private final EmployeeRepository employeeRepository;
    private final DepartmentRepository departmentRepository;
    private final EmployeeService employeeService;
    @GetMapping
    String index(Model model){
        model.addAttribute("employees", employeeRepository.findAll());
        model.addAttribute("departments", departmentRepository.findAll());
        return "employees";
    }

    @PostMapping
    String create(@Validated EmployeeCommand employeeCommand, BindingResult bindingResult){
        if(bindingResult.hasErrors()){
            return "employees";
        }
        employeeService.save(employeeCommand);
        return "redirect:/employees";
    }

    @GetMapping("/{id}")
    String getEmployee(@PathVariable Integer id, Model model) {
        model.addAttribute("departments", departmentRepository.findAll());

        Optional<Employee> optionalEmployee= employeeRepository.findById(id);
        optionalEmployee.ifPresent(employee -> {
            model.addAttribute("employee", optionalEmployee.get());
        });
        return "/employee";
    }

    @PostMapping("/{id}")
    String update(@PathVariable Integer id, EmployeeCommand employeeCommand){
        employeeService.update(id, employeeCommand);
        return "redirect:/employees";
    }


    @GetMapping("/delete/{id}")
    String delete(@PathVariable Integer id){
        Optional<Employee> optionalEmployee= employeeRepository.findById(id);
        optionalEmployee.ifPresent(employee -> {
            employeeRepository.deleteById(id);
        });
        return "redirect:/employees";
    }
}
