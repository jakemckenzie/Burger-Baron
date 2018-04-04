public class MyStack<Type> {
    
    private static class Node <Type> {
	
	private Type element;
	
	private Node <Type> next;

	public Node (Type type, Node <Type> node) {

	    element = type;

	    next = node;

	}

	public Type getElement () {

	    return element;
	    
	}

	public Node <Type> getNext () {

	    return next;
	    
	}

	public void setNext (Node <Type> node) {

	    next = node;
	    
	}

    }

    private Node <Type> head = null;

    private Node <Type> tail = null;

    private int size = 0;

    public MyStack () {};

    public int size () {

	return size;
	
    }

    public boolean isEmpty () {

	return size == 0;
	
    }

    public Type first () {

	if(isEmpty()) {
	    
	    return null;

	}

	return head.getElement();
	
    }

    public Type last () {

	if(isEmpty()) {

	    return null;
	    
	}

	return tail.getElement();
	
    }

    public void push (Type element) {

	head = new Node<Type>(element, head);

	if(size == 0) {

	    tail = head;
	    
	}

	size++;

    }
    public Type pop() {

	if(isEmpty()) {

	    return null;
	    
	}

	Type node = head.getElement();

	head = head.getNext();

	size--;

	if(size == 0) {

	    tail = null;
	    
	}

	return node;
    }

    public Type peek() {

	return head.getElement();
	
    }
    
    public String toString(){
	
        StringBuilder result = new StringBuilder();

        Node<Type> current = head;
	
        while (current != null) {
	    
            result.append(current.element);

	    current = current.next;
         
        }
	
        return result.toString();

    }

}
