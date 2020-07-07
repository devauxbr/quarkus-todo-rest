CREATE TABLE `hibernate_sequence` (
  `next_val` bigint DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
LOCK TABLES `hibernate_sequence` WRITE;
INSERT INTO `hibernate_sequence` VALUES (0);
UNLOCK TABLES;

CREATE TABLE `Todo` (
  `id` bigint NOT NULL,
  `createdAt` datetime(6) DEFAULT NULL,
  `description` varchar(255) DEFAULT NULL,
  `doneAt` datetime(6) DEFAULT NULL,
  `expireAt` datetime(6) DEFAULT NULL,
  `title` varchar(255) DEFAULT NULL,
  `updatedAt` datetime(6) DEFAULT NULL,
  `version` bigint DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
