package no.ntnu.candidate10034;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.Test;

/**
 * JUnit-test class for testing of the Warehoude class.
 *
 * @since       2022-12-02
 * @version     2022-12-02
 * @author      Candidate 10034
 */
public class WarehouseTest {
  /**
   * Create an empty instance of the Warehouse class.
   *
   * @return new Warehouse-object.
   */
  private Warehouse createNewWarehouse() {
    return new Warehouse();
  }


  /**
   * Receive a predefined Item-object to add to the register.
   *
   * @return Item-object for testing.
   */
  private Item getSampleItem() {
    return new Item(
    "DumbleDoor", "Skeidar", "grey",
    "Magical door", 95,
    150.0, 200.0, 15000,
    3, 3
    );
  }


  /**
   * Warehouse is created successfully.
   */
  @Test
  public void testCreationOfWarehouse() {
    assertNotNull(createNewWarehouse());
  }


  /**
   * Adding new item to the warehouse is successful.
   */
  @Test  
  public void testAddingNewItemToWarehouse_positive() {
    Warehouse warehouse = createNewWarehouse();
    Item item = getSampleItem();

    warehouse.addItem(item, item.getItemNumber(), item.getItemDescription());

    assertEquals(1, warehouse.getItems().size());
    assertEquals(true, warehouse.getItems().contains(item));
  }


  /**
   * Adding duplicate item to the warehouse does not get added.
   */
  @Test 
  public void testAddingNewItemToWarehouse_negative() {
    Warehouse warehouse = createNewWarehouse();
    Item item = getSampleItem();

    warehouse.addItem(item, item.getItemNumber(), item.getItemDescription());
    warehouse.addItem(item, item.getItemNumber(), item.getItemDescription());

    assertEquals(1, warehouse.getItems().size());
  }

  /**
   * Deleting existing Item is possible.
   */
  @Test
  public void testDeleteExistingItem() {
    Warehouse warehouse = createNewWarehouse();
    Item item = getSampleItem();

    warehouse.addItem(item, item.getItemNumber(), item.getItemDescription());
    assertEquals(1, warehouse.getItems().size());

    warehouse.deleteCurrentItem(item, item.getItemNumber(), item.getItemDescription());
    assertEquals(0, warehouse.getItems().size());
  }

  /**
   * Deleting non-existing item should not alter the registry.
   */
  @Test
  public void testDeleteNonExistingItem() {
    Warehouse warehouse = createNewWarehouse();
    Item item = getSampleItem();

    warehouse.addItem(item, item.getItemNumber(), item.getItemDescription());
    assertEquals(1, warehouse.getItems().size());

    warehouse.deleteCurrentItem(item, "other number", "other description");
    assertEquals(1, warehouse.getItems().size());
  }

  /**
   * Searching for existing item should be possible.
   */
  @Test 
  public void testSingleSearch_positive() {
    Warehouse warehouse = createNewWarehouse();
    Item item = getSampleItem();

    warehouse.addItem(item, item.getItemNumber(), item.getItemDescription());

    assertEquals(item, warehouse.search(item.getItemNumber()));
    assertEquals(item, warehouse.search(item.getItemDescription()));
  }

  /**
   * Searching for non-existent item should return null.
   */
  @Test 
  public void testSingleSearch_negatice() {
    Warehouse warehouse = createNewWarehouse();
    Item item = getSampleItem();

    warehouse.addItem(item, item.getItemNumber(), item.getItemDescription());

    assertEquals(null, warehouse.search("other number"));
    assertEquals(null, warehouse.search("other description"));
  }


  /**
   * Searching for existing item should be possible.
   */
  @Test 
  public void testDoubleSearch_positive() {
    Warehouse warehouse = createNewWarehouse();
    Item item = getSampleItem();

    warehouse.addItem(item, item.getItemNumber(), item.getItemDescription());

    assertEquals(item, warehouse.search(item.getItemDescription(), item.getItemNumber()));
  }


  /**
   * Searching for non-existent item should return null.
   */
  @Test 
  public void testDoubleSearch_negative() {
    Warehouse warehouse = createNewWarehouse();
    Item item = getSampleItem();

    warehouse.addItem(item, item.getItemNumber(), item.getItemDescription());

    assertEquals(null, warehouse.search(item.getItemDescription(), "test"));
    assertEquals(null, warehouse.search("test", item.getItemNumber()));
  }
}