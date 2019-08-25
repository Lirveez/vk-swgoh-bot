package ru.lirveez.service.vk;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import ru.lirveez.service.vk.dto.Conversation;

@Service
public class ConversationService {
    @Value("${bot.conversations.test}")
    private String TEST;
    @Value("${bot.conversations.officer}")
    private String OFFICER;
    @Value("${bot.conversations.flud}")
    private String FLUD;
    @Value("${bot.conversations.announcements}")
    private String ANNOUNCEMENTS;

    public Conversation getConversation(String groupId) {
        if (groupId.equals(TEST)){
            return Conversation.TEST;
        }
        return Conversation.TEST;
    }
}
