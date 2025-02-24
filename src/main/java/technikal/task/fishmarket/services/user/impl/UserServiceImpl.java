package technikal.task.fishmarket.services.user.impl;

import jakarta.annotation.Nonnull;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import technikal.task.fishmarket.mapper.UserMapper;
import technikal.task.fishmarket.persistence.UserRoles;
import technikal.task.fishmarket.persistence.entity.UserEntity;
import technikal.task.fishmarket.persistence.repository.UserRepository;
import technikal.task.fishmarket.pojo.UserReadPojo;
import technikal.task.fishmarket.pojo.UserWritePojo;
import technikal.task.fishmarket.services.user.UserService;

@Component
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

	private final UserMapper userMapper;
	private final UserRepository userRepository;
	private final PasswordEncoder passwordEncoder;

	@Override
	public UserReadPojo create(@Nonnull final UserWritePojo userWritePojo) {
		final var username = userWritePojo.getUsername();
		userRepository.findByUsername(username).orElseThrow(
				() -> new RuntimeException("User with current username already exists!"));
		final var userToSave = UserEntity.builder()
				.username(username)
				.password(passwordEncoder.encode(userWritePojo.getPassword()))
				.role(UserRoles.USER)
				.build();

		final var savedUser = userRepository.save(userToSave);
		return userMapper.toUserReadPojo(savedUser);
	}

}
