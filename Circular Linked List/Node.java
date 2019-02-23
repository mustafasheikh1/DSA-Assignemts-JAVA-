class Node
{
	private int data;
	private Node next;
	public Node(int d)
	{
		data=d;
	}
	public int getData()
	{
		return this.data;
	}
	public Node getNext()
	{
		return this.next;
	}
	public void setData(int d)
	{
		this.data=d;
	}
	public void setNext(Node node)
	{
		this.next=node;
	}
	public String toString()
	{
		return ""+data;
	}
}