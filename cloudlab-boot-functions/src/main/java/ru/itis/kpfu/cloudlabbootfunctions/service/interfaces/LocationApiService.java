package ru.itis.kpfu.cloudlabbootfunctions.service.interfaces;

import ru.itis.kpfu.cloudlabbootfunctions.models.Location;
import ru.itis.kpfu.cloudlabbootfunctions.models.SuggestionWrapper;

public interface LocationApiService {

    SuggestionWrapper getResponse(Location location);

}
