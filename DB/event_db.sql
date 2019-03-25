-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION';

-- -----------------------------------------------------
-- Schema event_db
-- -----------------------------------------------------

-- -----------------------------------------------------
-- Schema event_db
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `event_db` DEFAULT CHARACTER SET utf8 ;
USE `event_db` ;

-- -----------------------------------------------------
-- Table `event_db`.`schedule`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `event_db`.`schedule` ;

CREATE TABLE IF NOT EXISTS `event_db`.`schedule` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `user_id` INT NOT NULL,
  `prescription_name` VARCHAR(200) NOT NULL,
  `pill_size_in_ug` INT NOT NULL,
  `dose_in_ug` INT NOT NULL,
  `times_daily` INT NOT NULL,
  `is_active` TINYINT NOT NULL DEFAULT 1,
  PRIMARY KEY (`id`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `event_db`.`user`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `event_db`.`user` ;

CREATE TABLE IF NOT EXISTS `event_db`.`user` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `first_name` VARCHAR(100) NULL,
  `last_name` VARCHAR(100) NULL,
  `is_active` TINYINT NOT NULL DEFAULT 1,
  PRIMARY KEY (`id`),
  CONSTRAINT `fk_user_schedule_user_id`
    FOREIGN KEY (`id`)
    REFERENCES `event_db`.`schedule` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;

SET SQL_MODE = '';
DROP USER IF EXISTS event;
SET SQL_MODE='ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION';
CREATE USER 'event' IDENTIFIED BY 'event';

GRANT SELECT, INSERT, TRIGGER, UPDATE, DELETE ON TABLE `event_db`.* TO 'event';

SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;

-- -----------------------------------------------------
-- Data for table `event_db`.`schedule`
-- -----------------------------------------------------
START TRANSACTION;
USE `event_db`;
INSERT INTO `event_db`.`schedule` (`id`, `user_id`, `prescription_name`, `pill_size_in_ug`, `dose_in_ug`, `times_daily`, `is_active`) VALUES (1, 1, 'pill 1', 20000, 20000, 2, 1);
INSERT INTO `event_db`.`schedule` (`id`, `user_id`, `prescription_name`, `pill_size_in_ug`, `dose_in_ug`, `times_daily`, `is_active`) VALUES (2, 1, 'pill 2', 20000, 40000, 1, 1);
INSERT INTO `event_db`.`schedule` (`id`, `user_id`, `prescription_name`, `pill_size_in_ug`, `dose_in_ug`, `times_daily`, `is_active`) VALUES (3, 1, 'pill 3', 30000, 15000, 1, 1);

COMMIT;


-- -----------------------------------------------------
-- Data for table `event_db`.`user`
-- -----------------------------------------------------
START TRANSACTION;
USE `event_db`;
INSERT INTO `event_db`.`user` (`id`, `first_name`, `last_name`, `is_active`) VALUES (1, 'todd', 'trowbridge', 1);
INSERT INTO `event_db`.`user` (`id`, `first_name`, `last_name`, `is_active`) VALUES (2, 'bob', 'smith', 1);

COMMIT;

