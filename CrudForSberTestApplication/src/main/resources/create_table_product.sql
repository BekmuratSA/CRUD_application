CREATE TABLE product
(
    id INT GENERATED BY DEFAULT AS IDENTITY PRIMARY KEY,
    product_name VARCHAR(200) NOT NULL,
    price INT NOT NULL,
    customer_id INT,
    PRIMARY KEY (customer_id)
)