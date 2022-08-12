CREATE DATABASE `portfolio` /*!40100 DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci */ /*!80016 DEFAULT ENCRYPTION='N' */;

CREATE TABLE `stock` (
  `symbol` varchar(45) NOT NULL,
  `name` varchar(45) DEFAULT NULL,
  `quantity` int DEFAULT NULL,
  PRIMARY KEY (`symbol`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

CREATE TABLE `trades` (
  `trade_id` int NOT NULL,
  `shares` int DEFAULT NULL,
  `price` decimal(10,0) DEFAULT NULL,
  `timestamp_of_trade` int DEFAULT NULL,
  `type` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`trade_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;


insert INTO stock VALUES ('AAPL', 'APPLE', 3);