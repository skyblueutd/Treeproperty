import java.util.LinkedList;
import java.util.Queue;

// BinarySearchTree class
//
// CONSTRUCTION: with no initializer
//
// ******************PUBLIC OPERATIONS*********************
// void insert( x )       --> Insert x
// void remove( x )       --> Remove x
// boolean contains( x )  --> Return true if x is present
// Comparable findMin( )  --> Return smallest item
// Comparable findMax( )  --> Return largest item
// boolean isEmpty( )     --> Return true if empty; else false
// void makeEmpty( )      --> Remove all items
// void printTree( )      --> Print tree in sorted order
// ******************ERRORS********************************
// Throws UnderflowException as appropriate

/**
 * Implements an unbalanced binary search tree.
 * Note that all "matching" is based on the compareTo method.
 * @author Mark Allen Weiss
 */
public class BinarySearchTree<AnyType extends Comparable<? super AnyType>>
{
    /**
     * Construct the tree.
     */
    public BinarySearchTree( )
    {
        root = null;
    }

    /**
     * Insert into the tree; duplicates are ignored.
     * @param x the item to insert.
     */
    public void insert( AnyType x )
    {
        root = insert( x, root );
    }

    /**
     * Remove from the tree. Nothing is done if x is not found.
     * @param x the item to remove.
     */
    public void remove( AnyType x )
    {
        root = remove( x, root );
    }

    /**
     * Find the smallest item in the tree.
     * @return smallest item or null if empty.
     */
    public AnyType findMin( )
    {
        if( isEmpty( ) )
            throw new NullPointerException( );
        return findMin( root ).element;
    }

    /**
     * Find the largest item in the tree.
     * @return the largest item of null if empty.
     */
    public AnyType findMax( )
    {
        if( isEmpty( ) )
            throw new NullPointerException( );
        return findMax( root ).element;
    }

    /**
     * Find an item in the tree.
     * @param x the item to search for.
     * @return true if not found.
     */
    public boolean contains( AnyType x )
    {
        return contains( x, root );
    }

    /**
     * Make the tree logically empty.
     */
    public void makeEmpty( )
    {
        root = null;
    }

    /**
     * Test if the tree is logically empty.
     * @return true if empty, false otherwise.
     */
    public boolean isEmpty( )
    {
        return root == null;
    }

    /**
     * Print the tree contents in sorted order.
     */
    public void printTree( )
    {
        if( isEmpty( ) )
            System.out.println( "Empty tree" );
        else
            printTree( root );
    }

    /**
     * Internal method to insert into a subtree.
     * @param x the item to insert.
     * @param t the node that roots the subtree.
     * @return the new root of the subtree.
     */
    private BinaryNode<AnyType> insert( AnyType x, BinaryNode<AnyType> t )
    {
        if( t == null )
            return new BinaryNode<AnyType>( x, null, null );
        
        int compareResult = x.compareTo( t.element );
            
        if( compareResult < 0 )
            t.left = insert( x, t.left );
        else if( compareResult > 0 )
            t.right = insert( x, t.right );
        else
            ;  // Duplicate; do nothing
        return t;
    }

    /**
     * Internal method to remove from a subtree.
     * @param x the item to remove.
     * @param t the node that roots the subtree.
     * @return the new root of the subtree.
     */
    private BinaryNode<AnyType> remove( AnyType x, BinaryNode<AnyType> t )
    {
        if( t == null )
            return t;   // Item not found; do nothing
            
        int compareResult = x.compareTo( t.element );
            
        if( compareResult < 0 )
            t.left = remove( x, t.left );
        else if( compareResult > 0 )
            t.right = remove( x, t.right );
        else if( t.left != null && t.right != null ) // Two children
        {
            t.element = findMin( t.right ).element;
            t.right = remove( t.element, t.right );
        }
        else
            t = ( t.left != null ) ? t.left : t.right;
        return t;
    }

    /**
     * Internal method to find the smallest item in a subtree.
     * @param t the node that roots the subtree.
     * @return node containing the smallest item.
     */
    private BinaryNode<AnyType> findMin( BinaryNode<AnyType> t )
    {
        if( t == null )
            return null;
        else if( t.left == null )
            return t;
        return findMin( t.left );
    }

    /**
     * Internal method to find the largest item in a subtree.
     * @param t the node that roots the subtree.
     * @return node containing the largest item.
     */
    private BinaryNode<AnyType> findMax( BinaryNode<AnyType> t )
    {
        if( t != null )
            while( t.right != null )
                t = t.right;

        return t;
    }

    /**
     * Internal method to find an item in a subtree.
     * @param x is item to search for.
     * @param t the node that roots the subtree.
     * @return node containing the matched item.
     */
    private boolean contains( AnyType x, BinaryNode<AnyType> t )
    {
        if( t == null )
            return false;
            
        int compareResult = x.compareTo( t.element );
            
        if( compareResult < 0 )
            return contains( x, t.left );
        else if( compareResult > 0 )
            return contains( x, t.right );
        else
            return true;    // Match
    }

    /**
     * Internal method to print a subtree in sorted order.
     * @param t the node that roots the subtree.
     */
    private void printTree( BinaryNode<AnyType> t )
    {
        if( t != null )
        {
            printTree( t.left );
            System.out.println( t.element );
            printTree( t.right );
        }
    }

    /**
     * Internal method to compute height of a subtree.
     * @param t the node that roots the subtree.
     */
    private int height( BinaryNode<AnyType> t )
    {
        if( t == null )
            return -1;
        else
            return 1 + Math.max( height( t.left ), height( t.right ) );    
    }
    
    // Basic node stored in unbalanced binary search trees
    private static class BinaryNode<AnyType>
    {
            // Constructors
        BinaryNode(AnyType theElement)
        {
            this( theElement, null, null );
        }

        BinaryNode( AnyType theElement, BinaryNode<AnyType> lt, BinaryNode<AnyType> rt )
        {
            element  = theElement;
            left     = lt;
            right    = rt;
        }

        AnyType element;            // The data in the node
        BinaryNode<AnyType> left;   // Left child
        BinaryNode<AnyType> right;  // Right child
    }


      /** The tree root. */
    private BinaryNode<AnyType> root;
    
     /** My method */
     /**           */
     /**           */
     /**Recursively traverses the tree and returns the count of nodes n 
     * @param t */
    private int nodeCount (BinaryNode<AnyType> t)
    {
    	if (t.element == null)
    	return 0;
    	else
    	{
    	    int i= 1;
    	    if (t.left != null) i += nodeCount(t.left);
            if (t.right != null) i += nodeCount(t.right);;
    		return i;
    	}
    		
    }
     /**Returns true if the node is full. */
    private boolean isFull (BinaryNode<AnyType> t)
    {
    	if(t == null) return true;
        else if(t.left != null && t.right == null || t.left == null && t.right != null) return false;
        return (isFull(t.left) && isFull(t.right));

    }
    /**Returns true if two trees' structure is the same. */
    private boolean compareStructure(BinaryNode<AnyType> t1,BinaryNode<AnyType> t2)
    {
    	if (t1 == null && t2 == null)
    		return true;
    	if (t1 == null || t2 == null)
    		return false;
    	return (compareStructure(t1.left, t2.left) && compareStructure(t1.right, t2.right));
    		
    }
    /**Returns true if two trees are the same. */
    private boolean equals(BinaryNode<AnyType> t1,BinaryNode<AnyType> t2)
    {
    	if (t1 == null && t2 == null)
    		return true;
    	if (t1 == null || t2 == null || t1.element.compareTo(t2.element) != 0 )
    		return false;
    	return (equals(t1.left, t2.left) && equals(t1.right, t2.right));
    }
    /**Copy node */
    private BinaryNode<AnyType> Copy (BinaryNode<AnyType> t)
    {
    	BinaryNode p = new BinaryNode(null,null,null);
    	if (t!=null)
    	{
    		p.element = t.element;
    		if (t.left!=null)
    		{
    			p.left=Copy(t.left);
    		}
    		if (t.right!=null)
    	    {
    			p.right=Copy(t.right);
    	    }
    	}
    	
    	return p;
    				
    }
    /**Copy tree1 to tree2 */
    public BinarySearchTree<AnyType> copy ()
    {
    	BinarySearchTree <AnyType> p = new BinarySearchTree <AnyType> ();
        if(isEmpty()) return null;
        else 
        { 
          p.root = Copy(this.root);
        }
        return p;
    }
    
    /**mirror of a node */
    private BinaryNode<AnyType> Mirror (BinaryNode<AnyType> t)
    {
    	if (t!=null)
    	{
    		BinaryNode<AnyType> transit = t.left;
    		t.left = t.right;
    		t.right = transit;
    		t.left=Mirror(t.left);
    		t.right=Mirror(t.right);
    	}
    	return t;
    }
    
    /**mirror of a tree */
    public BinarySearchTree<AnyType> mirror() 
    {
    	BinarySearchTree <AnyType> p = new BinarySearchTree <AnyType> ();
        if(isEmpty()) return null;
        else 
        { p = this.copy();
          p.root = Mirror(p.root);
        }
        return p;
    }
    
    /**check isMirror */
    public boolean isMirror(BinarySearchTree <AnyType> t) 
    {
    	BinarySearchTree <AnyType> mirror = t.mirror();
        return this.equals(this.root,mirror.root);
    }
    
    /**rotate right */
    public void rotateRight(AnyType x) 
    {
        if(!contains(x)) System.out.println("Can not rotate, no such element");
        if(root.element.compareTo(x) == 0) 
        {
          if(root.left == null) 
          {
            System.out.println("can not rotate right, no left node");
          }
          else 
          {
            BinaryNode<AnyType> temp = root.left;
            root.left = temp.right;
            temp.right = root;
            root =  temp;
          }
        }
        else if(root.element.compareTo(x) < 0) 
        {
          root.right = rotateRight(root.right, x);
        }
        else if(root.element.compareTo(x) > 0) 
        {
          root.left = rotateRight(root.left, x);
        }
    }
        
    private BinaryNode<AnyType> rotateRight(BinaryNode<AnyType> n, AnyType x) 
    {
        BinaryNode<AnyType> r = null;
        if(n.element.compareTo(x) == 0) 
        {
          if(n.left == null) 
          {
            System.out.println("can not rotate right, no left node");
          }
          else 
          {
            BinaryNode<AnyType> temp = n.left;
            n.left = temp.right;
            temp.right = n;
            r = temp;
          } 
        }
        else if(n.element.compareTo(x) < 0) 
        {
          r = rotateRight(n.right, x);
        }
        else if(n.element.compareTo(x) > 0)
        {
          r = rotateRight(n.left, x);
        }
        return r;
     }
    
    /**rotate left */
    public void rotateLeft(AnyType x) 
    {
        if(!contains(x)) System.out.println("Can not rotate, no such element");
        if(root.element.compareTo(x) == 0) 
        {
          if(root.right == null) 
          {
            System.out.println("can not rotate left, no right node");
          }
          else 
          {
            BinaryNode<AnyType> temp = root.right;
            root.right = temp.left;
            temp.left = root;
            root =  temp;
          }
        }
        else if(root.element.compareTo(x) < 0) 
        {
          root.right = rotateLeft(root.right, x);
        }
        else if(root.element.compareTo(x) > 0) 
        {
          root.left = rotateLeft(root.left, x);
        }
    }
    
    private BinaryNode<AnyType> rotateLeft(BinaryNode<AnyType> n, AnyType x) 
    {
        BinaryNode<AnyType> r = null;
        if(n.element.compareTo(x) == 0) 
        {
          if(n.right == null) 
          {
            System.out.println("can not rotate left, no right node");
          }
          else 
          {
            BinaryNode<AnyType> temp = n.right;
            n.right = temp.left;
            temp.left = n;
            r = temp;
          } 
        }
        else if(n.element.compareTo(x) < 0) {
          r = rotateLeft(n.right, x);
        }
        else {
          r = rotateLeft(n.left, x);
        }
        return r;
      }
    
    /**print a tree level by level */
    public void printLevels(BinaryNode<AnyType> t)
    {   if(t != null)
        {
    	Queue<BinaryNode<AnyType>> currentLevel  = new LinkedList<>();
    	Queue<BinaryNode<AnyType>> nextLevel  = new LinkedList<>();
    	int treeLevel = height(t)+1;    
        int levelTotal = 0; 
        currentLevel.add(t);

        while(!currentLevel.isEmpty()&& (levelTotal< treeLevel))
        {
            while(!currentLevel.isEmpty())
            {
                System.out.print(currentLevel.peek().element +" ");
                t = currentLevel.peek().left;
                if(t != null)
                    nextLevel.add(t);
                else
                    nextLevel.add(new BinaryNode<AnyType>(null));
                t = currentLevel.remove().right;
                if(t != null)
                    nextLevel.add(t);
                else
                    nextLevel.add(new BinaryNode<AnyType>(null));

            }
            while(!nextLevel.isEmpty())
            {
                currentLevel.add(nextLevel.remove());
            }
            System.out.println("");
            levelTotal++;
            }
        }
   }

        // Test main program
    public static void main ( String [ ] args )
    {
        BinarySearchTree<Integer> t1 = new BinarySearchTree<Integer>( );
        int [] num1 = {11,2,20,8,25,1,16};
        for( int i = 0; i <num1.length; i ++ )
        { 
        	t1.insert( num1[i] );
        }
        BinarySearchTree<Integer> t2 = new BinarySearchTree<Integer>( );
        int [] num2 = {11,2,20,8,25,30};
        for( int i = 0; i <num2.length; i ++ )
        { 
        	t2.insert( num2[i] );
        }

        t1.printLevels (t1.root);
        t2.printLevels (t2.root);
        System.out.println (t1.nodeCount(t1.root)); //test nodeCount 
        System.out.println (t1.isFull(t1.root)); //test isFull
        System.out.println (t1.compareStructure(t1.root,t2.root)); //test compareStructure
        System.out.println (t1.equals(t1.root,t2.root)); // test equals
        
//        BinarySearchTree<Integer> t3 = new BinarySearchTree<Integer>( );
//        t3 = t1.copy();
//        System.out.println (t1.equals(t1.root,t3.root));
//        t1.printLevels(t1.root);
//        t3.printLevels(t3.root); // test copy and compare it with the original one
        
//        BinarySearchTree<Integer> t4 = new BinarySearchTree<Integer>( );
//        t4 = t1.mirror();
//        t4.printLevels(t4.root); // test mirror and check it with isMirror
//        System.out.println (t4.isMirror(t1));
        
        
        t1.rotateRight(11);
        t1.printLevels(t1.root);//check rotateRight with t1
        t2.rotateLeft(11);
        t2.printLevels(t2.root);//check rotateLeft with t2
       
        

    }
}
