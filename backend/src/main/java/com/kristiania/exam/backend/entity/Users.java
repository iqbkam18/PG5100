package com.kristiania.exam.backend.entity;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.List;
import java.util.Set;

//User: having info like name, surname, hashed-password, email, available_loot_boxes,in_game_currency tc.
@Entity
public class Users {
    //Could just use email as primary key, but I think it is easier to login with ID then with email (lengthwise)
    @Id
    @NotBlank
    private String userID;

    @NotBlank
    @Size(max = 128)
    private String name;

    @NotBlank
    @Size(max = 128)
    private String lastName;

    @NotBlank //Not null, and not empty
    private String hashedPassword;

    //We could have agency's that can add new trips and users that are going to book trips
    @ElementCollection(fetch = FetchType.EAGER)
    private Set<String> roles;

    @Email
    @Column(unique = true)
    private String email;

    @NotNull
    private Long availableLootBoxes;

    @NotNull
    private Long inGameCurrency;

    @NotNull
    private Boolean enabled;

    @OneToMany(mappedBy = "users", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Copy> copyList;

    public String getUserID() {
        return userID;
    }

    public void setUserID(String userID) {
        this.userID = userID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getHashedPassword() {
        return hashedPassword;
    }

    public void setHashedPassword(String hashedPassword) {
        this.hashedPassword = hashedPassword;
    }

    public String getEmail() {
        return email;
    }

    public Set<String> getRoles() {
        return roles;
    }

    public void setRoles(Set<String> roles) {
        this.roles = roles;
    }

    public Boolean getEnabled() {
        return enabled;
    }

    public void setEnabled(Boolean enabled) {
        this.enabled = enabled;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Long getAvailableLootBoxes() {
        return availableLootBoxes;
    }

    public Users setAvailableLootBoxes(Long availableLootBoxes) {
        this.availableLootBoxes = availableLootBoxes;
        return this;
    }

    public Long getInGameCurrency() {
        return inGameCurrency;
    }

    public Users setInGameCurrency(Long inGameCurrency) {
        this.inGameCurrency = inGameCurrency;
        return this;
    }

    public List<Copy> getCopyList() {
        return copyList;
    }

    public Users setCopyList(List<Copy> copyList) {
        this.copyList = copyList;
        return this;
    }
}
