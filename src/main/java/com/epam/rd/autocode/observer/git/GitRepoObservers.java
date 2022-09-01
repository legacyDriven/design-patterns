package com.epam.rd.autocode.observer.git;

public class GitRepoObservers {

    public static Repository newRepository(){
        return new RepositoryImpl();
    }

    public static WebHook mergeToBranchWebHook(String branchName){
        WebHookImpl mergeHook = new WebHookImpl(branchName);
        mergeHook.setEventType(Event.Type.MERGE);
        return mergeHook;
    }

    public static WebHook commitToBranchWebHook(String branchName){
        WebHookImpl commitHook = new WebHookImpl(branchName);
        commitHook.setEventType(Event.Type.COMMIT);
        return commitHook;
    }
}

/*
    Observer is a behavioral design pattern that lets you define a subscription mechanism to notify multiple objects about any events
     that happen with the object they’re observing.

Implement com.epam.rd.autocode.observer.git.GitRepoObservers methods:

newRepository - returns a Repository. It supports commits to various branches and merges between branches.
Also, it supports WebHooks - observers that observes commit or merge events.

mergeToBranchWebHook - returns a WebHook that observes merge events for a target branch.

commitToBranchWebHook - returns a WebHook that observes commit events for a target branch.
     */
