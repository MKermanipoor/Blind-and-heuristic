package common;

import javafx.util.Pair;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class Node {
    private State state;
    private Node parent;
    private int cost = 0;

    public enum Action{
        UP,DOWN,
        LEFT,RIGHT
    }

    public Node(State state) {
        this.state = state;
    }

    public List<Node> soccesoreFunction(){
        List<Node> result = new ArrayList<>();
        Node temp;

        //up
        temp = this.doAction(Action.UP);
        if (temp != null)
            result.add(temp);

        //down
        temp = this.doAction(Action.DOWN);
        if (temp != null)
            result.add(temp);

        //left
        temp = this.doAction(Action.LEFT);
        if (temp != null)
            result.add(temp);

        //right
        temp = this.doAction(Action.RIGHT);
        if (temp != null)
            result.add(temp);

        return result;
    }

    private Node doAction(Action act){
        Pair<State, Integer> pair = null;
        switch (act){
            case UP:
                 pair = this.state.up();
                break;
            case DOWN:
                pair = this.state.down();
                break;
            case LEFT:
                pair = this.state.left();
                break;
            case RIGHT:
                pair = this.state.right();
                break;
        }
        if (pair == null)
            return null;
        Node result = new Node(pair.getKey());
        result.parent = this;
        result.cost = this.cost + pair.getValue();
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof Node))
            return super.equals(obj);

        Node n = (Node) obj;
        if (n.cost>this.cost)
            return n.state.equals(this.state);
        return false;
    }

    @Override
    public String toString() {
        return state.toString() + cost;
    }

    public static Node getInput(){
        return new Node(State.getFromInput());
    }
}
