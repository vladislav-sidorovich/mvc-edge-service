package org.example.services;

import org.example.details.ExtendedClientDetails;
import org.example.domain.Client;
import org.example.repository.ClientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.oauth2.provider.ClientDetailsService;
import org.springframework.security.oauth2.provider.NoSuchClientException;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

@Service
public class ClientService implements ClientDetailsService {

    @Autowired
    private ClientRepository repository;

    @Override
    public ExtendedClientDetails loadClientByClientId(String clientId) throws NoSuchClientException {
        Assert.notNull(clientId, "clientId should no be null");

        Client client = repository.findOne(clientId);
        if (client == null) {
            throw new NoSuchClientException("Client with id " + clientId + " was not found.");
        }

        return new ExtendedClientDetails(client);
    }
}
