package ru.lirveez.service.vk;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.util.StringUtils;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
import ru.lirveez.config.VkRestConfigurationData;
import ru.lirveez.service.vk.dto.Conversation;

import java.util.Collections;
import java.util.Optional;

@Service
@Slf4j
@RequiredArgsConstructor
public class VkRestService {

    private final RestTemplate vkRestTemplate;
    private final VkRestConfigurationData vkRestConfigurationData;

    public void sendMessageToConversation(Conversation conversation, String message) {
        String url = buildVkUrl(vkRestConfigurationData.getPath(), conversation, message);

        MultiValueMap<String, String> body = new LinkedMultiValueMap<>();
        body.put(vkRestConfigurationData.getApiVersionParam(), Collections.singletonList(vkRestConfigurationData.getApiVersionValue()));
        body.put(vkRestConfigurationData.getTokenParam(), Collections.singletonList(vkRestConfigurationData.getTokenValue()));
        body.put(vkRestConfigurationData.getChatIdParam(), Collections.singletonList(conversation.toString()));
        body.put(vkRestConfigurationData.getMessageParam(), Collections.singletonList(message));

        MultiValueMap<String, String> headers = new LinkedMultiValueMap<>();
        headers.put("Content-Type", Collections.singletonList("application/x-www-form-urlencoded"));

        HttpEntity<MultiValueMap> request = new HttpEntity<>(body, headers);
        SendMessageResponse response = vkRestTemplate.postForObject(
                url, request, SendMessageResponse.class);
        Optional.ofNullable(response).ifPresent(result -> {
                    if (!StringUtils.isEmpty(result.getError()))
                        log.error("Error while sending messageParam: {}", result.getError());
                }
        );
    }

    private String buildVkUrl(String path, Conversation conversation, String message) {
        return UriComponentsBuilder
                .fromHttpUrl(vkRestConfigurationData.getUrl().trim())
                .path(path.trim())
                .build()
                .toUriString();
    }
}
