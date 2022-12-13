package no.ntnu.candidate10034;

/**
 * Represents an item in a warehouse, containing
 * general product information, pricing and the
 * amount stored units.
 *
 * </p>Considered an entity-class, with the purpose 
 * of storing entity-data. Methods available are 
 * therefore mainly acessors and mutators. 
 *
 * @since       2022-10-04
 * @version     2022-10-05
 * @author      Candidate 10034
 */
public class Item {

  /*
  * CLASS FIELDS
  * ----------------------------------------------------------------
  */

  /** Identifier of the item. */
  private String  itemNumber;
  /** Delivering brand. */
  private String  itemBrand;
  /** Color of the item. */
  private String  itemColor;
  /** Short description. */
  private String  itemDescription;
  /** Weight in kilograms. */
  private double  itemWeight;
  /** Length in meters. */
  private double  itemLength;
  /** Height in meters. */
  private double  itemHeight;
  /** Price of the item. */
  private int     itemPrice;
  /** Amount of this item in storage. */
  private int     itemAmount;
  /** Item-category identifier. */
  private int     itemCategory;

  /** Category of Item-instance. */
  private static final String[] CATEGORY = new String[] {
      "floor laminate", "window", "door", "lumber"
  };



  /*
  * CONSTRUCTORS
  * ----------------------------------------------------------------
  */

  /**
   * Creates an instance of <code>Item.class</code>,
   * with all object-fields assigned.
   *
   * @param itemNumber a unique identifier for the item. Cannot be null.
   * @param itemBrand the brand which the item belongs to. Cannot be null.
   * @param itemColor color of the item. Cannot be null.
   * @param itemDescription short item description. Cannot be null.
   * @param itemWeight weight of the item in kilograms. Cannot be negative.
   * @param itemLength item length in meters. Cannot be negative.
   * @param itemHeight item height in meters. Cannot be negative.
   * @param itemPrice sales price for the item. Cannot be negative.
   * @param itemAmount number of the item in storage. Cannot be negative.
   * @param itemCategory category of the item. Int must be in range 1 to 4.
   */
  public Item(
      final String itemNumber, final String itemBrand,
      final String itemColor,  final String itemDescription,
      final double itemWeight, final double itemLength,
      final double itemHeight, final int itemPrice,
      final int    itemAmount, final int itemCategory
  ) {
    setItemNumber(itemNumber);
    setItemBrand(itemBrand);
    setItemColor(itemColor);
    setItemDescription(itemDescription);
    setItemWeight(itemWeight);
    setItemLength(itemLength);
    setItemHeight(itemHeight);
    setItemPrice(itemPrice);
    setItemAmount(itemAmount);
    setItemCategory(itemCategory);
  }



  /*
  * ACCESSOR METHODS
  * ----------------------------------------------------------------
  */

  /**
   * Get itemNumber of the item.
   *
   * @return String itemNumber.
   */
  public String getItemNumber() {
    return itemNumber;
  }


  /**
   * Access the brand behind the item.
   *
   * @return String itemBrand.
   */
  public String getItemBrand() {
    return itemBrand;
  }


  /**
   * Get the color of the item
   * as text.
   *
   * @return String itemColor.
   */
  public String getItemColor() {
    return itemColor;
  }


  /**
   * Get a short item description.
   *
   * @return String description.
   */
  public String getItemDescription() {
    return itemDescription;
  }


  /**
   * Access the item-weight.
   *
   * @return double weight in kg.
   */
  public double getItemWeight() {
    return itemWeight;
  }


  /**
   * Get the item-length.
   *
   * @return double length in meters.
   */
  public double getItemLength() {
    return itemLength;
  }


  /**
   * Get the item-height. 
   *
   * @return double height in meters.
   */
  public double getItemHeight() {
    return itemHeight;
  }


  /**
   * Receive item price.
   *
   * @return int price.
   */
  public int getItemPrice() {
    return itemPrice;
  }


  /**
   * Get the number of this particular item in storage.
   *
   * @return int amount.
   */
  public int getItemAmount() {
    return itemAmount;
  }


  /**
   * Get the category of the item
   * as an integer.
   *
   * @return int category.
   */
  public int getItemCategoryAsInt() {
    return itemCategory;
  }

  /**
   * Get the category of the item
   * as a string.
   *
   * @return string category.
   */
  public String getItemCategoryAsString() {
    return CATEGORY[itemCategory];
  }

  
  /**
   * Get all field-values of the object
   * as String values. 
   *
   * @return new String[] { 
   *      articleNumber, articleBrand, 
   *      articleColor, articleDescription,
   *      articleWeight, articleLength,
   *      articleHeight, articlePrice,
   *      articleAmount, articleCategory
   *      } 
   */
  public String[] getAllFields() {
    return new String[] {
          this.getItemNumber(), this.getItemBrand(),
          this.getItemColor(), this.getItemDescription(),
          this.getItemWeight() + "", this.getItemLength() + "",
          this.getItemHeight() + "", this.getItemPrice() + "",
          this.getItemAmount() + "", 
          this.getItemCategoryAsString()
    };
  }

  /*
  * MUTATOR METHODS
  * ----------------------------------------------------------------
  */

  /**
   * Update the itemNumber of the item.
   * Must be a non-empty String.
   *
   * @param itemNumber new articleNumber.
   */
  public void setItemNumber(final String itemNumber) {
    validateField(itemNumber, "itemNumber");
    this.itemNumber = itemNumber;
  }


  /**
   * Set the brand which delivers the item.
   * Must be a non-empty String.
   *
   * @param itemBrand new brand.
   */
  public void setItemBrand(final String itemBrand) {
    validateField(itemBrand, "itemBrand");
    this.itemBrand = itemBrand;
  }


  /** 
   * Change the item color description.
   * Must be a non-empty String.
   *
   * @param itemColor new color.
  */
  public void setItemColor(final String itemColor) {
    validateField(itemColor, "itemColor");
    this.itemColor = itemColor;
  }


  /**
   * Update the items description.
   *
   * </p>Must be a short String no longer
   * than 100 characters.
   *
   * @param itemDescription new description
   */
  public void setItemDescription(final String itemDescription) {
    validateField(itemDescription, "itemDescription");
    this.itemDescription = itemDescription;
  }


  /**
   * Set the weight of the item as a double
   * value measured in kilograms.
   *
   * </p>The weight cannot be less than 0.
   *
   * @param itemWeight new weight.
   */
  public void setItemWeight(final double itemWeight) {
    validateField(itemWeight + "", "itemWeight");
    this.itemWeight = itemWeight;
  }


  /**
   * Assign the item-length as a double
   * value measured in meters.
   *
   * </p>The length cannot be less than 0.
   *
   * @param itemLength new length.
   */
  public void setItemLength(final double itemLength) {
    validateField(itemLength + "", "itemLength");
    this.itemLength = itemLength;
  }


  /**
   * Assign the item-height as a double
   * value measured in meters.
   *
   * </p>The height cannot be less than 0.
   *
   * @param itemHeight new height.
   */
  public void setItemHeight(final double itemHeight) {
    validateField(itemHeight + "", "itemHeight");
    this.itemHeight = itemHeight;
  }


  /**
   * Update the item pricing as an integer.
   *
   * </p>The price cannot be less than 0.
   *
   * @param itemPrice new price.
   */
  public void setItemPrice(final int itemPrice) {
    validateField(itemPrice + "", "itemPrice");
    this.itemPrice = itemPrice;
  }


  /**
   * Update the amount of items in store.
   * 
   * </p>The amount cannot be less than 0.
   *
   * @param itemAmount new amount.
   */
  public void setItemAmount(final int itemAmount) {
    validateField(itemAmount + "", "itemAmount");
    this.itemAmount = itemAmount;
  }

  /**
   * Set item category represented by an
   * integer.
   * 
   * </p>The category item must be in the 
   * range of [1, 4].
   *
   * @param itemCategory new item category.
   */
  public void setItemCategory(int itemCategory) {
    itemCategory--;
    validateField(itemCategory + "", "itemCategory");
    this.itemCategory = itemCategory;
  }



  /*
  * PARAMETER VALIDATORS
  * ----------------------------------------------------------------
  */

  /**
   * Validate a field value against based on a prefix.
   *
   * @param field the field value to validate.
   * @param prefix the prefix identifying the properties of the field.
   * @throws IllegalArgumentException 
   *      If field value is considered invalid according to its prefix.
   */
  private void validateField(String field, String prefix) {
    boolean valid = true;

    switch (prefix) {
      case "itemHeight":
      case "itemLength":
      case "itemWeight":
        if (Double.parseDouble(field) < 0) {
          valid = false;
        }
        break;
      case "itemPrice":
      case "itemAmount":
        if (Integer.parseInt(field) < 0) {
          valid = false;
        }
        break;
      case "itemCategory":
        int category = Integer.parseInt(field);
        if (category < 0 || category > 3) {
          valid = false;
        }
        break;
      default:
        if (field.isBlank()) {
          valid = false;
        }
        break;
    }

    if (!valid) {
      throw new IllegalArgumentException("Invalid input for " + prefix);
    }
  }



  /*
  * OVERRIDING METHODS
  * ----------------------------------------------------------------
  */


  // Overridden method already contains javadoc.
  @Override
  public final String toString() {
    return "Item [itemNumber=" + itemNumber + ", itemBrand=" 
            + itemBrand + ", itemPrice=" + itemPrice 
            + ", itemAmount=" + itemAmount + ", itemCategory=" 
            + itemCategory + "]";
  }
}