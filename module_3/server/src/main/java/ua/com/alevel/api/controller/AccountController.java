package ua.com.alevel.api.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ua.com.alevel.api.dto.request.AccountRequestDto;
import ua.com.alevel.api.dto.response.AccountResponseDto;
import ua.com.alevel.facade.AccountFacade;

import java.util.List;

@RestController
@RequestMapping("/api/accounts")
public class AccountController {

    private final AccountFacade accountFacade;

    public AccountController(AccountFacade accountFacade) {
        this.accountFacade = accountFacade;
    }

    @PostMapping
    public ResponseEntity<Boolean> create(@RequestBody AccountRequestDto dto) {
        accountFacade.create(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(true);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Boolean> update(@RequestBody AccountRequestDto dto, @PathVariable Long id) {
        accountFacade.update(dto, id);
        return ResponseEntity.ok(true);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Boolean> delete(@PathVariable Long id) {
        accountFacade.delete(id);
        return ResponseEntity.ok(true);
    }

    @GetMapping("/{id}")
    public ResponseEntity<AccountResponseDto> findById(@PathVariable Long id) {
        return ResponseEntity.ok(accountFacade.findById(id));
    }

    @GetMapping
    public ResponseEntity<List<AccountResponseDto>> findAll(@RequestParam(required = false) Long userId) {
        if (userId != null) {
            return ResponseEntity.ok(accountFacade.findAllByUserId(userId));
        }
        return ResponseEntity.ok(accountFacade.findAll());
    }
}
///employees/new?departmentId=undefined
