package com.bilyoner.livebettingapp.service;

import com.bilyoner.livebettingapp.dto.MatchRequestDTO;
import com.bilyoner.livebettingapp.dto.MatchResponseDTO;
import com.bilyoner.livebettingapp.entity.Match;
import com.bilyoner.livebettingapp.mapper.MatchMapper;
import com.bilyoner.livebettingapp.repository.MatchRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@RequiredArgsConstructor
@Service
public class MatchService {

    private final MatchRepository matchRepository;
    private final MatchMapper matchMapper;

    public MatchResponseDTO addMatch(MatchRequestDTO requestDTO) {
        Match match = matchMapper.toEntity(requestDTO);
        Match savedMatch = matchRepository.save(match);
        return matchMapper.toDTO(savedMatch);
    }

    public List<MatchResponseDTO> getAllMatchesSortedByTime() {
        List<Match> matches = matchRepository.findAllUpcomingMatches(LocalDateTime.now());
        return matchMapper.toDTOList(matches);
    }
}

