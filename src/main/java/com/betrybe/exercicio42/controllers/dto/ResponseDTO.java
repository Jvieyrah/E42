package com.betrybe.exercicio42.controllers.dto;

public record ResponseDTO<T>(String message, T data) {

}
