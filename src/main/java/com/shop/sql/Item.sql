CREATE TABLE item (
                      item_id BIGINT NOT NULL PRIMARY KEY,
                      item_number VARCHAR(50) NOT NULL,
                      item_detail TINYTEXT NOT NULL,
                      price INT NOT NULL,
                      stock_number INT NOT NULL,
                      reg_time DATETIME(6),
                      update_time DATETIME(6),
                      item_sell_status ENUM ('SELL', 'SOLD_OUT')
) ENGINE=InnoDB;