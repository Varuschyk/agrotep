package technikal.task.fishmarket.pojo;

import lombok.*;
import lombok.experimental.FieldDefaults;

@Getter
@Builder
@ToString
@EqualsAndHashCode
@FieldDefaults(level = AccessLevel.PRIVATE)
public class UserReadPojo {
	String username;
}
