package com.backend.springboot.docker.calculator;

import com.backend.springboot.docker.response.entity.Response;
import com.backend.springboot.docker.response.repository.ResponseRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Component
@Slf4j
public class PersonCalculator {
    private ResponseRepository responseRepository;

    public PersonCalculator(ResponseRepository responseRepository) {

        this.responseRepository = responseRepository;

    }

    /**
     * 9. Once the DB is populated, show at startup log PERSON with highest number of RESPONSE
     */
    @PostConstruct
    private void calculatePersonWithMoreResponses() {

        List<Response> allResponseList = responseRepository.findAll();
        Map<String, Long> personResponsesMap = allResponseList.stream().collect(Collectors.groupingBy(r -> r.getPerson().getName(), Collectors.counting()));
        Map<String, Long> sortedPersonResponseMap = new LinkedHashMap<>();
        personResponsesMap.entrySet().stream().sorted(Map.Entry.<String, Long>comparingByValue().reversed()).forEachOrdered(me -> sortedPersonResponseMap.put(me.getKey(), me.getValue()));
        log.info("The Person with more responses and the winner is .... {} ", sortedPersonResponseMap.keySet().stream().findFirst().get());

    }
}
