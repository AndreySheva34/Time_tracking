CREATE DATABASE Time_Tracker;

use Time_Tracker;

drop user 'admin'@localhost;
CREATE USER 'admin'@localhost IDENTIFIED BY 'admin';

GRANT ALL ON time_tracker.* TO 'admin'@localhost;



CREATE TABLE `user_info`(
                       `id` INT UNSIGNED NOT NULL AUTO_INCREMENT PRIMARY KEY,
                       `firstname` VARCHAR(255) NOT NULL,
                       `lastname` VARCHAR(255) NOT NULL,
                       `card_number` INT NULL,
                       `department_id` INT NULL,
                        unique (firstname,lastname)
);
CREATE TABLE `num_run_deteil`(
                                 `num_run_id` INT UNSIGNED NOT NULL AUTO_INCREMENT PRIMARY KEY,
                                 `start_time` TIME NOT NULL,
                                 `end_time` TIME NOT NULL,
                                 `sdays` INT NOT NULL,
                                 `edays` INT NOT NULL,
                                 `schclass_id` INT NOT NULL
);
CREATE TABLE `check_in_out`(
                               `id` INT UNSIGNED NOT NULL AUTO_INCREMENT PRIMARY KEY,
                               `user_id` INT NOT NULL,
                               `checktime` DATETIME NOT NULL,
                               `chektype` INT NOT NULL
);
CREATE TABLE `num_run`(
                          `id` INT UNSIGNED NOT NULL AUTO_INCREMENT PRIMARY KEY,
                          `title` VARCHAR(255) NOT NULL,
                          `start_date` DATE NOT NULL,
                          `end_date` DATE NOT NULL,
                          `cyle` INT NOT NULL,
                          `units` INT NOT NULL
);
ALTER TABLE
    `num_run` ADD UNIQUE `num_run_title_unique`(`title`);
CREATE TABLE `user_temp_shedule`(
                                    `id` INT UNSIGNED NOT NULL AUTO_INCREMENT PRIMARY KEY,
                                    `user_id` INT NOT NULL,
                                    `start_date` DATETIME NOT NULL,
                                    `end_date` DATETIME NOT NULL,
                                    `sedule_class_id` INT NULL,
                                    `type` INT NOT NULL
);
CREATE TABLE `departments`(
                               `id` INT UNSIGNED NOT NULL AUTO_INCREMENT PRIMARY KEY,
                               `sub_id` INT NULL,
                               `title` VARCHAR(255) NOT NULL
);
ALTER TABLE
    `departments` ADD UNIQUE `departments_title_unique`(`title`);
CREATE TABLE `schedule_class`(
                                 `id` INT UNSIGNED NOT NULL AUTO_INCREMENT PRIMARY KEY,
                                 `title` BIGINT NOT NULL,
                                 `start_time` TIME NOT NULL,
                                 `end_time` TIME NOT NULL
);
ALTER TABLE
    `schedule_class` ADD UNIQUE `schedule_class_title_unique`(`title`);
CREATE TABLE `num_of_run`(
                             `id` INT UNSIGNED NOT NULL AUTO_INCREMENT PRIMARY KEY,
                             `user_id` INT NOT NULL,
                             `start_date` DATE NOT NULL,
                             `end_date` DATE NOT NULL,
                             `num_of_run_id` INT NOT NULL
);
ALTER TABLE
    `num_of_run` ADD CONSTRAINT `num_of_run_user_id_foreign` FOREIGN KEY(`user_id`) REFERENCES `user_info`(`id`);
ALTER TABLE
    `num_run_deteil` ADD CONSTRAINT `num_run_deteil_num_run_id_foreign` FOREIGN KEY(`num_run_id`) REFERENCES `num_run`(`id`);
ALTER TABLE
    `check_in_out` ADD CONSTRAINT `check_in_out_user_id_foreign` FOREIGN KEY(`user_id`) REFERENCES `user_info`(`id`);
ALTER TABLE
    `user_temp_shedule` ADD CONSTRAINT `user_temp_shedule_sedule_class_id_foreign` FOREIGN KEY(`sedule_class_id`) REFERENCES `schedule_class`(`id`);
ALTER TABLE
    `user_temp_shedule` ADD CONSTRAINT `user_temp_shedule_user_id_foreign` FOREIGN KEY(`user_id`) REFERENCES `user_info`(`id`);
ALTER TABLE
    `num_run_deteil` ADD CONSTRAINT `num_run_deteil_schclass_id_foreign` FOREIGN KEY(`schclass_id`) REFERENCES `schedule_class`(`id`);
ALTER TABLE
    `user_info` ADD CONSTRAINT `user_department_id_foreign` FOREIGN KEY(`department_id`) REFERENCES `departments`(`id`);
ALTER TABLE
    `num_of_run` ADD CONSTRAINT `num_of_run_num_of_run_id_foreign` FOREIGN KEY(`num_of_run_id`) REFERENCES `num_run`(`id`);