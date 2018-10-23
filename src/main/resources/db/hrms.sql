-- phpMyAdmin SQL Dump
-- version 4.8.3
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1:3306
-- Generation Time: Oct 23, 2018 at 11:35 AM
-- Server version: 5.7.23
-- PHP Version: 7.2.10

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET AUTOCOMMIT = 0;
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `hrms`
--

-- --------------------------------------------------------

--
-- Table structure for table `designations`
--

DROP TABLE IF EXISTS `designations`;
CREATE TABLE IF NOT EXISTS `designations` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `designation` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UKaixwd91oi1xwxlqywh3q9l8bs` (`designation`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=latin1;

--
-- Dumping data for table `designations`
--

INSERT INTO `designations` (`id`, `designation`) VALUES
(5, 'Architect'),
(6, 'Manager'),
(3, 'Senior Solution Developer'),
(2, 'Solution developer'),
(4, 'Tech Lead'),
(1, 'Trainee');

-- --------------------------------------------------------

--
-- Table structure for table `users`
--

DROP TABLE IF EXISTS `users`;
CREATE TABLE IF NOT EXISTS `users` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `email` varchar(60) DEFAULT NULL,
  `is_active` varchar(60) DEFAULT NULL,
  `password` varchar(128) DEFAULT NULL,
  `user_name` varchar(60) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UKk8d0f2n7n88w1a16yhua64onx` (`user_name`),
  UNIQUE KEY `UK6dotkott2kjsp8vw4d0m25fb7` (`email`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=latin1;

--
-- Dumping data for table `users`
--

INSERT INTO `users` (`id`, `email`, `is_active`, `password`, `user_name`) VALUES
(1, 'mahendra@outlook.com', 'true', '1234', 'maddy'),
(2, 'mahendragohel@outlook.com', 'true', '1234', 'mahendra'),
(3, 'mahendra.gohel@outlook.com', 'true', '1234', 'mahendra.gohel'),
(4, 'mahendra@gmail.com', 'true', '12345678', 'mahendragohel'),
(5, 'mahendra.gohel@gmail.com', 'true', '12345678', 'maddy0139'),
(6, 'mahendra1690@gmail.com', 'true', '12345678', 'maddy1690');

-- --------------------------------------------------------

--
-- Table structure for table `user_address`
--

DROP TABLE IF EXISTS `user_address`;
CREATE TABLE IF NOT EXISTS `user_address` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `address1` varchar(100) DEFAULT NULL,
  `address2` varchar(100) DEFAULT NULL,
  `city` varchar(60) DEFAULT NULL,
  `country` varchar(60) DEFAULT NULL,
  `phone_number` varchar(15) DEFAULT NULL,
  `state` varchar(60) DEFAULT NULL,
  `zip_code` varchar(60) DEFAULT NULL,
  `user_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKrmincuqpi8m660j1c57xj7twr` (`user_id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=latin1;

--
-- Dumping data for table `user_address`
--

INSERT INTO `user_address` (`id`, `address1`, `address2`, `city`, `country`, `phone_number`, `state`, `zip_code`, `user_id`) VALUES
(1, 'Flat No. 3, jairaj Apartment', 'New DP Road, Vishal Nagar', 'Pune', 'India', '8000092239', 'Maharashtra', '411027', 1),
(2, 'Flat No. 3, jairaj Apartment', 'New DP Road, Vishal Nagar, Pimple Nilakh', 'Pune', 'India', '8000092239', 'Maharashtra', '411027', 3),
(3, 'Flat No. 3, jairaj Apartment', 'New DP Road, Vishal Nagar, Pimple Nilakh', 'Pune', 'India', '8000092239', 'Maharashtra', '411027', 3);

-- --------------------------------------------------------

--
-- Table structure for table `user_profiles`
--

DROP TABLE IF EXISTS `user_profiles`;
CREATE TABLE IF NOT EXISTS `user_profiles` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `first_name` varchar(60) DEFAULT NULL,
  `gender` varchar(255) DEFAULT NULL,
  `is_active` varchar(60) DEFAULT NULL,
  `last_name` varchar(60) DEFAULT NULL,
  `designation_id` bigint(20) DEFAULT NULL,
  `user_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UK_e5h89rk3ijvdmaiig4srogdc6` (`user_id`),
  KEY `FKgktdjql8criai5kj0fru5noiq` (`designation_id`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=latin1;

--
-- Dumping data for table `user_profiles`
--

INSERT INTO `user_profiles` (`id`, `first_name`, `gender`, `is_active`, `last_name`, `designation_id`, `user_id`) VALUES
(1, 'Mahendra', 'MALE', 'true', 'Gohel', 1, 1),
(2, 'Mahendra', 'MALE', 'true', 'Gohel', 2, 2),
(3, 'Mahendra', 'MALE', 'true', 'Gohel', 3, 3),
(4, 'Mahendra', 'MALE', 'true', 'Gohel', 4, 4),
(5, 'Mahendra', 'MALE', 'true', 'Gohel', 5, 5),
(6, 'Swapnil', 'MALE', 'true', 'Phatale', 2, 6);

--
-- Constraints for dumped tables
--

--
-- Constraints for table `user_address`
--
ALTER TABLE `user_address`
  ADD CONSTRAINT `FKrmincuqpi8m660j1c57xj7twr` FOREIGN KEY (`user_id`) REFERENCES `users` (`id`);

--
-- Constraints for table `user_profiles`
--
ALTER TABLE `user_profiles`
  ADD CONSTRAINT `FKgktdjql8criai5kj0fru5noiq` FOREIGN KEY (`designation_id`) REFERENCES `designations` (`id`),
  ADD CONSTRAINT `FKjcad5nfve11khsnpwj1mv8frj` FOREIGN KEY (`user_id`) REFERENCES `users` (`id`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
