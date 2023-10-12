package br.com.fabiopereira.desafio1.controllers;

import br.com.fabiopereira.desafio1.dominio.transaction.Transaction;
import br.com.fabiopereira.desafio1.dtos.TransactionDTO;
import br.com.fabiopereira.desafio1.services.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController

public class TransactionController {

    @Autowired
    private TransactionService transactionService;
    @RequestMapping("/transactions")
    public ResponseEntity<Transaction> createTransaction(@RequestBody TransactionDTO transaction) throws Exception {
         Transaction newTransaction  =   this.transactionService.createTransaction(transaction);
         return new ResponseEntity<>(newTransaction, HttpStatus.OK);
    }


}
