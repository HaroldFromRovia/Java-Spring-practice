package ru.itis.kpfu.bentos.springboothomework.utils;

import com.maxmind.geoip2.DatabaseReader;
import com.maxmind.geoip2.model.CountryResponse;
import com.maxmind.geoip2.record.Country;
import lombok.SneakyThrows;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.net.InetAddress;
import java.util.Optional;

@Component
public class Geolocation {

    @SneakyThrows
    public boolean checkRussia(String ip) {
        File database = new File("src/main/resources/db/GeoLite2-Country.mmdb");

        DatabaseReader reader = new DatabaseReader.Builder(database).build();

        InetAddress ipAddress = InetAddress.getByName(ip);

        CountryResponse response = reader.country(ipAddress);

        Country country = response.getCountry();

        return country.getIsoCode().equals("RU");
    }

    public String getClientIP(HttpServletRequest request) {

        Optional<String> xfHeaderCandidate = Optional.ofNullable(request.getHeader("X-Forwarded-For"));
        if (xfHeaderCandidate.isEmpty()) {
            return request.getRemoteAddr();
        }
        String xfHeader = xfHeaderCandidate.get();
        return xfHeader.split(",")[0];
    }
}
