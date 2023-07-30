CREATE DATABASE Time_Tracker;

use Time_Tracker;

drop user 'admin'@localhost;
CREATE USER 'admin'@localhost IDENTIFIED BY 'admin';

GRANT ALL ON time_tracker.* TO 'admin'@localhost;

create table users
(
    id                      int          not null AUTO_INCREMENT primary key,
    username                varchar(50)  not null unique,
    password                varchar(500) not null,
    enabled                 boolean      not null default true,
    account_non_expired     boolean      not null default true,
    account_non_locked      boolean      not null default true,
    credentials_non_expired boolean      not null default true
);

create table roles
(
    id   int         not null AUTO_INCREMENT primary key,
    name varchar(50) not null,
    unique (id, name)
);

create table users_roles
(
    users_id int not null,
    roles_id int not null,
    primary key (users_id, roles_id),
    constraint foreign key (users_id) references users (id),
    constraint foreign key (roles_id) references roles (id)
);
# ALTER TABLE users_roles add constraint foreign key(roles_id) references roles(id);
CREATE TABLE employee
(
    id            INT          NOT NULL AUTO_INCREMENT PRIMARY KEY,
    firstname     VARCHAR(255) NOT NULL,
    lastname      VARCHAR(255) NOT NULL,
    card_number   INT          NULL,
    department_id INT          NULL,
    user_id       INT,
    constraint fk_employee_users foreign key (user_id) references users (id),
    unique (firstname, lastname)
);

CREATE TABLE departments
(
    id        INT          NOT NULL AUTO_INCREMENT PRIMARY KEY,
    parent_id INT,
    title     VARCHAR(255) NOT NULL UNIQUE,
    constraint fk_recursive foreign key (parent_id) references departments (id)
);


CREATE TABLE `num_run_deteil`
(
    `num_run_id`  INT UNSIGNED NOT NULL AUTO_INCREMENT PRIMARY KEY,
    `start_time`  TIME         NOT NULL,
    `end_time`    TIME         NOT NULL,
    `sdays`       INT          NOT NULL,
    `edays`       INT          NOT NULL,
    `schclass_id` INT          NOT NULL
);
CREATE TABLE `check_in_out`
(
    `id`        INT UNSIGNED NOT NULL AUTO_INCREMENT PRIMARY KEY,
    `user_id`   INT          NOT NULL,
    `checktime` DATETIME     NOT NULL,
    `chektype`  INT          NOT NULL
);
CREATE TABLE `num_run`
(
    `id`         INT UNSIGNED NOT NULL AUTO_INCREMENT PRIMARY KEY,
    `title`      VARCHAR(255) NOT NULL,
    `start_date` DATE         NOT NULL,
    `end_date`   DATE         NOT NULL,
    `cyle`       INT          NOT NULL,
    `units`      INT          NOT NULL
);
ALTER TABLE
    `num_run`
    ADD UNIQUE `num_run_title_unique` (`title`);
CREATE TABLE `user_temp_shedule`
(
    `id`              INT UNSIGNED NOT NULL AUTO_INCREMENT PRIMARY KEY,
    `user_id`         INT          NOT NULL,
    `start_date`      DATETIME     NOT NULL,
    `end_date`        DATETIME     NOT NULL,
    `sedule_class_id` INT          NULL,
    `type`            INT          NOT NULL
);

CREATE TABLE schedule
(
    `id`         INT UNSIGNED NOT NULL AUTO_INCREMENT PRIMARY KEY,
    `title`      VARCHAR(50)  NOT NULL UNIQUE,
    `start_time` TIME         NOT NULL,
    `end_time`   TIME         NOT NULL
);

CREATE TABLE `num_of_run`
(
    `id`            INT UNSIGNED NOT NULL AUTO_INCREMENT PRIMARY KEY,
    `user_id`       INT          NOT NULL,
    `start_date`    DATE         NOT NULL,
    `end_date`      DATE         NOT NULL,
    `num_of_run_id` INT          NOT NULL
);
ALTER TABLE
    `num_of_run`
    ADD CONSTRAINT `num_of_run_user_id_foreign` FOREIGN KEY (`user_id`) REFERENCES employee (`id`);
ALTER TABLE
    `num_run_deteil`
    ADD CONSTRAINT `num_run_deteil_num_run_id_foreign` FOREIGN KEY (`num_run_id`) REFERENCES `num_run` (`id`);
ALTER TABLE
    `check_in_out`
    ADD CONSTRAINT `check_in_out_user_id_foreign` FOREIGN KEY (`user_id`) REFERENCES employee (`id`);
ALTER TABLE
    `user_temp_shedule`
    ADD CONSTRAINT `user_temp_shedule_sedule_class_id_foreign` FOREIGN KEY (`sedule_class_id`) REFERENCES schedule (`id`);
ALTER TABLE
    `user_temp_shedule`
    ADD CONSTRAINT `user_temp_shedule_user_id_foreign` FOREIGN KEY (`user_id`) REFERENCES employee (`id`);
ALTER TABLE
    `num_run_deteil`
    ADD CONSTRAINT `num_run_deteil_schclass_id_foreign` FOREIGN KEY (`schclass_id`) REFERENCES schedule (`id`);
ALTER TABLE
    employee
    ADD CONSTRAINT `user_department_id_foreign` FOREIGN KEY (`department_id`) REFERENCES `departments` (`id`);
ALTER TABLE
    `num_of_run`
    ADD CONSTRAINT `num_of_run_num_of_run_id_foreign` FOREIGN KEY (`num_of_run_id`) REFERENCES `num_run` (`id`);
