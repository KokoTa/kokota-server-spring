package com.example.kokotaserver.entity.request;

import java.io.Serializable;
import java.util.List;

import javax.validation.constraints.NotEmpty;

import lombok.Data;

@Data
public class RequestIds implements Serializable {

  private static final long serialVersionUID = 1870545634L;

  @NotEmpty
  private List<Long> ids;
}
