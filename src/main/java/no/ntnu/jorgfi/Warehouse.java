package no.ntnu.jorgfi;

import java.util.LinkedHashMap;
import java.util.LinkedHashSet;

/** 
 * Represents an Item registry for the Warehouse.
 *
 * Instances of the Item class is stored in a 
 * LinkedHashMap, making it possible to access
 * and modify entries, as well as creating new
 * instances and delete them.
 * 
 * The registry is accessible through the UI, and
 * the class is implemented in such a way that it
 * is the only class which has direct access to the
 * Item class, which satisfies the requirements of
 * loose coupling.
 * 
 * @since       2022-10-12
 * @version     2022-12-08
 * @author      Candidate 10034
*/
public class Warehouse {
    
    /** Contains Item-instances identifyed by both ItemNumber and ItemDescription. */
    private LinkedHashMap<String, Item> items;
    /** Container for the item which is currently being created or searched for. */
    private Item currentItem;


    /**
     * Creates a new, empty instance of the Warehouse class.
     * 
     * @return new warehouse.
     */
    public Warehouse() {
        this.items = new LinkedHashMap<>();
        this.currentItem = null;
    }    

    /**
     * Get all Item instances associated with this warehouse
     * as an array without duplicates.
     * 
     * Since there are two entries for each Item instance in
     * the LinkedHashMap identified by both ItemNumber as well
     * as ItemDescription, this method will return all the values
     * of the LinkedHashMap as a LinkedHashSet. This will ensure
     * that all duplicates of the Item instances are removed
     * before the Items are returned.
     * 
     * @return LinkedHashSet<Item> of all Item instances without
     *          duplicates.
     */
    public LinkedHashSet<Item> getItems() {
        LinkedHashSet<Item> set =  new LinkedHashSet<Item>();
        set.addAll(items.values());
        return set;
    }


    /**
     * Access the Item which is currently stored in
     * the <code>currentItem</code> variable.
     * 
     * @return current Item instance.
     */
    public Item getCurrentItem() {
        return this.currentItem;
    }



    /**
     * Add an instance of the Item class into the
     * warehouse registry.
     * 
     * <p>Puts the Item into the LinkedHashMap as two
     * keysets, where the first key is {@param itemNumber},
     * and the second is {@param itemDescription}, making
     * it possible to search for an item by either 
     * the itemNumber or the itemDescription or both.
     * 
     * <p>The new item cannot be null or already present.
     * 
     * @param newItem the Item to insert. Cannot be null.
     * @return boolean indicating whether the insertion was
     *      succesfull or not.
     */
    public boolean addItem(Item newItem) {
        String number = newItem.getItemNumber();
        String description = newItem.getItemDescription();

        boolean success = false;

        if (!items.containsKey(number) && !items.containsKey(description)) {
            items.put(newItem.getItemNumber(), newItem);
            items.put(newItem.getItemDescription(), newItem);
            success = true;
        } 
        return success;
    }


    /**
     * Search for an Item in the warehouse registry
     * by a specific searchword and assign it to the
     * <code>currentItem</code> variable.
     * 
     * <p>Since each Item is stored in the LinkedHashMap
     * by both ItemNumber and ItemDescription, this method
     * is capable of finding an Item without considering
     * whether the user intends to search by ItemNumber
     * or ItemDescription.
     * 
     * @param searchWord the string which are used to search
     *      for, representing either an ItemNumber or an
     *      ItemDescription.
     */
    public void search(String searchWord) { 
        currentItem = items.get(searchWord);
    }


    /**
     * Search for an Item in the warehouse registry
     * by several search words and assign it to the
     * <code>currentItem</code> variable.
     * 
     * <p>This method accepts two parameters which will
     * represent the ItemNumber and the ItemDescription
     * of the item to be searched for, allowing the user
     * to search for an item by those two properties.
     * The order of the parameters passed int the method
     * call does not matter, since the search method is
     * unaware whether a searchword represents an ItemNumber
     * or an ItemDescription.
     * 
     * <p>Will only set <code>currentItem</code> to a found
     * Item if the separate searches result in the same
     * Item. If this is not the case, <code>currentItem</code>
     * will be set to <i>null</i>.
     * 
     * @param searchWord1 the first string which are to
     *      be used in the search. Expected to be either
     *      an ItemNumber or an ItemDescription.
     * @param searchWord2 the second string which are to
     *      be used in the search. Expected to be the remaining
     *      property, i.e. if the first search word represents
     *      an ItemNumber, this is expected to represent the
     *      ItemDescription, and vice versa.
     */
    public void search(String searchWord1, String searchWord2) {
        Item item1 = items.get(searchWord1);
        Item item2 = items.get(searchWord2);

        currentItem = (item1.equals(item2) ? item1 : null);
    }

    /**
     * Delete the Item which is stored in the variable
     * <code>currentItem</code> from the LinkedHashMap.
     * 
     * <p>Both keysets associated with the Item in question
     * will be removed, indicating that this Item no longer
     * is part of the inventory of the warehouse. This method
     * will not try to remove an Item from the LinkedHashMap
     * if the currentItem is null. 
     */
    public void deleteCurrentItem() {
        if (currentItem != null) {
            items.remove(currentItem.getItemNumber());
            items.remove(currentItem.getItemDescription());
        }
    }

    /**
     * Mutate <code>currentItem.itemAmount</code> which  
     * indicates that the amount of products of the Item 
     * which are currently being accessed by the user is 
     * either reduced or increased.
     * 
     * <p>The current Item must not be null for this method
     * to perform its operations.
     * 
     * @param amount the new amount of products related
     *      to the current Item instance.
     */
    public void alterCurrentItemAmount(int amount) {
        if (currentItem != null) {
            currentItem.setItemAmount(amount);
        }
    }

    /**
     * Set a new price for the Item which are currently
     * being inspected.
     * 
     * <p>The new price must be a non-negative integer,
     * and the current Item must not be null.
     * 
     * @param price the new price for a product associated
     *      with this Item instance.
     */
    public void alterCurrentItemPrice(int price) {
        if (currentItem != null) {
            currentItem.setItemPrice(price);
        }
    }

    /**
     * Change the description of the Item which are currently
     * being inspected.
     * 
     * <p>The key which is identifying the Item by its description
     * in the LinkedHashMap will be changed when performing this
     * action. This ensures that it is possible to search for the
     * Item by the new description later on.
     * 
     * <p>The description must be a non-empty String, and the
     * current Item must not be null.
     * 
     * @param description the new description of the current Item.
     */
    public void alterCurrentItemDescription(String description) {
        if (currentItem != null) {
            String previousDescription = currentItem.getItemDescription();
            currentItem.setItemDescription(description);
            this.items.remove(previousDescription);
            this.items.put(description, currentItem);
        }
    }

    /**
     * Add a discount to the current price of the Item instance
     * which are currently being accessed.
     * 
     * <p>Will convert a percentage represented by a positive
     * integer between 0 and 100 into a new price for the
     * current Item. The current Item cannot be null.
     * 
     * @param discount an integer representing a percentage which
     *      will symbolize the reduction of the Item's current
     *      price.
     */
    public void setDiscount(int discount) {
        if (currentItem != null) {
            currentItem.setItemPrice(currentItem.getItemPrice() * (1 - (discount/100)));
        }
    }

    /**
     * Populate the register of this instance of the Warehouse class
     * with four default items.
     * 
     * <p>Adds four instances of the Item class to the LinkedHashMap.
     * This method can be called upon starting the application, and
     * will not be available after setting up the warehouse.
     * 
     * <p>The last of the Items created will be set as the current Item.
     */
    public void fillInventory() {
        Item item1 = new Item(
            "Floor 2.0", "Jysk", "brown",
            "Futuristic floor", 3,
            188.0, 2.0, 100,
            25, 1
        );
        Item item2 = new Item(
            "DumbleDoor", "Skeidar", "grey",
            "Magical door", 95,
            150.0, 200.0, 15000,
            3, 3
        );
        Item item3 = new Item(
            "Seamless", "Home Decor", "transparent",
            "Simplistic window", 20,
            100.0, 100.0, 2350,
            12, 2
        );
        Item item4 = new Item(
            "To-tom-fir-tom", "Monter", "white",
            "Classic Norwegian go-to lumber", 12,
            200.0, 5.08, 80,
            100, 4
        );

        addItem(item1);
        addItem(item2);
        addItem(item3);
        addItem(item4);
        
        currentItem = items.get("To-tom-fir-tom");
    }
}