package br.com.fabiopereira.desafio1.services;

import br.com.fabiopereira.desafio1.dominio.user.User;
import br.com.fabiopereira.desafio1.dtos.NotificationDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class NotificationService {
    @Autowired
    private RestTemplate restTemplate;

    public void sendNotification(User user, String message) throws Exception {
        String email = user.getEmail();
        NotificationDTO notificationRequest = new NotificationDTO(email,message);

        ResponseEntity<String> notificationResponse = restTemplate.postForEntity("https://19875.wiremockapi.cloud/notify",notificationRequest,String.class);

        if (notificationResponse.getStatusCode() != HttpStatus.OK){
            System.out.println("Erro ao enviar notificacao");
            throw new Exception("Servico de notificacao esta fora do ar");
        }

    }

}
