-- phpMyAdmin SQL Dump
-- version 5.2.1
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Waktu pembuatan: 01 Jul 2026 pada 19.08
-- Versi server: 10.4.32-MariaDB
-- Versi PHP: 8.2.12

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `db_ewallet`
--

-- --------------------------------------------------------

--
-- Struktur dari tabel `balance_logs`
--

CREATE TABLE `balance_logs` (
  `id` int(11) NOT NULL,
  `user_id` int(11) NOT NULL,
  `previous_balance` decimal(12,2) NOT NULL,
  `current_balance` decimal(12,2) NOT NULL,
  `balance_change` decimal(12,2) NOT NULL,
  `activity_type` varchar(100) NOT NULL,
  `created_at` timestamp NOT NULL DEFAULT current_timestamp()
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Struktur dari tabel `invoices`
--

CREATE TABLE `invoices` (
  `id` int(11) NOT NULL,
  `user_id` int(11) NOT NULL,
  `invoice_type` varchar(100) NOT NULL,
  `semester` varchar(20) NOT NULL,
  `academic_year` varchar(20) NOT NULL,
  `amount` decimal(12,2) NOT NULL,
  `invoice_status` enum('paid','unpaid') DEFAULT 'unpaid',
  `due_date` date NOT NULL,
  `created_at` timestamp NOT NULL DEFAULT current_timestamp()
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data untuk tabel `invoices`
--

INSERT INTO `invoices` (`id`, `user_id`, `invoice_type`, `semester`, `academic_year`, `amount`, `invoice_status`, `due_date`, `created_at`) VALUES
(1, 4, 'UKT', '4', '2026', 1600000.00, 'paid', '2026-06-25', '2026-06-07 09:22:08'),
(2, 100, 'UKT', '4', '2026', 1600000.00, 'paid', '2026-08-10', '2026-06-28 14:20:59');

-- --------------------------------------------------------

--
-- Struktur dari tabel `transactions`
--

CREATE TABLE `transactions` (
  `id` int(11) NOT NULL,
  `sender_id` int(11) NOT NULL,
  `receiver_id` int(11) DEFAULT NULL,
  `transaction_type` enum('topup','transfer','payment','withdraw','refund','adjustment') NOT NULL,
  `transaction_status` enum('pending','success','failed') DEFAULT 'success',
  `amount` decimal(12,2) NOT NULL,
  `transaction_date` timestamp NOT NULL DEFAULT current_timestamp()
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data untuk tabel `transactions`
--

INSERT INTO `transactions` (`id`, `sender_id`, `receiver_id`, `transaction_type`, `transaction_status`, `amount`, `transaction_date`) VALUES
(1, 4, NULL, 'topup', 'success', 125000.00, '2026-06-04 18:22:09'),
(2, 4, NULL, 'topup', 'success', 1.00, '2026-06-04 18:22:16'),
(3, 4, NULL, 'topup', 'success', 9999.00, '2026-06-04 18:22:21'),
(4, 4, NULL, 'topup', 'success', 90000.00, '2026-06-04 18:31:07'),
(5, 4, NULL, 'topup', 'success', 25000.00, '2026-06-04 18:36:27'),
(6, 5, NULL, 'topup', 'success', 1000000.00, '2026-06-07 08:30:18'),
(7, 5, 4, 'transfer', 'success', 10000.00, '2026-06-07 08:30:51'),
(8, 4, 5, 'transfer', 'success', 90002.00, '2026-06-07 08:33:12'),
(9, 4, NULL, 'topup', 'success', 2000000.00, '2026-06-07 09:28:49'),
(10, 4, NULL, 'payment', 'success', 1600000.00, '2026-06-07 09:28:57'),
(11, 5, 4, 'transfer', 'success', 10000.00, '2026-06-27 15:21:46'),
(12, 4, 100, 'transfer', 'success', 100000.00, '2026-06-28 13:42:20'),
(13, 100, NULL, 'topup', 'success', 2000000.00, '2026-06-28 14:18:11'),
(14, 100, 4, 'transfer', 'success', 100000.00, '2026-06-28 14:19:00'),
(15, 100, NULL, 'payment', 'success', 1600000.00, '2026-06-28 14:21:16');

-- --------------------------------------------------------

--
-- Struktur dari tabel `users`
--

CREATE TABLE `users` (
  `id` int(11) NOT NULL,
  `username` varchar(50) NOT NULL,
  `password` varchar(255) NOT NULL,
  `full_name` varchar(100) NOT NULL,
  `student_id` varchar(20) NOT NULL,
  `email` varchar(100) DEFAULT NULL,
  `phone_number` varchar(20) DEFAULT NULL,
  `transaction_pin` varchar(10) NOT NULL,
  `role` enum('admin','user') NOT NULL,
  `balance` decimal(12,2) DEFAULT 0.00,
  `created_at` timestamp NOT NULL DEFAULT current_timestamp(),
  `last_login` timestamp NULL DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data untuk tabel `users`
--

INSERT INTO `users` (`id`, `username`, `password`, `full_name`, `student_id`, `email`, `phone_number`, `transaction_pin`, `role`, `balance`, `created_at`, `last_login`) VALUES
(4, 'rafi', 'bambon78', 'Rafi Kamaly', '241011401981', 'rafikamaly@gmail.com', '0895383146202', '123456', 'user', 579998.00, '2026-05-29 14:15:43', '2026-06-28 14:23:58'),
(5, 'raka', 'bambon78', 'Raka', '2401011401982', 'raka@gmail.com', '089538314620', '123456', 'user', 1070002.00, '2026-06-04 17:29:02', '2026-06-27 15:21:34'),
(99, 'admin', 'admin123', 'Admin UNPAM', '12345', 'admin@unpam.ac.id', '08123456789', '123456', 'admin', 0.00, '2026-06-07 08:37:58', '2026-06-28 14:25:32'),
(100, 'diantech', 'qwerty123', 'Dian Abdul Manan', '241011400581', 'yanskuy00@gmail.com', '085860032740', '101010', 'user', 400000.00, '2026-06-27 15:51:36', '2026-06-28 14:21:09'),
(101, 'haryady', 'haryady123', 'Haryady Laksana Putra', '241011401988', 'haryady@gmail.com', '08953874121', '123123', 'user', 0.00, '2026-06-28 14:25:24', NULL);

--
-- Indexes for dumped tables
--

--
-- Indeks untuk tabel `balance_logs`
--
ALTER TABLE `balance_logs`
  ADD PRIMARY KEY (`id`),
  ADD KEY `user_id` (`user_id`);

--
-- Indeks untuk tabel `invoices`
--
ALTER TABLE `invoices`
  ADD PRIMARY KEY (`id`),
  ADD KEY `user_id` (`user_id`);

--
-- Indeks untuk tabel `transactions`
--
ALTER TABLE `transactions`
  ADD PRIMARY KEY (`id`),
  ADD KEY `sender_id` (`sender_id`),
  ADD KEY `receiver_id` (`receiver_id`);

--
-- Indeks untuk tabel `users`
--
ALTER TABLE `users`
  ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `username` (`username`),
  ADD UNIQUE KEY `student_id` (`student_id`),
  ADD UNIQUE KEY `email` (`email`);

--
-- AUTO_INCREMENT untuk tabel yang dibuang
--

--
-- AUTO_INCREMENT untuk tabel `balance_logs`
--
ALTER TABLE `balance_logs`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT untuk tabel `invoices`
--
ALTER TABLE `invoices`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;

--
-- AUTO_INCREMENT untuk tabel `transactions`
--
ALTER TABLE `transactions`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=16;

--
-- AUTO_INCREMENT untuk tabel `users`
--
ALTER TABLE `users`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=102;

--
-- Ketidakleluasaan untuk tabel pelimpahan (Dumped Tables)
--

--
-- Ketidakleluasaan untuk tabel `balance_logs`
--
ALTER TABLE `balance_logs`
  ADD CONSTRAINT `balance_logs_ibfk_1` FOREIGN KEY (`user_id`) REFERENCES `users` (`id`);

--
-- Ketidakleluasaan untuk tabel `invoices`
--
ALTER TABLE `invoices`
  ADD CONSTRAINT `invoices_ibfk_1` FOREIGN KEY (`user_id`) REFERENCES `users` (`id`);

--
-- Ketidakleluasaan untuk tabel `transactions`
--
ALTER TABLE `transactions`
  ADD CONSTRAINT `transactions_ibfk_1` FOREIGN KEY (`sender_id`) REFERENCES `users` (`id`),
  ADD CONSTRAINT `transactions_ibfk_2` FOREIGN KEY (`receiver_id`) REFERENCES `users` (`id`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
