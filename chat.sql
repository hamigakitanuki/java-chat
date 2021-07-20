-- -------------------------------------------------------------
-- TablePlus 4.0.0(370)
--
-- https://tableplus.com/
--
-- Database: chat
-- Generation Time: 2021-07-20 09:55:47.5830
-- -------------------------------------------------------------


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;


CREATE TABLE `chat` (
  `id` int NOT NULL AUTO_INCREMENT,
  `chat_room_member_id` int DEFAULT NULL,
  `chat_room_id` int DEFAULT NULL,
  `message` varchar(255) DEFAULT NULL,
  `type` int DEFAULT NULL,
  `created_at` datetime DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `chat_room_member_id` (`chat_room_member_id`),
  KEY `chat_room_id` (`chat_room_id`),
  CONSTRAINT `chat_ibfk_6` FOREIGN KEY (`chat_room_member_id`) REFERENCES `chat_room_member` (`id`) ON DELETE CASCADE,
  CONSTRAINT `chat_ibfk_7` FOREIGN KEY (`chat_room_id`) REFERENCES `chat_room` (`id`) ON DELETE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=42 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

CREATE TABLE `chat_room` (
  `id` int NOT NULL AUTO_INCREMENT,
  `chat_room_name` varchar(255) NOT NULL,
  `password` varchar(255) DEFAULT NULL,
  `created_at` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=25 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

CREATE TABLE `chat_room_member` (
  `id` int NOT NULL AUTO_INCREMENT,
  `user_id` int DEFAULT NULL,
  `chat_room_id` int DEFAULT NULL,
  `created_at` datetime DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `user_id` (`user_id`),
  KEY `chat_room_id` (`chat_room_id`),
  CONSTRAINT `chat_room_member_ibfk_3` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`) ON DELETE CASCADE,
  CONSTRAINT `chat_room_member_ibfk_4` FOREIGN KEY (`chat_room_id`) REFERENCES `chat_room` (`id`) ON DELETE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=17 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

CREATE TABLE `role` (
  `id` int NOT NULL AUTO_INCREMENT,
  `role` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

CREATE TABLE `role_user` (
  `user_id` int NOT NULL,
  `role_id` int DEFAULT NULL,
  PRIMARY KEY (`user_id`),
  KEY `role_id` (`role_id`),
  CONSTRAINT `role_user_ibfk_1` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`),
  CONSTRAINT `role_user_ibfk_2` FOREIGN KEY (`role_id`) REFERENCES `role` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

CREATE TABLE `user` (
  `id` int NOT NULL AUTO_INCREMENT,
  `email` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL,
  `password` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL,
  `name` varchar(255) NOT NULL,
  `type` int NOT NULL,
  `created_at` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=12 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

INSERT INTO `chat` (`id`, `chat_room_member_id`, `chat_room_id`, `message`, `type`, `created_at`) VALUES
(10, 6, 18, 'おーい', 1, '2021-07-18 23:54:47'),
(11, 6, 18, 'おーー', 1, '2021-07-19 00:02:51'),
(12, 9, 20, 'おいー', 1, '2021-07-19 11:06:18'),
(13, 9, 20, 'おー', 1, '2021-07-19 11:09:42'),
(14, 10, 20, 'o', 1, '2021-07-19 11:21:20'),
(15, 9, 20, 'あおー', 1, '2021-07-19 11:49:27'),
(16, 9, 20, 'あいー', 1, '2021-07-19 11:55:07'),
(17, 9, 20, 'おーーーーーー', 1, '2021-07-19 11:55:47'),
(18, 9, 20, 'いーーーーーー', 1, '2021-07-19 11:55:59'),
(19, 9, 20, 'ｌ', 1, '2021-07-19 11:56:13'),
(20, 9, 20, 'あああああああ', 1, '2021-07-19 12:01:05'),
(21, 9, 20, 'おー', 1, '2021-07-19 12:09:20'),
(22, 9, 20, 'a', 1, '2021-07-19 12:10:32'),
(23, 9, 20, 'e', 1, '2021-07-19 12:11:06'),
(24, 9, 20, 'o', 1, '2021-07-19 12:12:06'),
(25, 9, 20, ' あいうえお', 1, '2021-07-19 12:21:41'),
(26, 10, 20, 'うんこー', 1, '2021-07-19 12:21:46'),
(27, 11, 21, 'あー', 1, '2021-07-19 12:22:37'),
(28, 13, 22, 'さんご', 1, '2021-07-19 13:44:37'),
(29, 15, 24, 'こんにちわ！', 1, '2021-07-20 09:31:41'),
(30, 15, 24, '', 1, '2021-07-20 09:31:43'),
(31, 15, 24, 'うおー', 1, '2021-07-20 09:33:49'),
(33, 15, 24, '-', 1, '2021-07-20 09:44:53'),
(34, 15, 24, 'o-', 1, '2021-07-20 09:45:02'),
(35, 15, 24, '-o', 1, '2021-07-20 09:45:10'),
(36, 15, 24, 'おー', 1, '2021-07-20 09:46:25'),
(37, 16, 24, 'きたきた', 1, '2021-07-20 09:46:32'),
(38, 15, 24, 'o-', 1, '2021-07-20 09:46:41'),
(39, 15, 24, 'o -', 1, '2021-07-20 09:46:45'),
(40, 15, 24, '', 1, '2021-07-20 09:46:52'),
(41, 15, 24, 'o-', 1, '2021-07-20 09:46:55');

INSERT INTO `chat_room` (`id`, `chat_room_name`, `password`, `created_at`) VALUES
(16, 'うんち', '', '2021-07-18 22:40:54'),
(17, 'うんち２', '', '2021-07-18 22:41:06'),
(18, 'うんち', '', '2021-07-18 23:46:42'),
(19, 'asdaa', '', '2021-07-18 23:46:51'),
(20, 'テスト', '', '2021-07-19 11:05:44'),
(21, 'うんち', '', '2021-07-19 12:21:58'),
(22, 'asdaa', '', '2021-07-19 13:41:40'),
(23, 'うんち', '', '2021-07-19 13:44:42'),
(24, 'テスト', '', '2021-07-20 09:31:15');

INSERT INTO `chat_room_member` (`id`, `user_id`, `chat_room_id`, `created_at`) VALUES
(6, 4, 18, '2021-07-18 23:46:42'),
(7, 4, 19, '2021-07-18 23:46:51'),
(8, 6, 18, '2021-07-19 01:19:32'),
(9, 7, 20, '2021-07-19 11:05:44'),
(10, 8, 20, '2021-07-19 11:06:12'),
(11, 7, 21, '2021-07-19 12:21:58'),
(12, 8, 21, '2021-07-19 12:22:20'),
(13, 9, 22, '2021-07-19 13:41:40'),
(14, 9, 23, '2021-07-19 13:44:42'),
(15, 10, 24, '2021-07-20 09:31:15'),
(16, 11, 24, '2021-07-20 09:31:31');

INSERT INTO `user` (`id`, `email`, `password`, `name`, `type`, `created_at`) VALUES
(1, NULL, NULL, 'guest', 0, NULL),
(2, 'null', 'null', 'ゲスト80', 5, NULL),
(3, 'null', 'null', 'ゲスト91', 5, NULL),
(4, 'null', 'null', 'ゲスト96', 5, NULL),
(5, 'null', 'null', 'ゲスト44', 5, NULL),
(6, 'null', 'null', 'ゲスト60', 5, NULL),
(7, 'null', 'null', 'ゲスト43', 5, NULL),
(8, 'null', 'null', 'ゲスト14', 5, NULL),
(9, 'null', 'null', 'ゲスト43', 5, NULL),
(10, 'null', 'null', 'ゲスト83', 5, NULL),
(11, 'null', 'null', 'ゲスト30', 5, NULL);



/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;