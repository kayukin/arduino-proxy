package com.kayukin.arduinoproxy.connection;

import com.kayukin.arduinoproxy.model.SensorsData;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

public class ThingSpeakConnection {
    private static final Logger log = LoggerFactory.getLogger(ThingSpeakConnection.class);
    private static final String API_URL = "https://api.thingspeak.com/update";
    private final RestTemplate restTemplate;

    public ThingSpeakConnection(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public void sendSensorsData(SensorsData sensorsData) {
        ResponseEntity<Object> response = restTemplate.getForEntity(UriComponentsBuilder.fromHttpUrl(API_URL)
                .queryParam("api_key", "EQEGDGU0V5OB5IAY")
                .queryParam("field1", sensorsData.getTemperature())
                .queryParam("field2", sensorsData.getHumidity())
                .toUriString(), Object.class);
        if (!response.getStatusCode().is2xxSuccessful()) {
            log.error("Failed to send sensors data: {}", response.getStatusCode());
        }
    }
}
