package com.bilyoner.livebettingapp.util;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.text.DecimalFormat;
import java.util.Random;

@Component
public class OddsGenerator {

    private final Random random = new Random();
    private DecimalFormat decimalFormat = new DecimalFormat("0.00");

    @Value("${odds.margin.min}")
    private double minMargin;

    @Value("${odds.margin.max}")
    private double maxMargin;

    @Value("${odds.limit.min}")
    private double minOddsLimit;

    @Value("${odds.limit.max}")
    private double maxOddsLimit;

    public String[] generateOddsWithMargin() {
        double[] fairOdds = generateFairOdds();
        double margin = 1 + (minMargin + (random.nextDouble() * (maxMargin - minMargin)));

        double homeWinOdds = margin / fairOdds[0];
        double drawOdds = margin / fairOdds[1];
        double awayWinOdds = margin / fairOdds[2];

        homeWinOdds = Math.max(minOddsLimit, Math.min(maxOddsLimit, homeWinOdds));
        drawOdds = Math.max(minOddsLimit, Math.min(maxOddsLimit, drawOdds));
        awayWinOdds = Math.max(minOddsLimit, Math.min(maxOddsLimit, awayWinOdds));

        return new String[]{
                decimalFormat.format(homeWinOdds),
                decimalFormat.format(drawOdds),
                decimalFormat.format(awayWinOdds)
        };
    }

    private double[] generateFairOdds() {
        double homeWinProb = random.nextDouble();
        double drawProb = random.nextDouble();
        double awayWinProb = random.nextDouble();

        double totalProb = homeWinProb + drawProb + awayWinProb;
        homeWinProb /= totalProb;
        drawProb /= totalProb;
        awayWinProb /= totalProb;

        return new double[]{homeWinProb, drawProb, awayWinProb};
    }
}
