// --== CS400 File Header Information ==--
// Name: Jerry C Yu
// Email: jcyu4@wisc.edu
// Team: Red Team
// Group: HG
// TA: Hang Yin
// Lecturer: Gary Dahl
// Notes to Grader: none

import java.util.NoSuchElementException;
import java.util.LinkedList;

/**
 * This class implements a HashTable ADT and stores pairs of keys and values of any type.
 * 
 */
public class HashTableMap<KeyType, ValueType> implements MapADT<KeyType, ValueType> {
  /**
   * This private class acts as a wrapper to wrap the Key and Value into one object
   * 
   */
  private class Wrapper {
    KeyType key; // key of the pair
    ValueType value; // value of the pair

    /**
     * Constructor for the wrapper object that initializes the key and value
     * 
     * @param key   - inputed key that represents the key of the pair to be wrapped
     * @param value - inputed value that represents the value of the pair to be wrapped
     */
    public Wrapper(KeyType key, ValueType value) {
      this.key = key;
      this.value = value;
    }

    /**
     * Getter method for the instance variable key
     * 
     * @return key stored in this wrapper object
     */
    public KeyType getKey() {
      return this.key;
    }

    /**
     * Getter method for the instance variable value
     * 
     * @return value stored in this wrapper object
     */
    public ValueType getValue() {
      return this.value;
    }
  }

  private int size = 0; // amount of pairs that are currently in this HashTableMap
  private int capacity; // maximum amount of pairs this HashTableMap can hold
  // private instance array of linked lists that holds the pairs in this HashTableMap
  private LinkedList<Wrapper>[] hashTable = (LinkedList<Wrapper>[]) new LinkedList[capacity];

  /**
   * Constructor for the HashTableMap class that initializes capacity and the instance array
   * hashTable
   * 
   * @param capacity - maximum amount of pairs this HashTableMap can hold
   */
  public HashTableMap(int capacity) {
    // initializes capacity to the inputed capacity
    this.capacity = capacity;
    // creates an array of linked lists of size capacity
    hashTable = new LinkedList[capacity];
    // initializes the linked list at each index in the hashTable array
    for (int i = 0; i < capacity; i++) {
      hashTable[i] = new LinkedList<Wrapper>();
    }
  }

  /**
   * No argument constructor for the HashTableMap class that initializes capacity to the default of
   * 10 and the instance array hashTable
   * 
   */
  public HashTableMap() {
    // initializes capacity to the inputed capacity
    this.capacity = 10;
    // creates an array of linked lists of size capacity
    hashTable = new LinkedList[capacity];
    // initializes the linked list at each index in the hashTable array
    for (int i = 0; i < capacity; i++) {
      hashTable[i] = new LinkedList<Wrapper>();
    }
  }

  /**
   * private instance method that develops a hashCode for the key inputed
   * 
   * @param key - the key the hash function creates a hashCode for
   * @return a hashCode for the given key
   */
  private int hashFunction(KeyType key) {
    // Checks if the key is null and throws a NoSuchElementException if it is
    if (key == null) {
      throw new NoSuchElementException();
    }
    // Returns the integer that comes from this formula
    else {
      return Math.abs(key.hashCode() % capacity);
    }
  }

  /**
   * Overridden public instance method that adds a data pair into the HashTableMap to the
   * corresponding spot based on the hashFunction method. If the load factor exceeds 85%, the
   * private method growth() is called to resize and rehash the array. If the key is null or a pair
   * with the same key is already in the HashTableMap the pair is not added
   * 
   * @param key   - the key of the data pair
   * @param value - the value of the data pair
   * @return true if the pair was successfully added and false otherwise
   */
  @Override
  public boolean put(KeyType key, ValueType value) {
    // Checks if the key is null and returns false if it is
    if (key == null) {
      return false;
    }
    // Iterates through the HashTable and checks if the key is already in the HashTablemap
    for (int i = 0; i < hashTable.length; i++) {
      for (int j = 0; j < hashTable[i].size(); j++) {
        if (key.equals((hashTable[i]).get(j).getKey())) {
          return false;
        }
      }
    }
    // If the key was not found and is not null, the pair is added to the HashTableMap
    hashTable[hashFunction(key)].add(new Wrapper(key, value));
    // Size is incremented
    size++;
    // Checks if the loadFactor is above 85%
    double loadFactor = ((double) size / (double) capacity);
    // If the loadFactor is above 85%, growth() is called
    if (loadFactor >= 0.85) {
      growth();
    }
    return true;
  }

  /**
   * Overridden public instance method that returns the value of the pair that has the key that was
   * inputed.
   * 
   * @param key - the key of the pair that is being searched for
   * @throws NoSuchElementException if key is equal to null or if it was not found
   * @return the value of the pair corresponding to the correct key
   */
  @Override
  public ValueType get(KeyType key) throws NoSuchElementException {
    // Checks if the key is null and throws a NoSuchElementException if it is
    if (key == null) {
      throw new NoSuchElementException();
    }
    // Finds the correct linked list that corresponds to the hashFunction of the key inputed
    LinkedList<Wrapper> list = hashTable[hashFunction(key)];
    // Iterates through the linked list and returns the value of the pair corresponding to the
    // correct key if it is found
    for (Wrapper element : list) {
      if (element.getKey().equals(key)) {
        return element.getValue();
      }
    }
    // throws a NoSuchElementException if the key was not found
    throw new NoSuchElementException();
  }

  /**
   * Overridden public instance method that returns the current amount of pairs stored in the
   * HashTableMap
   * 
   * @return the number of pairs stored in the HashTableMap
   */
  @Override
  public int size() {
    return size;
  }

  /**
   * Overridden public instance method that checks if there is a pair with the same key as the key
   * inputed
   * @param key - the key that the method is looking for in the HashTableMap
   * @return true if the key is found and false otherwise
   */
  @Override
  public boolean containsKey(KeyType key) {
    // Checks if the key is null and returns false if it is
    if (key == null) {
      return false;
    }
    // Finds the correct linked list that corresponds to the hashFunction of the key inputed
    LinkedList<Wrapper> list = hashTable[hashFunction(key)];
    // Iterates through the linked list and if the key is in there the method returns true
    for (Wrapper element : list) {
      if (element.getKey().equals(key)) {
        return true;
      }
    }
    // returns false if the key is not found
    return false;
  }
  /**
   * Overridden public instance method that removes a pair of data from the HashTableMap based on
   * the key inputed
   * @param key - the key the method is searching for and removing
   * @return the value of the pair if it is successfully removed and false otherwise
   */
  @Override
  public ValueType remove(KeyType key) {
    // Checks if the key is null and returns null if it is
    if (key == null) {
      return null;
    }
    // Finds the correct linked list that corresponds to the hashFunction of the key inputed
    LinkedList<Wrapper> List = hashTable[hashFunction(key)];
    // Iterates through the linked list looking for a pair with the inputed key
    for (int i = 0; i < List.size(); i++) {
      // if the key is found it creates a new variable of type ValueType in order to store the value
      // of the pair that is to be removed
      if (List.get(i).getKey().equals(key)) {
        ValueType val = List.get(i).getValue();
        // removes the pair from the HashTableMap
        List.remove(i);
        // decrements size
        size--;
        // returns the value of the pair that is removed
        return val;
      }
    }
    // returns null if a pair is not removed
    return null;
  }
  /**
   * Overridden public instance method that clears the HashTableMap
   * 
   */
  @Override
  public void clear() {
    // rests the size to be zero
    this.size = 0;
    // Iterates through the array of linked lists and calls the clear() method for each one
    for (int i = 0; i < hashTable.length; i++) {
      hashTable[i].clear();
    }
  }
  /**
   * Private instance method that resizes and rehashes the HashTableMap if the load factor 
   * calculated in the put() method is greater than or equal to 85%
   */
  private void growth() {
    // doubles capacity
    capacity = capacity * 2;
    // creates a temporary linked list to hold all of the pairs
    LinkedList<Wrapper> temp = new LinkedList<Wrapper>();
    // transfers all of the contents of the HashTableMap into the linked list
    for (int i = 0; i < hashTable.length; i++) {
      for (int j = 0; j < hashTable[i].size(); j++) {
        temp.addAll(hashTable[i]);
      }
    }
    // resets the size
    this.size = 0;
    // creates a new array of linked lists with the new capacity
    hashTable = new LinkedList[capacity];
    // initializes a linked list for each index of the array
    for (int i = 0; i < capacity; i++) {
      hashTable[i] = new LinkedList<Wrapper>();
    }
    // rehashes the previous HashTableMap by calling the put() method on every pair stored in the
    // temporary linked list
    for (int i = 0; i < temp.size(); i++) {
      put(temp.get(i).getKey(), temp.get(i).getValue());
    }

  }
}


