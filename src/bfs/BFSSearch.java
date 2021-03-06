package bfs;

import common.Node;

import java.util.*;

public class BFSSearch {
    Set<Integer> visitSet = new HashSet<>();
    Queue<Node> queue = new LinkedList<>();
    Node last;
    public void run(){
        queue.add(Node.getInput());
        List<Node> neighbours;
        Node node;
        while(!queue.isEmpty()){
            node = queue.remove();
            visitSet.add(node.hashCode());
            if (node.fitness()) {
                last = node;
                System.out.println(this);
                break;
            }
            neighbours = node.successorFunction();
            for(Node n:neighbours){
                if (!visitSet.contains(n.hashCode())) {
                    visitSet.add(n.hashCode());
                    queue.add(n);
                }
            }
        }
    }

    @Override
    public String toString() {
        Node n = last;
        StringBuilder st = new StringBuilder();
        Stack<Node> result = new Stack<>();
        while (n != null){
            result.push(n);
            n = n.getParent();
        }
        int stackSize = result.size();
        while (!result.isEmpty()){
            st.append(result.pop().toString()).append("\n**************\n");
        }
        st.append("number of node visited : ").append(visitSet.size()).append("\n");
        st.append("number of step : ").append(stackSize);
        return st.toString();
    }
}
