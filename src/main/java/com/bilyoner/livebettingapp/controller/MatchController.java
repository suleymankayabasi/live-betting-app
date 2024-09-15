package com.bilyoner.livebettingapp.controller;

import com.bilyoner.livebettingapp.dto.MatchRequestDTO;
import com.bilyoner.livebettingapp.dto.MatchResponseDTO;
import com.bilyoner.livebettingapp.service.MatchService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/matches")
public class MatchController {

    private final MatchService matchService;

    @PostMapping
    public ResponseEntity<MatchResponseDTO> addMatch(@RequestBody MatchRequestDTO matchRequestDTO) {
        return ResponseEntity.ok(matchService.addMatch(matchRequestDTO));
    }

    @GetMapping
    public ResponseEntity<List<MatchResponseDTO>> getAllMatches() {
        return ResponseEntity.ok(matchService.getAllMatchesSortedByTime());
    }
}
