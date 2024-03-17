package am.itspace.authorbookrest.endpoint;


import am.itspace.authorbookrest.dto.CountryInfo;
import lombok.RequiredArgsConstructor;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/countries")
public class CountriesEndpoint {

    private final RestTemplate restTemplate;

    private final String COUNTRIES_URL = "https://geography4.p.rapidapi.com/apis/geography/v1/country";

    @GetMapping
    public ResponseEntity<List<CountryInfo>> getAllCountries() {
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.add("X-RapidAPI-Key", "asdf");
        httpHeaders.add("X-RapidAPI-Host", "geography4.p.rapidapi.com");

        HttpEntity httpEntity = new HttpEntity(null, httpHeaders);

        ResponseEntity<CountryInfo[]> exchange = restTemplate.exchange(COUNTRIES_URL, HttpMethod.GET, httpEntity, CountryInfo[].class);
        List<CountryInfo> result = List.of(exchange.getBody());
        return ResponseEntity.ok(result);
    }


}
