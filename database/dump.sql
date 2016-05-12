-- phpMyAdmin SQL Dump
-- version 4.0.10deb1
-- http://www.phpmyadmin.net
--
-- Host: localhost
-- Generation Time: May 11, 2016 at 12:22 PM
-- Server version: 5.5.47-0ubuntu0.14.04.1
-- PHP Version: 5.5.9-1ubuntu4.14

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;

--
-- Database: `facebook2`
--

DELIMITER $$
--
-- Procedures
--
CREATE DEFINER=`root`@`localhost` PROCEDURE `csp_registration`(IN `fnm` VARCHAR(20), IN `lnm` VARCHAR(20), IN `address` VARCHAR(20), IN `mobile` VARCHAR(20), IN `email` VARCHAR(20), IN `password` VARCHAR(20))
begin
declare a int default 0;
declare b int default 0;
start transaction;
insert into tbl_login(Email_id,Password) values (email,password);

set a=last_insert_id();
insert into tbl_registration(first_name,last_name,address,Mobile_no,Email,Password,fk_int_login_id) values (fnm,lnm,address,mobile,email,password,a);
set b=last_insert_id();

if (a>0 && b>0) then
COMMIT;
else
ROLLBACK;
end if;
end$$

DELIMITER ;

-- --------------------------------------------------------

--
-- Table structure for table `tbl_login`
--

CREATE TABLE IF NOT EXISTS `tbl_login` (
  `pk_int_login_id` int(11) NOT NULL AUTO_INCREMENT,
  `Email_id` varchar(20) DEFAULT NULL,
  `Password` varchar(20) DEFAULT NULL,
  PRIMARY KEY (`pk_int_login_id`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=10 ;

--
-- Dumping data for table `tbl_login`
--

INSERT INTO `tbl_login` (`pk_int_login_id`, `Email_id`, `Password`) VALUES
(5, 'nas@gmail.com', 'naslackh'),
(6, 'neha@gmail.com', 'nehaneha'),
(7, 'asw@gmail.com', 'aswathy'),
(8, 'anju@gmail.com', 'anjuanju'),
(9, 'nas@gmail.com', 'naslack');

-- --------------------------------------------------------

--
-- Table structure for table `tbl_registration`
--

CREATE TABLE IF NOT EXISTS `tbl_registration` (
  `pk_int_reg_id` int(11) NOT NULL AUTO_INCREMENT,
  `first_name` varchar(20) DEFAULT NULL,
  `last_name` varchar(20) DEFAULT NULL,
  `address` varchar(20) DEFAULT NULL,
  `Mobile_no` varchar(20) DEFAULT NULL,
  `Email` varchar(20) DEFAULT NULL,
  `Password` varchar(20) DEFAULT NULL,
  `fk_int_login_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`pk_int_reg_id`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=6 ;

--
-- Dumping data for table `tbl_registration`
--

INSERT INTO `tbl_registration` (`pk_int_reg_id`, `first_name`, `last_name`, `address`, `Mobile_no`, `Email`, `Password`, `fk_int_login_id`) VALUES
(1, 'nasla', 'ck', 'sdcvbdfgh', '9876543234', 'nas@gmail.com', 'naslackh', 5),
(2, 'neha', 'abdulla', 'sdcvbdfgh', '9876543234', 'neha@gmail.com', 'nehaneha', 6),
(3, 'aswathy', 'das', 'sdcvbdfgh', '9876543234', 'asw@gmail.com', 'aswathy', 7),
(4, 'anjali', 'anju', 'dfyfgjjjhgg', '9878654567', 'anju@gmail.com', 'anjuanju', 8);

-- --------------------------------------------------------

--
-- Table structure for table `tbl_relationship`
--

CREATE TABLE IF NOT EXISTS `tbl_relationship` (
  `fk_int_login_id` int(11) NOT NULL,
  `user_two_id` int(11) DEFAULT NULL,
  `status` int(11) DEFAULT NULL,
  `action_user_id` int(11) DEFAULT NULL,
  KEY `fk_int_login_id` (`fk_int_login_id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `tbl_relationship`
--

INSERT INTO `tbl_relationship` (`fk_int_login_id`, `user_two_id`, `status`, `action_user_id`) VALUES
(9, 6, 3, 9),
(9, 7, 2, 9),
(9, 8, 3, 9),
(9, 9, 1, 9),
(9, 8, 0, 9);

--
-- Constraints for dumped tables
--

--
-- Constraints for table `tbl_relationship`
--
ALTER TABLE `tbl_relationship`
  ADD CONSTRAINT `tbl_relationship_ibfk_1` FOREIGN KEY (`fk_int_login_id`) REFERENCES `tbl_login` (`pk_int_login_id`);

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
