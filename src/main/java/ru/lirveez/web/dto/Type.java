package ru.lirveez.web.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public enum Type {
    @JsonProperty("message_new")
    NEW_MESSAGE,
    @JsonProperty("confirmation")
    CONFIRMATION
}
