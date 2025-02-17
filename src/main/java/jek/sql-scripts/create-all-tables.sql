CREATE TABLE games (
       game_id INTEGER PRIMARY KEY AUTO_INCREMENT,
       title VARCHAR(255),
       min_players INTEGER,
       max_players INTEGER,
       rating INTEGER);

CREATE TABLE designers (
       designer_id INTEGER PRIMARY KEY AUTO_INCREMENT,
       designer_name varchar(255));

CREATE TABLE game_designer (
       game_id INTEGER,
       designer_id INTEGER,
       PRIMARY KEY (game_id, designer_id),
       FOREIGN KEY (game_id) REFERENCES games(game_id),
       FOREIGN KEY (designer_id) REFERENCES designers(designer_id));
