package com.portfolio;

import org.json.JSONObject;
import java.util.HashSet;
import java.util.Set;
import javax.persistence.FetchType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.OneToOne;
import javax.persistence.ManyToMany;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Entity
/* @Table(    name = "users",
        uniqueConstraints = {
            @UniqueConstraint(columnNames = "username"),
            @UniqueConstraint(columnNames = "email")
        }) */
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @OneToOne
    private Account account;

    @NotBlank
    @Size(max = 20)
    private String username;

    @NotBlank
    @Size(max = 50)
    @Email
    private String email;

    @NotBlank
    @Size(max = 120)
    private String password;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable( name = "user_roles",
                joinColumns = @JoinColumn(name = "user_id"),
                inverseJoinColumns = @JoinColumn(name = "role_id"))
    private Set<Role> roles = new HashSet<>();

    private int age;

    protected User() {}

    public User(String username, String password, String email, int age) {
        this.username = username;
        this.password = password;
        this.email = email;
        this.age = age;
    }

    public long getId() { return this.id; }
    public Account getAccount() { return this.account; }
    public String getUsername() { return this.username; }
    public String getEmail() { return this.email; }
    public String getPassword() { return this.password; }
    public Set<Role> getRoles() { return this.roles; }

    public void setAccount(Account account) { this.account = account; }
    public void setRoles(Set<Role> roles) { this.roles = roles; }

    public JSONObject toJson() {
        JSONObject object = new JSONObject();
        object.put("id", this.id);
        object.put("account", this.account.toJson());
        object.put("username", this.username);
        object.put("email", this.email);
        object.put("age", this.age);
        return object;
    }

    public String toString() {
        return String.format(
            "User(id: %s, username: %s, email: %s, age: %s)",
            this.id, this.username, this.email, this.age);
    }
}
