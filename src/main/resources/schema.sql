-- Match Table
CREATE TABLE IF NOT EXISTS matches
(
    id
    BIGINT
    AUTO_INCREMENT
    PRIMARY
    KEY,
    league
    VARCHAR
(
    255
) NOT NULL,
    home_team VARCHAR
(
    255
) NOT NULL,
    away_team VARCHAR
(
    255
) NOT NULL,
    home_win_odds DOUBLE NOT NULL,
    draw_odds DOUBLE NOT NULL,
    away_win_odds DOUBLE NOT NULL,
    match_start_time TIMESTAMP NOT NULL
    );
