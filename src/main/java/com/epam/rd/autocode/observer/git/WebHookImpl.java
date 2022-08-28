package com.epam.rd.autocode.observer.git;

import java.util.List;

public class WebHookImpl implements WebHook {
    @Override
    public String branch() {
        return null;
    }

    @Override
    public Event.Type type() {
        return null;
    }

    @Override
    public List<Event> caughtEvents() {
        return null;
    }

    @Override
    public void onEvent(Event event) {

    }
}
