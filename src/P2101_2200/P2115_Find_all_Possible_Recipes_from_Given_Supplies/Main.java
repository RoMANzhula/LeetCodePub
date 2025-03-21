package P2101_2200.P2115_Find_all_Possible_Recipes_from_Given_Supplies;

import java.util.*;

public class Main {

    public static void main(String[] args) {
        Main solution = new Main();

        String[] recipes1 = {"bread"};
        List<List<String>> ingredients1 = List.of(List.of("yeast", "flour"));
        String[] supplies1 = {"yeast", "flour", "corn"};
        System.out.println(solution.findAllRecipes(recipes1, ingredients1, supplies1)); // ["bread"]

        String[] recipes2 = {"bread", "sandwich"};
        List<List<String>> ingredients2 = List.of(List.of("yeast", "flour"), List.of("bread", "meat"));
        String[] supplies2 = {"yeast", "flour", "meat"};
        System.out.println(solution.findAllRecipes(recipes2, ingredients2, supplies2)); // ["bread", "sandwich"]

        String[] recipes3 = {"bread", "sandwich", "burger"};
        List<List<String>> ingredients3 = List.of(List.of("yeast", "flour"), List.of("bread", "meat"), List.of("sandwich", "meat", "bread"));
        String[] supplies3 = {"yeast", "flour", "meat"};
        System.out.println(solution.findAllRecipes(recipes3, ingredients3, supplies3)); // ["bread", "sandwich", "burger"]
    }

    public List<String> findAllRecipes(String[] recipes, List<List<String>> ingredients, String[] supplies) {
        // step 1: initialize data structures
        Set<String> available = new HashSet<>(Arrays.asList(supplies)); // Set of available ingredients
        Map<String, List<String>> graph = new HashMap<>(); // Recipe dependency graph
        Map<String, Integer> indegree = new HashMap<>(); // Ingredients needed for each recipe

        // step 2: build graph and indegree map
        for (int i = 0; i < recipes.length; i++) {
            String recipe = recipes[i];
            indegree.put(recipe, ingredients.get(i).size()); // Number of ingredients required
            for (String ingredient : ingredients.get(i)) {
                graph.computeIfAbsent(ingredient, k -> new ArrayList<>()).add(recipe);
            }
        }

        // step 3: use a queue for topological sorting
        Queue<String> queue = new LinkedList<>();

        // initially, add all supplies to the queue
        for (String supply : supplies) {
            queue.add(supply);
        }

        // step 4: pprocess the queue
        List<String> result = new ArrayList<>();
        while (!queue.isEmpty()) {
            String ingredient = queue.poll();
            if (graph.containsKey(ingredient)) {
                for (String recipe : graph.get(ingredient)) {
                    indegree.put(recipe, indegree.get(recipe) - 1);
                    if (indegree.get(recipe) == 0) {
                        result.add(recipe);
                        queue.add(recipe);
                    }
                }
            }
        }

        return result;
    }

}

//Explanation
//Build the Graph:
//-Each ingredient has an adjacency list pointing to the recipes that depend on it.
//-We maintain an indegree map to track the number of missing ingredients for each recipe.
//Initialize the Queue:
//-Start with all supplies since they are available.
//-Use a queue (BFS approach) to propagate availability to dependent recipes.
//Process the Queue:
//-Remove an ingredient from the queue.
//-Reduce the indegree of dependent recipes.
//-If a recipe’s indegree becomes 0, it means we have all its ingredients → add it to the queue and the result list.
//Time Complexity: O(N + M) where:
//N = number of recipes
//M = total number of ingredients across all recipes
//Each ingredient is processed once, and each recipe is added to the queue once.
//Space Complexity: O(N + M) for storing graphs, indegree map, and the queue.


//You have information about n different recipes. You are given a string array recipes and a 2D string array
// ingredients. The ith recipe has the name recipes[i], and you can create it if you have all the needed
// ingredients from ingredients[i]. A recipe can also be an ingredient for other recipes, i.e., ingredients[i] may
// contain a string that is in recipes.
//You are also given a string array supplies containing all the ingredients that you initially have, and you
// have an infinite supply of all of them.
//Return a list of all the recipes that you can create. You may return the answer in any order.
//Note that two recipes may contain each other in their ingredients.

//Example 1:
//Input: recipes = ["bread"], ingredients = [["yeast","flour"]], supplies = ["yeast","flour","corn"]
//Output: ["bread"]
//Explanation:
//We can create "bread" since we have the ingredients "yeast" and "flour".

//Example 2:
//Input: recipes = ["bread","sandwich"],
// ingredients = [["yeast","flour"],["bread","meat"]], supplies = ["yeast","flour","meat"]
//Output: ["bread","sandwich"]
//Explanation:
//We can create "bread" since we have the ingredients "yeast" and "flour".
//We can create "sandwich" since we have the ingredient "meat" and can create the ingredient "bread".

//Example 3:
//Input: recipes = ["bread","sandwich","burger"],
// ingredients = [["yeast","flour"],["bread","meat"],["sandwich","meat","bread"]], supplies = ["yeast","flour","meat"]
//Output: ["bread","sandwich","burger"]
//Explanation:
//We can create "bread" since we have the ingredients "yeast" and "flour".
//We can create "sandwich" since we have the ingredient "meat" and can create the ingredient "bread".
//We can create "burger" since we have the ingredient "meat" and can create the ingredients "bread" and "sandwich".

//Constraints:
//n == recipes.length == ingredients.length
//1 <= n <= 100
//1 <= ingredients[i].length, supplies.length <= 100
//1 <= recipes[i].length, ingredients[i][j].length, supplies[k].length <= 10
//recipes[i], ingredients[i][j], and supplies[k] consist only of lowercase English letters.
//All the values of recipes and supplies combined are unique.
//Each ingredients[i] does not contain any duplicate values.