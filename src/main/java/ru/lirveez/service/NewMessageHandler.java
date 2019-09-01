package ru.lirveez.service;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import ru.lirveez.service.google.GoogleService;
import ru.lirveez.service.vk.ConversationService;
import ru.lirveez.service.vk.VkRestService;
import ru.lirveez.web.dto.VkRequest;

@Service
@RequiredArgsConstructor
public class NewMessageHandler {
    @Value("${bot.commands.getPlayers}")
    private String PLAYERS;

    private final GoogleService googleService;
    private final VkRestService vkRestService;
    private final ConversationService conversationService;

    public void handle(VkRequest request) {
        String text = request.getObject().getText();
        if (text.charAt(0) == '/') {
            String[] split = text.split(".*? ");
            if (split[0].equals(PLAYERS)) {
                String playersContacts = googleService.getPlayersContacts();
                vkRestService.sendMessageToConversation(conversationService.getConversation(request.getGroup_id()), playersContacts);
            }

        }
    }
}
