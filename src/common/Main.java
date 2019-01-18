package common;

import aStar.AStar;
import bfs.BFSSearch;
import dfs.DFSSearch;
import ucs.UCS;
import bds.BDS;

public class Main {
    public static void main(String[] arg){
//        BFSSearch search = new BFSSearch();
//        DFSSearch search = new DFSSearch();
//        UCS search = new UCS();
//        AStar search = new AStar();
        BDS search = new BDS();
        search.run();
    }
}
