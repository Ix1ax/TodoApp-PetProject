package ru.ixlax.TodoWebApp.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.ixlax.TodoWebApp.models.user.UserRole;

@Repository
public interface UserRoleRepository extends JpaRepository<UserRole,Long> {
}
