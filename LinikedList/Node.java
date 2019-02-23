//Node class

public class Node { 

    int data;
    Node next;
    Node() {
        this(0, null);
    }

    Node(int m, Node s) {
        setData(m);
        next = s;
    }

    void setData(int m) {
        if (m > -1 && m < 101) {
            data = m;
        }
    }

    int getData() {
        return data;
    }

}//end of Node class
///////////////////////////////////////////////////
