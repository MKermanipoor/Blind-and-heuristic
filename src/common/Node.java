package common;

import javafx.util.Pair;

import java.util.ArrayList;
import java.util.List;

import static common.PublicValue.PUZZLE_SIZE;

public class Node {
    private State state;
    private Node parent;
    private int cost = 0;
    private long level = 0;
    private Action action;

    public enum Action {
        UP, DOWN,
        LEFT, RIGHT;

        @Override
        public String toString() {
            switch (this) {
                case DOWN:
                    return "Down";
                case UP:
                    return "Up";
                case LEFT:
                    return "Left";
                case RIGHT:
                    return "Right";
                default:
                    return super.toString();
            }
        }
    }

    public Node(State state) {
        this.state = state;
    }

    public Node getParent() {
        return parent;
    }

    public boolean fitness() {
        int count = 1;
        for (int i = 0; i < PUZZLE_SIZE; i++)
            for (int j = 0; j < PUZZLE_SIZE; j++)
                if (count != PUZZLE_SIZE * PUZZLE_SIZE && state.getIndex(i, j) != count++) {
                    return false;
                }
        return true;
    }

    public List<Node> successorFunction() {
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

    private Node doAction(Action act) {
        Pair<State, Integer> pair = null;
        switch (act) {
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
        result.level = this.level + 1;
        result.action = act;
        result.cost = this.cost + pair.getValue();
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof Node))
            return super.equals(obj);

        Node n = (Node) obj;
        if (n.cost >= this.cost)
            return n.state.equals(this.state);
        return false;
    }

    public int getHeuristic(){
        return this.state.getHeuristic();
    }

    public int getCost() {
        return cost;
    }

    public int getF(){
        return this.getCost() + this.getHeuristic();
    }
    public long getLevel() {
        return level;
    }


    @Override
    public int hashCode() {
        return this.state.hashCode();
    }

    @Override
    public String toString() {
        if (action != null)
            return action + "\n" + state.toString() + "cost : " + cost;
        else
            return state.toString() + "cost : " + cost;
    }

    public static Node getInput() {
        return new Node(State.getFromInput());
    }
}
