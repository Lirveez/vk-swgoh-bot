package ru.lirveez.web;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.lirveez.web.dto.VkRequest;

import static ru.lirveez.web.dto.Type.CONFIRMATION;
import static ru.lirveez.web.dto.Type.NEW_MESSAGE;

@RestController
@Validated
@RequestMapping("/bot")
@RequiredArgsConstructor
@Slf4j
public class Controller {

    @Value("${bot.statusOk}")
    private String okStatus;
    @Value("${bot.confirmationCode}")
    private String confirmationCode;

    @PostMapping(value = "/dnm")
    public String handle(@RequestBody VkRequest request) {
        if (request.getType().equals(CONFIRMATION)) {
            return confirmationCode;
        } else if (request.getType().equals(NEW_MESSAGE)) {

        }
        return okStatus;
    }
}
