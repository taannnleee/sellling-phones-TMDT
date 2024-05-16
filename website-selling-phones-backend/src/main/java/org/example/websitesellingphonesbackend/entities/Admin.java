package org.example.websitesellingphonesbackend.entities;

import jakarta.persistence.*;
import lombok.*;
import org.example.websitesellingphonesbackend.Enum.ERole;

import java.util.Date;

@Entity
@Table(name = "Admin")
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
@Setter
public class Admin {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "admin_id")
    private Long adminId;

    @Column(name = "email")
    private String email;

    @Column(name="password_hash")
    private String passHash;

    @Enumerated(EnumType.STRING)
    private ERole role;

}
