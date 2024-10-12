package code;
public class Node {
  State state;
  Node parent;
  String action;
  int cost;
  int depth; 
  
  public Node(State state, Node parent, String action, int cost, int depth) {
    this.state = state;
    this.parent = parent;
    this.action = action;
    this.cost = cost;
    this.depth = depth;
}

public boolean isGoal(){
  return state.isGoal();
}
}
