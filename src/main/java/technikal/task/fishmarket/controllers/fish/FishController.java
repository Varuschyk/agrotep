package technikal.task.fishmarket.controllers.fish;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import jakarta.validation.Valid;
import technikal.task.fishmarket.dto.fish.FishDto;
import technikal.task.fishmarket.services.fish.FishService;

@Controller
@RequiredArgsConstructor
public class FishController {

	private final FishService fishService;

	@GetMapping({"", "/", "/fish"})
	public String showFishList(Model model) {
		final var fishlist = fishService.get(Sort.by(Sort.Direction.DESC,"id"));
		model.addAttribute("fishlist", fishlist);
		return "index";
	}
	
	@GetMapping("/fish/create")
	public String showCreatePage(Model model) {
		final var fishDto = FishDto.builder().build();
		model.addAttribute("fishDto", fishDto);
		return "createFish";
	}
	
	@GetMapping("/fish/delete")
	public String deleteFish(@RequestParam int id) {
		fishService.delete(id);
		return "redirect:/fish";
	}
	
	@PostMapping("/fish/create")
	public String addFish(@Valid @ModelAttribute FishDto fishDto, BindingResult result) {
		if(fishDto.getImages().isEmpty()) {
			result.addError(new FieldError("fishDto", "imageFile", "Потрібне фото рибки"));
		}
		if(result.hasErrors()) {
			return "createFish";
		}
		fishService.create(fishDto);
		return "redirect:/fish";
	}

}
