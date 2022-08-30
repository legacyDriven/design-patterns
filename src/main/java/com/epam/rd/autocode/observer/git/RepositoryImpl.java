package com.epam.rd.autocode.observer.git;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class RepositoryImpl implements Repository {

    private final Map<String, List<Commit>> commits;

    private final List<WebHook> commitWebHooks;

    private final List<WebHook> mergeWebhooks;

    public RepositoryImpl() {
        this.commitWebHooks = new ArrayList<>();
        this.mergeWebhooks = new ArrayList<>();
        this.commits = new HashMap<>();
    }

    @Override
    public void addWebHook(WebHook webHook) {
        if(webHook.type().equals(Event.Type.COMMIT))
            commitWebHooks.add(webHook);
        else
            mergeWebhooks.add(webHook);
    }

    @Override
    public Commit commit(String branch, String author, String[] changes) {
        Commit toCommit = new Commit(author, changes);
        List<WebHook> toNotify = commitWebHooks.stream().filter(x-> x.branch().equals(branch)).collect(Collectors.toList());
        for(WebHook hook : toNotify){
            //hook.caughtEvents().
        }
        commits.get(branch).add(toCommit);
        return toCommit;
    }

    @Override
    public void merge(String sourceBranch, String targetBranch) {
        List<Commit> toMerge = commits.get(sourceBranch);
        if(!toMerge.isEmpty()) commits.put(targetBranch, toMerge);
    }

    private void notifyWebhooks(String branch, Event.Type event, Commit commit){

    }
}
