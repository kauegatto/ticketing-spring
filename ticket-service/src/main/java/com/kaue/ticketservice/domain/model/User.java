package com.kaue.ticketservice.domain.model;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;

@Document
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
