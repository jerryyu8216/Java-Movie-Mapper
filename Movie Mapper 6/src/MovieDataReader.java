import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.Reader;
import java.util.ArrayList;
import java.util.List;
import java.util.zip.DataFormatException;

// reference and outside help:
// https://stackoverflow.com/questions/1757065/java-splitting-a-comma-separated-string-but-ignoring-commas-in-quotes
// for algorithm on parsing the string and add into different

public class MovieDataReader implements MovieDataReaderInterface {
  /**
   * The implemented data interface that helps the backend developer load the file
   */
  @Override
  public List<MovieInterface> readDataSet(Reader inputFileReader)
      throws IOException, FileNotFoundException, DataFormatException {
    ArrayList<MovieInterface> movies = new ArrayList<MovieInterface>();
    // create a buffered reader so the files can be read and parsed
    BufferedReader br = new BufferedReader(inputFileReader);
    // each individual line to be read through the BufferedReader
    String line;
    // a list of strings (separated by comma) to keep track of the strings from CSV file
    ArrayList<String> input = new ArrayList<String>();
    // all the properties of the movies
    ArrayList<String> genre = new ArrayList<String>();
    String title;
    Integer year;
    String director;
    String description;
    Float avgVote;
    // read the first line of the CSV file
    // to skip the title, years, avg_vote etc
    br.readLine();
    // start to read the rest of the CSV file
    while ((line = br.readLine()) != null) {
      // create a starting index and a boolean isInDoubleQuote to check for conditions through the
      // string read from the BufferedReader
      int startingIndex = 0;
      boolean isInDoubleQuote = false;
      // scan through the entire String to find the double quote first
      for (int i = 0; i < line.length(); i++) {
        // if this conditional statement below is repeated twice, we know that we ENTERED and EXITED
        // double Quote, which will trigger the "else if statement" below to record the input
        if (line.charAt(i) == '\"') {
          // now it encounters a double Quote, so we negate the isInDoubleQuote boolean so that the
          // next conditional loop below will not execute
          isInDoubleQuote = !isInDoubleQuote;
        }
        // if the character is a comma, and we know that it is not inside the double quote
        else if (line.charAt(i) == ',' && !isInDoubleQuote) {
          // then we got our first input, which we will store substring from the starting
          // index(where we start to count, to the current index i)
          input.add(line.substring(startingIndex, i));
          // after that, we set the new starting index at a new position, 1 after the current index
          startingIndex = i + 1;
        }
      }
      // add the last remaining characters in after the for-loop ends
      input.add(line.substring(startingIndex));
      // [0] is title index of the movie in the CSV file
      // [2] is year index of the movie in the CSV file
      // [3] is genre index of the movie in the CSV file
      // [7] is director index of the movie in the CSV file
      // [11] is description index of the movie in the CSV file
      // [12] is avg_vote index of the movie in the CSV file
      title = input.get(0);
      year = Integer.parseInt(input.get(2));
      // add all the genres into the array, if there are multiple, then we split them again and get
      // rid of the quotation marks
      String[] genreArray = input.get(3).split(",");
      // to get rid of quotation marks:
      String temp = "";
      for (int j = 0; j < genreArray.length; j++) {
        // if the array contains the quotation marks
        if (genreArray[j].contains("\"")) {
          // replace that quotation mark with empty string
          temp = genreArray[j].replace("\"", "");
          // replace the word with quotation mark with just the word;
          genreArray[j] = temp;
        }
      }
      // put the genre into the array by looping through the genreArray:
      for (int i = 0; i < genreArray.length; i++) {
        genre.add(genreArray[i]);
      }
      
      director = input.get(7);
      description = input.get(11);
      avgVote = Float.parseFloat(input.get(12));
      // after each line, construct a movie using all the movie titles, year, genre, director,
      // description, and avgVote to add to the movies ArrayList


      movies.add(new Movie(title, year, genre, director, description, avgVote));
      // restart the input and genre after adding all the properties of movie into the movie object, and adding it to movies ArrayList
      input = new ArrayList<String>();
      genre = new ArrayList<String>();
    }
    return movies;
  }


}
