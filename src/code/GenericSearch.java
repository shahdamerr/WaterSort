package code;

import java.util.AbstractMap.SimpleEntry;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Set;
import java.util.Stack;

public abstract class GenericSearch {
 public State initialState;

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
  

  public List<String> getPathToGoal(Node goalNode){
    List<String> path = new ArrayList<>();
    Node currentNode = goalNode;
    while (currentNode.getParent() != null){
        SimpleEntry<Integer, Integer> action = currentNode.getAction();
        path.add("Pour" + action.getKey() +  + action.getValue());
        currentNode = currentNode.getParent();
    }
    Collections.reverse(path);
    return path;
  }

//this should include pour(i,j)
public Node bigPour (int i, int j, Node parentNode){
    State newState = parentNode.getState().copy();
    Stack<String> sourceBottle = newState.getBottles().get(i);
    Stack<String> targetBottle = newState.getBottles().get(j);
    int amountToPour=0;
    for (int k = 0; k < sourceBottle.size(); k++) {
        String colour= sourceBottle.peek();
        if (sourceBottle.get(k).equals(colour)){
            amountToPour++;
        }
        else{
            break;
        }   
    }
    int availableToPour= newState.getBottleCapacity()-targetBottle.size();
    availableToPour = Math.min(amountToPour, availableToPour);
    for (int k = 0; k < availableToPour; k++) {
        targetBottle.push(sourceBottle.pop()); 
    }
    // the cost should be revised and could be changed to equal the depth 
    return new Node(newState, parentNode, new SimpleEntry<>(i, j), parentNode.getCost() + amountToPour, parentNode.getDepth() + 1);

}
    
public SimpleEntry<List<SimpleEntry<Integer, Integer>>, Integer> search(Node initialNode, String strategy){
    State currentState= initialNode.getState();
    Set<Node> explored = new HashSet<>();
    
    switch (strategy) {
        case "BF":
        Queue<Node> orderOfExploring = new LinkedList<>();
        orderOfExploring.add(initialNode);
        while(!orderOfExploring.isEmpty()){
        if (currentState.isGoal()){
            //return the path to goal in arraylist
            return new SimpleEntry<>(new ArrayList<>(), explored.size());
        }
        explored.add(initialNode);
        List<SimpleEntry<Integer, Integer>> possibleActions = getSuccessors(initialNode);

        for (SimpleEntry<Integer, Integer> action : possibleActions) {
         Node childNode= bigPour(action.getKey(), action.getValue(), initialNode);
         if (!explored.contains(childNode)){
             orderOfExploring.add(childNode);
         }
        }
    }
            break;

        case "DF":
        break;

        case "ID":
        break;

        case "UC":
        break;

        case "GR1":
        break;

        case "GR2":
        break;


        case "AS1":
        break;

        case "AS2":
        break;
    
        default:
            break;
    }
    return null;
}
}
