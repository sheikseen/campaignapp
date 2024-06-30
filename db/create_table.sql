CREATE TABLE `roles`
(
    `id` enum('GUEST','MANAGER','ADMIN') NOT NULL,
    `name` varchar(255) DEFAULT NULL,
    PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE `principals`
(
    `id`        varchar(255) NOT NULL,
    `name` varchar(20)  NOT NULL,
    `phone`     varchar(255) NOT NULL,
    `username`  varchar(50)  NOT NULL,
    `email`     varchar(50)  NOT NULL,
    `password`  varchar(120) NOT NULL,
    PRIMARY KEY (`id`),
    UNIQUE KEY `username_UNIQUE` (`username`),
    UNIQUE KEY `email_UNIQUE` (`email`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE `principal_roles`
(
    `principal_id` varchar(255) NOT NULL,
    `role_id` enum('GUEST','MANAGER','ADMIN') NOT NULL,
    PRIMARY KEY (`principal_id`, `role_id`),
    KEY       `FK_user_roles_users_idx` (`principal_id`),
    KEY       `FK_user_roles_roles_idx` (`role_id`),
    CONSTRAINT `FK_user_roles_roles` FOREIGN KEY (`role_id`) REFERENCES `roles` (`id`),
    CONSTRAINT `FK_user_roles_users` FOREIGN KEY (`principal_id`) REFERENCES `principals` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;