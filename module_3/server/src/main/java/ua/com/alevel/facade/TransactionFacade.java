package ua.com.alevel.facade;

import ua.com.alevel.api.dto.request.TransactionRequestDto;
import ua.com.alevel.api.dto.response.TransactionResponseDto;

import java.util.List;

public interface TransactionFacade extends BaseFacade<TransactionRequestDto, TransactionResponseDto> {

    List<TransactionResponseDto> findAllByAccountId(Long accountId);
    List<TransactionResponseDto> findAllByCategoryId(Long categoryId);
}
