package service;

public class Status {
    private final String NAME;

    private Status(String name){

        NAME = name;
    }

    public String getNAME() {
        return NAME;
    }

    public static final Status FREE=new Status("free");
    public static final Status BUSY=new Status("busy");
    public static final Status VOCATION=new Status("VOCATION");

    @Override
    public String toString() {
        return NAME;
    }
}
