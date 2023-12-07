package com.lukasz.project.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.lukasz.project.token.Token;
import jakarta.persistence.*;
import lombok.*;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.time.OffsetDateTime;
import java.util.Collection;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Entity
@Getter
@Setter
@EqualsAndHashCode
@Table(name = "users")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(
        name = "user_type",
        discriminatorType = DiscriminatorType.STRING
)
@JsonIgnoreProperties({"password", "tokens","authorities","accountNonLocked","credentialsNonExpired","accountNonExpired"})
public class User implements UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

//    @Column(name = "identifier", unique = true)
//    private String identifier;

    @Column(name = "name")
    private String name;

    @Column(name = "surname")
    private String surname;

    @Column(name = "pesel")
    private String pesel;

    @Column(name = "country")
    private String country;

    @Column(name = "username")
    private String username;

    @Column(name = "email", unique = true)
    private String email;

    @Column(name = "password")
    private String password;

    @Column(name = "creation_date")
    private OffsetDateTime creationDate;

    @Column(name = "role")
    @Enumerated(EnumType.STRING)
    private Role role;

    @OneToMany(mappedBy = "user")
    private List<Token> tokens;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return role.getAuthorities();
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return email;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

//    private String generateRandomIdentifier() {
//        String entityName;
//        String randomNumber;
//        if (role.toString().equals("NORMAL_USER")) {
//            entityName = "NORMAL_USER";
//            randomNumber = RandomStringUtils.randomNumeric(20);
//        } else if (role.toString().equals("ADMIN")) {
//            entityName = "ADMIN";
//            randomNumber = RandomStringUtils.randomNumeric(20);
//        } else {
//            entityName = "RECRUITER";
//            randomNumber = RandomStringUtils.randomNumeric(20);
//
//        }
//        return entityName + "_" + randomNumber;
//    }


}
