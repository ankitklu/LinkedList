import java.util.*;
public class LinkedList{
    public static class Node{
        int data;
        Node next;
        
        public Node(int data)
        {
            this.data=data;
            this.next=null;
        }
    }
    //since only one head and one tail is there
    public static Node head;
    public static Node tail;
    public static int size;
    
    public void addFirst(int data)
    {
        //step 1= create a new node
        Node newNode=new Node(data);
        size++;
        //if no nodes in ll
        if(head==null){
            head=tail=newNode;
            return;
        }
        
        //step 2= assign newNode's value as head
        newNode.next=head;
        
        //step 3= assign value of head as newNode
        head=newNode;
        
    }
    public void addlast(int data)
    {
        //step 1= create a node
        Node newNode=new Node(data);
         size++;
        if(head==null)
        {
            head=tail=null;
            return;
        }
        tail.next=newNode;
        tail=newNode;
    }
    public void printll()
    {
        if(head==null){
            System.out.println("LinkedList is empty");
        }
        Node temp=head;
        while(temp!=null){
            System.out.print(temp.data+"-->");
            temp=temp.next;
        }
        System.out.println("null");
    }
    public void addAtMiddle(int index, int data)
    {
        if(index==0){
            addFirst(data);
            return;
        }
        Node newNode= new Node(data);
        size++;
        Node temp=head;
        int i=0;
        while(i<index-1){
            temp=temp.next;
            i++;
        }
        //i==index-1; 
        newNode.next=temp.next;
        temp.next=newNode;
    }
    public void length()
    {
        if(head==null)
        {
            System.out.println("No nodes are present");
        }
        Node temp=head;
        int count=0;
        while(temp!=null)
        {
            temp=temp.next;
            count++;
        }
        System.out.println("Count of ll  is "+count);
    }
    public int removeFirst()
    {
        if(size==0){
            System.out.println("The ll is empty");
            return Integer.MIN_VALUE;
        }
        else if(size==1){
            int val=head.data;
            head=tail=null;
            size=0;
            return val;
        }   
        int val=head.data;
        head=head.next;
        size--;
        return val;
    }
    public int removeLast()
    {
        if(size==0){
            System.out.println("LL is empty");
            return Integer.MIN_VALUE;
        }
        else if(size==1){
            int val=head.data;
            head=tail=null;
            size=0;
            return val;
        }
        Node prev=head;
        for(int i=0;i<size-2;i++){
            prev=prev.next;
        }
        int val=prev.next.data;
        prev.next=null;
        tail=prev;
        size--;
        return val;

    }
    public int iterativeSearch(int ele){
        Node temp=head;
        int index=0;
        while(temp!=null){
            
            if(temp.data==ele){
                return index;
            }

            temp=temp.next;
            index++;
        }
        return -1;
    }
    public int helper(Node head, int ele)
    {
        if(head==null){
            return -1;
        }
        if(head.data==ele){
            return 0;
        }
        int idx=helper(head.next,ele);
        if(idx==-1){
            return -1;
        }
        return idx+1;
    }
    public int recursiveSearch(int ele)
    {
        return helper(head,ele);
    }
    public void reverse()
    {
        Node prev=null;
        Node curr=head;
        Node next;
        while(curr!=null){
            next=curr.next;
            curr.next=prev;
            prev=curr;
            curr=next;
        }
        head=prev;
    }
    public void delNthEnd(int n)
    {
        if(n==size){
            head=head.next;
            return;
        }
        int i=1;
        int iToFind=size-n;
        Node prev=head;
        while(i< iToFind){
            prev=prev.next;
            i++;
        }
        prev.next=prev.next.next;
        size--;
    }
    public Node findMid(Node head) //slow fast approach
    {
        Node slow=head;
        Node fast= head;
        while(fast!=null && fast.next!=null){
            slow=slow.next;
            fast=fast.next.next;
        }
        return slow;
    }
    public boolean isPalindrome()
    {
        if(head==null || head.next==null){
            return true;
        }

        //step-1 find middle node
        Node midNode=findMid(head);

        //step-2 - reverse the second half of th ell
        Node prev=null;
        Node curr=midNode;
        Node next;
        while(curr!=null){
            next=curr.next;
            curr.next=prev;
            prev=curr;
            curr=next;
        } 
        Node right=prev;  //right half head
        Node left= head;

        //step-3 - Check if the laft side is equal to roght half
        while(right!=null){
            if(right.data!=left.data){
                return false;
            }
            left=left.next;
            right=right.next;
        }
        return true;
    }

    public static boolean isCycle()
    {
        Node fast=head;
        Node slow=head;

        while(fast!=null && fast.next!=null){
            slow=slow.next;
            fast=fast.next.next;
            if(fast==slow){
                return true;
            }
        }
        return false;
    }
    public static void removeCycle()
    {
        //detect cycle
        Node fast=head;
        Node slow=head;
        boolean cycle= false;

        while(fast!=null && fast.next!=null){
            slow=slow.next;
            fast=fast.next.next;
            if(slow==fast){
                cycle=true;
                break;
            }
        }
        if(cycle==false){
            return;
        }
        //meeting point
        slow=head;
        Node prev=null;
        while(slow!=fast){
            prev=fast;
            slow=slow.next;
            fast=fast.next;
        }
        //prev.next=null
        prev.next=null;
    }
    private Node getMid(Node head)
    {
        Node slow=head;
        Node fast=head.next;
        while(fast!=null && fast.next!=null){
            slow=slow.next;
            fast=fast.next;
        }
        return slow;
    }
    private Node merge(Node head1, Node head2)
    {
        Node mergedLL=new Node(-1);
        Node temp=mergedLL; //points to the head of the linked list
        while(head1!=null && head2!=null){
            if(head1.data<=head2.data){
                temp.next=head1;
                head1=head1.next;
                temp=temp.next;
            }
            else{
                temp.next=head2;
                head2=head2.next;
                temp=temp.next;
            }
        }
        while(head1!=null){
            temp.next=head1;
            head1=head1.next;
            temp=temp.next;
        }
        while(head2!=null){
            temp.next=head2;
            head2=head2.next;
            temp=temp.next;
        }
        return mergedLL.next;   //since the first node of the ll in -1;
    }
    public Node mergeSort(Node head)
    {
        if(head==null || head.next==null){
            return head;
        }
        Node mid=getMid(head);
        Node rightHead=mid.next;
        mid.next=null;   //ll is divided

        Node newLeft=mergeSort(head);
        Node newRight=mergeSort(rightHead);

        return merge (newLeft,newRight);
    }
    public void zigzag()
    {
        //find mid
        Node slow=head;
        Node fast= head.next;
        while(fast!=null && fast.next!=null){
            slow=slow.next;
            fast=fast.next.next;
        }
        Node mid=slow;

        //reverse right half
        Node curr=mid.next;
        mid.next=null;
        Node prev=null;
        Node next;
        while(curr!=null){
            next=curr.next;
            curr.next=prev;
            prev=curr;
            curr=next;
        }
        Node left=head;
        Node right=prev;
        Node nextLeft, nextRight;
        while(left!=null && right!=null){
            nextLeft=left.next;
            left.next=right;
            nextRight=right.next;
            right.next=nextLeft;

            left=nextLeft;
            right=nextRight;
        }
    }
    public static void main(String args[])
    {
        LinkedList ll= new LinkedList();
        // ll.printll();
        // ll.addFirst(1);
        // ll.printll();
        // ll.addFirst(2);
        // ll.printll();
        // ll.addlast(1);
        // ll.addAtMiddle(3,2);
        // ll.printll();

        // System.out.println("Size of ll is "+size);
        // ll.removeFirst();
        // ll.removeLast();
        // ll.printll();
        // System.out.println("size of the ll is "+size);
        // System.out.println(ll.iterativeSearch(9));
        // System.out.println(ll.iterativeSearch(10));
        // System.out.println(ll.recursiveSearch(9));

        // ll.reverse();
        // ll.printll();
        // ll.delNthEnd(2);
        // ll.printll();
        // System.out.println(ll.isPalindrome());

        // head= new Node(1);
        // Node temp=new Node(2);
        // head.next=new Node(2);
        // head.next.next=new Node(3);
        // head.next.next.next=new Node (4);
        // head.next.next.next.next=temp;
        // System.out.println(isCycle());
        // removeCycle();
        // System.out.println(isCycle());

        ll.addFirst(1);
        ll.addFirst(2);
        ll.addFirst(3);
        ll.addFirst(4);
        ll.addFirst(5);
        // ll.head= ll.mergeSort(ll.head);
        ll.printll();
        ll.zigzag();
        ll.printll();
    }
}
