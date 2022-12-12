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


    /**
     * Creates a new, empty instance of the Warehouse class.
     * 
     * @return new warehouse.
     */
    public Warehouse() {
        this.items = new LinkedHashMap<>();
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
    public boolean addItem(Item newItem, String number, String description) {
        boolean success = false;

        number = number.toLowerCase();
        description = description.toLowerCase();

        if (!items.containsKey(number) && !items.containsKey(description)) {
            items.put(number, newItem);
            items.put(description, newItem);
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
    public Item search(String searchWord) { 
        return items.get(searchWord.toLowerCase());
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
    public Item search(String searchWord1, String searchWord2) {
        Item item1 = items.get(searchWord1.toLowerCase());
        Item item2 = items.get(searchWord2.toLowerCase());
        Item found;

        if (item1 == null || item2 == null) {
            found = null;
        } else {
            found = (item1.equals(item2) ? item1 : null);
        }
        return found;
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
    public void deleteCurrentItem(Item item, String number, String description) {
        if (item != null) {
            items.remove(number.toLowerCase());
            items.remove(description.toLowerCase());
        }
    }
}