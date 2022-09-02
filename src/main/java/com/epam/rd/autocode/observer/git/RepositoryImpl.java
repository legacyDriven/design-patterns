package com.epam.rd.autocode.observer.git;

import java.util.*;
import java.util.stream.Collectors;

public class RepositoryImpl implements Repository {

    private final Map<Event.Type, List<WebHook>> webhooks;

    public RepositoryImpl() {
        this.webhooks = new HashMap<>();
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
        List<WebHook> toNotify = getWebhooksToNotify(branch);
        if(!toNotify.isEmpty()){
            for(WebHook hook : toNotify) hook.onEvent(new Event(Event.Type.COMMIT, branch, new ArrayList<>(List.of(toCommit))));
        }
        return toCommit;
    }

    private List<WebHook> getWebhooksToNotify(String branch){
        if(webhooks.containsKey(Event.Type.COMMIT)) {
            return webhooks.get(Event.Type.COMMIT).stream()
                    .filter(n -> n.branch().equals(branch))
                    .collect(Collectors.toList());
        } else return new ArrayList<>();
    }

    @Override
    public void merge(String sourceBranch, String targetBranch) {
        if(checkIfSourceBranchExists(sourceBranch) && checkIfMergeTargetExists(targetBranch)) {
            List<Commit> toMerge = getCommitsToMerge(sourceBranch);
            Optional<WebHook> toMergeTo = Optional.of(webhooks.get(Event.Type.MERGE).stream()
                    .filter(n -> n.branch().equals(targetBranch))
                    .findFirst()
                    .get());
            toMergeTo.ifPresent(webHook -> webHook.onEvent(new Event(Event.Type.MERGE, targetBranch, toMerge)));
        }
    }

    private boolean checkIfSourceBranchExists(String sourceBranch){
        List<WebHook> hooks = webhooks.get(Event.Type.COMMIT);
        if(hooks!=null) {
            List<WebHook> result = webhooks.get(Event.Type.COMMIT).stream()
                    .filter(n -> n.branch().equals(sourceBranch))
                    .collect(Collectors.toList());
            return !result.isEmpty();
        }
        return false;
    }

    private boolean checkIfMergeTargetExists(String targetBranch){
        WebHook hook = webhooks.get(Event.Type.MERGE).stream()
                .filter(n->n.branch().equals(targetBranch))
                .findFirst()
                .orElse(null);
        return hook!=null;
    }

    private List<Commit> getCommitsToMerge(String sourceBranch){
        return webhooks.get(Event.Type.COMMIT)
                .stream().filter(n -> n.branch().equals(sourceBranch))
                .map(WebHook::caughtEvents)
                .flatMap(List::stream)
                .map(Event::commits)
                .flatMap(List::stream)
                .collect(Collectors.toList());
    }
}
