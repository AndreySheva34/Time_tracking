package org.itstep.timeTracking.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.itstep.timeTracking.command.DepartmentCommand;

import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@Table(name = "departments")
@NoArgsConstructor
public class Department {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "parent_id")
    private Integer parentId;

    @Column(unique = true)
    private String title;

    @OneToMany(mappedBy = "department")
    private List<Employee> employees = new ArrayList<>();

    public Department(String title) {
        this.title = title;
    }

    public static Department fromDepartment(DepartmentCommand command){

        return new Department(command.title());
    }
}
