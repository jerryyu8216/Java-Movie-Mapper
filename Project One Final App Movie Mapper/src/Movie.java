import java.util.List;

public class Movie implements MovieInterface {
  // private instance fields
  private String title;
  private Integer year;
  private List<String> genre;
  private String director;
  private String description;
  private Float avgVote;
  
  /**
   * The constructor of the Movie Object class that takes in String title, Integer year, List<String> genre, String director
   * String description, and Float avgVote
   */
  public Movie(String title, Integer year, List<String> genre, String director, String description, Float avgVote) {
    this.title = title;
    this.year = year;
    this.genre = genre;
    this.director = director;
    this.description = description;
    this.avgVote = avgVote;
  }
  
  /**
   * accessor method for title
   */
  @Override
  public String getTitle() {
    return title;
  }
  /**
   * accessor method for year
   */
  @Override
  public Integer getYear() {
    return year;
  }
  /**
   * accessor method for genre
   */
  @Override
  public List<String> getGenres() {
    return genre;
  }
  /**
   * accessor method for director
   */
  @Override
  public String getDirector() {
    return director;
  }
  /**
   * accessor method for description
   */
  @Override
  public String getDescription() {
    return description;
  }
  /**
   * accessor method for avgVote
   */
  @Override
  public Float getAvgVote() {
    return avgVote;
  }
  /**
   * compareTo Method that overrides the default interface, it checks whether the title of the movie and the other movie are the same, returning 0;
   * it also checks whether the other movie's avgVote is greater than this one, which returns 1 if it is true, -1 if false
   */
  @Override
  public int compareTo(MovieInterface otherMovie) {
    if (this.getTitle().equals(otherMovie.getTitle())) {
      return 0;
    } else if (this.getAvgVote() < otherMovie.getAvgVote()) {
      return 1;
    } else {
      return -1;
    }
  }
  /**
   * toString method that overrides the default interface, it prints out the list of all the categories of this movie object
   */
  @Override
  public String toString() {
    return "[Title: " + this.title + "] [Year: " + this.year + "] [Genre: " + this.genre + "] [Director: "
        + this.director + "] [Description: " + this.description + "] [Avg_vote: " + this.avgVote + "]";
  }
}
