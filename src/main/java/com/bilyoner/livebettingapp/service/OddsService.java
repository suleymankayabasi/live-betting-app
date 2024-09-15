package com.bilyoner.livebettingapp.service;

import com.bilyoner.livebettingapp.dto.MatchResponseDTO;
import com.bilyoner.livebettingapp.entity.Match;
import com.bilyoner.livebettingapp.repository.MatchRepository;
import com.bilyoner.livebettingapp.util.OddsGenerator;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class OddsService {

    private final MatchRepository matchRepository;
    private final OddsGenerator oddsGenerator;
    private final WebSocketService webSocketService;
    private final MatchService matchService;

    @Value("${scheduled.fixedRate}")
    private long fixedRate;

    @Scheduled(fixedRateString = "${scheduled.fixedRate}")
    public void updateOddsAndNotifyClients() {
        List<Match> matches = matchRepository.findAll();
        int numberOfMatchesToUpdate = getNumberOfMatchesToUpdate(matches.size());
        List<Match> matchesToUpdate = getRandomMatches(matches, numberOfMatchesToUpdate);

        updateMatchesOdds(matchesToUpdate);
        broadcastUpdatedMatches();
    }

    private int getNumberOfMatchesToUpdate(int totalMatches) {
        Random random = new Random();
        int minMatchesToUpdate = 1;
        int maxMatchesToUpdate = Math.max(minMatchesToUpdate, Math.min(totalMatches, 5));
        return random.nextInt(maxMatchesToUpdate - minMatchesToUpdate + 1) + minMatchesToUpdate;
    }

    private List<Match> getRandomMatches(List<Match> matches, int numberOfMatchesToUpdate) {
        Collections.shuffle(matches);
        return matches.stream()
                .limit(numberOfMatchesToUpdate)
                .collect(Collectors.toList());
    }

    private void broadcastUpdatedMatches() {
        List<MatchResponseDTO> updatedMatches = matchService.getAllMatchesSortedByTime();
        webSocketService.broadcastMatches(updatedMatches);
    }

    private void updateMatchesOdds(List<Match> matchesToUpdate) {
        matchesToUpdate.forEach(this::updateMatchOdds);
    }

    private void updateMatchOdds(Match match) {
        String[] odds = oddsGenerator.generateOddsWithMargin();
        match.setHomeWinOdds(Double.parseDouble(odds[0]));
        match.setDrawOdds(Double.parseDouble(odds[1]));
        match.setAwayWinOdds(Double.parseDouble(odds[2]));
        matchRepository.save(match);
    }
}