package ua.com.alevel.facade.impl;

import org.springframework.stereotype.Service;
import ua.com.alevel.api.dto.request.AccountRequestDto;
import ua.com.alevel.api.dto.response.AccountResponseDto;
import ua.com.alevel.facade.AccountFacade;
import ua.com.alevel.persistence.entity.Account;
import ua.com.alevel.persistence.entity.User;
import ua.com.alevel.service.AccountService;
import ua.com.alevel.service.UserService;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class AccountFacadeImpl implements AccountFacade {

    private final AccountService accountService;
    private final UserService userService;

    public AccountFacadeImpl(AccountService accountService, UserService userService) {
        this.accountService = accountService;
        this.userService = userService;
    }

    @Override
    public void create(AccountRequestDto request) {
        Account account = new Account();
        account.setName(request.getName());
        account.setBalance(request.getBalance());
        account.setUsers(userService.findById(request.getUserId()));
        accountService.create(account);
    }

    @Override
    public void update(AccountRequestDto request, Long id) {
        Account account = accountService.findById(id);
        account.setName(request.getName());
        account.setBalance(request.getBalance());
        accountService.update(account);
    }

    @Override
    public void delete(Long id) {
        accountService.delete(id);
    }

    @Override
    public AccountResponseDto findById(Long id) {
        return new AccountResponseDto(accountService.findById(id));
    }

    @Override
    public List<AccountResponseDto> findAll() {
        return accountService.findAll().stream()
                .map(AccountResponseDto::new)
                .collect(Collectors.toList());
    }

    @Override
    public List<AccountResponseDto> findAllByUserId(Long userId) {
        return accountService.findAllByUser(userService.findById(userId))
                .stream().map(AccountResponseDto::new).collect(Collectors.toList());
    }
}
