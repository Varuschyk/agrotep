package technikal.task.fishmarket.mapper;

import jakarta.annotation.Nonnull;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;
import technikal.task.fishmarket.dto.auth.RegisterRequestDto;
import technikal.task.fishmarket.persistence.entity.UserEntity;
import technikal.task.fishmarket.pojo.UserReadPojo;
import technikal.task.fishmarket.pojo.UserWritePojo;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface UserMapper {
	@Nonnull
	UserReadPojo toUserReadPojo(@Nonnull final UserEntity userEntity);
	@Nonnull
	UserWritePojo toUserWritePojo(@Nonnull final RegisterRequestDto registerRequestDto);
}
