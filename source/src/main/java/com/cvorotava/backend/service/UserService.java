package com.cvorotava.backend.service;

import java.util.List;
import java.util.Optional;

import com.cvorotava.backend.error.exception.InternalServerException;
import com.cvorotava.backend.error.exception.NoContentException;
import com.cvorotava.backend.error.exception.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cvorotava.backend.entity.User;
import com.cvorotava.backend.repository.UserRepository;
import org.springframework.transaction.annotation.Transactional;

@Service
public class UserService implements IUserService {
	@Autowired
	private UserRepository userrepository;

	@Override
	@Transactional(readOnly = true)
	public List<User> findAll() {
		return getUsersOrThrowNoContent(userrepository.findAll(), "en la BBDD aun");
	}

	@Override
	@Transactional(readOnly = true)
	public User findById(Integer id) {
		return getUserOrThrowNotFound(userrepository.findById(id), "con ese id");
	}

	@Override
	@Transactional(readOnly = true)
	public User findByUsername(String username) {
		return getUserOrThrowNotFound(userrepository.findByUsername(username), "con ese nombre de usuario");
	}

	@Override
	@Transactional
	public void delete(Integer id) {
		try {
			userrepository.deleteById(id);
		} catch (Exception e) {
			throw new InternalServerException("No se ha podido borrar el user de la base de datos");
		}
	}

	@Override
	@Transactional
	public User save(User entity) {
		try {
			return userrepository.save(entity);
		} catch (Exception e) {
			throw new InternalServerException("No se ha podido insertar el user en la base de datos");
		}
	}

	private User getUserOrThrowNotFound(Optional<User> user, String message) {
		return user.orElseThrow(() -> new NotFoundException("No existe el usuario " + message));
	}

	private List<User> getUsersOrThrowNoContent(List<User> users, String message) {
		if (users.isEmpty()) {
			throw new NoContentException("No existen usuarios " + message);
		}
		return users;
	}
}
