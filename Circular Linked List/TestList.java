class TestList
{
	public static void main (String[] args) 
	{
		CircularLinkedList L1=new CircularLinkedList();

		java.util.Scanner key=new java.util.Scanner(System.in);
		while(true)//few options are added here, you can remaining
		{
			System.out.println("\n1. Add at Start\n"
                                            +"2. Add at end \n"
                                            +"3. Remove at End \n"
                                            +"4. Remove at Start \n"
                                            +"5. Search\n"
                                            +"6. print\n"
                                            +"0. Exit\n");
 
			System.out.print("\nEnter:\t");
			int ch=key.nextInt();
			int d;
			Node node;
			switch(ch)
			{
				case 1:
					System.out.print("Enter value:");
					d=key.nextInt();
					node=new Node(d);	//new node is created
					if(L1.addStart(node)==true)
						System.out.print("inserted....");
					else
					System.out.print("not inserted....");
 
					break;
                                case 2:
					System.out.print("Enter value:");
					d=key.nextInt();
					node=new Node(d);	//new node is created
					if(L1.addEnd(node)==true)
						System.out.print("inserted....");
					else
					System.out.print("not inserted....");
 
					break;    
				case 3:
					node=L1.removeEnd();
					if(node!=null)
						System.out.print("node deleted\n "+node);//toString of Node method will be called automatically
					else
						System.out.print("List is empty");
 					break;
                                        
                                case 4:
					node=L1.removeStart();
					if(node!=null)
						System.out.print("node deleted\n "+node);//toString of Node method will be called automatically
					else
						System.out.print("List is empty");
 					break;
                                        
				case 5:
					System.out.print("Enter value:");
					d=key.nextInt();
					node=L1.search(d);
					if(node!=null)
					{	
						System.out.print(" following node found\n"+node);
					}
					else
					System.out.print("not found....");
 
					break;
 
				case 6:
					L1.print();
					break;
 
				case 0:
					return;
 
				default:
				System.out.print("Invalid Choice");
			}
		}
	}
}
//=========================================================================
