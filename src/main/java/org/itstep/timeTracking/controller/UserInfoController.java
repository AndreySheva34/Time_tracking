package org.itstep.timeTracking.controller;

import lombok.RequiredArgsConstructor;
import org.itstep.timeTracking.command.UserInfoCommand;
import org.itstep.timeTracking.entity.Department;
import org.itstep.timeTracking.entity.UserInfo;
import org.itstep.timeTracking.repository.DepartmentRepository;
import org.itstep.timeTracking.repository.UserInfoRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/user")
@RequiredArgsConstructor
public class UserInfoController {
    private final UserInfoRepository repository;
    private final DepartmentRepository repositoryDepartment;
    @GetMapping
    String index(Model model){
        model.addAttribute("UserInfos", repository.findAll());
        model.addAttribute("departments", repositoryDepartment.findAll());
        return "user";
    }

    @PostMapping
    String create(UserInfoCommand command){
        UserInfo userInfo = UserInfo.fromUserInfo(command);
        repository.save(userInfo);
        return "redirect:/user";

    }
}
