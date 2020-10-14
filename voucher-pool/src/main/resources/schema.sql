CREATE TABLE RECIPIENT_TBL(
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(45) DEFAULT NULL,
  `email` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `email_UNIQUE` (`email`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;


CREATE TABLE VOUCHER_TBL (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `voucherCode` varchar(45) DEFAULT NULL,
  `recipient_id` int(11),
  `expDate` date DEFAULT NULL,
  `isUsed` varchar(1) DEFAULT NULL,
  `usedDate` date DEFAULT NULL,
  `offer_name` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
