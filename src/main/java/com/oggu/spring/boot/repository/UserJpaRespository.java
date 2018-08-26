package com.oggu.spring.boot.repository;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;

import com.oggu.spring.boot.model.Users;

@Component
public interface UserJpaRespository extends JpaRepository<Users, Long> {

	@Transactional
	Users findByName(String name);

}