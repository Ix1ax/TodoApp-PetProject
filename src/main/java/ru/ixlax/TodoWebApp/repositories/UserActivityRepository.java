package ru.ixlax.TodoWebApp.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.ixlax.TodoWebApp.models.user.UserActivity;

@Repository
public interface UserActivityRepository extends JpaRepository<UserActivity,Integer> {
}
