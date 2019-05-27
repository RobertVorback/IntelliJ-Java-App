package rgm19;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class Main {
    private static Map<Integer, Location> locations = new HashMap<Integer, Location>();

    public static void main(String[] args) {
        // Change the program to allow the players to type full words, or phrases, then move to the
        // correct location based on their input.
        // The player should be able to type commands such as "Go West", "run South", or just "East"
        // and the program will move to the appropriate location if there is one. As at present, an
        // attempt to move in an invalid direction should print a message and remain in the same place
        //
        // Single letter commands (N, W, S, E, Q) should still be available

        Scanner scanner = new Scanner(System.in);

        locations.put(0, new Location(0,  "You are sitting in front of a compter, learning Java"));
        locations.put(1, new Location(1,  "You are standing at the end of a road, before a small brick building"));
        locations.put(2, new Location(2,  "You are at the top of a hill"));
        locations.put(3, new Location(3,  "You are inside a building, a well house for a small spring"));
        locations.put(4, new Location(4,  "You are in a valley beside a stream"));
        locations.put(5, new Location(5,  "You are in the forest"));

        locations.get(1).adddExit("W", 2);
        locations.get(1).adddExit("E", 3);
        locations.get(1).adddExit("S", 4);
        locations.get(1).adddExit("N", 5);

        locations.get(2).adddExit("N", 5);

        locations.get(3).adddExit("W", 1);

        locations.get(4).adddExit("N", 1);
        locations.get(4).adddExit("W", 2);

        locations.get(5).adddExit("S", 1);
        locations.get(5).adddExit("W", 2);

        Map<String, String> vocabulary = new HashMap<String, String>();
        vocabulary.put("QUIT", "Q");
        vocabulary.put("NORTH", "N");
        vocabulary.put("SOUTH", "S");
        vocabulary.put("WEST", "W");
        vocabulary.put("EAST", "E");

        int loc = 1;
        while (true) {
            System.out.println(locations.get(loc).getDescription()); // print current location
            if (loc == 0) {
                break;
            }

            Map<String, Integer> exits = locations.get(loc).getExits();
            System.out.print("Available exits are "); // print available exits
            for (String exit: exits.keySet()) {
                System.out.print(exit + ", ");
            }
            System.out.println();

//            // get user input
//            String input = scanner.nextLine();
//            String direction = ""; // initialize empty string to store direction

//            // if input is greater than a single character split string
//            if (input.length() > 1) {
//                String[] stringArray = input.split(" ");
//                for (String i: stringArray) { // iterate through array elements, and determine direction
//                    switch (i.toLowerCase()) {
//                        case "north" :
//                            direction = "N";
//                            break;
//                        case "west" :
//                            direction = "W";
//                            break;
//                        case "south" :
//                            direction = "S";
//                            break;
//                        case "east" :
//                            direction = "E";
//                            break;
//                        case "quit" :
//                            direction = "Q";
//                            break;
//                    }
//                }
//            } else { // if input is a single character convert to uppercase and assign to direction variable
//                direction = input.toUpperCase();
//            }

            String direction = scanner.nextLine().toUpperCase(); // get direction input, convert to uppercase

            // process if input is more than a single letter, for example "go north"
            if (direction.length() > 1) {
                String[] words = direction.split(" ");
                for (String word: words) {
                    if (vocabulary.containsKey(word)) {
                        direction = vocabulary.get(word);
                        break;
                    }
                }
            }

            // process single letter input, for example "n"
            if (exits.containsKey(direction)) { // if one of current locations valid exits match input
                loc = exits.get(direction); // change location
            } else {
                System.out.println("You can not go in that direction"); // message if invalid input entered
            }
        }

//        String[] road = "You are standing at the end of a road, before a small brick building".split(" ");
//        for (String i: road) {
//            System.out.println(i);
//        }
//
//        System.out.println("===========================================");
//
//        String[] building = "You are inside a building, a well house for a small spring".split(", ");
//        for (String i: building) {
//            System.out.println(i);
//        }
    }
}
