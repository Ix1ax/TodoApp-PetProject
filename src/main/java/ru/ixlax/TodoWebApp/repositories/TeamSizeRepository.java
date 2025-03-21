package ru.ixlax.TodoWebApp.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.ixlax.TodoWebApp.models.user.TeamSize;

@Repository
public interface TeamSizeRepository extends JpaRepository<TeamSize,Integer> {

}
