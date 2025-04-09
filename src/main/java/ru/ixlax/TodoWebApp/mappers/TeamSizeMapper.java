package ru.ixlax.TodoWebApp.mappers;

import org.springframework.stereotype.Component;
import ru.ixlax.TodoWebApp.dto.response.TeamSizeResponse;
import ru.ixlax.TodoWebApp.models.user.TeamSize;

@Component
public class TeamSizeMapper {

    public TeamSizeResponse toTeamSizeDTO(TeamSize teamSize) {
        return new TeamSizeResponse(teamSize.getId(),teamSize.getSize());
    }

}
