package org.itstep.timeTracking.controller;

import lombok.RequiredArgsConstructor;
import org.itstep.timeTracking.command.NumRunCommand;
import org.itstep.timeTracking.entity.NumRun;
import org.itstep.timeTracking.repository.NumRunRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Optional;

@Controller
@RequestMapping("/numrun")
@RequiredArgsConstructor
public class NumRunController {
    private final NumRunRepository numRunRepository;

    @GetMapping
    String index(Model model) {
        model.addAttribute("numruns", numRunRepository.findAll());
        return "numrun";
    }

    @GetMapping("/create")
    String create(Model model) {
        model.addAttribute("numruns", numRunRepository.findAll());
        return "createnumrun";
    }

    @PostMapping("/create")
    String createNumRun(NumRunCommand numRunCommand) {
        NumRun numRun = NumRun.fromNumRun(numRunCommand);
        numRunRepository.save(numRun);
        return "redirect:/numrun";
    }

    @GetMapping("/{id}")
    String getNumRun(@PathVariable Integer id, Model model) {

        Optional<NumRun> optionalNumRun = numRunRepository.findById(id);
        optionalNumRun.ifPresent(numRun -> model.addAttribute("numRun", optionalNumRun.get()));
        return "editnumrun";
    }

    @PostMapping("/{id}")
    String update(@PathVariable Integer id, NumRunCommand numRunCommand) {
        Optional<NumRun> optionalNumRun = numRunRepository.findById(id);
        optionalNumRun.ifPresent(numRun -> {
            numRun.setTitle(numRunCommand.title());
            numRun.setStartDate(numRunCommand.startDate());
            numRun.setEndDate(numRunCommand.endDate());
            numRun.setCycle(numRunCommand.cycle());
            numRun.setUnits(numRunCommand.units());
            numRunRepository.save(numRun);
        });

        return "redirect:/numrun";
    }

    @GetMapping("delete/{id}")
    String delete(@PathVariable Integer id) {
        Optional<NumRun> optionalNumRun = numRunRepository.findById(id);
        optionalNumRun.ifPresent(numRun -> {
            numRunRepository.deleteById(id);
        });
        return "redirect:/numrun";
    }
}
