package org.itstep.timeTracking.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.itstep.timeTracking.command.UserInfoCommand;

@Data
@Entity
@Table(name = "user_info")
@NoArgsConstructor
public class UserInfo {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(name = "firstname")
    private String firstName;
    @Column(name = "lastname")
    private String lastName;
    @Column(name = "card_number")
    private Integer cardNumber;

    @ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    private Department department;

    public UserInfo(String firstName, String lastName) {
        this.firstName = firstName;
        this.lastName = lastName;
    }

    public void setDepartment(Department department){
        department.getUserInfos().add(this);
        this.department = department;
    }

    public static UserInfo fromUserInfo(UserInfoCommand command){
        return new UserInfo(command.firstName(), command.lastName());
    }
}
