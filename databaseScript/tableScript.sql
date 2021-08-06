CREATE TABLE `home` (
  `HOME_ID` int NOT NULL AUTO_INCREMENT,
  `ADDRESS` varchar(100) DEFAULT NULL,
  `SQ_FT` int DEFAULT NULL,
  `ROOMS` int DEFAULT NULL,
  `BATHROOMS` int DEFAULT NULL,
  `PRICE` int DEFAULT NULL,
  PRIMARY KEY (`HOME_ID`)
) ENGINE=InnoDB AUTO_INCREMENT=15 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

INSERT INTO HOME(ADDRESS, SQ_FT, ROOMS, BATHROOMS, PRICE) VALUES ('145 dis way', 2000, 6, 3, 240000);
INSERT INTO HOME(ADDRESS, SQ_FT, ROOMS, BATHROOMS, PRICE) VALUES ('965 dat way', 1780, 2, 1, 183559);
INSERT INTO HOME(ADDRESS, SQ_FT, ROOMS, BATHROOMS, PRICE) VALUES ('234 witch way', 1200, 1, 1, 100000);
INSERT INTO HOME(ADDRESS, SQ_FT, ROOMS, BATHROOMS, PRICE) VALUES ('8732 every way', 3300, 8, 5, 600000);
INSERT INTO HOME(ADDRESS, SQ_FT, ROOMS, BATHROOMS, PRICE) VALUES ('000 no way', 500, 0, 1, 60000);
