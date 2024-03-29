package com.kpatil.friend.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotBlank;

@Entity
public class Friend {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private int id;

  @JsonProperty("first-name")
  @NotBlank
  private String firstName;

  @JsonProperty("last-name")
  @NotBlank
  private String lastName;

  private int age;

  @JsonIgnore
  private boolean married;

  @JsonManagedReference
  @OneToMany(mappedBy = "friend", cascade = CascadeType.ALL)
  @JsonProperty("addresses")
  private List<Address> addressList;

  public Friend() {
  }

  public Friend(@NotBlank String firstName,
      @NotBlank String lastName, int age, boolean married,
      List<Address> addressList) {
    this.firstName = firstName;
    this.lastName = lastName;
    this.age = age;
    this.married = married;
    this.addressList = addressList;
  }

  public List<Address> getAddressList() {
    return addressList;
  }

  public void setAddressList(List<Address> addressList) {
    this.addressList = addressList;
  }

  public int getAge() {
    return age;
  }

  public void setAge(int age) {
    this.age = age;
  }

  public boolean isMarried() {
    return married;
  }

  public void setMarried(boolean married) {
    this.married = married;
  }

  public int getId() {
    return id;
  }

  public void setId(int id) {
    this.id = id;
  }

  public String getFirstName() {
    return firstName;
  }

  public void setFirstName(String firstName) {
    this.firstName = firstName;
  }

  public String getLastName() {
    return lastName;
  }

  public void setLastName(String lastName) {
    this.lastName = lastName;
  }
}
