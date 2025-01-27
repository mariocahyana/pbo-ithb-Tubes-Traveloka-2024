-- phpMyAdmin SQL Dump
-- version 5.2.1
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: Dec 18, 2024 at 08:06 AM
-- Server version: 10.4.32-MariaDB
-- PHP Version: 8.2.12

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `tubespbo`
--

-- --------------------------------------------------------

--
-- Table structure for table `airline`
--

CREATE TABLE `airline` (
  `airlineID` int(11) NOT NULL,
  `airline_name` varchar(10) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

INSERT INTO `airline` (`airlineID`, `airline_name`) VALUES
(1, 'GARUDA');

--
-- Table structure for table `airplane`
--

CREATE TABLE `airplane` (
  `airplaneID` int(11) NOT NULL,
  `airlineID` int(11) NOT NULL,
  `airline_name` varchar(10) NOT NULL,
  `airplane_name` varchar(10) NOT NULL,
  `seat` int(2) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

INSERT INTO `airplane` (`airplaneID`, `airlineID`, `airline_name`, `airplane_name`, `seat`) VALUES
(1, 1, 'GARUDA', 'GRD-001', 12);

--
-- Table structure for table `airport`
--

CREATE TABLE `airport` (
  `airportID` int(11) NOT NULL,
  `city` varchar(10) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

INSERT INTO `airport` (`airportID`, `city`) VALUES
(1, 'BANDUNG'),
(2, 'JAKARTA');

--
-- Table structure for table `flight`
--

CREATE TABLE `flight` (
  `flightID` int(11) NOT NULL,
  `flight_name` varchar(10) NOT NULL,
  `airplaneID` int(11) NOT NULL,
  `airplane_name` varchar(10) NOT NULL,
  `origin` int(11) NOT NULL,
  `destination` int(11) NOT NULL,
  `count_ticket` int(11) NOT NULL,
  `origin_city` varchar(10) NOT NULL,
  `destination_city` varchar(10) NOT NULL,
  `depature_time` date NOT NULL,
  `arrival_time` date NOT NULL,
  `flight_class` enum('ECONOMY','BUSINESS','FIRSTCLASS') NOT NULL,
  `price_flight` double NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------


INSERT INTO `flight` (`flightID`, `flight_name`, `airplaneID`, `airplane_name`, `origin`, `destination`, `count_ticket`, `origin_city`, `destination_city`, `depature_time`, `arrival_time`, `flight_class`, `price_flight`) VALUES
(1, 'BDG-JKT-BNS', 1, 'GRD-001', 1, 2, 12, 'BANDUNG', 'JAKARTA', '2025-01-10', '2025-01-10', 'BUSINESS', 350);

--
-- Table structure for table `password_reset`
--

CREATE TABLE `password_reset` (
  `resetID` int(11) NOT NULL,
  `userID` int(11) NOT NULL,
  `newPassword` varchar(255) NOT NULL,
  `status` enum('PENDING','APPROVED','REJECTED') DEFAULT 'PENDING',
  `request_date` timestamp NOT NULL DEFAULT current_timestamp()
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Table structure for table `reply`
--

CREATE TABLE `reply` (
  `reviewID` int(11) NOT NULL,
  `userID` int(11) NOT NULL,
  `reply_admin` text NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Table structure for table `reschedule_request`
--

CREATE TABLE `reschedule_request` (
  `requestID` int(11) NOT NULL,
  `userID` int(11) NOT NULL,
  `old_transaksiID` int(11) NOT NULL,
  `new_flightID` int(11) DEFAULT NULL,
  `reason` varchar(255) NOT NULL,
  `status` enum('PENDING','APPROVED','REJECTED') DEFAULT 'PENDING',
  `request_date` timestamp NOT NULL DEFAULT current_timestamp(),
  `process_date` timestamp NULL DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;
-- --------------------------------------------------------

--
-- Table structure for table `review`
--

CREATE TABLE `review` (
  `reviewID` int(11) NOT NULL,
  `userID` int(11) NOT NULL,
  `customer_review` varchar(255) NOT NULL,
  `date_review` date NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Table structure for table `topup_requests`
--

CREATE TABLE `topup_requests` (
  `requestID` int(11) NOT NULL,
  `userID` int(11) NOT NULL,
  `amount` double NOT NULL,
  `status` enum('PENDING','APPROVED','REJECTED') DEFAULT 'PENDING',
  `request_date` timestamp NOT NULL DEFAULT current_timestamp()
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Table structure for table `transaksi`
--

CREATE TABLE `transaksi` (
  `transaksiID` int(11) NOT NULL,
  `userID` int(11) NOT NULL,
  `price_transaction` int(7) NOT NULL,
  `flightID` int(11) NOT NULL,
  `nik` int(16) NOT NULL,
  `name` varchar(10) NOT NULL,
  `date_transaksi` date DEFAULT NULL,
  `age` enum('BALITA','DEWASA','LANSIA') DEFAULT NULL,
  `payment_confirmation` enum('PAYNOW','PAYLATER','CANCEL') NOT NULL,
  `active_ticket` enum('ACTIVE','NONACTIVE') NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Table structure for table `users`
--

CREATE TABLE `users` (
  `userID` int(11) NOT NULL,
  `name` varchar(10) NOT NULL,
  `password` varchar(10) NOT NULL,
  `email` varchar(10) DEFAULT NULL,
  `phone_num` varchar(13) DEFAULT NULL,
  `balance` double(8,0) DEFAULT NULL,
  `role` enum('CUSTOMER','ADMIN') NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

INSERT INTO `users` (`userID`, `name`, `password`, `email`, `phone_num`, `balance`, `role`) VALUES
(1, 'mc', 'pass123', 'mc@mail.co', NULL, NULL, 'ADMIN'),
(2, 'jpb', 'pass456', 'jpb@mail.c', NULL, NULL, 'ADMIN'),
(3, 'def', 'pass789', 'def@mail.c', NULL, NULL, 'ADMIN');

--
-- Indexes for dumped tables
--

--
-- Indexes for table `airline`
--
ALTER TABLE `airline`
  ADD PRIMARY KEY (`airlineID`),
  ADD UNIQUE KEY `unique_airline_name` (`airline_name`);

--
-- Indexes for table `airplane`
--
ALTER TABLE `airplane`
  ADD PRIMARY KEY (`airplaneID`),
  ADD KEY `airlineID` (`airlineID`),
  ADD UNIQUE KEY `unique_airplane_name` (`airplane_name`);


--
-- Indexes for table `airport`
--
ALTER TABLE `airport`
  ADD PRIMARY KEY (`airportID`),
  ADD UNIQUE KEY `unique_airport_name` (`city`);

--
-- Indexes for table `flight`
--
ALTER TABLE `flight`
  ADD PRIMARY KEY (`flightID`),
  ADD KEY `airplaneID` (`airplaneID`),
  ADD KEY `origin` (`origin`),
  ADD KEY `destination` (`destination`),
  ADD UNIQUE KEY `unique_flight_name` (`flight_name`);

--
-- Indexes for table `password_reset`
--
ALTER TABLE `password_reset`
  ADD PRIMARY KEY (`resetID`),
  ADD KEY `userID` (`userID`);

--
-- Indexes for table `reply`
--
ALTER TABLE `reply`
  ADD PRIMARY KEY (`reviewID`,`userID`),
  ADD KEY `userID` (`userID`);

--
-- Indexes for table `reschedule_request`
--
ALTER TABLE `reschedule_request`
  ADD PRIMARY KEY (`requestID`),
  ADD KEY `userID` (`userID`),
  ADD KEY `old_transaksiID` (`old_transaksiID`),
  ADD KEY `new_flightID` (`new_flightID`);

--
-- Indexes for table `review`
--
ALTER TABLE `review`
  ADD PRIMARY KEY (`reviewID`),
  ADD KEY `userID` (`userID`);

--
-- Indexes for table `topup_requests`
--
ALTER TABLE `topup_requests`
  ADD PRIMARY KEY (`requestID`),
  ADD KEY `userID` (`userID`);

--
-- Indexes for table `transaksi`
--
ALTER TABLE `transaksi`
  ADD PRIMARY KEY (`transaksiID`),
  ADD KEY `userID` (`userID`);

--
-- Indexes for table `users`
--
ALTER TABLE `users`
  ADD PRIMARY KEY (`userID`),
  ADD UNIQUE KEY `unique_users_email` (`email`),
  ADD UNIQUE KEY `unique_users_phone_num` (`phone_num`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `airline`
--
ALTER TABLE `airline`
  MODIFY `airlineID` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=2;

--
-- AUTO_INCREMENT for table `airplane`
--
ALTER TABLE `airplane`
  MODIFY `airplaneID` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=2;

--
-- AUTO_INCREMENT for table `airport`
--
ALTER TABLE `airport`
  MODIFY `airportID` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;

--
-- AUTO_INCREMENT for table `flight`
--
ALTER TABLE `flight`
  MODIFY `flightID` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=2;

--
-- AUTO_INCREMENT for table `password_reset`
--
ALTER TABLE `password_reset`
  MODIFY `resetID` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;

--
-- AUTO_INCREMENT for table `reply`
--
ALTER TABLE `reply`
  MODIFY `reviewID` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT for table `reschedule_request`
--
ALTER TABLE `reschedule_request`
  MODIFY `requestID` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT for table `review`
--
ALTER TABLE `review`
  MODIFY `reviewID` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT for table `topup_requests`
--
ALTER TABLE `topup_requests`
  MODIFY `requestID` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=10;

--
-- AUTO_INCREMENT for table `transaksi`
--
ALTER TABLE `transaksi`
  MODIFY `transaksiID` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT for table `users`
--
ALTER TABLE `users`
  MODIFY `userID` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;

--
-- Constraints for dumped tables
--

--
-- Constraints for table `airplane`
--
ALTER TABLE `airplane`
  ADD CONSTRAINT `airplane_ibfk_1` FOREIGN KEY (`airlineID`) REFERENCES `airline` (`airlineID`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Constraints for table `flight`
--
ALTER TABLE `flight`
  ADD CONSTRAINT `flight_ibfk_1` FOREIGN KEY (`airplaneID`) REFERENCES `airplane` (`airplaneID`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `flight_ibfk_2` FOREIGN KEY (`origin`) REFERENCES `airport` (`airportID`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `flight_ibfk_3` FOREIGN KEY (`destination`) REFERENCES `airport` (`airportID`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Constraints for table `password_reset`
--
ALTER TABLE `password_reset`
  ADD CONSTRAINT `password_reset_ibfk_1` FOREIGN KEY (`userID`) REFERENCES `users` (`userID`);

--
-- Constraints for table `reply`
--
ALTER TABLE `reply`
  ADD CONSTRAINT `reply_ibfk_1` FOREIGN KEY (`reviewID`) REFERENCES `review` (`reviewID`) ON DELETE CASCADE,
  ADD CONSTRAINT `reply_ibfk_2` FOREIGN KEY (`userID`) REFERENCES `users` (`userID`) ON DELETE CASCADE;


--
-- Constraints for table `reschedule_request`
--
ALTER TABLE `reschedule_request`
  ADD CONSTRAINT `reschedule_request_ibfk_1` FOREIGN KEY (`userID`) REFERENCES `users` (`userID`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `reschedule_request_ibfk_2` FOREIGN KEY (`old_transaksiID`) REFERENCES `transaksi` (`transaksiID`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `reschedule_request_ibfk_3` FOREIGN KEY (`new_flightID`) REFERENCES `flight` (`flightID`) ON DELETE SET NULL ON UPDATE CASCADE;

--
-- Constraints for table `review`
--
ALTER TABLE `review`
  ADD CONSTRAINT `review_ibfk_1` FOREIGN KEY (`userID`) REFERENCES `users` (`userID`);

--
-- Constraints for table `topup_requests`
--
ALTER TABLE `topup_requests`
  ADD CONSTRAINT `topup_requests_ibfk_1` FOREIGN KEY (`userID`) REFERENCES `users` (`userID`);

--
-- Constraints for table `transaksi`
--
ALTER TABLE `transaksi`
  ADD CONSTRAINT `transaksi_ibfk_1` FOREIGN KEY (`userID`) REFERENCES `users` (`userID`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
