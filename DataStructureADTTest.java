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
import static org.junit.jupiter.api.Assertions.fail;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

abstract class DataStructureADTTest<T extends DataStructureADT<String, String>> {

  private T dataStructureInstance;

  protected abstract T createInstance();

  @BeforeAll
  static void setUpBeforeClass() throws Exception {}

  @AfterAll
  static void tearDownAfterClass() throws Exception {}

  @BeforeEach
  void setUp() throws Exception {
    dataStructureInstance = createInstance();
  }

  @AfterEach
  void tearDown() throws Exception {
    dataStructureInstance = null;
  }

  /**
   * checks if adt starts of with size 0
   */
  @Test
  void test00_empty_ds_size() {
    if (dataStructureInstance.size() != 0)
      fail("data structure should be empty, with size=0, but size=" + dataStructureInstance.size());
  }

  /**
   * checks if size is one once one object is added
   */
  @Test
  void test01_after_insert_one_size_is_one() {
    dataStructureInstance.insert("sk", "shaurya kethireddy");
    int size = dataStructureInstance.size();
    if (size != 1) {
      fail("data structure should be of size=1 but size=" + dataStructureInstance.size());
    }
  }

  /**
   * checks size when adding one object and removing one object with correct values
   */
  @Test
  void test02_after_insert_one_remove_one_size_is_0() {
    dataStructureInstance.insert("sk", "shaurya kethireddy");
    if (dataStructureInstance.size() != 1) {
      fail("data structure should be of size=1 but size=" + dataStructureInstance.size());
    }
    dataStructureInstance.remove("sk");
    if (dataStructureInstance.size() != 0) {
      fail("data structure should be of size=0 but size=" + dataStructureInstance.size());
    }
  }


  /**
   * checks if exception is thrown when trying to add duplicate key
   */
  @Test
  void test03_duplicate_exception_is_thrown() {
    dataStructureInstance.insert("sk", "shaurya kethireddy");
    try {
      dataStructureInstance.insert("sk", "shaurya kethireddy");
      fail("duplicate exception wasn't thrown and the duplicate key was added to the adt");
    } catch (Exception e) {

    }

  }

  /**
   * checks if method returns false when remove a "key" which isn't present
   */
  @Test
  void test04_remove_returns_false_when_key_not_present() {
    dataStructureInstance.insert("sk", "shaurya kethireddy");
    boolean result = dataStructureInstance.remove("remove");
    if (result == true) {
      fail("data structure should not be able to remove the unpresent key but still 'removed' it");
    }

  }

  /**
   * checks if adt can handle adding 500 items with correct size and removes them all
   */
  @Test
  void test05_add_500_items_and_check_size_then_remove() {
    if (dataStructureInstance.size() != 0) {
      fail("data structure should start of with size 0 but isn't");
    }
    for (int i = 0; i < 500; i++) {
      dataStructureInstance.insert("key" + i, "object" + i);
    }
    int size = dataStructureInstance.size();
    if (size != 500) {
      fail("data structure shold be of size 500 but is of size " + size);
    }
    for (int i = 0; i < 500; i++) {
      dataStructureInstance.remove("key" + i);
    }
    int removedSize = dataStructureInstance.size();
    if (removedSize != 0) {
      fail("data structure shold be of size 0 but is of size " + removedSize);
    }
  }

  /**
   * checks if object with same key can be added when it was added and removed previously
   */
  @Test
  void test06_add_and_remove_and_with_same_key_and_object() {
    dataStructureInstance.insert("test06", "test06");
    dataStructureInstance.remove("test06");
    try {
      dataStructureInstance.insert("test06", "test06");
    } catch (Exception e) {
      fail("data structure wasn't able to add new object and key even though it was removed");
    }
  }

  /**
   * checks if null exception is thrown when inserting with a null key
   */
  @Test
  void test07_null_check_when_adding_null_key() {
    try {
      dataStructureInstance.insert(null, "test07");
      fail("data structure didn't throw null pointer exception even though key is null");
    } catch (Exception e) {

    }
  }

  /**
   * checks if contains method returns true if user tries to access a key which is already in adt
   */
  @Test
  void test08_contains_returns_true_when_key_is_in_adt() {
    dataStructureInstance.insert("test08", "test08");
    boolean result = dataStructureInstance.contains("test08");
    if (result == false) {
      fail("data structure returns false even though object with that key exists in adt");
    }
  }

  /**
   * checks if get method returns correct value of the desired object
   */
  @Test
  void test09_get_method_returns_correct_value() {
    for (int i = 0; i < 20; i++) {
      dataStructureInstance.insert("key" + i, "object" + i);
    }
    int random = (int) (Math.random() * 20);
    String result = dataStructureInstance.get("key" + random);
    if (!result.equals("object" + random)) {
      fail("get method didn't return the correct value from the given key. it returned '" + result
          + "' but should be 'object" + random + "'");
    }
  }

  /**
   * adds and removes objects in a random order ands checks if get method still is able to function 
   * properly
   */
  @Test
  void test10_insert_and_remove_and_get_correct_object() {
    dataStructureInstance.insert("123", "123");
    dataStructureInstance.insert("1234", "1234");
    dataStructureInstance.insert("12345", "12345");
    dataStructureInstance.remove("12345");
    dataStructureInstance.remove("123");
    dataStructureInstance.insert("12345", "12345");
    String result = "123451234";
    String temp = dataStructureInstance.get("12345") + dataStructureInstance.get("1234");
    if (!result.equals(temp)) {
      fail("data structure doesn't contain the right values, it should be " + result + " but has "
          + temp);
    }

  }


}
