package ru.lirveez.service.vk.dto;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public enum Conversation {
    TEST("1");

    private String value;

    @Override
    public String toString() {
        return this.value;
    }
}
