-- phpMyAdmin SQL Dump
-- version 3.2.4
-- http://www.phpmyadmin.net
--
-- Host: localhost
-- Generation Time: Mar 29, 2014 at 10:54 PM
-- Server version: 5.1.41
-- PHP Version: 5.3.1

SET SQL_MODE="NO_AUTO_VALUE_ON_ZERO";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;

--
-- Database: `anri`
--

-- --------------------------------------------------------

--
-- Table structure for table `cuti`
--

CREATE TABLE IF NOT EXISTS `cuti` (
  `id_cuti` int(11) NOT NULL,
  `jenis_cuti` varchar(5) NOT NULL,
  `nama_cuti` varchar(20) NOT NULL,
  PRIMARY KEY (`id_cuti`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

--
-- Dumping data for table `cuti`
--

INSERT INTO `cuti` (`id_cuti`, `jenis_cuti`, `nama_cuti`) VALUES
(1, 'I', 'Izin'),
(2, 'S', 'Sakit'),
(3, 'TL', 'Tugas Luar'),
(4, 'CT', 'Cuti Tahunan');

-- --------------------------------------------------------

--
-- Table structure for table `kalender`
--

CREATE TABLE IF NOT EXISTS `kalender` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `tanggal` date NOT NULL,
  `libur` int(11) NOT NULL,
  `ket` text NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=MyISAM  DEFAULT CHARSET=latin1 AUTO_INCREMENT=77 ;

--
-- Dumping data for table `kalender`
--

INSERT INTO `kalender` (`id`, `tanggal`, `libur`, `ket`) VALUES
(1, '2014-03-21', 0, ''),
(2, '2014-03-22', 1, 'Sabtu'),
(3, '2014-03-23', 1, 'Minggu'),
(4, '2014-03-24', 0, ''),
(5, '2014-03-25', 0, ''),
(6, '2014-03-26', 0, ''),
(7, '2014-03-27', 0, ''),
(8, '2014-03-28', 0, ''),
(9, '2014-03-29', 1, 'Sabtu'),
(10, '2014-03-30', 1, 'Minggu'),
(11, '2014-03-31', 0, ''),
(12, '2014-04-01', 0, ''),
(13, '2014-04-02', 0, ''),
(14, '2014-04-03', 0, ''),
(15, '2014-04-04', 0, ''),
(16, '2014-04-05', 1, 'Sabtu'),
(17, '2014-04-06', 1, 'Minggu'),
(18, '2014-04-07', 0, ''),
(19, '2014-04-08', 0, ''),
(20, '2014-04-09', 0, ''),
(21, '2014-04-10', 0, ''),
(22, '2014-04-11', 0, ''),
(23, '2014-04-12', 1, 'Sabtu'),
(24, '2014-04-13', 1, 'Minggu'),
(25, '2014-04-14', 0, ''),
(26, '2014-04-15', 0, ''),
(27, '2014-04-16', 0, ''),
(28, '2014-04-17', 0, ''),
(29, '2014-04-18', 0, ''),
(30, '2014-04-19', 1, 'Sabtu'),
(31, '2014-04-20', 1, 'Minggu'),
(32, '2014-04-21', 0, ''),
(33, '2014-04-22', 0, ''),
(34, '2014-04-23', 0, ''),
(35, '2014-04-24', 0, ''),
(36, '2014-04-25', 0, ''),
(37, '2014-04-26', 1, 'Sabtu'),
(38, '2014-04-27', 1, 'Minggu'),
(39, '2014-04-28', 0, ''),
(40, '2014-04-29', 0, ''),
(41, '2014-04-30', 0, ''),
(42, '2014-05-01', 0, ''),
(43, '2014-05-02', 0, ''),
(44, '2014-05-03', 1, 'Sabtu'),
(45, '2014-05-04', 1, 'Minggu'),
(46, '2014-05-05', 0, ''),
(47, '2014-05-06', 0, ''),
(48, '2014-05-07', 0, ''),
(49, '2014-05-08', 0, ''),
(50, '2014-05-09', 0, ''),
(51, '2014-05-10', 1, 'Sabtu'),
(52, '2014-05-11', 1, 'Minggu'),
(53, '2014-05-12', 0, ''),
(54, '2014-05-13', 0, ''),
(55, '2014-05-14', 0, ''),
(56, '2014-05-15', 0, ''),
(57, '2014-05-16', 0, ''),
(58, '2014-05-17', 1, 'Sabtu'),
(59, '2014-05-18', 1, 'Minggu'),
(60, '2014-05-19', 0, ''),
(61, '2014-05-20', 0, ''),
(62, '2014-05-21', 0, ''),
(63, '2014-05-22', 0, ''),
(64, '2014-05-23', 0, ''),
(65, '2014-05-24', 1, 'Sabtu'),
(66, '2014-05-25', 1, 'Minggu'),
(67, '2014-05-26', 0, ''),
(68, '2014-05-27', 0, ''),
(69, '2014-05-28', 0, ''),
(70, '2014-05-29', 0, ''),
(71, '2014-05-30', 0, ''),
(72, '2014-05-31', 1, 'Sabtu'),
(73, '2014-06-01', 1, 'Minggu'),
(74, '2014-06-02', 0, ''),
(75, '2014-06-03', 0, ''),
(76, '2014-06-04', 0, '');

-- --------------------------------------------------------

--
-- Table structure for table `pegawai`
--

CREATE TABLE IF NOT EXISTS `pegawai` (
  `pin` int(11) NOT NULL,
  `nama` varchar(35) NOT NULL,
  `username` varchar(25) NOT NULL,
  `password` varchar(35) NOT NULL,
  `tahunlalu` int(11) NOT NULL,
  `tahunini` int(11) NOT NULL,
  `total` int(11) NOT NULL,
  `role` varchar(2) NOT NULL,
  PRIMARY KEY (`pin`),
  UNIQUE KEY `username` (`username`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

--
-- Dumping data for table `pegawai`
--

INSERT INTO `pegawai` (`pin`, `nama`, `username`, `password`, `tahunlalu`, `tahunini`, `total`, `role`) VALUES
(12, 'Farham Rujuli', 'jalih', '1234', 8, 1, 9, ''),
(123, 'ratna pandu pratami', 'nana', '1234', 0, 4, 7, ''),
(1234, 'muhammad balvas thoriq', 'bapas', '1234', 0, 5, 6, 'Y'),
(12345, 'taufik widhiarto', 'aboot', '1234', 0, -48, -48, 'y'),
(123456, 'anu', 'admin', '1234', 0, 4, 4, ''),
(1022, 'niko', 'niko', '1234', 0, -7, -7, '');

-- --------------------------------------------------------

--
-- Table structure for table `permintaan`
--

CREATE TABLE IF NOT EXISTS `permintaan` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `pin` int(11) NOT NULL,
  `id_cuti` int(3) NOT NULL,
  `tgl_permintaan` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `tgl_awal` date NOT NULL,
  `tgl_akhir` date NOT NULL,
  `jml_hari` int(11) NOT NULL,
  `ket` text NOT NULL,
  `status` varchar(21) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=MyISAM  DEFAULT CHARSET=latin1 AUTO_INCREMENT=75 ;

--
-- Dumping data for table `permintaan`
--

INSERT INTO `permintaan` (`id`, `pin`, `id_cuti`, `tgl_permintaan`, `tgl_awal`, `tgl_akhir`, `jml_hari`, `ket`, `status`) VALUES
(61, 12, 1, '2014-03-27 13:27:14', '2014-03-27', '2014-04-27', 22, 'ijin cuti ke bali', 'Accept'),
(62, 12345, 4, '2014-03-27 15:38:38', '2014-03-27', '2014-03-29', 2, 'bhh', 'Accept'),
(63, 1022, 4, '2014-03-27 15:50:30', '2014-03-27', '2014-03-28', 2, 'cuti tahunan', 'Accept'),
(64, 1022, 4, '2014-03-27 15:54:02', '2014-03-27', '2014-03-28', 2, '', 'Accept'),
(65, 1022, 4, '2014-03-27 15:57:00', '2014-03-27', '2014-04-03', 6, '', 'Accept'),
(66, 1022, 4, '2014-03-27 16:00:40', '2014-03-27', '2014-04-08', 9, '', 'Accept'),
(67, 1022, 4, '2014-03-27 16:01:40', '2014-03-27', '2014-04-02', 5, '', 'Accept'),
(68, 1022, 4, '2014-03-27 16:02:46', '2014-04-06', '2014-04-06', 0, '', 'Accept'),
(69, 1022, 4, '2014-03-27 16:04:23', '2014-04-07', '2014-04-03', 0, '', 'Accept'),
(70, 12345, 4, '2014-03-27 21:00:07', '2014-03-27', '2014-03-27', 1, '', 'Accept'),
(71, 12345, 4, '2014-03-27 21:17:26', '2014-03-27', '2014-04-02', 5, 'percobaan \ndengan\ntext \npanjang \nkebawah \noke \nsip \nbos\njalih\ntidur \nmulu\nga \nada\nkerjaan\nmantep\ndah\npokoknya\nmaklum\nanak \ndubai\nhahaha\n', 'Accept'),
(72, 12345, 4, '2014-03-29 13:00:34', '2014-03-29', '2014-04-11', 10, 'vacation', 'Accept'),
(73, 12345, 1, '2014-03-29 16:21:56', '2014-03-29', '2014-04-06', 5, 'svgddbdjdurdhddhdhdbxhfhfhfbfjfbfuwyerirjdudgziyeotskgwtoogsktwotwo', 'Accept'),
(74, 12345, 4, '2014-03-29 22:27:32', '2014-04-04', '2014-04-30', 19, '', 'Accept'),
(60, 1234, 4, '2014-03-27 09:40:25', '2014-04-01', '2014-04-02', 2, 'maderfaker', 'Accept'),
(59, 123456, 4, '2014-03-27 01:16:46', '2014-03-27', '2014-03-31', 3, 'ini', 'Accept'),
(58, 123456, 4, '2014-03-27 01:08:32', '2014-03-28', '2014-04-02', 4, 'anu', 'accept'),
(57, 12, 4, '2014-03-27 01:06:02', '2014-03-27', '2014-03-31', 3, 'jalih', 'accept'),
(56, 123, 4, '2014-03-27 00:08:25', '2014-03-27', '2014-03-28', 2, 'coba', 'Pending'),
(55, 1234, 4, '2014-03-27 00:00:09', '2014-03-26', '2014-03-26', 1, 'coba ', 'Pending'),
(54, 12345, 4, '2014-03-26 22:18:24', '2014-03-26', '2014-04-08', 10, 'percobaan\ndengan\ntext\nenter\nyang\nagak\nbanyak\nsupaya \ntau\njadinya\nkaya\ngimana\ndan\nhanya\nada\nbeberapa\nspasi \nterima\nkasih', 'Accept'),
(53, 12345, 2, '2014-03-26 21:06:12', '2014-03-26', '2014-04-02', 6, 'ini adalah percobaan dengan teks yang amat panjang untuk mencari tau kesalahan di dalam aplikasi sederhana ini. supaya dapat melangkah maju ', 'Pending'),
(52, 12345, 2, '2014-03-26 14:14:01', '2014-03-27', '2014-04-02', 5, 'ini tahunan', 'Aceept'),
(40, 12345, 2, '2014-03-24 22:38:12', '2014-03-24', '2014-03-26', 2, 'gggagaha', 'Pending'),
(41, 12345, 2, '2014-03-25 19:41:26', '2014-03-25', '2014-04-03', 7, 'hehhshsbs', 'Pending'),
(42, 123, 1, '2014-03-25 19:50:01', '2014-03-27', '2014-04-04', 6, '', 'Pending'),
(43, 12345, 2, '2014-03-25 20:28:34', '2014-03-26', '2014-04-01', 5, 'bzbs', 'Pending'),
(44, 12345, 1, '2014-03-25 21:23:15', '2014-03-27', '2014-04-11', 6, '', 'Pending'),
(45, 12345, 2, '2014-03-26 00:59:51', '2014-03-26', '2014-03-31', 4, 'gtr', 'Pending'),
(46, 12345, 2, '2014-03-26 11:49:36', '2014-03-26', '2014-03-27', 2, '', 'Accept'),
(47, 1234, 2, '2014-03-26 11:51:40', '2014-03-26', '2014-05-26', 62, 'sanab', 'Pending'),
(48, 1234, 1, '2014-03-26 11:52:42', '2014-03-26', '2014-03-26', 1, 'fvck', 'Pending'),
(49, 1234, 2, '2014-03-26 11:53:46', '2014-03-27', '2014-03-28', 2, 'hahaha', 'Pending'),
(50, 1234, 2, '2014-03-26 11:54:53', '2014-03-30', '2014-05-06', 38, 'kop', 'Pending'),
(51, 1234, 2, '2014-03-26 11:55:45', '2014-03-26', '2014-06-05', 71, 'cape kerja mulu', 'Accept');

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
