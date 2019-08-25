package ru.lirveez.service.vk;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SendMessageResponse {
    private String peer_id;
    private String message_id;
    private String error;
}
