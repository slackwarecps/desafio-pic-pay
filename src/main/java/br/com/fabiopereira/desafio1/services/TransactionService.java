package br.com.fabiopereira.desafio1.services;

import br.com.fabiopereira.desafio1.dominio.transaction.Transaction;
import br.com.fabiopereira.desafio1.dominio.user.User;
import br.com.fabiopereira.desafio1.dtos.NotificationDTO;
import br.com.fabiopereira.desafio1.dtos.TransactionDTO;
import br.com.fabiopereira.desafio1.repositorios.TransactionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Map;

@Service
public class TransactionService {
    @Autowired
    private UserService userService;
    @Autowired
    private TransactionRepository repository;

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private NotificationService notificationService;


    public Transaction createTransaction(TransactionDTO transaction) throws Exception {
        User sender = this.userService.findUserById(transaction.senderId());
        User receiver = this.userService.findUserById(transaction.receiverId());

        userService.validateTransaction(sender,transaction.value());

        if (this.authorizeTransaction(sender,transaction.value())){
            throw new Exception("Transacao não autorizada");

        }

        Transaction newTransaction = new Transaction();
        newTransaction.setAmount(transaction.value());
        newTransaction.setSender(sender);
        newTransaction.setReceiver(receiver);
        newTransaction.setTimestamp(LocalDateTime.now());

        sender.setBalance(sender.getBalance().subtract(transaction.value()));
        receiver.setBalance(receiver.getBalance().add(transaction.value()));

        repository.save(newTransaction);
        userService.saveUser(sender);
        userService.saveUser(receiver);

        this.notificationService.sendNotification(sender,"Transacao realizada com sucesso");
        this.notificationService.sendNotification(receiver,"Transacao recebida com sucesso");

        return newTransaction;
    }

    public boolean authorizeTransaction(User sender, BigDecimal value){
        System.out.println("validando autorizacao...");
       ResponseEntity<Map> authorizationResponse = restTemplate.getForEntity("https://run.mocky.io/v3/6a348f7b-f371-4dae-bff9-0f352caa2886", Map.class);
        if (authorizationResponse.getStatusCode()== HttpStatus.OK){
            String message = (String) authorizationResponse.getBody().get("message");
            return "Autorizado".equalsIgnoreCase(message);
        } else return false;
    }
}
