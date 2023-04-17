package br.ufpe.cin.aps.authservice.repositories;

import br.ufpe.cin.aps.authservice.models.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, String> {
}