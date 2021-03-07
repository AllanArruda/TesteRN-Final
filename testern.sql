-- phpMyAdmin SQL Dump
-- version 5.0.2
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1:3306
-- Tempo de geração: 07-Mar-2021 às 17:59
-- Versão do servidor: 5.7.31
-- versão do PHP: 7.3.21

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Banco de dados: `testern`
--

-- --------------------------------------------------------

--
-- Estrutura da tabela `aluno`
--

DROP TABLE IF EXISTS `aluno`;
CREATE TABLE IF NOT EXISTS `aluno` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `identificacao` int(11) NOT NULL,
  `nome` varchar(200) NOT NULL,
  `sexo` char(1) NOT NULL,
  `data_nascimento` date NOT NULL,
  `status` tinyint(1) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=MyISAM AUTO_INCREMENT=91 DEFAULT CHARSET=latin1;

--
-- Extraindo dados da tabela `aluno`
--

INSERT INTO `aluno` (`id`, `identificacao`, `nome`, `sexo`, `data_nascimento`, `status`) VALUES
(73, 123456, 'Maria da Silva', 'F', '1995-08-04', 1),
(74, 789101, 'José dos Santos', 'M', '1991-04-05', 1),
(75, 121314, 'Joana Aguiar', 'F', '1990-07-06', 1),
(76, 151617, 'Antônio Fernandes', 'M', '1997-08-06', 1),
(77, 181920, 'Priscila Mendes', 'F', '1993-05-25', 1),
(78, 212223, 'Leonardo Martins', 'M', '1990-09-23', 1),
(79, 242526, 'Janaina Gomes', 'F', '1989-06-12', 1),
(80, 272829, 'Nelson Garcia', 'M', '1993-03-23', 1),
(81, 303132, 'Fernanda Lopes', 'F', '1995-09-30', 1),
(82, 333435, 'Gabriel Pereira', 'M', '1998-05-01', 1),
(83, 363738, 'Larissa Diniz', 'F', '1994-11-22', 1),
(84, 394041, 'Rodolfo Maia', 'M', '1999-07-11', 1),
(85, 424344, 'Letícia Rodrigues', 'F', '1990-08-14', 1),
(86, 454647, 'Marina Azevedo', 'F', '1994-12-03', 1),
(87, 484950, 'Ivan Nunes', 'M', '1993-09-04', 1),
(88, 515253, 'Eliza Lage', 'F', '1996-04-19', 1),
(89, 545556, 'Otavio Mendonça', 'M', '1994-09-17', 1),
(90, 575859, 'Silvana Andrade', 'F', '1997-10-25', 1);

-- --------------------------------------------------------

--
-- Estrutura da tabela `notas`
--

DROP TABLE IF EXISTS `notas`;
CREATE TABLE IF NOT EXISTS `notas` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `periodo` int(11) NOT NULL,
  `nota` double NOT NULL,
  `id_aluno` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `id_aluno` (`id_aluno`)
) ENGINE=MyISAM AUTO_INCREMENT=271 DEFAULT CHARSET=latin1;

--
-- Extraindo dados da tabela `notas`
--

INSERT INTO `notas` (`id`, `periodo`, `nota`, `id_aluno`) VALUES
(270, 3, 35, 90),
(269, 2, 35, 90),
(268, 1, 30, 90),
(267, 3, 27, 89),
(266, 2, 22, 89),
(265, 1, 19, 89),
(264, 3, 34, 88),
(263, 2, 32, 88),
(262, 1, 29, 88),
(261, 3, 27, 87),
(260, 2, 29, 87),
(259, 1, 25, 87),
(258, 3, 23, 86),
(257, 2, 29, 86),
(256, 1, 11, 86),
(255, 3, 33, 85),
(254, 2, 29, 85),
(253, 1, 27, 85),
(252, 3, 34, 84),
(251, 2, 35, 84),
(250, 1, 30, 84),
(249, 3, 29, 83),
(248, 2, 25, 83),
(247, 1, 20, 83),
(246, 3, 30, 82),
(245, 2, 22, 82),
(244, 1, 14, 82),
(243, 3, 31, 81),
(242, 2, 34, 81),
(241, 1, 23, 81),
(240, 3, 25, 80),
(239, 2, 28, 80),
(238, 1, 18, 80),
(237, 3, 25, 79),
(236, 2, 18, 79),
(235, 1, 21, 79),
(234, 3, 15, 78),
(233, 2, 13, 78),
(232, 1, 10, 78),
(231, 3, 35, 77),
(230, 2, 35, 77),
(229, 1, 30, 77),
(228, 3, 32, 76),
(227, 2, 30, 76),
(226, 1, 30, 76),
(225, 3, 15, 75),
(224, 2, 20, 75),
(223, 1, 25, 75),
(222, 3, 25, 74),
(221, 2, 20, 74),
(220, 1, 20, 74),
(219, 3, 30, 73),
(218, 2, 30, 73),
(217, 1, 15, 73);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
