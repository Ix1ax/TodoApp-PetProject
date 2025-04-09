package ru.ixlax.TodoWebApp.services;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import ru.ixlax.TodoWebApp.dto.response.TeamSizeResponse;
import ru.ixlax.TodoWebApp.mappers.TeamSizeMapper;
import ru.ixlax.TodoWebApp.models.user.TeamSize;
import ru.ixlax.TodoWebApp.repositories.TeamSizeRepository;

import java.util.ArrayList;
import java.util.List;

@Service
@AllArgsConstructor
public class TeamSizeService {

    private final TeamSizeRepository teamSizeRepository;
    private final TeamSizeMapper teamSizeMapper;

    public List<TeamSizeResponse> getAllTeamSizes() {
        List<TeamSize> teamSizes = teamSizeRepository.findAll();

        return mapToDtoList(teamSizes);

    }

    public List<TeamSizeResponse> mapToDtoList(List<TeamSize> teamSizes) {
        if(teamSizes.isEmpty()) {
            return new ArrayList<>();
        }

        return teamSizes.stream()
                .map(teamSizeMapper::toTeamSizeDTO)
                .toList();
    }

    public TeamSize getTeamSizeById(Long id) {
        return teamSizeRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid TeamSize ID: " + id));
    }

}
