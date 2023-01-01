package Resources;

public class Node {

    Node next;

    Object nodeData;

    public Node(Object nodeData) {
        next = null;
        this.nodeData = nodeData;
    }

    public Node getNext() {
        return next;
    }

    public void setNext(Node next) {
        this.next = next;
    }

    public Object getNodeData() {
        return nodeData;
    }

    public void setNodeData(Object nodeData) {
        this.nodeData = nodeData;
    }
}
