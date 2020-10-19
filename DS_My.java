//////////////////// ALL ASSIGNMENTS INCLUDE THIS SECTION /////////////////////
//
// Title: (P1 ADT)
// Course: (CS 400, Fall 2019)
//
// Author: (Shaurya Kethireddy)
// Email: (skethireddy@wisc.edu)
// Lecturer's Name: (Debra Deppeler)
// Description: This project allows user to add, remove, get, and check contain
// in the adt that is built
/////////////////////////////// 80 COLUMNS WIDE ///////////////////////////////

@SuppressWarnings("rawtypes")
public class DS_My implements DataStructureADT {

  private int size = 0; // class variable to keep track of size
  private LinkedNode head = null; // sets head to null

  public DS_My() {

  }

  /**
   * this class makes an object which can associate key and value into one
   */
  private class LinkedNode {
    private Object v; // declare variable
    private Comparable k; // declare variable
    private LinkedNode next;

    /**
     * getter for value
     */
    private Object getV() {
      return this.v;
    }

    /**
     * getter for key
     */
    private Comparable getK() {
      return this.k;
    }

    /**
     * constructor for key and value 
     */
    private LinkedNode(Comparable key, Object value) {
      this.k = key;
      this.v = value;
      this.next = null;
    }

    private LinkedNode getNext() {
      return next;
    }



  }


  /**
   * Add the key,value pair to the data structure and increases size.
   * If key is null, throws IllegalArgumentException("null key");
   * If key is already in data structure, throws RuntimeException("duplicate key");
   * can accept and insert null values
   */
  @Override
  public void insert(Comparable k, Object v) {
    LinkedNode one = new LinkedNode(k, v); // creates object for insertion
    LinkedNode temp = head;

    if (k == null) {
      throw new IllegalArgumentException("The key is null");
    }
    if (contains(k)) {
      System.out.print(contains(k));
      throw new RuntimeException("This is a duplicate key");
    }

    if (head == null) { // checks if head is null
      head = one; // creates new head
      head.next = null;
    } else {
      while (temp.getNext() != null) { // goes through adt
        temp = temp.getNext(); // gets to the end of "list"
      }
      temp.next = one; // sets next element in list to new object
      one.next = null;
    }

    this.size++; // increment size
  }

  /**
   * If key is found, Removes the key from the data structure and decreases size
   * If key is null, throws IllegalArgumentException("null key") without decreasing size
   * If key is not found, returns false.
   */
  @Override
  public boolean remove(Comparable k) {
    LinkedNode temp = head;
    LinkedNode remove = null;
    if (k == null) { // null check
      throw new IllegalArgumentException("The key is null");
    }
    if (size == 0) { // size check
      return false;
    }
    if (size == 1) { // special condition when size is one
      if (head.getK().equals(k)) {
        head = null;
        size = 0;
        return true;
      }

    } else {
      while (temp.getNext() != null) { // go through the adt
        if (head.getK().equals(k)) { // checks if head needs to be removed when adt is more than one
          head = head.getNext(); // assign head to next element
          this.size--; // decrement size
          return true;
        }

        remove = temp;
        temp = temp.getNext();
        if (temp.getK().equals(k)) {
          remove.next = temp.next;
          size = size - 1;
          return true;
        }
      }
    }

    return false;
  }

  /**
   * Returns true if the key is in the data structure
   * Returns false if key is null or not present
   */
  @Override
  public boolean contains(Comparable k) {
    if (k == null) { // null check
      return false;
    }
    if (size == 0) { // size check
      return false;
    }
    LinkedNode temp = head;
    if (head.getK().equals(k)) { // check if head contains key
      return true;
    }
    while (temp.getNext() != null) { // goes through adt to compare
      temp = temp.getNext();
      if (temp.getK().equals(k)) {
        return true;
      }
    }

    return false;
  }

  /**
   * Returns the value associated with the specified key
   * get - does not remove key or decrease size
   * If key is null, throws IllegalArgumentException("null key") 
   */
  @Override
  public Object get(Comparable k) {
    if (k == null) { // null check
      throw new IllegalArgumentException("The key is null");
    }
    LinkedNode temp = head;
    if (head.getK().equals(k)) { // compares with head
      return head.getV();
    }
    while (temp.getNext() != null) { // goes through adt to compare for key and return the value
                                     // associated with it
      temp = temp.getNext();
      if (temp.getK().equals(k)) {
        return temp.getV();
      }
    }
    return null; // if key isn't found then returns null
  }

  /**
   * Returns the number of elements in the data structure
   */
  @Override
  public int size() {
    return this.size; // returns class variable size
  }



}
