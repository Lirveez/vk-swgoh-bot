package ru.lirveez.service.google;

import com.google.api.services.sheets.v4.Sheets;
import com.google.api.services.sheets.v4.model.ValueRange;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Slf4j
@RequiredArgsConstructor
public class GoogleService {
    private static final String SPREAD_SHEET_ID = "11rlxX8eqFjIDg5fSPLqUnFeXUXd2rce2cdlg3Wv7KME";
    private static final String RANGE = "G2:G51";

    private final Sheets service;

    public String getPlayersContacts() {
        ValueRange response;
        try {
            response = service.spreadsheets().values()
                    .get(SPREAD_SHEET_ID, RANGE)
                    .execute();
        } catch (IOException exception) {
            log.error("Google Tables Error: {}", exception);
            return "Exception during Google Sheets call.";
        }
        if (response == null || CollectionUtils.isEmpty(response.getValues())) {
            return "No data found.";
        } else {
            List<List<Object>> values = response.getValues();
            log.info("Found {} guild mates", values.size());
            return values.stream()
                    .map(row -> row.get(0).toString())
                    .collect(Collectors.joining("\n"));
        }
    }
}
