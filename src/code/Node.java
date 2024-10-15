package code;

import java.util.AbstractMap.SimpleEntry;

public class Node {
  State state;
  Node parent;
  SimpleEntry<Integer, Integer> action;
  int cost;
  int depth; 
  
  public Node(State state, Node parent, SimpleEntry<Integer, Integer> action, int cost, int depth) {
    this.state = state;
    this.parent = parent;
    this.action = action;
    this.cost = cost;
    this.depth = depth;
}

public boolean isGoal(){
  return state.isGoal();
}

public State getState(){
  return state;
}

public Node getParent(){
  return parent;
}

public SimpleEntry<Integer, Integer> getAction(){
  return action;

}

public int getCost(){
  return cost;
}
public int getDepth(){
  return depth;
}
}
