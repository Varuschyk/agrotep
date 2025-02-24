package technikal.task.fishmarket.dto.auth;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Builder;
import lombok.RequiredArgsConstructor;
import lombok.Value;
import lombok.extern.jackson.Jacksonized;

@Value
@Builder
@Jacksonized
@JsonInclude
@RequiredArgsConstructor
public class RegisterRequestDto {
	String username;
	String password;
}
