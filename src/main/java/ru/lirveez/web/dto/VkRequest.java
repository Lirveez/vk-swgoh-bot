package ru.lirveez.web.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class VkRequest {
    private String type;
    private NewMessageObject object;
    private String group_id;
}
