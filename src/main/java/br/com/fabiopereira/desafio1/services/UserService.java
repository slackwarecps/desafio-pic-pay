package br.com.fabiopereira.desafio1.services;

import br.com.fabiopereira.desafio1.dominio.user.User;
import br.com.fabiopereira.desafio1.dominio.user.UserType;
import br.com.fabiopereira.desafio1.dtos.UserDTO;
import br.com.fabiopereira.desafio1.repositorios.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import java.math.BigDecimal;
import java.util.List;

@Service
public class UserService {
    @Autowired
    private UserRepository repository;


    public void validateTransaction(User sender, BigDecimal amount) throws Exception {
        if (sender.getUserType()== UserType.MERCHANT){
            throw new Exception("usuariodo tipo lojista nao esta autorizado a realizar a transacao");
        }

        if (sender.getBalance().compareTo(amount)<0){
            throw new Exception("Saldo insuficiente");
        }
    }

    public User findUserById(Long id) throws Exception {
       return this.repository.findUserById(id).orElseThrow( ()-> new Exception("Usuario nao encontrado"));

    }

    public void saveUser(User user){
        this.repository.save(user);
    }

    public User createUser(UserDTO data) {
       User  newUser = new User(data);
       this.saveUser(newUser);
       return newUser;
    }

    public List<User> getAllUsers()  {
        return this.repository.findAll();

    }
}
