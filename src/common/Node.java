package common;

import java.util.ArrayList;
import java.util.List;

public class Node {
    private State state;
    private Node parent;

//    public List<State> soccesoreFunction(){
//        List<State> result = new ArrayList<>();
//
//    }

    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof Node))
            return super.equals(obj);

        Node n = (Node) obj;
        return n.state.equals(this.state);
    }
}
