package com.nolanmortenson.portfolio.mappers;

import com.nolanmortenson.portfolio.dto.AppUserDTO;
import com.nolanmortenson.portfolio.models.AppUser;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface MapStructMapper {
    AppUserDTO appUserToDto(AppUser appUser);
}
