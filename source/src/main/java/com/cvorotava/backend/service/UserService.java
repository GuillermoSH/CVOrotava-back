package com.cvorotava.backend.service;

import java.util.List;
import java.util.Optional;

import com.cvorotava.backend.dto.UserDto;
import com.cvorotava.backend.error.exception.DeleteOperationException;
import com.cvorotava.backend.error.exception.InternalServerException;
import com.cvorotava.backend.error.exception.NoContentException;
import com.cvorotava.backend.error.exception.NotFoundException;
import com.cvorotava.backend.mapper.IUserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cvorotava.backend.entity.User;
import com.cvorotava.backend.repository.UserRepository;
import org.springframework.transaction.annotation.Transactional;

@Service
public class UserService implements IUserService {
	private final UserRepository userrepository;
	private final IUserMapper userMapper;

	@Autowired
	public UserService (UserRepository userRepository, IUserMapper userMapper) {
		this.userrepository = userRepository;
		this.userMapper = userMapper;
	}

	@Override
	@Transactional(readOnly = true)
	public List<UserDto> findAll() {
		return getUsersOrThrowNoContent(userrepository.findAll(), "en la BBDD aun");
	}

	@Override
	@Transactional(readOnly = true)
	public UserDto findById(Integer id) {
		return getUserOrThrowNotFound(userrepository.findById(id), "con ese id");
	}

	@Override
	@Transactional(readOnly = true)
	public UserDto findByUsername(String username) {
		return getUserOrThrowNotFound(userrepository.findByUsername(username), "con ese nombre de usuario");
	}

	@Override
	@Transactional
	public void delete(UserDto dto) {
		try {
			userrepository.delete(userMapper.userDTOToEntity(dto));
		} catch (Exception e) {
			throw new DeleteOperationException("No se ha podido borrar el usuario", e);
		}
	}

	@Override
	@Transactional
	public UserDto save(User user) {
		try {
			return userMapper.userToDTO(userrepository.save(user));
		} catch (Exception e) {
			throw new InternalServerException("No se ha podido insertar el user en la base de datos", e);
		}
	}

	private UserDto getUserOrThrowNotFound(Optional<User> user, String message) {
		return userMapper.userToDTO(user.orElseThrow(() -> new NotFoundException("No existe el usuario " + message)));
	}

	private List<UserDto> getUsersOrThrowNoContent(List<User> users, String message) {
		if (users.isEmpty()) {
			throw new NoContentException("No existen usuarios " + message);
		}
		return userMapper.usersListToDTOList(users);
	}
}
