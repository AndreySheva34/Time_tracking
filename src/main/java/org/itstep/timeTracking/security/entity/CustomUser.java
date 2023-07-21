package org.itstep.timeTracking.security.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "users")
@Data
@NoArgsConstructor
public class CustomUser implements UserDetails {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NotBlank
    private String username;
    @NotBlank
    private String password;
    @Column(name = "account_non_expired")
    boolean accountNonExpired = true;
    @Column(name = "account_non_locked")
    boolean accountNonLocked = true;
    @Column(name = "credentials_non_expired")
    boolean credentialsNonExpired = true;
    boolean enabled = true;

    @ManyToMany(mappedBy = "users")
    private Set<CustomRole> authorities = new HashSet<>();

    public CustomUser(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public void addRole (CustomRole... roles){
        Arrays.stream(roles).forEach(role -> {
        authorities.add(role);
        role.getUsers().add(this);
        });
    }

}
