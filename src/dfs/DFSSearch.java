package dfs;

import common.Node;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.Stack;

public class DFSSearch {
    private Set<Integer> visitSet = new HashSet<>();
    private long visitSize = 0;
    private int cutOff = 1;
    private Node last = null;

    public void run() {
        Node parent = Node.getInput();
        while (true) {
            visitSize += visitSet.size();
            visitSet.clear();
            if (dfs(parent))
                break;
            cutOff++;
        }
    }

    private boolean dfs(Node node) {
        if (node.fitness()) {
            last = node;
            System.out.println(this);
            return true;
        }
        if (visitSet.contains(node.hashCode()))
            return false;

        if (node.getLevel() > cutOff)
            return false;

        visitSet.add(node.hashCode());
        List<Node> child = node.successorFunction();
        boolean result;
        for (Node n : child) {
            result = dfs(n);
            if (result)
                return result;
        }
        return false;
    }

    private void print(Node n) {
        while (n != null) {
            System.out.println(n + "\n*******************\n");
            n = n.getParent();
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
        st.append("number of step : ").append(stackSize).append("\n");
        st.append("number of total node visited : ").append(visitSize);
        return st.toString();
    }
}
