package bds;

import common.Node;

import java.util.*;

public class BDS {
    Set<Integer> forwardVisitSet = new HashSet<>();
    Set<Integer> backwardVisitSet = new HashSet<>();
    Queue<Node> forwardQueue = new LinkedList<>();
    Queue<Node> backwardQueue = new LinkedList<>();
    Node lastForwardBFS;
    Node lastBackwardBFS;

    public void run(){
        forwardQueue.add(Node.getInput());
        backwardQueue.add(Node.getGoalNode());
        List<Node> neighbours;
        Node node;

        Main:
        while(!forwardQueue.isEmpty()){
            node = forwardQueue.remove();
            forwardVisitSet.add(node.hashCode());
            neighbours = node.successorFunction();
            for(Node neighbour:neighbours){
                if (forwardVisitSet.contains(neighbour.hashCode()))
                    continue ;

                if (backwardQueue.contains(neighbour)){
                    lastForwardBFS = neighbour;
                    Node temp ;
                    while(!backwardQueue.isEmpty()){
                        temp = backwardQueue.remove();
                        if (temp.equals(neighbour)){
                            lastBackwardBFS = temp;
                            break ;
                        }
                    }
                    break Main;
                }
                forwardQueue.add(neighbour);
            }

            node = backwardQueue.remove();
            backwardVisitSet.add(node.hashCode());
            neighbours = node.reverseSuccessorFunction();
            for(Node neighbour:neighbours){
                if (backwardVisitSet.contains(neighbour.hashCode()))
                    continue ;

                if (forwardQueue.contains(neighbour)){
                    lastBackwardBFS = neighbour;
                    Node temp ;
                    while(!forwardQueue.isEmpty()){
                        temp = forwardQueue.remove();
                        if (temp.equals(neighbour)){
                            lastForwardBFS = temp;
                            break ;
                        }
                    }
                    break Main;
                }
                backwardQueue.add(neighbour);
            }
        }
        System.out.println(this);
    }

    @Override
    public String toString() {
        Node n = lastForwardBFS;
        StringBuilder st = new StringBuilder();

        //forward
        Stack<Node> result = new Stack<>();
        while (n != null){
            result.push(n);
            n = n.getParent();
        }
        int stackSize = result.size();
        while (!result.isEmpty()){
            st.append(result.pop().toString()).append("\n**************\n");
        }

        //backward
        int backwardPath = 0;
        n = lastBackwardBFS.getParent();
        while(n != null){
            st.append(n.toString()).append("\n**************\n");
            backwardPath ++;
            n = n.getParent();
        }

        st.append("number of node visited : ").append(forwardVisitSet.size() + backwardVisitSet.size()).append("\n");
        st.append("number of step : ").append(stackSize + backwardPath);
        return st.toString();
    }
}
