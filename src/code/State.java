package code;
import java.util.*;

public class State {
  private List<Stack<String>> bottles; //each bottle is a stack of strings 
  private int bottleCapacity; //height of stack 
  private int numberOfBottles; //length of list

  public State(String initialState) {
    bottles = new ArrayList<>();
    parseInitialState(initialState);
}

//constructor for copy
private State(List<Stack<String>> bottles, int bottleCapacity,int numberOfBottles) {
  this.bottles = bottles;
  this.bottleCapacity = bottleCapacity;
  this.numberOfBottles=numberOfBottles;
}

private void parseInitialState(String initialState){
    String[] parse =initialState.split(";");

    numberOfBottles=Integer.parseInt(parse[0]);
    bottleCapacity= Integer.parseInt(parse[1]);

    for (int i=2;i<parse.length;i++){
      Stack<String> bottle = new Stack<>();
      String[] layers = parse[i].split(",");

      for(String layer : layers){
          bottle.push(layer);
      }
      bottles.add(bottle);
    }

}

public State copy() {
  List<Stack<String>> newBottles = new ArrayList<>();
  for (Stack<String> bottle : this.bottles) {
      newBottles.add((Stack<String>) bottle.clone());
  }
  return new State(newBottles, this.bottleCapacity,this.numberOfBottles);
}

//getters
public List<Stack<String>> getBottles() {
  return bottles;
}

public int getBottleCapacity() {
  return bottleCapacity;
}

public int getNumberOfBottles() {
  return numberOfBottles;
}

public boolean isGoal() {
  for (Stack<String> bottle : bottles) {
      if (bottle.isEmpty()) continue;  // Skip empty bottles
      String topColor = bottle.peek();  // Get the top color
      for (String layer : bottle) {
          if (!layer.equals(topColor)) {
              return false;  // If any layer is different, it's not a goal state
          }
      }
  }
  return true;  // All bottles are same color
}

@Override
public boolean equals(Object obj) {
    if (this == obj) return true;
    if (obj == null || getClass() != obj.getClass()) return false;
    State other = (State) obj;
    return bottles.equals(other.bottles);
}

@Override
public int hashCode() {
    return Objects.hash(bottles);
}

@Override
public String toString() {
    StringBuilder stateString = new StringBuilder();
    for (Stack<String> bottle : bottles) {
        stateString.append(bottle.toString()).append("\n");
    }
    return stateString.toString();
}

}
