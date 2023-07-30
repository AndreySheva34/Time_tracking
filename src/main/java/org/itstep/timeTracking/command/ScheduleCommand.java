package org.itstep.timeTracking.command;

import java.time.LocalTime;

public record ScheduleCommand(
        String title,
        LocalTime startTime,
        LocalTime endTime) {

}
