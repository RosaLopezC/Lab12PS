package com.tecsup.petclinic.exception;

public class VetNotFoundException extends RuntimeException {

  public VetNotFoundException(Long id) {
    super("No se pudo encontrar al veterinario con ID: " + id);
  }
}
