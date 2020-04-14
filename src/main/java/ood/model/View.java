package ood.model;

public class View {
    public interface User{}
    public interface Group extends User {}
    public interface Event extends Group{}
    public interface Voting extends Event{}
}
