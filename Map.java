package assignment9;

public class Map< T extends Comparable<T>, E>
{
    //Binary Search Tree to store the key value pairs
    private BinarySearchTree<Map_Pair<T,E>> bst = null;

    //Pair class to store the key value pairs
    //Implements Comparable interface to compare the keys
    public class Map_Pair<T extends Comparable<T>,E> implements Comparable<Map_Pair<T,E>>
    {
        //Key and value of the pair
        private T key;
        private E value;

        //Constructor for the pair
        public Map_Pair(T key, E value)
        {
            this.key = key;
            this.value = value;
        }
        //Getters for the key and value
        public T getKey()
        {
            return key;
        }
        public E getValue()
        {
            return value;
        }
        //Compare the keys of the pairs
        @Override
        public int compareTo(Map_Pair<T,E> other)
        {
            if (other == null) {return 1;}
            if (this==other || (this.key == null && other.key == null)) {return 0;}
            if (this.key == null) {return -1;}
            if (other.key == null) {return 1;}
            return this.key.compareTo(other.key);
        }
    }

    //Constructor for the map
    public Map()
    {
    bst = new BinarySearchTree<>();
    }

    //Add a key value pair to the map
    //using the add method of the BST
    public boolean add(T key, E value)
    {
        if(key==null || value==null)
        {
            return false;
        }
        Map_Pair<T , E> pair = new Map_Pair<>(key, value);
        return bst.add(pair);
    }

    //get the value of a key
    //using the get method of the BST
    public E get(T key) {
        //Create a pair with the key and null value
        Map_Pair<T,E> pair = bst.get(new Map_Pair<>(key, null));
        if(pair==null)
        {
            return null;
        }
        return pair.value;
    }

    //get the size of the map
    //using the size method of the BST
    public int size()
    {
        return bst.size();
    }

    //check if the map contains a key
    //using the contains method of the BST
    public boolean contains(T key)
    {
        return bst.contains(new Map_Pair<>(key, null));
    }

}

