package com.mappers;

import com.dto.AppUserDTO;
import com.models.AppUser;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface MapStructMapper {
    AppUserDTO appUserToDto(AppUser appUser);
}
