package technikal.task.fishmarket.controllers.auth;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import technikal.task.fishmarket.dto.auth.AuthRequestDto;
import technikal.task.fishmarket.dto.auth.RegisterRequestDto;
import technikal.task.fishmarket.mapper.UserMapper;
import technikal.task.fishmarket.services.user.UserService;

@Validated
@Controller
@RequiredArgsConstructor
public class AuthenticationController {

	private final UserMapper userMapper;
	private final UserService userService;

	@GetMapping("/login")
	public String loginPage(@NotNull final Model model) {
		model.addAttribute("loginRequestDto", new AuthRequestDto("", ""));
		return "login";
	}

	@PostMapping("/register")
	public String registerUser(
			@NotNull @ModelAttribute("registerRequestDto") @Valid final RegisterRequestDto requestDto,
			@NotNull final BindingResult result) {
		final var userWritePojo = userMapper.toUserWritePojo(requestDto);
		userService.create(userWritePojo);
		return "redirect:/login";
	}
}
