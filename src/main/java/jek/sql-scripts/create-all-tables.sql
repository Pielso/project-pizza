CREATE TABLE users (
    user_id INTEGER PRIMARY KEY AUTO_INCREMENT,
    username VARCHAR(255),
    password VARCHAR(255));

CREATE TABLE recipes (
   recipe_id INTEGER PRIMARY KEY AUTO_INCREMENT,
   recipe_name VARCHAR(255),
   user_id INTEGER,
   FOREIGN KEY (user_id) REFERENCES users(user_id));

CREATE TABLE toppings (
   topping_id INTEGER PRIMARY KEY AUTO_INCREMENT,
   topping_name VARCHAR(255),
   amount_in_stock INTEGER);

CREATE TABLE recipe_topping (
   recipe_toppings_id INTEGER PRIMARY KEY AUTO_INCREMENT,
   recipe_id INTEGER,
   topping_id INTEGER,
   FOREIGN KEY (recipe_id) REFERENCES recipes(recipe_id) ON DELETE CASCADE,
   FOREIGN KEY (topping_id) REFERENCES toppings(topping_id) ON DELETE CASCADE );

CREATE TABLE customers (
    customer_id INTEGER PRIMARY KEY AUTO_INCREMENT,
    customer_name VARCHAR(255),
    desired_topping1 INTEGER,
    desired_topping2 INTEGER,
    desired_topping3 INTEGER,
    FOREIGN KEY (desired_topping1) REFERENCES toppings(topping_id) ON DELETE CASCADE,
    FOREIGN KEY (desired_topping2) REFERENCES toppings(topping_id) ON DELETE CASCADE,
    FOREIGN KEY (desired_topping3) REFERENCES toppings(topping_id) ON DELETE CASCADE);

CREATE TABLE basic_ingredients (
    basic_ingredient_id INTEGER PRIMARY KEY AUTO_INCREMENT,
    basic_ingredient_name VARCHAR(255),
    amount_in_stock INTEGER);

CREATE TABLE raw_ingredients (
    raw_ingredient_id INTEGER PRIMARY KEY AUTO_INCREMENT,
    raw_ingredient_name VARCHAR(255),
    amount_in_stock INTEGER);

CREATE TABLE progress (
    user_id INTEGER PRIMARY KEY,
    cash DECIMAL,
    loan DECIMAL,
    interest_rate DECIMAL,
    customers_per_day INTEGER,
    restaurant_size INTEGER,
    days_played INTEGER,
    FOREIGN KEY (user_id) REFERENCES users(user_id));



