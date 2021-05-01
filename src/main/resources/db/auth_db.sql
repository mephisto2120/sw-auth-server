SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION';

-- -----------------------------------------------------
-- Schema auth
-- -----------------------------------------------------

-- -----------------------------------------------------
-- Schema auth
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `auth` DEFAULT CHARACTER SET utf8 ;

SET character_set_client = UTF8;
SET character_set_connection = UTF8;
SET character_set_database = UTF8;
SET character_set_results = UTF8;
SET character_set_filesystem = binary;
SET character_set_server = UTF8;

USE `auth` ;

-- -----------------------------------------------------
-- Table `auth`.`users`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `auth`.`users` ;

CREATE TABLE IF NOT EXISTS `auth`.`users` (
                                              `usr_id` BIGINT(20) NOT NULL,
                                              `usr_email` VARCHAR(100) NOT NULL,
                                              `usr_password` VARCHAR(200) NOT NULL,
                                              `usr_last_name` VARCHAR(200) NOT NULL,
                                              `usr_first_name` VARCHAR(200) NOT NULL,
                                              `usr_date_inserted` VARCHAR(45) NOT NULL,
                                              `usr_date_modified` TIMESTAMP NOT NULL,

                                              PRIMARY KEY (`usr_id`))
    ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `auth`.`role`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `auth`.`role` ;

CREATE TABLE IF NOT EXISTS `auth`.`role` (
                                             `rol_id` BIGINT(20) NOT NULL,
                                             `rol_name` VARCHAR(45) NOT NULL,
                                             `rol_desc` VARCHAR(200) NOT NULL,
                                             PRIMARY KEY (`rol_id`))
    ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `auth`.`status`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `auth`.`status` ;

CREATE TABLE IF NOT EXISTS `auth`.`status` (
                                               `sts_id` BIGINT(20) NOT NULL,
                                               `sts_name` VARCHAR(45) NOT NULL,
                                               `sts_desc` VARCHAR(100) NOT NULL,
                                               `users_usr_id` BIGINT(20) NOT NULL,
                                               PRIMARY KEY (`sts_id`, `users_usr_id`),
                                               CONSTRAINT `fk_status_users`
                                                   FOREIGN KEY (`users_usr_id`)
                                                       REFERENCES `auth`.`users` (`usr_id`)
                                                       ON DELETE NO ACTION
                                                       ON UPDATE NO ACTION)
    ENGINE = InnoDB;

CREATE INDEX `fk_status_users_idx` ON `auth`.`status` (`users_usr_id` ASC) VISIBLE;


-- -----------------------------------------------------
-- Table `auth`.`users_roles`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `auth`.`users_roles` ;

CREATE TABLE IF NOT EXISTS `auth`.`users_roles` (
                                                    `ur_usr_id` BIGINT(20) NOT NULL,
                                                    `ur_rol_id` BIGINT(20) NOT NULL,
                                                    `ur_date_inserted` VARCHAR(45) NOT NULL,
                                                    `ur_date_modified` TIMESTAMP NOT NULL,
                                                    PRIMARY KEY (`ur_usr_id`, `ur_rol_id`),
                                                    CONSTRAINT `fk_users_has_role_users1`
                                                        FOREIGN KEY (`ur_usr_id`)
                                                            REFERENCES `auth`.`users` (`usr_id`)
                                                            ON DELETE NO ACTION
                                                            ON UPDATE NO ACTION,
                                                    CONSTRAINT `fk_users_has_role_role1`
                                                        FOREIGN KEY (`ur_rol_id`)
                                                            REFERENCES `auth`.`role` (`rol_id`)
                                                            ON DELETE NO ACTION
                                                            ON UPDATE NO ACTION)
    ENGINE = InnoDB;

CREATE INDEX `fk_users_has_role_role1_idx` ON `auth`.`users_roles` (`ur_rol_id` ASC) VISIBLE;

CREATE INDEX `fk_users_has_role_users1_idx` ON `auth`.`users_roles` (`ur_usr_id` ASC) VISIBLE;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;