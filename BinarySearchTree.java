package assignment9;

import java.util.EmptyStackException;
import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Stack;

public class BinarySearchTree<T extends Comparable<T>> implements Iterable<T>
{
    // Root of the tree
    private Node<T> root;

    // Constructor
    public BinarySearchTree() {
        this.root = null;
    }
    // Returns the number of elements in the tree
    //using an iterator
    public int size()
    {
        int size = 0 ;
        for(T curr : this)
        {
            size++;
        }
        return size;
    }
    // Returns the root of the tree
    public Node<T> getRoot()
    {
        return this.root;
    }
    // Returns true if the tree contains the value
    //using the get method
    public boolean contains(T value)
    {
        if(value==null)
            return false;
        if(get(value)==null)
            return false;
        return true;
    }
    // Returns the value if it is in the tree
    // Returns null if the value is not in the tree
    //using the iterator
    public T get(T value)
    {
        if(value==null)
            return null;
        for(T curr : this)
        {
            if(curr.compareTo(value)==0)
                return curr;
            if(curr.compareTo(value)>0)
                return null;
        }
        return null;
    }
    // Returns an iterator for the tree in-order traversal
    public Iterator<T> iterator()
    {
        return new InOrderIterator<T>(this.root);
    }
    // Adds a value to the tree
    //using the addRecursive method
    public boolean add(T value) {
        if (value == null)
            return false;
        if (root == null) {
            root = new Node<T>(value);
            return true;
        }
        else
        {
            return addRecursive(root, value);
        }
    }

    // Adds a value to the tree
    //using recursion calls
    private boolean addRecursive(Node<T> current, T value) {
        int cmp = value.compareTo(current.value);

        // if value is less than current node
        // go left
        if (cmp < 0)
        {
            if (current.left == null) {
                current.left = new Node<>(value);
                return true;
            } else {
                return addRecursive(current.left, value);
            }
        }
        // if value is greater than current node
        // go right
        else if (cmp > 0)
        {
            if (current.right == null) {
                current.right = new Node<>(value);
                return true;
            } else {
                return addRecursive(current.right, value);
            }
        }
        // if value is equal to current node
        // return false
        // value already exists
        else
        {
            return false;
        }
    }


    // Node class
    // Contains a value and references to the left and right child
    public static class Node<T>
    {
        private Node<T> right;
        private Node<T> left;
        private T value;
        // Constructor
        Node(T value) {
            this.value=value;
        }
        // Getters
        public Node<T> getRight() {
            return right;
        }
        public Node<T> getLeft() {
            return left;
        }
        public T getValue() {
            return value;
        }


    }

    // InOrderIterator class
    // Implements an iterator for the tree
    // Traverses the tree in order
    public static class InOrderIterator<T> implements Iterator<T>
    {
        // Stack to keep track of nodes already visited
        private Stack<Node<T>> stack;

        // Constructor
        public InOrderIterator(Node<T> root) {
            stack = new Stack<Node<T>>();
            pushLeft(root);
        }

        // Returns true if there are more elements to visit
        public boolean hasNext() {
            return !stack.isEmpty();
        }

        // Returns the next element in the tree
        public T next() {
            if(!hasNext())
                throw new NoSuchElementException();

            Node<T> root = stack.pop();
            pushLeft(root.right);
            return root.value;
        }

        // Pushes the left child of the root onto the stack
        private void pushLeft(Node<T> root) {
            while(root!=null) {
                stack.push(root);
                root = root.left;
            }
        }
    }
}
