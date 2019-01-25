package rbfs;

import common.Node;

import java.util.*;

public class RBFS {
    private Set<Integer> visitSet = new HashSet<>();
    private Node last = null;
    private Map<Integer, Integer> updateH = new HashMap<>();

    public void run() {
        Node parent = Node.getInput();
        while (!rbfs(parent));
        System.out.println(this);
    }

    private boolean rbfs(Node n){
        if (n.fitness()){
            last = n;
            return true;
        }


        int h = getUpdateHeuristic(n);

        boolean findLess = false;
        int minChildrenHeuristic = Integer.MAX_VALUE;
        visitSet.add(n.hashCode());

        for (Node child:n.successorFunction()){
            if (visitSet.contains(child.hashCode()))
                continue;

            if (getUpdateHeuristic(child) <= h){
                findLess = true;
                boolean result = rbfs(child);
                if (!result)
                    visitSet.remove(child.hashCode());
                return result;
            }
            else if (!findLess){
                minChildrenHeuristic = Math.min(minChildrenHeuristic, getUpdateHeuristic(child));
            }
        }
        if (!findLess){
            updateH.put(n.hashCode(), minChildrenHeuristic);
        }
        visitSet.remove(n.hashCode());
        return false;
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
        st.append("number of step : ").append(stackSize).append("\n");
        return st.toString();
    }

    private int getUpdateHeuristic(Node node){
        if (updateH.containsKey(node.hashCode()))
            return updateH.get(node.hashCode());
        return node.getHeuristic();
    }
}
