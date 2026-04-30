package br.com.project.prontpet.repositories;

import br.com.project.prontpet.models.Owner;
import org.apache.catalina.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface OwnerRepository extends JpaRepository <Owner, Long> {
    public Optional<User> findByEmail(String email);

}
