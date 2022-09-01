package com.epam.rd.autocode.observer.git;

import java.util.ArrayList;
import java.util.List;

public class WebHookImpl implements WebHook {

    private Event.Type eventType;
    private final String branch;
    private final List<Event> caughtEvents;

    public WebHookImpl(String branch) {
        this.branch = branch;
        this.caughtEvents = new ArrayList<>();
    }

    @Override
    public String branch() {
        return branch;
    }

    public void setEventType(Event.Type eventType) {
        this.eventType = eventType;
    }

    @Override
    public Event.Type type() {
        return eventType;
    }

    @Override
    public List<Event> caughtEvents() {
        return caughtEvents;
    }

    @Override
    public void onEvent(Event event) {
        if (eventType.equals(Event.Type.COMMIT)) caughtEvents.add(event);
        else {
            //caughtEvents().add(event);
            for (Commit c : event.commits()) {

                // caughtEvents.add(new Event(Event.Type.MERGE, event.branch(), event.commits()));
            }
        }
    }
    @Override
    public String toString() {
        return "WebHookImpl{" +
                "eventType=" + eventType +
                ", branch='" + branch + '\'' +
                ", caughtEvents=" + caughtEvents +
                '}';
    }
}
