package org.itstep.timeTracking.security.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.springframework.security.core.GrantedAuthority;

import java.util.Set;

@Entity
@Table(name = "roles")
@Data
@NoArgsConstructor
@EqualsAndHashCode(exclude = "users")
@ToString(exclude = "users")
public class CustomRole implements GrantedAuthority {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @NotBlank
    @Column(name = "name")
    private String authority;
    @ManyToMany
    @JoinTable(name = "users_roles",
            joinColumns = @JoinColumn(name="roles_id"),
            inverseJoinColumns = @JoinColumn(name = "users_id")
    )
    private Set<CustomUser> users;

    public CustomRole(String authority) {
        this.authority = authority;
    }
}
