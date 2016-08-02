package org.example.domain;

import org.hibernate.annotations.Type;

import javax.persistence.CollectionTable;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import java.io.Serializable;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "user")
public class User implements Serializable {

    private static final long serialVersionUID = -7954197344364872565L;

    @Id
    @Column(name = "user_id")
    private String userId;

    @ManyToOne(optional = false)
    @JoinColumn(name = "client_id", referencedColumnName = "client_id", nullable = false, updatable = false)
    private Client client;

    @ElementCollection(fetch = FetchType.EAGER)
    @CollectionTable(name = "billing_info", joinColumns = @JoinColumn(name = "user_id", referencedColumnName = "user_id"))
    @Column(name = "billing_id")
    @Type(type = "pg-uuid")
    private List<UUID> billingAccounts;

    public User() {
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public List<UUID> getBillingAccounts() {
        return billingAccounts;
    }

    public void setBillingAccounts(List<UUID> billingAccounts) {
        this.billingAccounts = billingAccounts;
    }


    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    @Override
    public boolean equals(Object o) {

        if (this == o) return true;
        if (!(o instanceof User)) return false;

        User user = (User) o;

        return !(userId != null ? !userId.equals(user.userId) : user.userId != null);

    }

    @Override
    public int hashCode() {
        return userId != null ? userId.hashCode() : 0;
    }

    @Override
    public String toString() {
        return "User{" +
                "userId=" + userId +
                ", client=" + client +
                ", billingAccounts=" + billingAccounts +
                '}';
    }
}
