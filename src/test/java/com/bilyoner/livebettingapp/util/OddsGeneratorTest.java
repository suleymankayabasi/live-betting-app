package com.bilyoner.livebettingapp.util;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Spy;
import org.springframework.test.util.ReflectionTestUtils;

import java.util.Random;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.when;

class OddsGeneratorTest {

    @InjectMocks
    private OddsGenerator oddsGenerator;

    @Spy
    private Random random;

    @BeforeEach
    void setUp() {
        oddsGenerator = new OddsGenerator();

        ReflectionTestUtils.setField(oddsGenerator, "minMargin", 0.05);
        ReflectionTestUtils.setField(oddsGenerator, "maxMargin", 0.1);
        ReflectionTestUtils.setField(oddsGenerator, "minOddsLimit", 1.1);
        ReflectionTestUtils.setField(oddsGenerator, "maxOddsLimit", 5.0);

        random = spy(new Random());
        ReflectionTestUtils.setField(oddsGenerator, "random", random);
    }

    @Test
    void testGenerateOddsWithMargin() {
        when(random.nextDouble()).thenReturn(0.3, 0.2, 0.5, 0.05);  // Last value is for the margin calculation

        String[] odds = oddsGenerator.generateOddsWithMargin();

        assertNotNull(odds);
        assertEquals(3, odds.length);
        assertTrue(Double.parseDouble(odds[0]) >= 1.1 && Double.parseDouble(odds[0]) <= 5.0);
        assertTrue(Double.parseDouble(odds[1]) >= 1.1 && Double.parseDouble(odds[1]) <= 5.0);
        assertTrue(Double.parseDouble(odds[2]) >= 1.1 && Double.parseDouble(odds[2]) <= 5.0);

        assertEquals("3.51", odds[0]);
        assertEquals("5.00", odds[1]);
        assertEquals("2.10", odds[2]);
    }


    @Test
    void testOddsWithinLimits() {
        when(random.nextDouble()).thenReturn(0.4, 0.3, 0.3);

        // Generate odds
        String[] odds = oddsGenerator.generateOddsWithMargin();

        for (String odd : odds) {
            double value = Double.parseDouble(odd);
            assertTrue(value >= 1.1 && value <= 5.0, "Odds are within the specified limits.");
        }
    }

    @Test
    void testMarginCalculation() {
        when(random.nextDouble()).thenReturn(0.4, 0.3, 0.3);
        when(random.nextDouble()).thenReturn(0.05);

        String[] odds = oddsGenerator.generateOddsWithMargin();

        assertNotNull(odds);
        assertTrue(Double.parseDouble(odds[0]) > 1.1 && Double.parseDouble(odds[0]) <= 5.0);
    }
}
