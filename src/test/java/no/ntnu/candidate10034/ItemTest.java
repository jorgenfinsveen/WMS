package no.ntnu.candidate10034;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;

/**
 * JUnit-test class for testing of the Item class.
 *
 * @since       2022-12-02
 * @version     2022-12-02
 * @author      Candidate 10034
 */
public class ItemTest {

  /**
  * Create a new instance of the Item class which is valid.
  *
  * @return valid Item-object.
  */
  private Item createValidItem() {
    return new Item(
      "Floor 2.0", "Jysk", "brown",
      "Futuristic floor", 3,
      188.0, 2.0, 100,
      25, 1
    );
  }


  /**
   * Create new instance of the Item class which is invalid.
   *
   * @return invalid Item-object.
   */
  private Item createInvalidItem() {
    return new Item(
      "Floor 3", "Jysk", "brown",
      "Futuristic floor", -13,
      188.0, 2.0, 0,
      -25, 6
    );
  }


  /**
   * Assert valid creation of a new Item is possible.
   */
  @Test
  public void testCreateValidItem() {
    assertNotNull(createValidItem());
  }


  /**
   * Creation of invalid Item should throw an exception.
   */
  @Test
  public void testCreateInvalidItem() {
    assertThrows(IllegalArgumentException.class, () -> {
      createInvalidItem();
    });
  }


  /**
   * Assert all get-methods return expected results.
   */
  @Test
  public void testAllAccessorMethods() {
    Item item = createValidItem();

    assertEquals("Floor 2.0", item.getItemNumber());
    assertEquals("Jysk", item.getItemBrand());
    assertEquals("brown", item.getItemColor());
    assertEquals("Futuristic floor", item.getItemDescription());
    assertEquals(3, item.getItemWeight());
    assertEquals(188.0, item.getItemLength());
    assertEquals(2.0, item.getItemHeight());
    assertEquals(100, item.getItemPrice());
    assertEquals(25, item.getItemAmount());
    assertEquals(0, item.getItemCategoryAsInt());
    assertEquals("floor laminate", item.getItemCategoryAsString());
  }


  /**
   * Confirm all setter-methods work as expected for valid parameters.
   */
  @Test
  public void testAllMutatorsPositive() {
    Item item = createValidItem();

    item.setItemNumber("Floor 3.0");
    item.setItemBrand("Skeidar");
    item.setItemColor("blue");
    item.setItemDescription("New version");
    item.setItemWeight(5);
    item.setItemLength(200.0);
    item.setItemHeight(3.0);
    item.setItemPrice(200);
    item.setItemAmount(60);
    item.setItemCategory(2);

    assertEquals("Floor 3.0", item.getItemNumber());
    assertEquals("Skeidar", item.getItemBrand());
    assertEquals("blue", item.getItemColor());
    assertEquals("New version", item.getItemDescription());
    assertEquals(5, item.getItemWeight());
    assertEquals(200.0, item.getItemLength());
    assertEquals(3.0, item.getItemHeight());
    assertEquals(200, item.getItemPrice());
    assertEquals(60, item.getItemAmount());
    assertEquals(1, item.getItemCategoryAsInt());
    assertEquals("window", item.getItemCategoryAsString());
  }


  /**
   * Confirm all setter-methods is not allowed for invalid parameters.
   */
  @Test
  public void testAllMutatorsNegative() {
    Item item = createValidItem();

    assertThrows(IllegalArgumentException.class, () -> {
      item.setItemWeight(-5); 
    });
    assertThrows(IllegalArgumentException.class, () -> {
      item.setItemLength(-200.0); 
    });
    assertThrows(IllegalArgumentException.class, () -> { 
      item.setItemHeight(-2.0); 
    });
    assertThrows(IllegalArgumentException.class, () -> { 
      item.setItemPrice(-50); 
    });
    assertThrows(IllegalArgumentException.class, () -> { 
      item.setItemAmount(-1); 
    });
    assertThrows(IllegalArgumentException.class, () -> { 
      item.setItemCategory(0); 
    });
  }
}
