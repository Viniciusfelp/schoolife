package br.ufpe.cin.aps.authservice.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String email;

    @Column(nullable = false)
    private String password;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private boolean enabled;

    @Column(name = "user_type", nullable = false)
    private String userType; // "ALUNO" or "PROFESSOR"

    @Column(name = "related_id", nullable = false)
    private Long relatedId; // ID do Aluno ou Professor no outro serviço

    public User(String email, String password, String name, boolean enabled, String userType, Long relatedId) {
        this.email = email;
        this.password = password;
        this.name = name;
        this.enabled = enabled;
        this.userType = userType;
        this.relatedId = relatedId;
    }
}
