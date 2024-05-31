package org.system.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.system.model.User;

public interface UserRepository extends JpaRepository<User, Long> {
}
