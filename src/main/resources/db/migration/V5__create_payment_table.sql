CREATE TABLE PAYMENTS(
  order_id INT PRIMARY KEY,
  user_id INT,
  pay_type VARCHAR(255),
  amount FLOAT,
  time TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
  FOREIGN KEY (order_id) REFERENCES ORDERS(id),
  FOREIGN KEY (user_id) REFERENCES USERS(id)
);