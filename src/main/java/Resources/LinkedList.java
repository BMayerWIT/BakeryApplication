package Resources;


public class LinkedList<W> {
    private Node head = null;
    private int nodeCount = 1;


    public LinkedList() {}

    //adds new node to linked list
    public void add(Object obj) {
        if (head == null) { //if node is null, a new node is created and set to head
            head = new Node(obj);
        } else {
            Node newNode = new Node(obj); //if head is not null a new node variable is created but not set to head
            Node current = head;

            if (current != null) { //if head is not null (linked list is not empty)
                while (current.getNext() != null) { //whilst the next node is not null
                    current = current.getNext(); //sets current node to the next
                }
                current.setNext(newNode); //once at the end of the list, a new node is created
                nodeCount++; //counter of nodes increases
            }
        }
    }

    public void remove() {
        if (head != null) { //if there is any nodes in the linked list, head is set to null thus deleted all other nodes too
            head = null;
        }
    }

    public void deleteNode(int index) {
        if (head != null) { //if linked list is not empty
            if (index == 0) { //if first node is selected to delete
                if (head.getNext() == null) { //if there is no nodes after the head (only 1 in the linked list)
                    head = null; //head is set to null
                } else {
                    head = head.getNext(); //otherwise head is set to the next node (thus deleting the first)
                }
            }
            index--;
            Node selectedNode = head;
            if (selectedNode != null) { //if head is not null (linked list is not empty)
                for (int i = 0; i < index; i++) { //cycles through linked list until getting to 1 node before selected index to delete
                    selectedNode = selectedNode.getNext();
                }
                if (selectedNode.getNext() != null) { //if the node after selected node is not null
                    Node point = selectedNode.getNext().getNext(); //points to node in front of node you want to delete
                    if (point != null) { //if the node in front is not null
                        selectedNode.setNext(point); //sets the selected node to one in front thus orphaning the one you wish to delete and deleting it
                    } else {
                        selectedNode.setNext(null); //if the node after the one you want to delete is null, it sets the one you want to delete to null
                    }
                } else {
                    selectedNode.setNext(null);
                }
                nodeCount--; //removes one from total node count
            }
            else if (selectedNode == null) { //if head is null
                head = null;
            }
        }
    }

    public Object get(int index) {
        if (index == 0) { //if index points to first node
            return head.getNodeData(); //returns head
        }

        Node current = head; //sets current node variable to head (start of list)

        if (current != null) { //whilst list is not empty
            for (int i = 1; i < index; i++) { //cycles through until node before index

                if (current.getNext() == null) { //if the index you wish to get is empty, return null
                    return null;
                } else { //if index you wish to get is not empty, return it
                    current = current.getNext(); //return next as current is set to the one before the index you wish to get
                }
            }
            return current.getNodeData();
        }
        else {
            return null;
        }
    }

    public int numberOfNodes() {
        if (head == null) { //if its empty set to 0
            return 0;
        }
        else
        return nodeCount; //return global counter
    }

}

