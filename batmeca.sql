-- phpMyAdmin SQL Dump
-- version 3.4.10.1deb1
-- http://www.phpmyadmin.net
--
-- Client: localhost
-- Généré le : Mer 18 Décembre 2013 à 23:45
-- Version du serveur: 5.5.34
-- Version de PHP: 5.3.10-1ubuntu3.9

SET SQL_MODE="NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;

--
-- Base de données: `batmeca`
--

-- --------------------------------------------------------

--
-- Structure de la table `material`
--

CREATE TABLE IF NOT EXISTS `material` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `id_parent` int(11) DEFAULT NULL,
  `id_creator` int(11) DEFAULT NULL,
  `name` varchar(255) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `id_parent` (`id_parent`),
  KEY `id_creator` (`id_creator`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=46 ;

-- --------------------------------------------------------

--
-- Structure de la table `material_attribute`
--

CREATE TABLE IF NOT EXISTS `material_attribute` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `id_material` int(11) NOT NULL,
  `id_type_material_attribute` int(11) NOT NULL,
  `value` varchar(255) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `id_material` (`id_material`),
  KEY `id_type_material_attribute` (`id_type_material_attribute`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1 AUTO_INCREMENT=1 ;

-- --------------------------------------------------------

--
-- Structure de la table `test`
--

CREATE TABLE IF NOT EXISTS `test` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) NOT NULL,
  `date` datetime NOT NULL,
  `id_material` int(11) NOT NULL,
  `id_creator` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `date` (`date`),
  KEY `id_material` (`id_material`),
  KEY `id_creator` (`id_creator`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=74 ;

-- --------------------------------------------------------

--
-- Structure de la table `test_attribute`
--

CREATE TABLE IF NOT EXISTS `test_attribute` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `id_test` int(11) NOT NULL,
  `id_material` int(11) DEFAULT NULL,
  `id_type_test_attribute` int(11) NOT NULL,
  `value` varchar(255) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `id_test` (`id_test`),
  KEY `id_type_test_attribute` (`id_type_test_attribute`),
  KEY `id_material` (`id_material`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1 AUTO_INCREMENT=1 ;

-- --------------------------------------------------------

--
-- Structure de la table `typeuser`
--

CREATE TABLE IF NOT EXISTS `typeuser` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `type` varchar(255) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=3 ;

-- --------------------------------------------------------

--
-- Structure de la table `type_material_attribute`
--

CREATE TABLE IF NOT EXISTS `type_material_attribute` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) DEFAULT NULL,
  `pattern` varchar(255) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=16 ;

-- --------------------------------------------------------

--
-- Structure de la table `type_test_attribute`
--

CREATE TABLE IF NOT EXISTS `type_test_attribute` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) NOT NULL,
  `pattern` varchar(255) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=6 ;

-- --------------------------------------------------------

--
-- Structure de la table `user`
--

CREATE TABLE IF NOT EXISTS `user` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) NOT NULL,
  `first_name` varchar(255) NOT NULL,
  `password` varchar(255) NOT NULL,
  `email` varchar(255) NOT NULL,
  `type` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `type` (`type`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=4 ;

--
-- Dumping data for table `user`
--

INSERT INTO `user` (`id`, `name`, `first_name`, `password`, `email`, `type`) VALUES
(1, 'Admin', 'Admin', 'admin', 'admin@admin.com', 1);

--
-- Dumping data for table `typeuser`
--

INSERT INTO `typeuser` (`id`, `type`) VALUES
(1, 'admin'),
(2, 'user');

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
