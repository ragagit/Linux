/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.raga.sec.repository;

/**
 *
 * @author raga
 * 
 *  CREATE TABLE `role` (
    `role_id` int(11) NOT NULL AUTO_INCREMENT,
    `role` varchar(255) DEFAULT NULL,
    PRIMARY KEY (`role_id`)
    ) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;
 */
import com.raga.sec.entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository("roleRepository")
public interface RoleRepository extends JpaRepository<Role, Integer>{
	Role findByRole(String role);

}