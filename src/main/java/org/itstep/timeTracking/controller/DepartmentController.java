package org.itstep.timeTracking.controller;

import lombok.RequiredArgsConstructor;
import org.itstep.timeTracking.command.DepartmentCommand;
import org.itstep.timeTracking.command.EmployeeCommand;
import org.itstep.timeTracking.entity.Department;
import org.itstep.timeTracking.entity.Employee;
import org.itstep.timeTracking.repository.DepartmentRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Optional;

@Controller
@RequestMapping("/department")
@RequiredArgsConstructor
public class DepartmentController {
    private final DepartmentRepository departmentRepository;

    @GetMapping
    String index(Model model){
        model.addAttribute("departments", departmentRepository.findAll());
        return "department";
    }

    @GetMapping("/create")
    String create(Model model) {
        model.addAttribute("departments", departmentRepository.findAll());
        return "createdepartment";
    }

    @PostMapping("/create")
    String createDepartment(DepartmentCommand departmentCommand) {
        Department departmentParent = Department.fromDepartment(departmentCommand);

        if (departmentCommand.parentId() != null ){
            Optional<Department> optionalDepartment = departmentRepository.findById(departmentCommand.parentId().getId());
            optionalDepartment.ifPresent(departmentParent::setDepartmentParent);
        }

        departmentRepository.save(departmentParent);
        return "redirect:/department";
    }

    @GetMapping("/{id}")
    String getDepartment(@PathVariable Integer id, Model model) {
        model.addAttribute("departments", departmentRepository.findAll());

        Optional<Department> optionalDepartment= departmentRepository.findById(id);
        optionalDepartment.ifPresent(department1 -> model.addAttribute("department", optionalDepartment.get()));
        return "editdepartment";
    }

    @PostMapping("/{id}")
    String update(@PathVariable Integer id, DepartmentCommand departmentCommand){
        Optional<Department> optionalDepartment= departmentRepository.findById(id);
        optionalDepartment.ifPresent(department -> {
            department.setTitle(departmentCommand.title());
            department.setParentId(departmentCommand.parentId());
            departmentRepository.save(department);
        });

        return "redirect:/department";
    }


    @GetMapping("delete/{id}")
    String delete(@PathVariable Integer id){
        Optional<Department> optionalDepartment = departmentRepository.findById(id);
        optionalDepartment.ifPresent(depatrment -> {
            departmentRepository.deleteById(id);
        });
        return "redirect:/department";
    }
}
