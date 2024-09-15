package com.bilyoner.livebettingapp.repository;

import com.bilyoner.livebettingapp.entity.Bet;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BetRepository extends JpaRepository<Bet, Long> {
}
