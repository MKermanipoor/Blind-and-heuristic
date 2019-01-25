package common;

import aStar.AStar;
import bfs.BFSSearch;
import dfs.DFSSearch;
import rbfs.RBFS;
import ucs.UCS;
import bds.BDS;

public class Main {
    public static void main(String[] arg){
        //blind searches
//        BDS search = new BDS();
//        BFSSearch search = new BFSSearch();
//        DFSSearch search = new DFSSearch();
        UCS search = new UCS();

        //heuristic searches
//        AStar search = new AStar();
//        RBFS search = new RBFS();
        search.run();
    }
}
