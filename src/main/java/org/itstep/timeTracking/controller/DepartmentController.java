package org.itstep.timeTracking.controller;

import lombok.RequiredArgsConstructor;
import org.itstep.timeTracking.command.DepartmentCommand;
import org.itstep.timeTracking.entity.Department;
import org.itstep.timeTracking.repository.DepartmentRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/department")
@RequiredArgsConstructor
public class DepartmentController {
    private final DepartmentRepository repository;

    @GetMapping

    String index(Model model){
        model.addAttribute("departments",repository.findAll());
        return "department";
    }

    @PostMapping
    String create(DepartmentCommand command){
        Department department = Department.fromDepartment(command);
        repository.save(department);
        return "redirect:/department";

    }
}
