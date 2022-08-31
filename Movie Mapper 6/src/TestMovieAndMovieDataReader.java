import java.io.StringReader;
import java.util.Collections;
import java.util.List;

/**
 * This class contains a set of tests for the MovieInterface and MovieDataReaderInterface
 * implementation of the Movie Mapper project.
 */
public class TestMovieAndMovieDataReader {

  MovieDataReaderInterface readerToTest;

  public static void main(String[] args) {
    (new TestMovieAndMovieDataReader()).runTests();
  }

  /**
   * This method calls all of the test methods in the class and ouputs pass / fail for each test.
   */
  public void runTests() {
    // instantiate reader to test once it is implemented
    readerToTest = new MovieDataReader();

    // add all tests to this method
    if (this.testReaderNumberOfMovies()) {
      System.out.println("Test number of movies: PASSED");
    } else {
      System.out.println("Test number of movies: FAILED");
    }
    if (this.testReaderMovieTitles()) {
      System.out.println("Test movie titles: PASSED");
    } else {
      System.out.println("Test movie titles: FAILED");
    }
    if (this.testMovieOrder()) {
      System.out.println("Test movie order: PASSED");
    } else {
      System.out.println("Test movie order: FAILED");
    }

    if (this.testMovieYear()) {
      System.out.println("Test movie years: PASSED");
    } else {
      System.out.println("Test movie years: FAILED");
    }
    if (this.testMovieDescription()) {
      System.out.println("Test movie description: PASSED");
    } else {
      System.out.println("Test movie description: FAILED");


    }
    if (this.testMovieGenre()) {
      System.out.println("Test movie Genre: PASSED");
    } else {
      System.out.println("Test movie Genre: FAILED");


    }

  }

  public boolean testReaderNumberOfMovies() {
    List<MovieInterface> movieList;
    try {
      movieList = readerToTest.readDataSet(new StringReader(
          "title,original_title,year,genre,duration,country,language,director,writer,production_company,actors,description,avg_vote\n"
              + "The Source of Shadows,The Source of Shadows,2020,Horror,83,USA,English,\"Ryan Bury, Jennifer Bonior\",\"Jennifer Bonior, Trevor Botkin\",Four Thieves Productions,\"Ashleigh Allard, Tom Bonington, Eliane Gagnon, Marissa Kaye Grinestaff, Jenna Heffernan, Joshua Hummel, Janice Kingsley, Chris Labasbas, Jared Laufree, Dominic Lee, Vic May, Sienna Mazzone, Lizzie Mounter, Grace Mumm, Ashley Otis\",\"A series of stories woven together by one of our most primal fears, the fear of the unknown.\",3.5\n"
              + "The Insurrection,The Insurrection,2020,Action,90,USA,English,Rene Perez,Rene Perez,,\"Michael ParÈ, Wilma Elles, Joseph Camilleri, Rebecca Tarabocchia, Jeanine Harrington, Malorie Glavan, Danner Boyd, Michael Cendejas, Woody Clendenen, Keely Dervin, Aaron Harvey, Tony Jackson, Michael Jarrod, Angelina Karo, Bernie Kelly\",The director of the largest media company wants to expose how left-wing powers use film to control populations.,2.9\n"
              + "Valley Girl,Valley Girl,2020,\"Comedy, Musical, Romance\",102,USA,English,Rachel Lee Goldenberg,\"Amy Talkington, Andrew Lane\",Sneak Preview Productions,\"Jessica Rothe, Josh Whitehouse, Jessie Ennis, Ashleigh Murray, Chloe Bennet, Logan Paul, Mae Whitman, Mario Revolori, Rob Huebel, Judy Greer, Alex Lewis, Alex MacNicoll, Danny Ramirez, Andrew Kai, Allyn Rachel\",\"Set to a new wave '80s soundtrack, a pair of young lovers from different backgrounds defy their parents and friends to stay together. A musical adaptation of the 1983 film.\",5.4\n"));
    } catch (Exception e) {
      e.printStackTrace();
      // test failed
      return false;
    }
    if (movieList.size() == 3) {
      // test passed
      return true;
    } else {
      // test failed
      return false;
    }
  }

  /**
   * This test reads in 3 movies and tests whether the list of movies contains all 3 titles of the
   * source data in any order. It fails if the list returned is missing one or more titles or if an
   * exception occurs while reading in the data.
   * 
   * @return true if the test passed, false if it failed
   */
  public boolean testReaderMovieTitles() {
    List<MovieInterface> movieList;
    try {
      movieList = readerToTest.readDataSet(new StringReader(

          "title,original_title,year,genre,duration,country,language,director,writer,production_company,actors,description,avg_vote\n"
              + "The Source of Shadows,The Source of Shadows,2020,Horror,83,USA,English,\"Ryan Bury, Jennifer Bonior\",\"Jennifer Bonior, Trevor Botkin\",Four Thieves Productions,\"Ashleigh Allard, Tom Bonington, Eliane Gagnon, Marissa Kaye Grinestaff, Jenna Heffernan, Joshua Hummel, Janice Kingsley, Chris Labasbas, Jared Laufree, Dominic Lee, Vic May, Sienna Mazzone, Lizzie Mounter, Grace Mumm, Ashley Otis\",\"A series of stories woven together by one of our most primal fears, the fear of the unknown.\",3.5\n"
              + "The Insurrection,The Insurrection,2020,Action,90,USA,English,Rene Perez,Rene Perez,,\"Michael ParÈ, Wilma Elles, Joseph Camilleri, Rebecca Tarabocchia, Jeanine Harrington, Malorie Glavan, Danner Boyd, Michael Cendejas, Woody Clendenen, Keely Dervin, Aaron Harvey, Tony Jackson, Michael Jarrod, Angelina Karo, Bernie Kelly\",The director of the largest media company wants to expose how left-wing powers use film to control populations.,2.9\n"
              + "Valley Girl,Valley Girl,2020,\"Comedy, Musical, Romance\",102,USA,English,Rachel Lee Goldenberg,\"Amy Talkington, Andrew Lane\",Sneak Preview Productions,\"Jessica Rothe, Josh Whitehouse, Jessie Ennis, Ashleigh Murray, Chloe Bennet, Logan Paul, Mae Whitman, Mario Revolori, Rob Huebel, Judy Greer, Alex Lewis, Alex MacNicoll, Danny Ramirez, Andrew Kai, Allyn Rachel\",\"Set to a new wave '80s soundtrack, a pair of young lovers from different backgrounds defy their parents and friends to stay together. A musical adaptation of the 1983 film.\",5.4\n"));
    } catch (Exception e) {
      e.printStackTrace();
      // test failed
      return false;
    }
    String title1 = "The Source of Shadows";
    String title2 = "The Insurrection";
    String title3 = "Valley Girl";
    boolean equalOne = true;
    // check if first movie is has of the above titles
    equalOne = equalOne
        && (title1.equals(movieList.get(0).getTitle()) || title2.equals(movieList.get(0).getTitle())
            || title3.equals(movieList.get(0).getTitle()));
    // check if second movie is has of the above titles
    equalOne = equalOne
        && (title1.equals(movieList.get(1).getTitle()) || title2.equals(movieList.get(1).getTitle())
            || title3.equals(movieList.get(1).getTitle()));
    // check if third movie is has of the above titles
    equalOne = equalOne
        && (title1.equals(movieList.get(2).getTitle()) || title2.equals(movieList.get(2).getTitle())
            || title3.equals(movieList.get(2).getTitle()));
    // true if the three movies have the right titles
    return equalOne;

  }

  /**
   * This test reads in 3 movies, then sorts them. It then checks whether the default sorting order
   * is descending based on the average ratings.
   * 
   * @return true if the test passed, false if it failed
   */
  public boolean testMovieOrder() {
    List<MovieInterface> movieList;
    try {
      movieList = readerToTest.readDataSet(new StringReader(

          "title,original_title,year,genre,duration,country,language,director,writer,production_company,actors,description,avg_vote\n"
              + "The Source of Shadows,The Source of Shadows,2020,Horror,83,USA,English,\"Ryan Bury, Jennifer Bonior\",\"Jennifer Bonior, Trevor Botkin\",Four Thieves Productions,\"Ashleigh Allard, Tom Bonington, Eliane Gagnon, Marissa Kaye Grinestaff, Jenna Heffernan, Joshua Hummel, Janice Kingsley, Chris Labasbas, Jared Laufree, Dominic Lee, Vic May, Sienna Mazzone, Lizzie Mounter, Grace Mumm, Ashley Otis\",\"A series of stories woven together by one of our most primal fears, the fear of the unknown.\",3.5\n"
              + "The Insurrection,The Insurrection,2020,Action,90,USA,English,Rene Perez,Rene Perez,,\"Michael ParÈ, Wilma Elles, Joseph Camilleri, Rebecca Tarabocchia, Jeanine Harrington, Malorie Glavan, Danner Boyd, Michael Cendejas, Woody Clendenen, Keely Dervin, Aaron Harvey, Tony Jackson, Michael Jarrod, Angelina Karo, Bernie Kelly\",The director of the largest media company wants to expose how left-wing powers use film to control populations.,2.9\n"
              + "Valley Girl,Valley Girl,2020,\"Comedy, Musical, Romance\",102,USA,English,Rachel Lee Goldenberg,\"Amy Talkington, Andrew Lane\",Sneak Preview Productions,\"Jessica Rothe, Josh Whitehouse, Jessie Ennis, Ashleigh Murray, Chloe Bennet, Logan Paul, Mae Whitman, Mario Revolori, Rob Huebel, Judy Greer, Alex Lewis, Alex MacNicoll, Danny Ramirez, Andrew Kai, Allyn Rachel\",\"Set to a new wave '80s soundtrack, a pair of young lovers from different backgrounds defy their parents and friends to stay together. A musical adaptation of the 1983 film.\",5.4\n"));
    } catch (Exception e) {
      e.printStackTrace();
      // test failed
      return false;
    }


    Collections.sort(movieList);
    double lastRating = 11.0;
    for (MovieInterface movie : movieList) {
      if (movie.getAvgVote() > lastRating) {
        // test fails
        return false;
      }
      lastRating = movie.getAvgVote();
    }
    // test passes
    return true;
  }

  /**
   * This test reads in 3 movies made in 2020 and ensure that the list contains the year of each
   * individual movies. The test fails if the year of the movies is not correctly outputted
   * 
   * @return true if the test passes, fail otherwise
   */
  public boolean testMovieYear() {
    List<MovieInterface> movieList;
    try {
      movieList = readerToTest.readDataSet(new StringReader(

          "title,original_title,year,genre,duration,country,language,director,writer,production_company,actors,description,avg_vote\n"
              + "The Source of Shadows,The Source of Shadows,2020,Horror,83,USA,English,\"Ryan Bury, Jennifer Bonior\",\"Jennifer Bonior, Trevor Botkin\",Four Thieves Productions,\"Ashleigh Allard, Tom Bonington, Eliane Gagnon, Marissa Kaye Grinestaff, Jenna Heffernan, Joshua Hummel, Janice Kingsley, Chris Labasbas, Jared Laufree, Dominic Lee, Vic May, Sienna Mazzone, Lizzie Mounter, Grace Mumm, Ashley Otis\",\"A series of stories woven together by one of our most primal fears, the fear of the unknown.\",3.5\n"
              + "The Insurrection,The Insurrection,2020,Action,90,USA,English,Rene Perez,Rene Perez,,\"Michael ParÈ, Wilma Elles, Joseph Camilleri, Rebecca Tarabocchia, Jeanine Harrington, Malorie Glavan, Danner Boyd, Michael Cendejas, Woody Clendenen, Keely Dervin, Aaron Harvey, Tony Jackson, Michael Jarrod, Angelina Karo, Bernie Kelly\",The director of the largest media company wants to expose how left-wing powers use film to control populations.,2.9\n"
              + "Valley Girl,Valley Girl,2020,\"Comedy, Musical, Romance\",102,USA,English,Rachel Lee Goldenberg,\"Amy Talkington, Andrew Lane\",Sneak Preview Productions,\"Jessica Rothe, Josh Whitehouse, Jessie Ennis, Ashleigh Murray, Chloe Bennet, Logan Paul, Mae Whitman, Mario Revolori, Rob Huebel, Judy Greer, Alex Lewis, Alex MacNicoll, Danny Ramirez, Andrew Kai, Allyn Rachel\",\"Set to a new wave '80s soundtrack, a pair of young lovers from different backgrounds defy their parents and friends to stay together. A musical adaptation of the 1983 film.\",5.4\n"));
    } catch (Exception e) {
      e.printStackTrace();
      // test failed
      return false;
    }
    Integer movieYear = new Integer(2020);
    // iterate through the list
    for (int i = 0; i < movieList.size(); i++) {
      // if the year does not equal to 2020, then return false
      if (!(movieList.get(i).getYear().equals(movieYear))) {
        return false;
      }
    }
    return true;
  }

  /**
   * This test reads in 3 movies and checks the functionality of getDescription() method within the
   * MovieInterface class. The test checks for the similarity between the 2 strings, and the whether
   * the correct movie description is outputted
   * 
   * @return true if the test passes, fail otherwise
   */
  public boolean testMovieDescription() {
    List<MovieInterface> movieList;
    try {
      movieList = readerToTest.readDataSet(new StringReader(

          "title,original_title,year,genre,duration,country,language,director,writer,production_company,actors,description,avg_vote\n"
              + "The Source of Shadows,The Source of Shadows,2020,Horror,83,USA,English,\"Ryan Bury, Jennifer Bonior\",\"Jennifer Bonior, Trevor Botkin\",Four Thieves Productions,\"Ashleigh Allard, Tom Bonington, Eliane Gagnon, Marissa Kaye Grinestaff, Jenna Heffernan, Joshua Hummel, Janice Kingsley, Chris Labasbas, Jared Laufree, Dominic Lee, Vic May, Sienna Mazzone, Lizzie Mounter, Grace Mumm, Ashley Otis\",\"A series of stories woven together by one of our most primal fears, the fear of the unknown.\",3.5\n"
              + "The Insurrection,The Insurrection,2020,Action,90,USA,English,Rene Perez,Rene Perez,,\"Michael ParÈ, Wilma Elles, Joseph Camilleri, Rebecca Tarabocchia, Jeanine Harrington, Malorie Glavan, Danner Boyd, Michael Cendejas, Woody Clendenen, Keely Dervin, Aaron Harvey, Tony Jackson, Michael Jarrod, Angelina Karo, Bernie Kelly\",The director of the largest media company wants to expose how left-wing powers use film to control populations.,2.9\n"
              + "Valley Girl,Valley Girl,2020,\"Comedy, Musical, Romance\",102,USA,English,Rachel Lee Goldenberg,\"Amy Talkington, Andrew Lane\",Sneak Preview Productions,\"Jessica Rothe, Josh Whitehouse, Jessie Ennis, Ashleigh Murray, Chloe Bennet, Logan Paul, Mae Whitman, Mario Revolori, Rob Huebel, Judy Greer, Alex Lewis, Alex MacNicoll, Danny Ramirez, Andrew Kai, Allyn Rachel\",\"Set to a new wave '80s soundtrack, a pair of young lovers from different backgrounds defy their parents and friends to stay together. A musical adaptation of the 1983 film.\",5.4\n"));
    } catch (Exception e) {
      e.printStackTrace();
      // test failed
      return false;
    } // list out each descriptions of movies
    String description1 =
        "\"A series of stories woven together by one of our most primal fears, the fear of the unknown.\"";
    String description2 =
        "The director of the largest media company wants to expose how left-wing powers use film to control populations.";
    String description3 =
        "\"Set to a new wave '80s soundtrack, a pair of young lovers from different backgrounds defy their parents and friends to stay together. A musical adaptation of the 1983 film.\"";
    boolean output = true;
    for (int i = 0; i < movieList.size(); i++) {
      // check if all 3 movies has at least one of the description
      output = output && (description1.equals(movieList.get(i).getDescription())
          || description2.equals(movieList.get(i).getDescription())
          || description3.equals(movieList.get(i).getDescription()));
    }
    return output;
  }

  public boolean testMovieGenre() {
    List<MovieInterface> movieList;
    try {
      movieList = readerToTest.readDataSet(new StringReader(

          "title,original_title,year,genre,duration,country,language,director,writer,production_company,actors,description,avg_vote\n"
              + "The Source of Shadows,The Source of Shadows,2020,Horror,83,USA,English,\"Ryan Bury, Jennifer Bonior\",\"Jennifer Bonior, Trevor Botkin\",Four Thieves Productions,\"Ashleigh Allard, Tom Bonington, Eliane Gagnon, Marissa Kaye Grinestaff, Jenna Heffernan, Joshua Hummel, Janice Kingsley, Chris Labasbas, Jared Laufree, Dominic Lee, Vic May, Sienna Mazzone, Lizzie Mounter, Grace Mumm, Ashley Otis\",\"A series of stories woven together by one of our most primal fears, the fear of the unknown.\",3.5\n"
              + "The Insurrection,The Insurrection,2020,Action,90,USA,English,Rene Perez,Rene Perez,,\"Michael ParÈ, Wilma Elles, Joseph Camilleri, Rebecca Tarabocchia, Jeanine Harrington, Malorie Glavan, Danner Boyd, Michael Cendejas, Woody Clendenen, Keely Dervin, Aaron Harvey, Tony Jackson, Michael Jarrod, Angelina Karo, Bernie Kelly\",The director of the largest media company wants to expose how left-wing powers use film to control populations.,2.9\n"
              + "Valley Girl,Valley Girl,2020,\"Comedy, Musical, Romance\",102,USA,English,Rachel Lee Goldenberg,\"Amy Talkington, Andrew Lane\",Sneak Preview Productions,\"Jessica Rothe, Josh Whitehouse, Jessie Ennis, Ashleigh Murray, Chloe Bennet, Logan Paul, Mae Whitman, Mario Revolori, Rob Huebel, Judy Greer, Alex Lewis, Alex MacNicoll, Danny Ramirez, Andrew Kai, Allyn Rachel\",\"Set to a new wave '80s soundtrack, a pair of young lovers from different backgrounds defy their parents and friends to stay together. A musical adaptation of the 1983 film.\",5.4\n"));
    } catch (Exception e) {
      e.printStackTrace();
      // test failed
      return false;
    } // list out each descriptions of movies
    String genre1 = "Comedy";
    String genre2 = "Musical";
    String genre3 = "Romance";
    boolean output = true;
    if (!movieList.get(2).getGenres().get(0).equals(genre1)
        && !movieList.get(2).getGenres().get(1).equals(genre2)
        && !movieList.get(2).getGenres().get(2).equals(genre3)) {

      output = false;

    }
    return output;
  }
}
