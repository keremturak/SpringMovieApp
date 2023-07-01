package com.keremturak.mapper;

import com.keremturak.dto.request.RegisterUserRequestDto;
import com.keremturak.dto.response.UserLoginResponseDto;
import com.keremturak.dto.response.UserRegisterWvResponseDto;
import com.keremturak.entity.User;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "string", unmappedTargetPolicy = ReportingPolicy.IGNORE)

public interface IUserMapper{

    IUserMapper INSTANCE = Mappers.getMapper(IUserMapper.class);

    User UserFromDto(final UserRegisterWvResponseDto dto);
    RegisterUserRequestDto DtoFromUser(final User user);
    UserLoginResponseDto dtoFromUserLogin(final User user);

}
