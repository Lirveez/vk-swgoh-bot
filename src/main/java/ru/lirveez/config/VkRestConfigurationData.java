package ru.lirveez.config;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

@Configuration
@Data
public class VkRestConfigurationData {
    @Value("${bot.message.url}")
    private String url;
    @Value("${bot.message.path}")
    private String path;
    @Value("${bot.message.chatIdParam}")
    private String chatIdParam;
    @Value("${bot.message.messageParam}")
    private String messageParam;
    @Value("${bot.message.apiVersionParam}")
    private String apiVersionParam;
    @Value("${bot.message.apiVersionValue}")
    private String apiVersionValue;
    @Value("${bot.message.tokenParam}")
    private String tokenParam;
    @Value("${bot.message.tokenValue}")
    private String tokenValue;
}
