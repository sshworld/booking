CREATE TABLE rental
(
    id             INT(16) AUTO_INCREMENT PRIMARY KEY,
    user_id        INT(16) NOT NULL,
    consignment_id INT(16) NOT NULL,
    status         VARCHAR(10) NOT NULL,
    created_at     TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_at     TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
)
