package Managers;

import Framework.Basic_Characteristics;
import Framework.User;
import RatingsWatcher.RatingsWatcher;

import java.util.*;

public class Neighbor_Manager {
    private HashMap<RatingsWatcher, ArrayList<RatingsWatcher>> Neighbor_Lists;

    Neighbor_Manager(){
        Neighbor_Lists = new HashMap<>();
    }

    ArrayList<RatingsWatcher> GetNeighbors(RatingsWatcher Watcher){
        return Neighbor_Lists.get(Watcher);
    }

    void UpdateNeighbors(RatingsWatcher Watcher){
        TreeMap<RatingsWatcher, Double> UserSimilarity = new TreeMap<>();
    }
}


