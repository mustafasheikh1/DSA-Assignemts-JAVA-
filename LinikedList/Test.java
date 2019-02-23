
//Test class
import java.util.Scanner;

public class Test{
 
    public static void main (String[] args) 
	{
            int c;
            Scanner key = new Scanner(System.in);
            System.out.println("What do you want to test?\n 1 for Singly Linked list?  and\n 2 for Doubly Linked List");
            c = key.nextInt();
            if (c == 1) {
             LinkedList   list = new LinkedList();
            
            
                
		while(true)
		{
			System.out.println("\n1.  Insert At Start\n"
                                        +"2.  Insert value at specific node number\n"
                                        +"3.  Insert At End\n"
                                        +"4.  Search value\n"
					+"5.  Delete At Start\n"
                                        +"6.  Delete a value\n"
                                        +"7.  Delete At End\n"
					+"8.  Delete node n\n"
					+"9.  Get data at node n\n"
					+"10. print\n"
					+"11. Sort\n" 
					+"0.  Exit \n");
			System.out.print("\nEnter choice:\t");
			int ch=key.nextInt();
			int n,i;
			switch(ch)
			{
				case 1:
					System.out.print("Enter value:\t");
					n=key.nextInt();
					if(list.insertAtStart(n)==true)
                                            System.out.print("inserted....");
					else
                                            System.out.print("not inserted....");
					
					break;
                                case 2:
					System.out.print("Enter value:");
					n=key.nextInt();
                                        System.out.print("Enter node number (node starting from 1):");
                                        i=key.nextInt();
					if((list.insertAt(n, i))==true)
                                            System.out.print("inserted....");
					else
                                            System.out.print("not inserted....");
					
					break;
				
				case 3:
                                        System.out.print("Enter value:");
					n=key.nextInt();
					if(list.insertAtEnd(n)!=false)
                                            System.out.print("Value is inserted:");
					else
                                            System.out.print("not inserted....");
                                        break;
                
				case 4:
                                        System.out.print("Enter value to search:");
					n=key.nextInt();
                                        if((list.search(n))!=null)
						System.out.print("data Value of node is at: "+ list.search(n));
					else
						System.out.print("The list is either empty.... or value not found");
                                        break;
                
				case 5:
                                    if(list.deleteAtStart()!=false)
                                                System.out.print("node is deleted");
					else
						System.out.print("The list is already empty");
                                    break;
                                case 6:
                                    System.out.print("Enter value to delete:");
					n=key.nextInt();
                                    if(list.deleteValue(n)!=false)
                                                System.out.print("node with value: "+n+" is deleted");
                                    else
						System.out.print("The list is empty or value doesn't exist");
                                    break;
                                case 7:
                                    if(list.deleteAtEnd()!=false)
						System.out.print("node is deleted:");
                                    else
						System.out.print("The list is empty....");
                                    break;
				case 8:
                                    System.out.print("Enter node number to delete:");
					n=key.nextInt();
                                    if(list.deleteAtPosition(n)!=false)
                                                System.out.print("node is deleted");
                                    else
						System.out.print("The list is empty");
                                    break;
				case 9:
                                    System.out.print("Enter node number to get data:\t");
                                    n=key.nextInt();
                                    if(n <= 0){
                                        System.out.print("\nNodes list staring from 1 Please enter number bigger than 0\n");
                                    }else{
                                        if(list.Data(n) == -1){
                                            System.out.print("\nNode doesn't exist\n");
                                        }else if(list.Data(n) != -1){
                                            System.out.printf("\nThe data is :\t%d",list.Data(n));
                                        }
                                    }
                                    break;
                                case 10:
					list.printMe();
                                    break;
				
//				case 11:
//					list.sort();
//                                    break;
                
				
				case 0:
                                    return;
				
				default:
				System.out.print("Invalid Choice");
			}
                }
		}//end if
            else if(c==2){
            //write all above set of choices to implement doubly linked list here
            }
	}
}

