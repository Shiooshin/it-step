package com.it.step.events.producer;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.*;

public final class Randomizer {
    private Randomizer() {
    }

    private static final Logger LOG = LoggerFactory
            .getLogger(Randomizer.class);

    private static final Map<String, String> COUNTRY_IPS = Map.of("United States", "18.10",
            "Europe", "3.74",
            "Africa", "882.124",
            "Australia", "123.90",
            "Ukraine", "177.11");

    private static final List<String> EVENT_TYPES =
            Arrays.asList("lights-on", "light-off", "dim", "brighten", "error", "disco");

    private static final List<String> ROUTER_NAMES =
            Arrays.asList("router-1", "router-2", "unknown", "");

    private static final Random RANDOM = new Random();


    public static String getRandomValue() {

        ObjectMapper objectMapper = new ObjectMapper();
        String jsonValues = null;
        try {
            jsonValues = objectMapper.writeValueAsString(getRandomMap());
            System.out.println(jsonValues);
        } catch (JsonProcessingException e) {
            LOG.error("Got exception {}", e.getMessage());
        }

        return jsonValues;
    }

    private static Map<String, String> getRandomMap() {
        Map<String, String> sendValues = new HashMap<>();
        sendValues.put("time", String.valueOf(System.currentTimeMillis()));

        sendValues.put("ip", getRandomIP());
        sendValues.put("event-name", EVENT_TYPES.get(RANDOM.nextInt(EVENT_TYPES.size())));
        sendValues.put("energy-consumption", String.valueOf(RANDOM.nextInt(35) - 10));
        sendValues.put("router-name", ROUTER_NAMES.get(RANDOM.nextInt(ROUTER_NAMES.size())));

        return sendValues;
    }

    private static String getRandomIP() {
        List<String> keySet = new LinkedList<>(COUNTRY_IPS.keySet());
        String ipPrefix = COUNTRY_IPS.get(keySet.get(RANDOM.nextInt(keySet.size())));

        return new StringBuilder().append(ipPrefix)
                .append('.')
                .append(RANDOM.nextInt(256))
                .append('.')
                .append(RANDOM.nextInt(256))
                .toString();
    }
}
