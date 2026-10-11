
public class APQ<K extends Comparable<K>, V> 
{
    private APQEntry<K, V>[] heap;
    private int size = 0;
    private boolean isMinHeap = true;
    private static final int DEFAULT_CAPACITY = 16;

    public APQ() 
    {
        heap = (APQEntry<K, V>[]) new APQEntry[DEFAULT_CAPACITY];
    }

    public String state() 
    {
        return isMinHeap ? "Min" : "Max";
    }

    public boolean isEmpty() 
    {
        return size == 0;
    }

    public int size() 
    {
        return size;
    }

    public void toggle() 
    {
        isMinHeap = !isMinHeap;
        rebuildHeap();
    }

    private void rebuildHeap() 
    {
        for (int i = parent(size - 1); i >= 0; i--) 
        {
            downHeap(i);
        }
    }

    public APQEntry<K, V> insert(K key, V value) 
    {
        ensureCapacity();
        APQEntry<K, V> entry = new APQEntry<K, V>(key, value);
        heap[size] = entry;
        upHeap(size);
        size++;
        return entry;
    }

    public APQEntry<K, V> top() 
    {
        if (isEmpty()) throw new RuntimeException("Heap is empty");
        return heap[0];
    }

    public APQEntry<K, V> removeTop() 
    {
        if (isEmpty()) throw new RuntimeException("Heap is empty");
        APQEntry<K, V> removed = heap[0];
        heap[0] = heap[size - 1];
        heap[size - 1] = null;
        size--;
        downHeap(0);
        return removed;
    }

    public APQEntry<K, V> remove(APQEntry<K, V> entry) 
    {
        int index = indexOf(entry);
        if (index == -1) throw new RuntimeException("Entry not found");
        APQEntry<K, V> removed = heap[index];
        heap[index] = heap[size - 1];
        heap[size - 1] = null;
        size--;
        downHeap(index);
        upHeap(index);
        return removed;
    }

    public K replaceKey(APQEntry<K, V> entry, K newKey) 
    {
        int index = indexOf(entry);
        if (index == -1) throw new RuntimeException("Entry not found");
        K oldKey = entry.getKey();
        entry.setKey(newKey);
        downHeap(index);
        upHeap(index);
        return oldKey;
    }

    public V replaceValue(APQEntry<K, V> entry, V newValue) 
    {
        int index = indexOf(entry);
        if (index == -1) throw new RuntimeException("Entry not found");
        V oldValue = entry.getValue();
        entry.setValue(newValue);
        return oldValue;
    }

    public APQEntry<K, V> peekAt(int n) 
    {
        if (n < 1 || n > size) throw new RuntimeException("Index out of bounds");

        // Selection sort-style temporary copy and sort
        APQEntry<K, V>[] copy = (APQEntry<K, V>[]) new APQEntry[size];
        for (int i = 0; i < size; i++) 
        {
            copy[i] = heap[i];
        }

        for (int i = 0; i < size - 1; i++) 
        {
            int minIdx = i;
            for (int j = i + 1; j < size; j++) 
            {
                int cmp = isMinHeap ? copy[j].getKey().compareTo(copy[minIdx].getKey())
                        : copy[minIdx].getKey().compareTo(copy[j].getKey());
                if (cmp < 0) 
                {
                    minIdx = j;
                }
            }
            if (minIdx != i) 
            {
                APQEntry<K, V> temp = copy[i];
                copy[i] = copy[minIdx];
                copy[minIdx] = temp;
            }
        }
        return copy[n - 1];
    }

    public void merge(APQ<K, V> other) 
    {
        for (int i = 0; i < other.size; i++) 
        {
            this.insert(other.heap[i].getKey(), other.heap[i].getValue());
        }
    }

    private void ensureCapacity() 
    {
        if (size >= heap.length) 
        {
            APQEntry<K, V>[] newHeap = (APQEntry<K, V>[]) new APQEntry[heap.length * 2];
            for (int i = 0; i < heap.length; i++) 
            {
                newHeap[i] = heap[i];
            }
            heap = newHeap;
        }
    }

    private int indexOf(APQEntry<K, V> entry) 
    {
        for (int i = 0; i < size; i++) 
        {
            if (heap[i] == entry) return i;
        }
        return -1;
    }

    private void upHeap(int i) 
    {
        while (i > 0) 
        {
            int p = parent(i);
            if (compare(heap[i], heap[p]) >= 0) break;
            swap(i, p);
            i = p;
        }
    }

    private void downHeap(int i) 
    {
        while (left(i) < size) 
        {
            int child = left(i);
            int right = right(i);
            if (right < size && compare(heap[right], heap[child]) < 0) 
            {
                child = right;
            }
            if (compare(heap[child], heap[i]) >= 0) break;
            swap(i, child);
            i = child;
        }
    }

    private int compare(APQEntry<K, V> a, APQEntry<K, V> b) 
    {
        return isMinHeap ? a.getKey().compareTo(b.getKey()) : b.getKey().compareTo(a.getKey());
    }

    private void swap(int i, int j) 
    {
        APQEntry<K, V> temp = heap[i];
        heap[i] = heap[j];
        heap[j] = temp;
    }

    private int parent(int i) { return (i - 1) / 2; }
    private int left(int i) { return 2 * i + 1; }
    private int right(int i) { return 2 * i + 2; }
}

