package com.api.paideia.domain.user;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "users")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id_user;
    private String name;
    private String email;
    private String password;

    @Override
    public String toString() {
        return "User{" +
                "id=" + id_user +
                ", name='" + name + '\'' +
                ", email='" + email + '\'' +
                '}';
    }
}
