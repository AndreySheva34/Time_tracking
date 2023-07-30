package org.itstep.timeTracking.controller;

import lombok.RequiredArgsConstructor;
import org.itstep.timeTracking.command.DepartmentCommand;
import org.itstep.timeTracking.command.ScheduleCommand;
import org.itstep.timeTracking.entity.Department;
import org.itstep.timeTracking.entity.Schedule;
import org.itstep.timeTracking.repository.ScheduleRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Optional;

@Controller
@RequestMapping("/schedule")
@RequiredArgsConstructor
public class ScheduleController {
    private final ScheduleRepository scheduleRepository;

    @GetMapping
    String index(Model model) {
        model.addAttribute("schedules", scheduleRepository.findAll());
        return "schedule";
    }

    @GetMapping("/create")
    String create(Model model) {
        model.addAttribute("schedules", scheduleRepository.findAll());
        return "createschedule";
    }

    @PostMapping("/create")
    String createDepartment(ScheduleCommand ScheduleCommand) {
        Schedule schedule = Schedule.fromSchedule(ScheduleCommand);
        scheduleRepository.save(schedule);
        return "redirect:/schedule";
    }

    @GetMapping("/{id}")
    String getDepartment(@PathVariable Integer id, Model model) {

        Optional<Schedule> optionalSchedule = scheduleRepository.findById(id);
        optionalSchedule.ifPresent(schedule -> model.addAttribute("schedule", optionalSchedule.get()));
        return "editschedule";
    }

    @PostMapping("/{id}")
    String update(@PathVariable Integer id, ScheduleCommand scheduleCommand) {
        Optional<Schedule> optionalSchedule = scheduleRepository.findById(id);
        optionalSchedule.ifPresent(schedule -> {
            schedule.setTitle(scheduleCommand.title());
            schedule.setStartTime(scheduleCommand.startTime());
            schedule.setEndTime(scheduleCommand.endTime());
            scheduleRepository.save(schedule);
        });

        return "redirect:/schedule";
    }

    @GetMapping("delete/{id}")
    String delete(@PathVariable Integer id) {
        Optional<Schedule> optionalSchedule = scheduleRepository.findById(id);
        optionalSchedule.ifPresent(schedule -> {
            scheduleRepository.deleteById(id);
        });
        return "redirect:/schedule";
    }
}
