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
            System.out.println("level : " + node.getLevel() + "\t queue size : " + queue.size() + "\t visit size : " + visitSet.size());
            System.out.println(node + " \n\n");
        }
    }

    @Override
    public String toString() {
        Node n = last;
        StringBuilder st = new StringBuilder();
        while (n != null){
            st.append(n.toString()).append("\n****************\n");
            n = n.getParent();
        }
        return st.toString();
    }
}
