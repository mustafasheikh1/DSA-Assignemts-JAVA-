class CircularLinkedList
{
	Node head; //I don't recommend to use it for circular linked list
	Node tail;	//last node
	public CircularLinkedList(){
		head=null;
		tail=null; //head and tail is empty at start
	}
	//=======================================
	//postcondition: node will be inserted at end, tail must be the new node
	public boolean addEnd(Node node)
	{       
            if(tail == null){
               tail = node;
               node.setNext(node);
               return true;
            }else if (tail != null){
                Node temp = tail.getNext();                
                node.setNext(temp);
                tail.setNext(node);
                tail = node;
                return true;
            }
            return false;
	}
	//precondition: list must not be empty
	//postcondition:last node will be deleted and a reference to this node will be returned, tail would be updated
	//output: delete last node and return, null otherwise 
	public Node removeEnd()
	{
            if(tail == null){
                return null;
            }else if(tail != null){
                Node current = tail.getNext();
                Node temp = tail;
                do{
                    if(current.getNext().equals(tail)){
                        current.setNext(tail.getNext());
                        tail = current;
                    }
                    current = current.getNext();
                }while(tail.getNext() != current);
                
                return temp;
            }
            return null;
	}
	//postcondition: tail node must point to new node
	public boolean addStart(Node n)
        {
            if(tail == null){
               tail = n;
               n.setNext(n);
               return true;
            }else{
                Node temp = tail.getNext();
                tail.setNext(n);
                n.setNext(temp);
                return true;
            }
	}
	//precondition: list must not be empty
	//postcondition: tail must point to next to first node (not the node that was first node previously)
	public Node removeStart()
	{
            if(tail == null){
                return null;
            }else if(tail != null){
                Node temp = tail.getNext();
                Node current = tail.getNext();
                tail.setNext(current.getNext());
                return temp;
            }
            return null;
	}
	
	//precondition: list must not be empty
	//postcondition:node's data must be updated
	public Node search(int data)
	{   
            if(tail == null){
                return null;
            }else if(tail != null){
                Node current = tail.getNext();
                do{
                    if(current.getData() == data){
                        return current;
                    }
                    current = current.getNext();
                }while(tail.getNext() != current);
            }
            return null;
	}
	public void print()
	{
            if(tail == null){
                System.out.print("\nThere is no LinkList\n");
            }else{
                Node current = tail.getNext();
                do{                                                   
                    System.out.printf("%d\t",current.getData());
                    current = current.getNext();
                }while(tail.getNext() != current);
            }
	}
}
