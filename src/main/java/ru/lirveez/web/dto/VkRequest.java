package ru.lirveez.web.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class VkRequest {
    private Type type;
    private NewMessageObject object;
}
