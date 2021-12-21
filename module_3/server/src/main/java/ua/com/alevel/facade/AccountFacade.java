package ua.com.alevel.facade;

import ua.com.alevel.api.dto.request.AccountRequestDto;
import ua.com.alevel.api.dto.response.AccountResponseDto;

import java.util.List;

public interface AccountFacade extends BaseFacade<AccountRequestDto, AccountResponseDto> {

    List<AccountResponseDto> findAllByUserId(Long userId);
}
