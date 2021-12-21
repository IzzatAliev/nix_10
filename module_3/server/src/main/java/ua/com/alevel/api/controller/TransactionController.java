package ua.com.alevel.api.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ua.com.alevel.api.dto.request.TransactionRequestDto;
import ua.com.alevel.api.dto.response.TransactionResponseDto;
import ua.com.alevel.facade.TransactionFacade;

import java.util.List;

@RestController
@RequestMapping("/api/transactions")
public class TransactionController {

    private final TransactionFacade transactionFacade;

    public TransactionController(TransactionFacade transactionFacade) {
        this.transactionFacade = transactionFacade;
    }

    @PostMapping
    public ResponseEntity<Boolean> create(@RequestBody TransactionRequestDto dto) {
        transactionFacade.create(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(true);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Boolean> update(@RequestBody TransactionRequestDto dto, @PathVariable Long id) {
        transactionFacade.update(dto, id);
        return ResponseEntity.ok(true);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Boolean> delete(@PathVariable Long id) {
        transactionFacade.delete(id);
        return ResponseEntity.ok(true);
    }

    @GetMapping("/{id}")
    public ResponseEntity<TransactionResponseDto> findById(@PathVariable Long id) {
        return ResponseEntity.ok(transactionFacade.findById(id));
    }

    @GetMapping
    public ResponseEntity<List<TransactionResponseDto>> findAll(@RequestParam(required = false) Long accountId,
                                                                @RequestParam(required = false) Long categoryId) {
        if (accountId != null) {
            return ResponseEntity.ok(transactionFacade.findAllByAccountId(accountId));
        }
        else if (categoryId != null){
            return ResponseEntity.ok(transactionFacade.findAllByCategoryId(categoryId));
        }
        return ResponseEntity.ok(transactionFacade.findAll());
    }
}
