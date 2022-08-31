// --== CS400 File Header Information ==--
// Name: Jerry C Yu
// Email: jcyu4@wisc.edu
// Team: Red Team
// Group: HG
// TA: Hang Yin
// Lecturer: Gary Dahl
// Notes to Grader: none

import java.util.NoSuchElementException;

public interface MapADT<KeyType, ValueType> {

  public boolean put(KeyType key, ValueType value);

  public ValueType get(KeyType key) throws NoSuchElementException;

  public int size();

  public boolean containsKey(KeyType key);

  public ValueType remove(KeyType key);

  public void clear();

}
