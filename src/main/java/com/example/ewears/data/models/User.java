package com.example.ewears.data.models;

import lombok.*;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.Set;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "USER")
@Builder
public class User extends DateAudit{

    @ManyToMany(fetch = FetchType.EAGER,
        cascade = CascadeType.ALL)
    @JoinTable(name = "USER_ROLES",
        joinColumns = {@JoinColumn(name = "user_id")},
        inverseJoinColumns =  {@JoinColumn(name = "role_id")})
    Set<Role> roles;

    @Id
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "uuid2")
    @Column(name = "USER_ID" , updatable = false, nullable = false)
    private String userId;

    @Column(name = "USER_NAME", updatable = false, nullable = false)
    private String userName;

    @Column(name = "PASSWORD", nullable = false)
    private String password;

    @Column(name = "FIRST_NAME", nullable = false)
    private String firstName;

    @Column(name = "LAST_NAME", nullable = false)
    private String lastName;

    @Column(name = "EMAIL", nullable = false)
    private String email;

    @Column(name = "CART_ID")
    private String cartId;

    @Column(name = "Gender")
    private Gender gender;

    public User(String userName, String password, String firstName, String lastName, String email, Gender gender) {
        this.userName = userName;
        this.password = password;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.gender = gender;
    }
}
