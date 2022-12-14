package com.nolanmortenson.portfolio.repositories;

import com.nolanmortenson.portfolio.models.AppUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AppUserRepo extends JpaRepository<AppUser, Long> {
    boolean existsByEmail(String email);
    boolean existsByUsername(String username);

    boolean existsByUsernameOrEmail(String username, String email);
}
