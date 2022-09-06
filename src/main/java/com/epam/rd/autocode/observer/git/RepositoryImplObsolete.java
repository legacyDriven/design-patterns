package com.epam.rd.autocode.observer.git;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class RepositoryImplObsolete implements Repository {

    private final Map<String, List<Commit>> commits;

    private Map<Event.Type, List<WebHook>> webhooks;

    public RepositoryImplObsolete() {
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
        if(webhooks.containsKey(Event.Type.COMMIT))  {
            List<WebHook> toNotify = webhooks.get(Event.Type.COMMIT).stream().filter(n-> n.branch().equals(branch)).collect(Collectors.toList());
            for(WebHook hook : toNotify){
                hook.onEvent(toPropagate);
            }
        } //notifyWebhooks(toPropagate);
        addCommitToRepo(toCommit, branch);
        return toCommit;
    }

    private void addCommitToRepo(Commit commit, String branch){
        if(commits.containsKey(branch)) commits.get(branch).add(commit);
        else commits.put(branch, new ArrayList<>(List.of(commit)));
    }

//    @Override
//    public void merge(String sourceBranch, String targetBranch) {
//        List<Event> toMerge = extractEventsToMerge(sourceBranch);
//        for (Event e : toMerge){
//        notifyWebhooks(new Event(Event.Type.MERGE, targetBranch, e.commits()));
//        }
//    }
    public void merge(String sourceBranch, String targetBranch){
        if(commits.containsKey(sourceBranch)){
            List<Commit> toMerge = commits.get(sourceBranch);
            notifyWebhooks(new Event(Event.Type.MERGE, targetBranch, toMerge));
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
