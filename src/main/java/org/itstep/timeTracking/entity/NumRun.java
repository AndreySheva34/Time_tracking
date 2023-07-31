package org.itstep.timeTracking.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.itstep.timeTracking.command.NumRunCommand;
import org.itstep.timeTracking.command.ScheduleCommand;

import java.time.LocalDate;

@Data
@Entity
@Table(name = "num_run")
@NoArgsConstructor
public class NumRun {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(unique = true)
    private String title;

    @Column(name = "start_date")
    private LocalDate startDate;

    @Column(name = "end_date")
    private LocalDate endDate;

    @Column(name = "cycle")
    private Integer cycle;

    @Column(name = "units")
    private Integer units;


    public NumRun(String title, LocalDate startDate, LocalDate endDate, Integer cycle, Integer units) {
        this.title = title;
        this.startDate = startDate;
        this.endDate = endDate;
        this.cycle = cycle;
        this.units = units;
    }

    public static NumRun fromNumRun(NumRunCommand command) {
        return new NumRun(command.title(), command.startDate(), command.endDate(), command.cycle(), command.units());
    }
}
