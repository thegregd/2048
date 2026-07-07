package com.greg._2048.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.greg._2048.service.Status;

import static com.fasterxml.jackson.annotation.JsonInclude.Include;

@JsonInclude(Include.NON_EMPTY)
public record Game(int[][] board, Status status, int score, String hint) {
}
