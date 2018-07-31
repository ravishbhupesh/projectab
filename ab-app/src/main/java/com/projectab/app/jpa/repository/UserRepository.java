/**
 * 
 */
package com.projectab.app.jpa.repository;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

import com.projectab.app.model.User;

/**
 * @author Bhupesh
 *
 */
public interface UserRepository extends CrudRepository<User, Long> {

	Optional<User> findByUsername(String username);
}
