package technikal.task.fishmarket.dto.fish;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.RequiredArgsConstructor;
import lombok.Value;
import lombok.experimental.FieldDefaults;
import lombok.extern.jackson.Jacksonized;
import org.springframework.web.multipart.MultipartFile;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;

import java.util.Collection;

@Value
@Builder
@Jacksonized
@JsonInclude
@RequiredArgsConstructor
public class FishDto {

	@NotEmpty(message = "потрібна назва рибки")
	String name;
	@Min(0)
	double price;
	Collection<MultipartFile> images;

}
