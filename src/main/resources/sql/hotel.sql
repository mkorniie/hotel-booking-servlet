-- MySQL Script generated by MySQL Workbench
-- Sat Jul 27 18:13:53 2019
-- Model: New Model    Version: 1.0
-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION';

-- -----------------------------------------------------
-- Schema hotel
-- -----------------------------------------------------

-- -----------------------------------------------------
-- Schema hotel
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `hotel` DEFAULT CHARACTER SET utf8 ;
USE `hotel` ;

-- -----------------------------------------------------
-- Table `hotel`.`users`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `hotel`.`users` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `name` NVARCHAR(120) NOT NULL,
  `role` VARCHAR(45) NOT NULL,
  `pass_encoded` VARCHAR(200) NOT NULL,
  `email` VARCHAR(100) NOT NULL,
  `language` VARCHAR(55) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE INDEX `idusers_UNIQUE` (`id` ASC) VISIBLE,
  UNIQUE INDEX `name_UNIQUE` (`name` ASC) VISIBLE)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `hotel`.`requests`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `hotel`.`requests` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `users_id` INT NOT NULL,
  `places` INT NOT NULL,
  `class` VARCHAR(100) NOT NULL,
  `start_date` DATE NOT NULL,
  `end_date` DATE NOT NULL,
  `isApproved` TINYINT NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE INDEX `idrequests_UNIQUE` (`id` ASC) VISIBLE,
  INDEX `fk_requests_users2_idx` (`users_id` ASC) VISIBLE,
  CONSTRAINT `fk_requests_users2`
    FOREIGN KEY (`users_id`)
    REFERENCES `hotel`.`users` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `hotel`.`rooms`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `hotel`.`rooms` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `places` INT NOT NULL,
  `class` VARCHAR(45) NOT NULL,
  `isOccupied` TINYINT NOT NULL,
  `picURL` VARCHAR(200) NOT NULL,
  `price` DECIMAL(13,2) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE INDEX `idrooms_UNIQUE` (`id` ASC) VISIBLE)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `hotel`.`bills`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `hotel`.`bills` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `sum` DECIMAL(13,2) NOT NULL,
  `isPaid` TINYINT NOT NULL,
  `requests_id` INT NOT NULL,
  `rooms_id` INT NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE INDEX `idbills_UNIQUE` (`id` ASC) VISIBLE,
  INDEX `fk_bills_requests1_idx` (`requests_id` ASC) VISIBLE,
  UNIQUE INDEX `requests_id_UNIQUE` (`requests_id` ASC) VISIBLE,
  INDEX `fk_bills_rooms2_idx` (`rooms_id` ASC) VISIBLE,
  CONSTRAINT `fk_bills_requests1`
    FOREIGN KEY (`requests_id`)
    REFERENCES `hotel`.`requests` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_bills_rooms2`
    FOREIGN KEY (`rooms_id`)
    REFERENCES `hotel`.`rooms` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;

INSERT INTO `hotel`.`users` (`name`, `role`, `pass_encoded`, `email`) VALUES ('user', 'USER', 'a665a45920422f9d417e4867efdc4fb8a04a1f3fff1fa07e998e86f7f7a27ae3', 'user@user.com');
INSERT INTO `hotel`.`users` (`name`, `role`, `pass_encoded`, `email`) VALUES ('admin', 'USER', 'a665a45920422f9d417e4867efdc4fb8a04a1f3fff1fa07e998e86f7f7a27ae3', 'ad@ad.com');

