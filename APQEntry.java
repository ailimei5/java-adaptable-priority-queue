// No use of Java built-in classes like Arrays, NoSuchElementException, etc.
// Modified APQ implementation for Eclipse (no java.util)

class APQEntry<K extends Comparable<K>, V> 
{
    K key;
    V value;

    public APQEntry(K key, V value) 
    {
        this.key = key;
        this.value = value;
    }

    public K getKey() 
    {
        return key;
    }

    public V getValue() 
    {
        return value;
    }

    public void setKey(K key) 
    {
        this.key = key;
    }

    public void setValue(V value) 
    {
        this.value = value;
    }

    public String toString() 
    {
        return "(" + key + ", " + value + ")";
    }
}