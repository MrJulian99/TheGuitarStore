package com.theguitarstore.gs_client.web;

import com.theguitarstore.gs_client.repository.ClientRepository;
import com.theguitarstore.gs_client.services.SequenceGeneratorService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.theguitarstore.gs_client.domain.Client;
import com.theguitarstore.gs_client.exceptions.ResourceNotFoundException;
import java.util.HashMap;
import java.util.Map;
import javax.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;


/**
 *
 * @author Julian
 */
@RestController
@RequestMapping("/customer_api/v1")
public class ClientController {
    
    @Autowired
    public ClientRepository clientRepository;
    
    @Autowired
    public SequenceGeneratorService sequenceGeneratorService;
    
    @GetMapping("/getAllClients")
    public List< Client >  getAllClients(){
       return  clientRepository.findAll();
    }
    
    @GetMapping("/getAllClients/{id}")
    public ResponseEntity <Client> getClientById(@PathVariable(value ="id") Long clientId)
    throws ResourceNotFoundException {
        Client client = clientRepository.findById(clientId)
            .orElseThrow(() -> new ResourceNotFoundException("Client not found for this id: " + clientId));
        return ResponseEntity.ok().body(client);
    }
    
    @PutMapping("/getAllClients/{id}")
    public ResponseEntity<Client>  updateClient(@PathVariable(value="id") Long clientId,
            @Valid @RequestBody Client newUpdatedClient) throws ResourceNotFoundException{
    //@RequestBody nos transforma de un HttpBody a un Objeto Java(En este caso Spring automaticmanete
            //des-serializa el JSON en un objeto java del tipo indicado (Client) en este caso.      
       Client client = clientRepository.findById(clientId)
               .orElseThrow(()->new ResourceNotFoundException("Client not found with id: " + clientId));
        //clientRepository.deleteById(clientId);
        client.setEmail(newUpdatedClient.getEmail());
        client.setName(newUpdatedClient.getName());
        client.setPhoneNumber(newUpdatedClient.getPhoneNumber());
        final Client updatedClient = clientRepository.save(client);
        return ResponseEntity.ok().body(updatedClient);
    } 
    
    @DeleteMapping("/getAllClients/{id}")
    public Map<String,Boolean> deleteClient(@PathVariable(value="id") Long clientId) 
            throws ResourceNotFoundException{
        Client client = clientRepository.findById(clientId)
                .orElseThrow(()->new ResourceNotFoundException("Client not found with id: " +clientId));
        
        clientRepository.delete(client);
        Map<String,Boolean> response = new HashMap<>();
        response.put("deleted", Boolean.TRUE);
        return response;
    }
    
    @PostMapping("/getAllClients")
    public Client createClient(@Valid @RequestBody Client client){
        client.setId(sequenceGeneratorService.generateSequence(Client.SEQUENCE_NAME));
        return clientRepository.save(client);
    }
}
