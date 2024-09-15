package com.bilyoner.livebettingapp.util;

import com.bilyoner.livebettingapp.constant.BetOutcome;
import com.bilyoner.livebettingapp.entity.Match;

public class OddsUtil {
    public static double getOddsForOutcome(Match match, BetOutcome outcome) {
        return switch (outcome) {
            case HOME_WIN -> match.getHomeWinOdds();
            case DRAW -> match.getDrawOdds();
            case AWAY_WIN -> match.getAwayWinOdds();
        };
    }
}
