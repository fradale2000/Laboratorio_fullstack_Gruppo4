-- phpMyAdmin SQL Dump
-- version 5.2.0
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Creato il: Dic 15, 2022 alle 09:14
-- Versione del server: 10.4.25-MariaDB
-- Versione PHP: 7.4.30

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `fullstack`
--

-- --------------------------------------------------------

--
-- Struttura della tabella `domande`
--

CREATE TABLE `domande` (
  `iddomanda` int(11) NOT NULL,
  `testodomanda` varchar(500) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dump dei dati per la tabella `domande`
--

INSERT INTO `domande` (`iddomanda`, `testodomanda`) VALUES
(1, 'Trovi che sia sufficientemente attrezzata l\'aula in cui frequenti il corso?'),
(2, 'Sei soddisfatto dei servizi della segreteria?'),
(3, 'Sei soddisfatto dai contenuti proposti dal corso?'),
(4, 'Il tempo necessario per questo corso è stato appropriato?'),
(5, 'Valuta la qualità tecnica dei materiali del corso'),
(6, 'Valuta la disponibilità del supporto tecnico'),
(7, 'Quanto sei soddisfatto del corso?'),
(8, 'Ti ritieni soddisfatto dalle spalle poderose di Marco Brancaccio?');

-- --------------------------------------------------------

--
-- Struttura della tabella `feedbacks`
--

CREATE TABLE `feedbacks` (
  `idfeedback` int(11) NOT NULL,
  `voto` int(11) NOT NULL,
  `datafeedback` datetime NOT NULL,
  `idutente` int(11) NOT NULL,
  `iddomanda` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dump dei dati per la tabella `feedbacks`
--

INSERT INTO `feedbacks` (`idfeedback`, `voto`, `datafeedback`, `idutente`, `iddomanda`) VALUES
(1, 2, '2022-12-05 17:04:55', 16, 4),
(2, 3, '2022-12-05 17:04:55', 16, 6),
(3, 1, '2022-12-05 17:04:55', 16, 5),
(4, 2, '2022-12-05 17:04:55', 16, 2),
(5, 3, '2022-12-09 13:21:59', 16, 2),
(6, 1, '2022-12-09 13:21:59', 16, 1),
(7, 4, '2022-12-09 13:21:59', 16, 5),
(8, 1, '2022-12-09 13:21:59', 16, 3),
(13, 4, '2022-12-09 13:23:29', 15, 1),
(14, 3, '2022-12-09 13:23:29', 15, 6),
(15, 2, '2022-12-09 13:23:29', 15, 4),
(16, 1, '2022-12-09 13:23:29', 15, 5),
(17, 1, '2022-12-09 15:54:40', 16, 5),
(18, 3, '2022-12-09 15:54:40', 16, 4),
(19, 2, '2022-12-09 15:54:40', 16, 6),
(20, 1, '2022-12-09 15:54:40', 16, 3),
(21, 4, '2022-12-09 15:54:55', 16, 2),
(22, 2, '2022-12-09 15:54:55', 16, 6),
(23, 1, '2022-12-09 15:54:55', 16, 3),
(24, 3, '2022-12-09 15:54:55', 16, 1),
(25, 2, '2022-12-09 15:55:04', 16, 3),
(26, 1, '2022-12-09 15:55:04', 16, 4),
(27, 4, '2022-12-09 15:55:04', 16, 7),
(28, 3, '2022-12-09 15:55:04', 16, 2),
(29, 1, '2022-12-09 15:55:18', 16, 6),
(30, 2, '2022-12-09 15:55:18', 16, 1),
(31, 3, '2022-12-09 15:55:18', 16, 8),
(32, 4, '2022-12-09 15:55:18', 16, 2),
(33, 4, '2022-12-09 15:55:28', 16, 6),
(34, 3, '2022-12-09 15:55:28', 16, 3),
(35, 2, '2022-12-09 15:55:28', 16, 7),
(36, 1, '2022-12-09 15:55:28', 16, 8),
(37, 4, '2022-12-09 15:56:11', 16, 2),
(38, 3, '2022-12-09 15:56:11', 16, 6),
(39, 3, '2022-12-09 15:56:11', 16, 5),
(40, 1, '2022-12-09 15:56:11', 16, 7),
(41, 2, '2022-12-09 15:57:49', 16, 8),
(42, 1, '2022-12-09 15:57:49', 16, 4),
(43, 4, '2022-12-09 15:57:49', 16, 6),
(44, 3, '2022-12-09 15:57:49', 16, 1),
(45, 4, '2022-12-10 15:10:28', 16, 7),
(46, 1, '2022-12-10 15:10:28', 16, 2),
(47, 2, '2022-12-10 15:10:28', 16, 4),
(48, 3, '2022-12-10 15:10:28', 16, 6),
(49, 2, '2022-12-11 17:05:16', 15, 1),
(50, 3, '2022-12-11 17:05:16', 15, 5),
(51, 1, '2022-12-11 17:05:16', 15, 7),
(52, 4, '2022-12-11 17:05:16', 15, 6),
(77, 3, '2022-12-13 20:03:47', 31, 8),
(78, 3, '2022-12-13 20:03:47', 31, 6),
(79, 2, '2022-12-13 20:03:47', 31, 7),
(80, 3, '2022-12-13 20:03:47', 31, 5),
(81, 3, '2022-12-13 21:52:30', 32, 1),
(82, 2, '2022-12-13 21:52:30', 32, 5),
(83, 3, '2022-12-13 21:52:30', 32, 4),
(84, 3, '2022-12-13 21:52:30', 32, 2),
(89, 1, '2022-12-14 14:32:45', 36, 2),
(90, 2, '2022-12-14 14:32:45', 36, 3),
(91, 3, '2022-12-14 14:32:45', 36, 6),
(92, 4, '2022-12-14 14:32:45', 36, 7),
(93, 1, '2022-12-14 14:36:08', 34, 6),
(94, 2, '2022-12-14 14:36:08', 34, 1),
(95, 3, '2022-12-14 14:36:08', 34, 4),
(96, 2, '2022-12-14 14:36:08', 34, 5),
(101, 1, '2022-12-11 14:40:34', 35, 4),
(102, 2, '2022-12-11 14:40:34', 35, 5),
(103, 2, '2022-12-11 14:40:34', 35, 6),
(104, 4, '2022-12-11 14:40:34', 35, 2),
(105, 1, '2022-12-11 14:41:59', 35, 2),
(106, 2, '2022-12-11 14:41:59', 35, 1),
(107, 3, '2022-12-11 14:41:59', 35, 7),
(108, 4, '2022-12-11 14:41:59', 35, 6),
(125, 1, '2022-12-14 16:45:17', 38, 3),
(126, 3, '2022-12-14 16:45:17', 38, 4),
(127, 3, '2022-12-14 16:45:17', 38, 2),
(128, 3, '2022-12-14 16:45:17', 38, 5),
(129, 3, '2022-12-14 17:16:11', 39, 2),
(130, 3, '2022-12-14 17:16:11', 39, 5),
(131, 3, '2022-12-14 17:16:11', 39, 7),
(132, 3, '2022-12-14 17:16:11', 39, 3);

-- --------------------------------------------------------

--
-- Struttura della tabella `utenti`
--

CREATE TABLE `utenti` (
  `idutente` int(11) NOT NULL,
  `nome` varchar(20) NOT NULL,
  `cognome` varchar(20) NOT NULL,
  `email` varchar(100) NOT NULL,
  `pwd` text DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dump dei dati per la tabella `utenti`
--

INSERT INTO `utenti` (`idutente`, `nome`, `cognome`, `email`, `pwd`) VALUES
(15, 'france', 'dale', 'fradale@scuola.it', '0fe4f43e1dd173abc07ce508a74800e2'),
(16, 'prova', 'prova', 'prova@scuola.it', '5de864c9e801b37e5afebc22ac89653f'),
(17, 'Admin', 'admin', 'admin@admin.it', 'admin'),
(30, 'Samira', 'Boutabt', 'ciao@gmail.it', '6e6bc4e49dd477ebc98ef4046c067b5f'),
(31, 'Ceci', 'Baccolo', 'prova@prova.it', '772dfa9f45d0dd58e74ac988920e5708'),
(32, 'Edoardo', 'Alessandro', 'ealessandro@yahoo.com', '0fe4f43e1dd173abc07ce508a74800e2'),
(34, 'Mohamed', 'Osman', 'momo.osman@scuola.it', '06c56a89949d617def52f371c357b6db'),
(35, 'Marco', 'Brancaccio', 'marco.brancaccio@scuola.it', 'f5888d0bb58d611107e11f7cbc41c97a'),
(36, 'Andrea', 'Colombo', 'andrea.colo@scuola.it', '1c42f9c1ca2f65441465b43cd9339d6c'),
(37, 'Fabio', 'Fiora', 'fabio.fiora@scuola.it', 'a53bd0415947807bcb95ceec535820ee'),
(38, 'christian', 'mitra', 'chrimitra@scuola.it', '548b45f798559b5c738a555e2c157dba'),
(39, 'Chiara', 'Cardinali', 'chiara.cardinali@scuola.it', '243a3b6f7ddfea2599743ce3370d5229');

--
-- Indici per le tabelle scaricate
--

--
-- Indici per le tabelle `domande`
--
ALTER TABLE `domande`
  ADD PRIMARY KEY (`iddomanda`);

--
-- Indici per le tabelle `feedbacks`
--
ALTER TABLE `feedbacks`
  ADD PRIMARY KEY (`idfeedback`),
  ADD KEY `fk_utente` (`idutente`),
  ADD KEY `fk_domanda` (`iddomanda`);

--
-- Indici per le tabelle `utenti`
--
ALTER TABLE `utenti`
  ADD PRIMARY KEY (`idutente`),
  ADD UNIQUE KEY `email` (`email`);

--
-- AUTO_INCREMENT per le tabelle scaricate
--

--
-- AUTO_INCREMENT per la tabella `domande`
--
ALTER TABLE `domande`
  MODIFY `iddomanda` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=9;

--
-- AUTO_INCREMENT per la tabella `feedbacks`
--
ALTER TABLE `feedbacks`
  MODIFY `idfeedback` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=133;

--
-- AUTO_INCREMENT per la tabella `utenti`
--
ALTER TABLE `utenti`
  MODIFY `idutente` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=40;

--
-- Limiti per le tabelle scaricate
--

--
-- Limiti per la tabella `feedbacks`
--
ALTER TABLE `feedbacks`
  ADD CONSTRAINT `fk_domanda` FOREIGN KEY (`iddomanda`) REFERENCES `domande` (`iddomanda`),
  ADD CONSTRAINT `fk_utente` FOREIGN KEY (`idutente`) REFERENCES `utenti` (`idutente`) ON DELETE CASCADE;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
