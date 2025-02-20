CREATE TABLE recipes (
   recipe_id INTEGER PRIMARY KEY AUTO_INCREMENT,
   recipe_name VARCHAR(255));

CREATE TABLE ingredients (
   ingredient_id INTEGER PRIMARY KEY AUTO_INCREMENT,
   ingredient_name VARCHAR(255),
   amount_in_stock INTEGER);

CREATE TABLE recipe_ingredients (
   recipe_ingredient_id INTEGER PRIMARY KEY AUTO_INCREMENT,
   ingredient_name VARCHAR(255),
   recipe_name VARCHAR(255),
   FOREIGN KEY (ingredient_name) REFERENCES ingredients(ingredient_name),
   FOREIGN KEY (recipe_name) REFERENCES recipes(recipe_name));

CREATE TABLE customers (
    customer_id INTEGER PRIMARY KEY AUTO_INCREMENT,
    customer_name VARCHAR(255),
    favorite_ingredient_1 VARCHAR(255),
    favorite_ingredient_2 VARCHAR(255),
    favorite_ingredient_3 VARCHAR(255));



