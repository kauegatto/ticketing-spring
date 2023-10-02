package com.kaue.ticketservice.domain.model;

import jakarta.persistence.Entity;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Entity
public class User {
  public User(String name, String lastName, Date bornAt) {
    this.name = name;
    this.lastName = lastName;
    this.bornAt = bornAt;
  }
  @Getter @Setter
  String cpf;
  @Getter @Setter
  private String name;

  @Getter @Setter
  private String lastName;

  @Getter @Setter
  private Date bornAt;
}
