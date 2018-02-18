/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.raga.sec.repository;

/**
 *
 * @author raga
 * DROP TABLE IF EXISTS `user`;

    CREATE TABLE `user` (
    `user_id` int(11) NOT NULL AUTO_INCREMENT,
    `active` int(11) DEFAULT NULL,
    `email` varchar(255) NOT NULL,
    `last_name` varchar(255) NOT NULL,
    `name` varchar(255) NOT NULL,
    `password` varchar(255) NOT NULL,
    PRIMARY KEY (`user_id`)
    ) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;
    
    DROP TABLE IF EXISTS `user_role`;
    CREATE TABLE `user_role` (
    `user_id` int(11) NOT NULL,
    `role_id` int(11) NOT NULL,
    PRIMARY KEY (`user_id`,`role_id`),
    KEY `FKa68196081fvovjhkek5m97n3y` (`role_id`),
    CONSTRAINT `FK859n2jvi8ivhui0rl0esws6o` FOREIGN KEY (`user_id`) REFERENCES `user` (`user_id`),
    CONSTRAINT `FKa68196081fvovjhkek5m97n3y` FOREIGN KEY (`role_id`) REFERENCES `role` (`role_id`)
    ) ENGINE=InnoDB DEFAULT CHARSET=utf8;
    
    INSERT INTO `role` VALUES (1,'ADMIN');
    
 */
import com.raga.sec.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository("userRepository")
public interface UserRepository extends JpaRepository<User, Long> {
	 User findByEmail(String email);
}
