//MyLinkedList Class
        
public class LinkedList {

    Node head;
    Node tail;//optional if you want to use (using it is helpful to reach last element directly)

    LinkedList() {
        head = null;
        tail = null;
    }
    
    //insert at after any postion
    boolean insertAt(int v, int p){
        Node current  = head;                    //current node (use in loops)
        Node target = search(Data(p));  //(using short trick to get target) - target node  (use to reach the target)
        /*
            if list doesn't exist,
            if the node number entered doesn't exist
            (the method will return false)
        
            if there is only one element or the insertion is to happen after the last element in the 
            list the method will call the method insertAtLast()
        */
        if(head == null && tail == null || p <= 0 || Data(p) == -1){
            return false;
        }else if(head == tail || target.equals(tail)){ 
            insertAtEnd(v);     //calling other mehtod
            return true;
        }else{
            while(current != null){
                if(current == target.next){
                    Node n = new Node();
                    n.setData(v);
                    n.next = current;
                    target.next = n;
                    return true;
                }
                current = current.next;
            }
        }
        return false;
    }

    //isertion at start
    boolean insertAtStart(int v) {
        //using head node logic
        try {
            Node n = new Node();                        // declering new nodes
            n.setData(v);
            if(head == null && tail == null){           // iff head null
                head = n;                       // inserting address of first node to head
                tail = n;
            }else if(head != null && tail != null ){     // iff head not null
                n.next = head;                  // inserting address of ex-first node to new first node 
                head = n;                       // inserting address of first node to head
            }
            /*
                no need for the condition (head == tail) in this method
            */
            return true;
        } catch (java.lang.OutOfMemoryError e) {
            return false;
        }
    }
    
    //insertion at end
    boolean insertAtEnd(int v) {
        // using tail node logic (benefit): no loop required 
        try {
            Node n = new Node();
            n.setData(v);
            if(head == null && tail == null){           // iff head null
                head = n;                       // inserting address of first node to head
                tail = n;
            }else if(head != null && tail != null ){     // iff head not null
                tail.next = n;                  // inserting new reference to ex last node to the new last node
                tail = n;                       // inserting reference to last node to tail
            }
            return true;
        } catch (java.lang.OutOfMemoryError e) {
            return false;
        }
    }
    
    //sorting (not being used)
    void sort() {
        //leave it empty , will be discussed later
    }
    
    //method for printing
    void printMe() {
        if(head == null && tail == null){  //print if list is empty: "List is empty"
            System.out.print("\nList is empty\n");
        }else{                             //else print all array elements
            Node current;
            current = head;
            System.out.print("\n");
            while(current != null){      // loop till the end
                System.out.printf("%d\t",current.getData());
                current = current.next;  // loop increment 
            }
        }
    }

    // search method
    Node search(int v) {
        //search the value and if found then return the reference at which the value "v" is present
        Node current = head;
        while(current != null){
            if(current.data == v){
                return current;
            }
            current = current.next;  // loop incremnet
        }
        return null;  //return null if list empty / not found
    }

    // delete at start method
    boolean deleteAtStart() {
        // using head node logic
        if(head == null && tail == null){  //if list is em1pty then return false
            return false;
        }else if(head == tail){   //if not then delete the node at start and return true
            head = null;
            tail = null;
            return true;
        }else if(head != null && tail != null){
            head = head.next;
            return true;
        }
        return false;
    }
    
    // delete by value method
    boolean deleteValue(int v) {
        if(head == null){
            return false;
        }else{
            if(search(v) == null){
                return false;
            }else{
                Node target = search(v);  // setting target
                Node current = head;
                while(current != null){
                    if(current.next == target){
                        current.next = target.next;
                    }
                    current = current.next;
                }
            }    
            return true;
        }
    }

    //delete at the end
    boolean deleteAtEnd() {
        if(head == null && tail == null){  //if list is em1pty then return false
            return false;
        }else if(head == tail){   //if not then delete the node at start and return true
            head = null;
            tail = null;
            return true;
        }else if(head != null && tail != null){
            Node current = head; 
            while(current != null){     // using a loop to get at the position
                if(current.next == tail){
                    current.next = null;
                    tail = current;
                }
                current = current.next;  //loop increment 
            }
            return true;
        }
        return false;
    }
    
    //method to fetch data
    int Data(int number) {
        if(head == null){ //if list empty then return -1
            return -1;
        }else{
            Node current = head;
            int count = 1;              // counter starting at 1
            while (current != null){    //loop
                if(count == number)     // findng node
                    return current.getData();
                current = current.next; // loop increment
                count++;                // counter increment
            }
        }
        return -1;  //if the position doesn't exist
    }
    
    //method to delete at a certain 
    boolean deleteAtPosition(int v) {
        Node current  = head;
        Node target = search(Data(v));
        /*
            if list doesn't exist,
            if the node number entered doesn't exist
            (the method will return false)
        
            if there is only one element in the list the method will call the 
            method insert at last
        */
        if(head == null && tail == null || v <= 0 || Data(v) == -1){
            return false;
        }else if(head == tail || target.equals(tail)){
            deleteAtEnd();
            return true;
        }else{
            while(current != null){
                if(current.next == target){
                    current.next = target.next;
                    return true;
                }else if(current == target){
                    deleteAtStart();
                    return true;
                }
                current = current.next;
            }
        }
        return false;  // return false if list is empty
    }   
}
//end of Linked list class

