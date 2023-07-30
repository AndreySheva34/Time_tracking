package org.itstep.timeTracking.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.itstep.timeTracking.command.ScheduleCommand;

import java.time.LocalTime;

@Data
@Entity
@Table(name = "schedule")
@NoArgsConstructor
public class Schedule {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(name = "title")
    private String title;
    @Column(name = "start_time")
    private LocalTime startTime;
    @Column(name = "end_time")
    private LocalTime endTime;

    public Schedule(String title, LocalTime startTime, LocalTime endTime) {
        this.title = title;
        this.startTime = startTime;
        this.endTime = endTime;
    }

    public static Schedule fromSchedule(ScheduleCommand command) {
        return new Schedule(command.title(), command.startTime(), command.endTime());
    }
}
