package com.epam.rd.autocode.observer.git;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class RepositoryImpl implements Repository {

    private final Map<String, List<Commit>> commits;//List<Commit> commits;

    private final List<WebHook> webHooks;

    public RepositoryImpl() {
        this.webHooks = new ArrayList<>();
        this.commits = new HashMap<>();
    }

    @Override
    public void addWebHook(WebHook webHook) {
        webHooks.add(webHook);
    }

    @Override
    public Commit commit(String branch, String author, String[] changes) {
        commits.get(branch).add(new Commit(author, changes)); //new Commit(author, changes));
        return null;
    }

    @Override
    public void merge(String sourceBranch, String targetBranch) {

    }
}
