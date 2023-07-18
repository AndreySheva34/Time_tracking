package org.itstep.timeTracking.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.itstep.timeTracking.command.DepartmentCommand;

import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@Table(name = "departments")
@NoArgsConstructor
@EqualsAndHashCode(exclude = "employees")
@ToString(exclude = "employees")
public class Department {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

//    @Column(name = "parent_id")
//    private Integer parentId;

    @Column(unique = true)
    private String title;

    @OneToMany(mappedBy = "department")
    private List<Employee> employees = new ArrayList<>();

    @ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinColumn(name = "parentId")
    private Department parentId;

    public Department(String title, Department parentId) {
        this.title = title;
        this.parentId = parentId;
    }

    public static Department fromDepartment(DepartmentCommand command){

        return new Department(command.title(), command.parentId());
    }

    public void setDepartmentParent(Department parentDepartment) {
        this.parentId = parentDepartment;
    }
}
