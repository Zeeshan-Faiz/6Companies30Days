package Atlassian;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

/*
A kingdom consists of a king, his children, his grandchildren, and so on. Every once in a while, '
someone in the family dies or a child is born.
The kingdom has a well-defined order of inheritance that consists of the king as the first member. 
Let's define the recursive function Successor(x, curOrder), which given a person x and the 
inheritance order so far, returns who should be the next person after x in the order of 
inheritance.
Successor(x, curOrder):
    if x has no children or all of x's children are in curOrder:
        if x is the king return null
        else return Successor(x's parent, curOrder)
    else return x's oldest child who's not in curOrder

For example, assume we have a kingdom that consists of the king, his children Alice and Bob (Alice is older than Bob), and finally Alice's son Jack.
    In the beginning, curOrder will be ["king"].
    Calling Successor(king, curOrder) will return Alice, so we append to curOrder to get ["king", "Alice"].
    Calling Successor(Alice, curOrder) will return Jack, so we append to curOrder to get ["king", "Alice", "Jack"].
    Calling Successor(Jack, curOrder) will return Bob, so we append to curOrder to get ["king", "Alice", "Jack", "Bob"].
    Calling Successor(Bob, curOrder) will return null. Thus the order of inheritance will be ["king", "Alice", "Jack", "Bob"].

Using the above function, we can always obtain a unique order of inheritance.
Implement the ThroneInheritance class:

    ThroneInheritance(string kingName) Initializes an object of the ThroneInheritance class. The name of the king is given as part of the constructor.
    void birth(string parentName, string childName) Indicates that parentName gave birth to childName.
    void death(string name) Indicates the death of name. The death of the person doesn't affect the Successor function nor the current inheritance order. You can treat it as just marking the person as dead.
    string[] getInheritanceOrder() Returns a list representing the current order of inheritance excluding dead people.

Example 1:
Input
["ThroneInheritance", "birth", "birth", "birth", "birth", "birth", "birth", "getInheritanceOrder", "death", "getInheritanceOrder"]
[["king"], ["king", "andy"], ["king", "bob"], ["king", "catherine"], ["andy", "matthew"], ["bob", "alex"], ["bob", "asha"], [null], ["bob"], [null]]
Output
[null, null, null, null, null, null, null, ["king", "andy", "matthew", "bob", "alex", "asha", "catherine"], null, ["king", "andy", "matthew", "alex", "asha", "catherine"]]

Explanation
ThroneInheritance t= new ThroneInheritance("king"); // order: king
t.birth("king", "andy"); // order: king > andy
t.birth("king", "bob"); // order: king > andy > bob
t.birth("king", "catherine"); // order: king > andy > bob > catherine
t.birth("andy", "matthew"); // order: king > andy > matthew > bob > catherine
t.birth("bob", "alex"); // order: king > andy > matthew > bob > alex > catherine
t.birth("bob", "asha"); // order: king > andy > matthew > bob > alex > asha > catherine
t.getInheritanceOrder(); // return ["king", "andy", "matthew", "bob", "alex", "asha", "catherine"]
t.death("bob"); // order: king > andy > matthew > bob > alex > asha > catherine
t.getInheritanceOrder(); // return ["king", "andy", "matthew", "alex", "asha", "catherine"]
*/

public class Q1600ThroneInheritance {

    private Map<String, List<String>> tree; // To store parent to children mapping
    private Set<String> alive;
    private String king;

    public Q1600ThroneInheritance(String kingName) {
        this.king = kingName;
        this.tree = new HashMap<>();
        this.alive = new HashSet<>();

        // Initialize the tree with the king
        this.tree.put(kingName, new ArrayList<>());
        this.alive.add(kingName);
    }

    public void birth(String parentName, String childName) {
        // Add child to parent's list of children
        this.tree.putIfAbsent(parentName, new ArrayList<>());
        this.tree.get(parentName).add(childName);

        // Initialize the child in the tree and mark as alive
        this.tree.putIfAbsent(childName, new ArrayList<>());
        this.alive.add(childName);
    }

    public void death(String name) {
        // Mark the person as dead
        this.alive.remove(name);
    }

    public String[] getInheritanceOrder() {
        List<String> order = new ArrayList<>();
        dfs(this.king, order);
        return order.toArray(new String[0]);
    }

    private void dfs(String node, List<String> order) {
        // If the node is alive, add it to the order
        if (this.alive.contains(node)) {
            order.add(node);
        }

        // Recur for all children
        if (this.tree.containsKey(node)) {
            for (String child : this.tree.get(node)) {
                dfs(child, order);
            }
        }
    }
}