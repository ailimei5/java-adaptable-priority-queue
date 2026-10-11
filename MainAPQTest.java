
public class MainAPQTest 
{
    public static void main(String[] args) 
    {
        APQ<Integer, String> apq = new APQ<Integer, String>();

        System.out.println("Initial state: " + apq.state());

        // Insert elements
        APQEntry<Integer, String> e1 = apq.insert(5, "five");
        APQEntry<Integer, String> e2 = apq.insert(3, "three");
        APQEntry<Integer, String> e3 = apq.insert(8, "eight");
        APQEntry<Integer, String> e4 = apq.insert(1, "one");
        APQEntry<Integer, String> e5 = apq.insert(9, "nine");

        System.out.println("Top: " + apq.top());

        // Toggle and check state
        apq.toggle();
        System.out.println("Toggled state: " + apq.state());
        System.out.println("Top after toggle: " + apq.top());

        // Replace key and value
        apq.replaceKey(e3, 10);
        apq.replaceValue(e2, "THREE");
        System.out.println("Replaced at key: " + e3);
        System.out.println("Replaced at value: " + e2);

        // Peek at positions
        System.out.println("Peek at 1st: " + apq.peekAt(1));
        System.out.println("Peek at 3rd: " + apq.peekAt(3));

        // Remove specific entry
        System.out.println("Removed entry: " + apq.remove(e4));

        // Insert more to trigger resizing
        for (int i = 11; i <= 30; i++) 
        {
            apq.insert(i, "val" + i);
        }

        System.out.println("Heap size after more inserts: " + apq.size());

        // Remove top elements
        for (int i = 0; i < 3; i++) 
        {
            System.out.println("Remove top: " + apq.removeTop());
        }

        // Check heap state
        System.out.println("Current top: " + apq.top());
        System.out.println("Current size: " + apq.size());

        // Merge with another APQ
        APQ<Integer, String> another = new APQ<Integer, String>();
        another.insert(42, "forty-two");
        another.insert(37, "thirty-seven");
        another.insert(13, "thirteen");
        another.insert(0, "zero");
        System.out.println("Merging another APQ");
        apq.merge(another);

        System.out.println("Top after merge: " + apq.top());
        System.out.println("New size: " + apq.size());
        apq.toggle();
        System.out.println("Top after merge: " + apq.top());

        // Final peek tests
        System.out.println("Peek at 5th: " + apq.peekAt(5));
        System.out.println("Peek at 10th: " + apq.peekAt(10));

        // Emptying the APQ
        while (!apq.isEmpty()) 
        {
            System.out.println("Popping: " + apq.removeTop());
        }

        System.out.println("Heap empty? " + apq.isEmpty());
    }
}
