CREATE TABLE customer(
    id INT GENERATED BY DEFAULT AS IDENTITY,
    firstName VARCHAR(50) NOT NULL,
    lastName VARCHAR(50) NOT NULL
)