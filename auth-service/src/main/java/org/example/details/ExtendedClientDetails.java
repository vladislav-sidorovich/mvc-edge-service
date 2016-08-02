package org.example.details;

import org.example.domain.Client;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.oauth2.provider.ClientDetails;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class ExtendedClientDetails implements ClientDetails {

    private static final String ROLE_CLIENT = "ROLE_CLIENT";

    private static final String SCOPE = "client";

    private static final String GRANT_TYPE_CLIENT_CREDENTIALS = "client_credentials";

    private static final String GRANT_TYPE_PASSWORD = "password";

    private Client client;

    private List<GrantedAuthority> grantedAuthorityList;

    private Set<String> scopes;

    private Set<String> grantTypes;

    public ExtendedClientDetails(Client client) {
        this.client = client;
        grantedAuthorityList = new ArrayList<>();
        grantedAuthorityList.add(new SimpleGrantedAuthority(ROLE_CLIENT));

        scopes = new HashSet<>();
        scopes.add(SCOPE);

        grantTypes = new HashSet<>();
        grantTypes.add(GRANT_TYPE_CLIENT_CREDENTIALS);
        grantTypes.add(GRANT_TYPE_PASSWORD);
    }

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    @Override
    public String getClientId() {
        return client.getClientId().toString();
    }

    @Override
    public Set<String> getResourceIds() {
        return null;
    }

    @Override
    public boolean isSecretRequired() {
        return true;
    }

    @Override
    public String getClientSecret() {
        return client.getSecret();
    }

    @Override
    public boolean isScoped() {
        return false;
    }

    @Override
    public Set<String> getScope() {
        return Collections.EMPTY_SET;
    }

    @Override
    public Set<String> getAuthorizedGrantTypes() {
        return grantTypes;
    }

    @Override
    public Set<String> getRegisteredRedirectUri() {
        return null;
    }

    @Override
    public Collection<GrantedAuthority> getAuthorities() {
        return grantedAuthorityList;
    }

    @Override
    public Integer getAccessTokenValiditySeconds() {
        return null;
    }

    @Override
    public Integer getRefreshTokenValiditySeconds() {
        return null;
    }

    @Override
    public boolean isAutoApprove(String scope) {
        return false;
    }

    @Override
    public Map<String, Object> getAdditionalInformation() {
        return null;
    }
}
