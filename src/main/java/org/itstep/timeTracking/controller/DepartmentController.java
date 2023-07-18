package org.itstep.timeTracking.controller;

import lombok.RequiredArgsConstructor;
import org.itstep.timeTracking.command.DepartmentCommand;
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

    @PostMapping
    String create(DepartmentCommand departmentCommand) {
        Department departmentParent = Department.fromDepartment(departmentCommand);

        if (departmentCommand.parentId() != null ){
            Optional<Department> optionalDepartment = departmentRepository.findById(departmentCommand.parentId().getId());
            optionalDepartment.ifPresent(departmentParent::setDepartmentParent);
        }

        departmentRepository.save(departmentParent);
        return "redirect:/department";
    }

    @GetMapping("/{id}")
    String delete(@PathVariable Integer id){
        Optional<Department> optionalDepartment = departmentRepository.findById(id);
        optionalDepartment.ifPresent(depatrment -> {
            departmentRepository.deleteById(id);
        });
        return "redirect:/department";
    }
}
