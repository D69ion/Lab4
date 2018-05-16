package com.company.io;

public abstract class GroupsManagerFileSource implements FileSource {
    private String path;

    private static String DEFAULT_PATH = "";

    public GroupsManagerFileSource(){
        this.path = DEFAULT_PATH;
    }

    public GroupsManagerFileSource(String path){
        this.path = path;
    }

    @Override
    public String getPath() {
        return path;
    }

    @Override
    public void setPath(String path) {
        this.path = path;
    }
}
