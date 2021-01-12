package com.palmerale91.sportsbetting.model.respositories;

import com.palmerale91.sportsbetting.model.SportEvent;
import org.springframework.stereotype.Repository;

import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Repository
public class OnMemorySportEventRepository implements SportEventRepository {

    private List<SportEvent> events;

    public OnMemorySportEventRepository() {
        events = new ArrayList<>();
    }

    @Override
    public List<SportEvent> getData() {
        if(events.size() == 0) {
            seedData();
        }
        return events;
    }

    private void seedData() {
        events.addAll(
                IntStream.range(1, 10).mapToObj(i -> {
                    var e = new SportEvent();
                    e.setId(i);
                    e.setName("Event " + i);
                    e.setOdds1stTeam(Math.random() * 10);
                    e.setOddsForDraw(Math.random() * 10);
                    e.setOdds2ndTeam(Math.random() * 10);
                    e.setDate(ZonedDateTime.now());
                    return e;
                }).collect(Collectors.toList())
        );
    }
}
