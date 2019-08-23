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
        if (request.getType().equals("confirmation"))
            return confirmationCode;
        return okStatus;
    }
}
