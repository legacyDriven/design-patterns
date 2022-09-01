package com.epam.rd.autocode.observer.git;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Consumer;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class RepositoryImpl implements Repository {

    private final Map<String, List<Commit>> commits;

    private Map<Event.Type, List<WebHook>> webhooks;

    public RepositoryImpl() {
        this.webhooks = new HashMap<>();
        this.commits = new HashMap<>();
    }

    @Override
    public void addWebHook(WebHook webHook) {
        if(!webhooks.containsKey(webHook.type())) addNewEntryOfType(webhooks, webHook);
        else webhooks.get(webHook.type()).add(webHook);
    }

    private void addNewEntryOfType(Map<Event.Type, List<WebHook>> hooks, WebHook webHook){
        hooks.put(webHook.type(), new ArrayList<>());
        hooks.get(webHook.type()).add(webHook);
    }

    @Override
    public Commit commit(String branch, String author, String[] changes) {
        Commit toCommit = new Commit(author, changes);
        Event toPropagate = new Event(Event.Type.COMMIT, branch, Stream.of(toCommit).collect(Collectors.toCollection(ArrayList::new)));
        notifyWebhooks(toPropagate);
        //commits.get(branch).add(toCommit);
        return toCommit;
    }

    @Override
    public void merge(String sourceBranch, String targetBranch) {
        List<Event> toMerge = extractEventsToMerge(sourceBranch);
        for (Event e : toMerge){
        notifyWebhooks(new Event(Event.Type.MERGE, targetBranch, e.commits()));
        }
    }

    private List<Event> extractEventsToMerge(String sourceBranch){
        return webhooks.get(Event.Type.COMMIT).stream()
                .filter(n-> n.branch().equals(sourceBranch))
                .map(WebHook::caughtEvents)
                .flatMap(List::stream)//.distinct() might be needed to add
                .collect(Collectors.toList());
    }

    private void notifyWebhooks(Event event) {
        Event.Type type = event.type();
        //if (event.type().equals(Event.Type.COMMIT)) {
            webhooks.get(type).stream()
                    .filter(s -> s.branch().equals(event.branch()))
                    .forEach(n -> n.onEvent(event));

    }
    @Override
    public String toString() {
        return "RepositoryImpl{" +
                "commits=" + commits +
                ", webhooks=" + webhooks +
                '}';
    }
}
