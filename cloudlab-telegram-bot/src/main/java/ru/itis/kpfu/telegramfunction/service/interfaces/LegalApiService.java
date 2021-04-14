package ru.itis.kpfu.telegramfunction.service.interfaces;

import ru.itis.kpfu.telegramfunction.models.Request;
import ru.itis.kpfu.telegramfunction.models.SuggestionWrapper;

public interface LegalApiService {

    SuggestionWrapper getResponse(Request request);

}
