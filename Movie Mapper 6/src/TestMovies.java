import java.io.IOException;
import java.io.StringReader;
import java.util.List;
import java.util.zip.DataFormatException;

public class TestMovies {
  public static void main(String[] args) {
    MovieDataReaderInterface data = new MovieDataReader();
    try {
      List<MovieInterface> movieList = data.readDataSet(new StringReader(
          "title,original_title,year,genre,duration,country,language,director,writer,production_company,actors,description,avg_vote\n"
              + "The Source of Shadows,The Source of Shadows,2020,Horror,83,USA,English,\"Ryan Bury, Jennifer Bonior\",\"Jennifer Bonior, Trevor Botkin\",Four Thieves Productions,\"Ashleigh Allard, Tom Bonington, Eliane Gagnon, Marissa Kaye Grinestaff, Jenna Heffernan, Joshua Hummel, Janice Kingsley, Chris Labasbas, Jared Laufree, Dominic Lee, Vic May, Sienna Mazzone, Lizzie Mounter, Grace Mumm, Ashley Otis\",\"A series of stories woven together by one of our most primal fears, the fear of the unknown.\",3.5\n"
              + "The Insurrection,The Insurrection,2020,Action,90,USA,English,Rene Perez,Rene Perez,,\"Michael ParÈ, Wilma Elles, Joseph Camilleri, Rebecca Tarabocchia, Jeanine Harrington, Malorie Glavan, Danner Boyd, Michael Cendejas, Woody Clendenen, Keely Dervin, Aaron Harvey, Tony Jackson, Michael Jarrod, Angelina Karo, Bernie Kelly\",The director of the largest media company wants to expose how left-wing powers use film to control populations.,2.9\n"
              + "Valley Girl,Valley Girl,2020,\"Comedy, Musical, Romance\",102,USA,English,Rachel Lee Goldenberg,\"Amy Talkington, Andrew Lane\",Sneak Preview Productions,\"Jessica Rothe, Josh Whitehouse, Jessie Ennis, Ashleigh Murray, Chloe Bennet, Logan Paul, Mae Whitman, Mario Revolori, Rob Huebel, Judy Greer, Alex Lewis, Alex MacNicoll, Danny Ramirez, Andrew Kai, Allyn Rachel\",\"Set to a new wave '80s soundtrack, a pair of young lovers from different backgrounds defy their parents and friends to stay together. A musical adaptation of the 1983 film.\",5.4\n"));
      List<MovieInterface> m = data.readDataSet(new StringReader(
          "title,original_title,year,genre,duration,country,language,director,writer,p>"
              + "The Source of Shadows,The Source of Shadows,2020,Horror,83,USA,English,\" >"
              + "The Insurrection,The Insurrection,2020,Action,90,USA,English,Rene Perez,R>"
              + "Valley Girl,Valley Girl,2020,\"Comedy, Musical, Romance\",102,USA,English>"));
      System.out.println(movieList.size());
      System.out.println(m.size());
      for(int i = 0; i < m.size(); i++) {
        System.out.println(m.get(i).getTitle());
      }
    } catch (IOException | DataFormatException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }
    
  }
}
