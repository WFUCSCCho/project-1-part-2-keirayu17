/**
 * @file: Parser.java
 * @description: A parser that reads from the .csv file and fills the BST
 *               with movie information.
 * @author: Keira Yu
 * @date: September 26, 2024
 */
import java.io.*;
import java.util.Iterator;
import java.util.Scanner;

public class Parser {

    // Create a BST tree of your class type
    private BST<Movie> mybst = new BST<>();

    public Parser(String filename) throws FileNotFoundException {
        process(new File(filename));
    }

    // Implement the process method
    public void process(File input) throws IOException {
        Scanner scanner = new Scanner(input);

        while (scanner.hasNextLine()) {
            String line = scanner.nextLine().trim();
            if (!line.isEmpty()) { // Skip empty line
                String[] command = line.split("\\s+", 2);  // Split into command and the rest of the line
                operate_BST(command);
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
                    Movie movieToInsert = parseMovie(command[1]);  // Parse the movie details
                    mybst.insert(movieToInsert);
                    writeToFile("insert " + movieToInsert.toString(), "./result.txt");
                }
                break;

            case "remove":
                if (command.length == 2) {
                    Movie movieToRemove = parseMovie(command[1]);  // Parse the movie details
                    Movie removedMovie = mybst.remove(movieToRemove);  // Remove by movie object

                    if (removedMovie != null) {
                        writeToFile("removed " + removedMovie.toString(), "./result.txt");
                    } else {
                        writeToFile("remove failed", "./result.txt");
                    }
                }
                break;

            case "search":
                if (command.length == 2) {
                    Movie movieToSearch = parseMovie(command[1]);  // Parse the movie details
                    Movie foundMovie = mybst.search(movieToSearch);  // Search by movie object

                    if (foundMovie != null) {
                        writeToFile("found " + foundMovie.toString(), "./result.txt");
                    } else {
                        writeToFile("search failed", "./result.txt");
                    }
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

    // Implement the writeToFile method
    // Generate the result file
    public void writeToFile(String content, String filePath) throws IOException {
        FileWriter fileWriter = new FileWriter(filePath, true);
        fileWriter.write(content);
        fileWriter.write("\n");
        fileWriter.close();
    }

    // Organize data entry
    public Movie parseMovie(String line) {
        // Split the CSV line by commas, considering fields with quotes
        String[] content = line.split(",(?=(?:[^\"]*\"[^\"]*\")*[^\"]*$)");

        // Remove quotes from the relevant fields
        String title = content[1].replace("\"", "");
        int rank = Integer.parseInt(content[0]);
        int theaters = Integer.parseInt(content[2].replace("\"", "").replace(",", ""));
        int totalGross = Integer.parseInt(content[3].replace("\"", "").replace(",", ""));
        String releaseDate = content[4];
        String distributor = content[5].replace("\"", "");

        // Create and return a new Movie object
        return new Movie(rank, title, theaters, totalGross, releaseDate, distributor);
    }
}