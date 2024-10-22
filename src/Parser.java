/**
 * @file: Parser.java
 * @description: A parser that reads from the .csv file and fills the BST
 *               with movie information.
 * @author: Keira Yu
 * @date: September 26, 2024
 */
import java.io.*;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Scanner;

public class Parser {

    // Create a BST tree of your class type
    private BST<Movie> mybst = new BST<>();
    private List<Movie> movieList = new ArrayList<>();

    public Parser(String filename) throws FileNotFoundException {
        processCSV(new File("Top_200_Movies_Dataset_2023_Cleaned.csv"));
        processInput(new File(filename));
    }

    // Process the csv file and stores all data entries into a list
    public void processCSV(File file) throws FileNotFoundException {
        Scanner scanner = new Scanner(file);
        while (scanner.hasNextLine()) {
            String line = scanner.nextLine().trim();
            if (!line.isEmpty()) {
                String[] parts = line.split(",");
                if (parts.length == 4) {
                    try{
                        String title = parts[0].trim();
                        int totalGross = Integer.parseInt(parts[1].trim());
                        String releaseDate = parts[2].trim();
                        String distributor = parts[3].trim();

                        Movie movie = new Movie(title, totalGross, releaseDate, distributor);
                        movieList.add(movie);
                    }
                    catch (Exception e) {
                        System.out.println(e.getMessage());
                    }
                }
            }
        }
        scanner.close();
    }

    // Process input file
    public void processInput(File input) throws FileNotFoundException {
        Scanner scanner = new Scanner(input);
        while (scanner.hasNextLine()) {
            // Remove redundant spaces for each input command
            String line = scanner.nextLine().trim();

            if (!line.isEmpty()){
                String[] command = line.split("\\s+");
                //call operate_BST method;
                try {
                    //writeToFile("process","./result.txt");
                    operate_BST(command);
                }
                catch (Exception e){
                    System.out.println(e.getMessage());
                }
            }
        }
        scanner.close();
    }

    // Implement the operate_BST method
    // Determine the incoming command and operate on the BST
    public void operate_BST(String[] command) throws IOException {
        switch (command[0]) {
            case "insert":
                if (command.length == 2) {
                    String title = command[1].trim();
                    Movie foundMovie = findMovieInCSV(title);
                    if (foundMovie != null) {
                        mybst.insert(foundMovie);
                        writeToFile("insert " + title, "./result.txt");
                    }
                }
                else {
                    writeToFile("Invalid Command", "./result.txt");
                }
                break;

            case "remove":
                if (command.length == 2) {
                    String title = command[1].trim();
                    Movie foundMovie = searchMovieByTitle(title); // Search in the BST
                    if (foundMovie != null) {
                        mybst.remove(foundMovie);
                        writeToFile("removed " + title, "./result.txt");
                    }
                    else {
                        writeToFile("remove failed", "./result.txt");
                    }
                }
                else {
                    writeToFile("Invalid Command", "./result.txt");
                }
                break;

            case "search":
                if (command.length == 2) {
                    String title = command[1].trim();
                    Movie foundMovie = searchMovieByTitle(title); // Search in the BST
                    if (foundMovie != null) {
                        writeToFile("found " + title, "./result.txt");
                    } else {
                        writeToFile("search failed", "./result.txt");
                    }
                }
                else {
                    writeToFile("Invalid Command", "./result.txt");
                }
                break;

            case "print":
                Iterator<Movie> iterator = mybst.iterator();
                String result = "";
                while (iterator.hasNext()) {
                    result += iterator.next() + " ";
                }
                writeToFile(result, "./result.txt");
                break;

            default:
                writeToFile("Invalid Command", "./result.txt");
        }
    }

    public Movie findMovieInCSV(String title) {
        for (Movie movie : movieList) {
            if (movie.getTitle().equalsIgnoreCase(title)) {
                return movie;
            }
        }
        return null;
    }

    public Movie searchMovieByTitle(String title) {
        Iterator<Movie> iterator = mybst.iterator();
        while (iterator.hasNext()) {
            Movie movie = iterator.next();
            if (movie.getTitle().equalsIgnoreCase(title)) {
                return movie;
            }
        }
        return null;
    }


    // Implement the writeToFile method
    // Generate the result file
    public void writeToFile(String content, String filePath) throws IOException {
        FileWriter fileWriter = new FileWriter(filePath, true);
        fileWriter.write(content);
        fileWriter.write("\n");
        fileWriter.close();
    }

}