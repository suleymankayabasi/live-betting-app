package com.bilyoner.livebettingapp.repository;

import com.bilyoner.livebettingapp.entity.Match;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface MatchRepository extends JpaRepository<Match, Long> {

    @Query("SELECT m FROM Match m WHERE m.matchStartTime >= :now ORDER BY m.matchStartTime")
    List<Match> findAllUpcomingMatches(@Param("now") LocalDateTime now);
}
