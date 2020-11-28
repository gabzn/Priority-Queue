public class PriorityQueue<K extends Comparable<K>>
{
    private K minHeap[];
    private int capacity;
    private int size;

    public PriorityQueue()
    {
        capacity = 100;
        size = 0;
        minHeap = (K[]) new Comparable[capacity];
    }

    public PriorityQueue(int initial_capacity)
    {
        capacity = initial_capacity;
        size = 0;
        minHeap = (K[]) new Comparable[capacity];
    }

    public void add(K x) throws Exception
    {
        if(size >= capacity) throw new Exception("Queue is full");
        minHeap[size++] = x;
        bubbleUp(size-1);
    }

    private void bubbleUp(int childIndex)
    {
        if(childIndex == 0) return; //When it gets to 0, it means that it's at the root.

        //    parent index number = (child index number - 1) / 2
        int parentIndex = (childIndex-1) / 2;

        K currentNode = minHeap[childIndex];
        K parentNode = minHeap[parentIndex];


        //If child has greater value than parent, it means it has lower priority.
        if(currentNode.compareTo(parentNode) >= 0) return;


        //If child has higher priority, swap it with its parent.
        //Then recurse itself to check if it needs to bubble up.
        swap(childIndex,parentIndex);
        bubbleUp(parentIndex);
    }

    public K removeMin() throws Exception
    {
        if(size == 0) throw new Exception("Queue is empty");
        swap(0,--size);
        bubbleDown(0);
        return minHeap[size];
    }

    private void bubbleDown(int parentIndex)
    {
        if(parentIndex >= size) return;

        //   left child index = 2*parent index + 1
        //   right child index = 2*parent index + 2
        int leftChildIndex = 2*parentIndex + 1;
        int rightChildIndex = 2*parentIndex + 2;

        K currentNode = minHeap[parentIndex];
        K leftChildNode = minHeap[leftChildIndex];
        K rightChildNode = null;


        //Check if the right child exists. If it does, assign the correct value to it.
        //If it doesn't, let it have the same value as left child.
        if(rightChildIndex < size)      rightChildNode = minHeap[rightChildIndex];
        else                            rightChildNode = leftChildNode;


        //If current has lower value, it means it has higher priority than its children.
        if(currentNode.compareTo(leftChildNode) < 0 && currentNode.compareTo(rightChildNode) < 0) return;
        
        
        if(rightChildNode.compareTo(leftChildNode) < 0)
        {
            swap(parentIndex,rightChildIndex);
            bubbleDown(rightChildIndex);
        }
        else
        {
            swap(parentIndex,leftChildIndex);
            bubbleDown(leftChildIndex);
        }
    }

    private void swap(int x,int y)
    {
        K temp = minHeap[y];
        minHeap[y] = minHeap[x];
        minHeap[x] = temp;
    }
}
