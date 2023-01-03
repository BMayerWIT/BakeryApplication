package Resources;

public class LinkedList<W> {
    private Node head = null;
    private int nodeCount = 1;


    public LinkedList() {}

    public void add(Object obj) {
        if (head == null) {
            head = new Node(obj);
        } else {
            Node newNode = new Node(obj);
            Node current = head;

            if (current != null) {
                while (current.getNext() != null) {
                    current = current.getNext();
                }
                current.setNext(newNode);
                nodeCount++;
            }
        }
    }

    public void remove() {
        if (head != null) {
            head = null;
        }
    }

    public void deleteNode(int index) {
        if (head != null) {
            if (index == 0) {
                if (head.getNext() == null) {
                    head = null;
                }
                else {
                    head = head.getNext();
                }
            }
            index--;
            Node selectedNode = head;
            for (int i = 0; i < index; i++) {
                selectedNode = selectedNode.getNext();
            }
            Node point = selectedNode.getNext().getNext();
            if (point != null) {
                selectedNode.setNext(point);
            }
            else {
                selectedNode.setNext(null);
            }
        }
    }

    public Object get(int index) {
        if (index == 0)
            return head.getNodeData();
        Node current = null;
        if (head != null) {
            current = head.getNext();
            for (int i = 1; i < index; i++) {
                if (current.getNext() == null)
                    return null;
                current = current.getNext();
            }
            return current.getNodeData();
        }
        else {
            return null;
        }
    }

    public int numberOfNodes() {
        if (head == null) {
            return 0;
        }
        else
        return nodeCount;
    }

    public boolean search(String name) {
        Node current = head;
        while (current != null) {
            if (current.nodeData.getClass().getName().equals(name)) {
                return true;
            }
            current = current.next;
        }
        return false;
    }
}

