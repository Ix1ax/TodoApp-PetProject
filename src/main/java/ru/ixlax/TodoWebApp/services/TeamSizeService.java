package ru.ixlax.TodoWebApp.services;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import ru.ixlax.TodoWebApp.dto.TeamSizeRequest;
import ru.ixlax.TodoWebApp.models.user.TeamSize;
import ru.ixlax.TodoWebApp.repositories.TeamSizeRepository;

import java.util.ArrayList;
import java.util.List;

@Service
@AllArgsConstructor
public class TeamSizeService {

    private final TeamSizeRepository teamSizeRepository;

    public List<TeamSizeRequest> getAllTeamSizes() {
        List<TeamSize> teamSizes = teamSizeRepository.findAll();

        return mapToDtoList(teamSizes);

    }

    public List<TeamSizeRequest> mapToDtoList(List<TeamSize> teamSizes) {
        if(teamSizes.isEmpty()) {
            return new ArrayList<>();
        }

        return teamSizes.stream()
                .map(teamSize -> new TeamSizeRequest(teamSize.getId(),teamSize.getSize()))
                .toList();
    }

    public TeamSize getTeamSizeById(Integer id) {
        return teamSizeRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid TeamSize ID: " + id));
    }

}
