package data;

/**
 * A class used for testing purposes.
 */
public class MyTestingClass {
    private int attribute1; // First attribute of the class
    private int attribute2; // Second attribute of the class

    /**
     * Constructor to create an instance of data.MyTestingClass with the given attributes.
     * @param attribute1 The first attribute.
     * @param attribute2 The second attribute.
     */
    public MyTestingClass(int attribute1, int attribute2) {
        this.attribute1 = attribute1;
        this.attribute2 = attribute2;
    }

    /**
     * Computes a custom hash code for this instance based on its attributes.
     * The hash code is calculated using a formula that combines the two attributes
     * with different prime numbers to reduce the likelihood of collisions.
     * This approach helps in creating a more uniformly distributed hash code.
     *
     * @return The hash code of this instance.
     */
    @Override
    public int hashCode() {
        int prime1 = 31; // First prime number used in hash calculation
        int prime2 = 37; // Second prime number used in hash calculation
        return prime1 * attribute1 + prime2 * attribute2;
    }

    /**
     * Compares this instance with another object for equality.
     * @param obj The object to be compared.
     * @return True if the given object is equal to this instance, false otherwise.
     */
    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true; // Check if both references are the same
        if (obj == null || getClass() != obj.getClass()) return false; // Check if obj is null or not of the same class
        MyTestingClass other = (MyTestingClass) obj; // Typecast obj to data.MyTestingClass
        return attribute1 == other.attribute1 && attribute2 == other.attribute2; // Compare attributes for equality
    }

    /**
     * Provides a string representation of this instance.
     * @return A string representation of the instance in the format "data.MyTestingClass {attribute1 = x, attribute2 = y}".
     */
    @Override
    public String toString() {
        return "data.MyTestingClass {" +
                "attribute1 = " + attribute1 +
                ", attribute2 = " + attribute2 +
                " }";
    }
}
