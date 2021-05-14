package com.theguitarstore.gs_client.repository;

import org.springframework.stereotype.Repository;
import org.springframework.data.mongodb.repository.MongoRepository;
import com.theguitarstore.gs_client.domain.Client;
/**
 *
 * @author Julian
 */
@Repository
public interface ClientRepository extends MongoRepository<Client, Long>{
    
}
