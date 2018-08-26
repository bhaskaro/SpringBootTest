/**
 * 
 */
package com.oggu.spring.boot;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.oggu.spring.boot.model.Users;
import com.oggu.spring.boot.repository.UserJpaRespository;

/**
 * @author Bhaskar
 *
 */
@RestController
@RequestMapping("/users")
public class UserController {

	@Autowired
	private UserJpaRespository userJpaRespository;

	@GetMapping(value = "/all")
	public List<Users> findAll() {
		return userJpaRespository.findAll();
	}

	@GetMapping(value = "/{name}")
	public Users findByName(@PathVariable final String name) {
		return userJpaRespository.findByName(name);
	}

	@DeleteMapping(value = "/{name}")
	public void deleteByName(@PathVariable final String name) {

		userJpaRespository.delete(findByName(name).getId());
	}

	@PostMapping(value = "/load")
	public Users load(@RequestBody final Users users) {
		userJpaRespository.save(users);
		return userJpaRespository.findByName(users.getName());
	}
}