package com.epam.rd.autocode.observer.git;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class WebHookImpl implements WebHook {

    private Event.Type eventType;
    private final String branch;
    private List<Event> caughtEvents;

    public WebHookImpl(String branch) {
        this.branch = branch;
        this.caughtEvents = new ArrayList<>();
    }

    @Override
    public String branch() { return branch; }

    @Override
    public Event.Type type() {
        return eventType;
    }

    public void setEventType(Event.Type eventType) {
        this.eventType = eventType;
    }

    @Override
    public List<Event> caughtEvents() {
        return caughtEvents;
    }

    @Override
    public void onEvent(Event event) {
        caughtEvents.add(event);
        if(this.eventType.equals(Event.Type.MERGE)){
            caughtEvents = caughtEvents.stream().distinct().collect(Collectors.toList());
        }
    }
}
