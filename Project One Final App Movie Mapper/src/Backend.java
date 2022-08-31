import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.zip.DataFormatException;

public class Backend extends HashTableMap<String, String> implements BackendInterface {
	List<String> getGenres = new ArrayList<String>();
	List<String> getRatings = new ArrayList<String>();
	List<String> allGenreList = new ArrayList<String>();
	
	List<MovieInterface> selectedMovies = new ArrayList<MovieInterface>();
	List<MovieInterface> m;
	
	MovieDataReaderInterface reader = new MovieDataReader();

	HashTableMap<String, List<MovieInterface>> genreTable = new HashTableMap<String, List<MovieInterface>>();
	HashTableMap<String, List<MovieInterface>> ratingTable = new HashTableMap<String, List<MovieInterface>>();

	public Backend(String[] args) {
		FileReader f;
		
		try {
			f = new FileReader(new File(args[0]));
			m = reader.readDataSet(f);
			
			for (int i = 0; i < m.size(); i++) {
				for (int j = 0; j < m.get(i).getGenres().size(); j++) {
					if (!allGenreList.contains(m.get(i).getGenres().get(j))) {
						allGenreList.add(m.get(i).getGenres().get(j));
					}
				}
			}
			
			for (int i = 0; i < 10; i++) {
				String rat = Integer.toString(i);
				ArrayList<MovieInterface> addAvgRating = new ArrayList<MovieInterface>();
				
				for (int j = 0; j < m.size(); j++) {
					if (m.get(j).getAvgVote() >= i && m.get(j).getAvgVote() < i + 1) {
						addAvgRating.add(m.get(j));
					}
				}
				
				ratingTable.put(rat, addAvgRating);
			}
			
			for (int i = 0; i < allGenreList.size(); i++) {
				ArrayList<MovieInterface> addGenre = new ArrayList<MovieInterface>();
				
				for (int j = 0; j < m.size(); j++) {
					if (m.get(j).getGenres().contains(allGenreList.get(i))) {
						addGenre.add(m.get(j));
					}
				}
				
				genreTable.put(allGenreList.get(i), addGenre);
			}
		} catch (IOException | DataFormatException e) {
			e.printStackTrace();
		}
	}

	public Backend(Reader r) {
		try {
			m = reader.readDataSet(r);
			
			for (int i = 0; i < m.size(); i++) {
				for (int j = 0; j < m.get(i).getGenres().size(); j++) {
					if (!allGenreList.contains(m.get(i).getGenres().get(j))) {
						allGenreList.add(m.get(i).getGenres().get(j));
					}
				}
			}
			
			for (int i = 0; i < 10; i++) {
				String rat = Integer.toString(i);
				ArrayList<MovieInterface> addAvgRating = new ArrayList<MovieInterface>();
				
				for (int j = 0; j < m.size(); j++) {
					if (m.get(j).getAvgVote() >= i && m.get(j).getAvgVote() < i + 1) {
						addAvgRating.add(m.get(j));
					}
				}
				
				ratingTable.put(rat, addAvgRating);
			}
			
			for (int i = 0; i < allGenreList.size(); i++) {
				ArrayList<MovieInterface> addGenre = new ArrayList<MovieInterface>();
				
				for (int j = 0; j < m.size(); j++) {
					if (m.get(j).getGenres().contains(allGenreList.get(i))) {
						addGenre.add(m.get(j));
					}
				}
				
				genreTable.put(allGenreList.get(i), addGenre);
			}
			
		} catch (IOException | DataFormatException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void addGenre(String genre) {
		getGenres.add(genre);
		getSelectedMovies();
	}

	@Override
	public void addAvgRating(String rating) {
		getRatings.add(rating);
		getSelectedMovies();
	}

	@Override
	public void removeGenre(String genre) {
		getGenres.remove(genre);
		getSelectedMovies();
	}

	@Override
	public void removeAvgRating(String rating) {
		getRatings.remove(rating);
		getSelectedMovies();
	}

	@Override
	public List<String> getGenres() {
		return getGenres;
	}

	@Override
	public List<String> getAvgRatings() {
		return getRatings;
	}

	@Override
	public int getNumberOfMovies() {
		return selectedMovies.size();
	}

	@Override
	public List<String> getAllGenres() {
		return allGenreList;
	}

	@Override
	public List<MovieInterface> getThreeMovies(int startingIndex) {
		List<MovieInterface> returnList = new ArrayList<MovieInterface>();
		
		for (int i = startingIndex; i < startingIndex + 3; i++) {
			if (i >= selectedMovies.size()) {} 
			else if (returnList.size() < 4 && !returnList.contains(selectedMovies.get(i))) {
				returnList.add(selectedMovies.get(i));
			}
		}
		
		return returnList;
	}

	private List<MovieInterface> getSelectedMovies() {
		selectedMovies.removeAll(selectedMovies);
		
		List<MovieInterface> moviesGenre = new ArrayList<MovieInterface>();
		List<MovieInterface> moviesRating = new ArrayList<MovieInterface>();
		
		try {
			if (getGenres.size() == 0) {
				moviesGenre = m;
			} else {
				for (MovieInterface m1 : m) {
					int amountGenres = 0;
					
					for (String s : getGenres) {
						if (m1.getGenres().contains(s)) {
							amountGenres++;
						}
					}
					
					if (amountGenres == getGenres.size()) {
						moviesGenre.add(m1);
					}
				}
			}
			
			for (int i = 0; i < getRatings.size(); i++) {
				int r = Integer.parseInt(getRatings.get(i));
				String rat = Integer.toString(r);
				
				moviesRating.addAll(ratingTable.get(rat));
			}
		} catch (NoSuchElementException nee) {

		}

		for (int i = 0; i < moviesGenre.size(); i++) {
			for (int j = 0; j < moviesRating.size(); j++) {
				if (moviesGenre.get(i).equals(moviesRating.get(j))) {
					selectedMovies.add(moviesGenre.get(i));
				}
			}
		}
		
		Collections.sort(selectedMovies);
		return selectedMovies;
	}
}
