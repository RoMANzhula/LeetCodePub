package P1901_2000.P1912_Design_Movie_Rental_System;

import java.util.*;

public class Main {


    public static void main(String[] args) {
        int[][] entries = {
                {0, 1, 5}, {0, 2, 6}, {0, 3, 7},
                {1, 1, 4}, {1, 2, 7}, {2, 1, 5}
        };

        Main movieRentingSystem = new Main(3, entries);

        System.out.println(movieRentingSystem.search(1)); // [1, 0, 2]
        movieRentingSystem.rent(0, 1);
        movieRentingSystem.rent(1, 2);
        System.out.println(movieRentingSystem.report());  // [[0, 1], [1, 2]]
        movieRentingSystem.drop(1, 2);
        System.out.println(movieRentingSystem.search(2)); // [0, 1]

    }

    // Key: (shop, movie) -> price
    private Map<String, Integer> priceMap;
    // for each movie: stores unrented copies sorted by (price, shop)
    private Map<Integer, TreeSet<int[]>> movieAvailable;
    // all rented movies sorted by (price, shop, movie)
    private TreeSet<int[]> rented;

    public Main(int n, int[][] entries) {
        priceMap = new HashMap<>();
        movieAvailable = new HashMap<>();
        rented = new TreeSet<>((a, b) -> {
            if (a[2] != b[2]) return a[2] - b[2];  // price

            if (a[0] != b[0]) return a[0] - b[0];  // shop

            return a[1] - b[1];                    // movie
        });

        for (int[] e : entries) {
            int shop = e[0], movie = e[1], price = e[2];
            String key = shop + "#" + movie;
            priceMap.put(key, price);

            movieAvailable.putIfAbsent(movie, new TreeSet<>((a, b) -> {
                if (a[1] != b[1]) return a[1] - b[1]; // price

                return a[0] - b[0];                   // shop
            }));

            movieAvailable.get(movie).add(new int[]{shop, price});
        }
    }

    public List<Integer> search(int movie) {
        List<Integer> res = new ArrayList<>();

        if (!movieAvailable.containsKey(movie)) return res;

        TreeSet<int[]> set = movieAvailable.get(movie);

        int count = 0;

        for (int[] e : set) {
            res.add(e[0]);
            count++;

            if (count == 5) break;
        }

        return res;
    }

    public void rent(int shop, int movie) {
        String key = shop + "#" + movie;
        int price = priceMap.get(key);

        movieAvailable.get(movie).remove(new int[]{shop, price});
        rented.add(new int[]{shop, movie, price});
    }

    public void drop(int shop, int movie) {
        String key = shop + "#" + movie;
        int price = priceMap.get(key);

        rented.remove(new int[]{shop, movie, price});
        movieAvailable.get(movie).add(new int[]{shop, price});
    }

    public List<List<Integer>> report() {
        List<List<Integer>> res = new ArrayList<>();
        int count = 0;

        for (int[] e : rented) {
            res.add(Arrays.asList(e[0], e[1]));
            count++;
            if (count == 5) break;
        }

        return res;
    }


}


//You have a movie renting company consisting of n shops. You want to implement a renting system that supports
// searching for, booking, and returning movies. The system should also support generating a report of the
// currently rented movies.
//Each movie is given as a 2D integer array entries where entries[i] = [shopi, moviei, pricei] indicates that
// there is a copy of movie moviei at shop shopi with a rental price of pricei. Each shop carries at most one
// copy of a movie moviei.
//The system should support the following functions:
//Search: Finds the cheapest 5 shops that have an unrented copy of a given movie. The shops should be sorted by
// price in ascending order, and in case of a tie, the one with the smaller shopi should appear first. If
// there are less than 5 matching shops, then all of them should be returned. If no shop has an unrented copy,
// then an empty list should be returned.
//Rent: Rents an unrented copy of a given movie from a given shop.
//Drop: Drops off a previously rented copy of a given movie at a given shop.
//Report: Returns the cheapest 5 rented movies (possibly of the same movie ID) as a 2D list
// res where res[j] = [shopj, moviej] describes that the jth cheapest rented movie moviej was rented from the
// shop shopj. The movies in res should be sorted by price in ascending order, and in case of a tie, the one
// with the smaller shopj should appear first, and if there is still tie, the one with the smaller moviej should
// appear first. If there are fewer than 5 rented movies, then all of them should be returned. If no movies are
// currently being rented, then an empty list should be returned.
//Implement the MovieRentingSystem class:
//MovieRentingSystem(int n, int[][] entries) Initializes the MovieRentingSystem object with n shops and the
// movies in entries.
//List<Integer> search(int movie) Returns a list of shops that have an unrented copy of the given movie as
// described above.
//void rent(int shop, int movie) Rents the given movie from the given shop.
//void drop(int shop, int movie) Drops off a previously rented movie at the given shop.
//List<List<Integer>> report() Returns a list of cheapest rented movies as described above.
//Note: The test cases will be generated such that rent will only be called if the shop has an unrented copy of
// the movie, and drop will only be called if the shop had previously rented out the movie.

//Example 1:
//Input
//["MovieRentingSystem", "search", "rent", "rent", "report", "drop", "search"]
//[[3, [[0, 1, 5], [0, 2, 6], [0, 3, 7], [1, 1, 4], [1, 2, 7], [2, 1, 5]]], [1], [0, 1], [1, 2], [], [1, 2], [2]]
//Output
//[null, [1, 0, 2], null, null, [[0, 1], [1, 2]], null, [0, 1]]
//Explanation
//MovieRentingSystem movieRentingSystem =
//              new MovieRentingSystem(3, [[0, 1, 5], [0, 2, 6], [0, 3, 7], [1, 1, 4], [1, 2, 7], [2, 1, 5]]);
//movieRentingSystem.search(1);  // return [1, 0, 2], Movies of ID 1 are unrented at shops 1, 0, and 2. Shop 1 is
// cheapest; shop 0 and 2 are the same price, so order by shop number.
//movieRentingSystem.rent(0, 1); // Rent movie 1 from shop 0. Unrented movies at shop 0 are now [2,3].
//movieRentingSystem.rent(1, 2); // Rent movie 2 from shop 1. Unrented movies at shop 1 are now [1].
//movieRentingSystem.report();   // return [[0, 1], [1, 2]]. Movie 1 from shop 0 is cheapest, followed by
// movie 2 from shop 1.
//movieRentingSystem.drop(1, 2); // Drop off movie 2 at shop 1. Unrented movies at shop 1 are now [1,2].
//movieRentingSystem.search(2);  // return [0, 1]. Movies of ID 2 are unrented at shops 0 and 1. Shop 0 is cheapest,
// followed by shop 1.

//Constraints:
//1 <= n <= 3 * 105
//1 <= entries.length <= 105
//0 <= shopi < n
//1 <= moviei, pricei <= 104
//Each shop carries at most one copy of a movie moviei.
//At most 105 calls in total will be made to search, rent, drop and report.
