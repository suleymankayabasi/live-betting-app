package com.bilyoner.livebettingapp.util;

import com.bilyoner.livebettingapp.constant.BetOutcome;
import com.bilyoner.livebettingapp.entity.Match;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class OddsUtilTest {

    private Match match;

    @BeforeEach
    void setUp() {
        match = new Match();
        match.setHomeWinOdds(2.0);
        match.setDrawOdds(3.5);
        match.setAwayWinOdds(4.2);
    }

    @Test
    void testGetOddsForHomeWin() {
        double odds = OddsUtil.getOddsForOutcome(match, BetOutcome.HOME_WIN);
        assertEquals(2.0, odds);
    }

    @Test
    void testGetOddsForDraw() {
        double odds = OddsUtil.getOddsForOutcome(match, BetOutcome.DRAW);
        assertEquals(3.5, odds);
    }

    @Test
    void testGetOddsForAwayWin() {
        double odds = OddsUtil.getOddsForOutcome(match, BetOutcome.AWAY_WIN);
        assertEquals(4.2, odds);
    }
}
