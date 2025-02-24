package technikal.task.fishmarket.services.user;

import technikal.task.fishmarket.pojo.UserReadPojo;
import technikal.task.fishmarket.pojo.UserWritePojo;

public interface UserService {
	UserReadPojo create(final UserWritePojo userWritePojo);
}
