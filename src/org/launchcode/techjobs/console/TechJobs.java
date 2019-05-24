package org.launchcode.techjobs.console;

import java.util.ArrayList;
import java.util.HashMap;

import java.util.Scanner;

/**
 * Created by LaunchCode
 */
public class TechJobs {

    private static Scanner in = new Scanner(System.in);

    public static void main (String[] args) {

        // Initialize our field map with key/name pairs
        HashMap<String, String> columnChoices = new HashMap<>();
        columnChoices.put("core competency", "Skill");
        columnChoices.put("employer", "Employer");
        columnChoices.put("location", "Location");
        columnChoices.put("position type", "Position Type");
        columnChoices.put("all", "All");

        // Top-level menu options
        HashMap<String, String> actionChoices = new HashMap<>();
        actionChoices.put("search", "Search");
        actionChoices.put("list", "List");

        System.out.println("Welcome to LaunchCode's TechJobs App!");

        // Allow the user to search until they manually quit
        while (true) {

            String actionChoice = getUserSelection("View jobs by:", actionChoices);

            if (actionChoice.equals("list")) {

                String columnChoice = getUserSelection("List", columnChoices);

                if (columnChoice.equals("all")) {
                    printJobs(JobData.findAll());  //passing all the info from table to function printJobs...I think, should I pass columnChoice okay

                } else {

                    ArrayList<String> results = JobData.findAll(columnChoice);
//prints out if 1-list is chosen and postion type, employer, location or skill is chosen (not for 0 - All though)
                    System.out.println("\n*** All " + columnChoices.get(columnChoice) + " Values ***");

                    // Print list of skills, employers, etc
                    for (String item : results) {
                        System.out.println(item);
                    }
                }
// choice is 'search'
            } else { // choice is "search"

                // How does the user want to search (e.g. by skill or employer)
                String searchField = getUserSelection("Search by:", columnChoices);

                // What is their search term?
                System.out.println("\nSearch term: ");
                String searchTerm = in.nextLine();

                if (searchField.equals("all")) {
                    findByValue(JobData.findByValue(searchField));
                    //System.out.println("Search all fields not yet implemented.");          //findByValue GOES HERE!!!!!!!!!!!!!!
                } else {
                    printJobs(JobData.findByColumnAndValue(searchField, searchTerm));
                }
            }
        }
    }

    // ﻿Returns the key of the selected item from the choices Dictionary
    private static String getUserSelection(String menuHeader, HashMap<String, String> choices) {

        Integer choiceIdx;
        Boolean validChoice = false;
        String[] choiceKeys = new String[choices.size()];

        // Put the choices in an ordered structure so we can
        // associate an integer with each one
        Integer i = 0;
        for (String choiceKey : choices.keySet()) {
            choiceKeys[i] = choiceKey;
            i++;
        }

        do {

            System.out.println("\n" + menuHeader);

            // Print available choices ****This prints the list of choices 0 - All 1 - Position Type etc****
            for (Integer j = 0; j < choiceKeys.length; j++) {
                System.out.println("" + j + " - " + choices.get(choiceKeys[j]));
            }

            choiceIdx = in.nextInt();
            in.nextLine();

            // Validate user's input
            if (choiceIdx < 0 || choiceIdx >= choiceKeys.length) {
                System.out.println("Invalid choice. Try again.");
            } else {
                validChoice = true;
            }

        } while(!validChoice);

        return choiceKeys[choiceIdx];
    }

    // Print a list of jobs
    private static void printJobs(ArrayList<HashMap<String, String>> someJobs) {

        //TODO If there are no results, print message "No Results Found"


// Pseudo code: for each job in jobs, iterate over each column of info (iterate over an arraylist made up of hashmaps
//Do I need to create a new HashMap and ArrayList with the JobData?
//        HashMap<String, String> someJobs = new HashMap<>();
        if (someJobs.size() > 0) {
            for (HashMap<String, String> job : someJobs) {
                System.out.println("\n*****");
                for (String key : job.keySet()) {
                    String value = job.get(key);
                    System.out.println(key + " : " + value);
                }
                System.out.println("*****");                   //may be able to do this with do while..or is it while, that checks loop at end
            }
        }else{System.out.println("No Jobs Found");
    }
}
