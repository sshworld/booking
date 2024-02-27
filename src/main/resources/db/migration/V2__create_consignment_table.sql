CREATE TABLE consignment
(
    id                                 INT(16) AUTO_INCREMENT PRIMARY KEY,
    user_id                            INT(16) NOT NULL,
    user_name                          VARCHAR(10)  NOT NULL,
    book_name                          VARCHAR(255) NOT NULL,
    international_standard_book_number VARCHAR(13)  NOT NULL,
    rental_price                       INT(8) NOT NULL,
    status                             VARCHAR(10)  NOT NULL,
    created_at                         TIMESTAMP DEFAULT CURRENT_TIMESTAMP(),
    updated_at                         TIMESTAMP DEFAULT CURRENT_TIMESTAMP() ON UPDATE CURRENT_TIMESTAMP
);
