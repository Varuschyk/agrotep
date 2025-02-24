package technikal.task.fishmarket.dto.auth;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Builder;
import lombok.Value;
import lombok.extern.jackson.Jacksonized;

@Value
@Builder
@Jacksonized
@JsonInclude
public class RegisterRequestDto {
	String username;
	String password;
}
