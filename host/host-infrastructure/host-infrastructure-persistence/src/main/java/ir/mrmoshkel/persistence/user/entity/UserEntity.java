package ir.mrmoshkel.persistence.user.entity;

import ir.mrmoshkel.persistence.framework.BaseEntity;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Table(name = "users")
@Entity
@Getter
@Setter
@NoArgsConstructor
public class UserEntity extends BaseEntity<Long> {
    @Column(name = "first_name", nullable = false)
    private String firstName;
    @Column(name = "last_name", nullable = false)
    private String lastName;
    @Column(nullable = false, unique = true, length = 64, updatable = false)
    private String username;
    @Column(nullable = false, unique = true)
    private String password;
    @Column
    private String email;
    @Column
    private String phoneNumber;
}
