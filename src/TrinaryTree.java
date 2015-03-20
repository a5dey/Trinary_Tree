//A tri-nary tree’s node will look like this
class Node
{
	int val;
	Node left;
	Node right;
	Node middle;
	public Node(int val)
	{
		this.val=val;
		this.left=null;
		this.right=null;
		this.middle=null;
	}
}
class TrinaryTree
{
	Node root;
	public void insert(int value)
	{
		if(this.root==null)
		{
			this.root=new Node(value);
			return;
		}
		this.findPos(this.root,value);
	}
	void findPos(Node node, int value)
	{
		if(node.val>value)
		{
			if(node.left==null)
				node.left=new Node(value);
			else
				this.findPos(node.left,value);
		}
		else if(node.val==value)
		{
			if(node.middle==null)
				node.middle=new Node(value);
			else
				this.findPos(node.middle, value);
		}
		else
		{
			if(node.right==null)
				node.right=new Node(value);
			else
				this.findPos(node.right,value);
		}
	}

//delete operation can be in 2 ways. In both cases, we are given a value and we can delete only one node with that particular value or delete all occurrences of nodes with that value. For this implementation, let us consider the deletion of all occurrences of a value.
	public void delete(int value)
	{
		this.deleteByValue(this.root,value);
	}
	Node deleteByValue(Node node, int value)
	{
		if(value<node.val)
			node.left=deleteByValue(node.left,value);
		else if(value>node.val)
			node.right=deleteByValue(node.right,value);
		else //replace a node by the lowest node element on its right or by its left. This would take care of all the middle elements as well
		{
			if(node.right!=null)
			{
				int min=minimum(node.right).val;
				node.val=min;
				node.right=deleteByValue(node.right,min);
			}
			else
				node=node.left;
		}
		return node;
	}
	
	Node minimum(Node node)
	{
		if(node!=null)
		{
			while(node.left!=null)
				return minimum(node.left);
		}
		return node;
	}
	
	public static void main(String[] args)
	{
		TrinaryTree tree=new TrinaryTree();
		tree.insert(5);
		tree.insert(4);
		tree.insert(9);
		tree.insert(5);
		tree.insert(7);
		tree.insert(2);
		tree.insert(2);

		tree.print(tree.root);
		tree.delete(2);
		tree.print(tree.root);
	}
	public void print(Node node)
	{
		if(node==null)
			return;
		System.out.println(node.val);
		this.print(node.left);
		this.print(node.middle);
		this.print(node.right);
	}
}