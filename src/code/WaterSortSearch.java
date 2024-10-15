package code;

import java.util.AbstractMap.SimpleEntry;
import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class WaterSortSearch extends GenericSearch{
  
@Override
  public List<SimpleEntry<Integer, Integer>> getSuccessors(Node current) {
    List<SimpleEntry<Integer, Integer>> successors = new ArrayList<>();
    State state = current.getState();
    for (int i = 0; i < state.getBottles().size(); i++) {
        Stack<String> sourceBottle = state.getBottles().get(i);
        if (sourceBottle.isEmpty()) continue;  // Skip empty bottles
        for (int j = 0; j < state.getBottles().size(); j++) {
            if (i == j) continue;  // Skip the same bottle
            if (state.isFull(state.getBottles().get(j))){
              continue;  // Skip full bottles
            }
            Stack<String> targetBottle = state.getBottles().get(j);
  
            if (targetBottle.isEmpty()){
              successors.add((new SimpleEntry<>(i, j))); //possible node that comes from pouring from i to j
            }
            else if (sourceBottle.peek().equals(targetBottle.peek())) {
              successors.add((new SimpleEntry<>(i, j)));
            }
        }
    }
    return successors;
  }
  








  public static String  solve(String initialState,String strategy, boolean visualize){
     return "lmaooo";
  }
  



}
