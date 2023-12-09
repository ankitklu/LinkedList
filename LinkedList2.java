import java.util.*;
public class LinkedList2{
    public static class Node{

    int data;
    Node next;

    public Node(int data)
    {
        this.data=data;
        this.next=null;
    }
}
public static Node head;
public static Node tail;
public static int size;

public void addFirst(int data){
    Node newNode= new Node(data);
    size++;
    if(head==null){
        head=tail=newNode;
        return;
    }
    newNode.next=head;
    head=newNode;

}
public void addLast(int data){
    Node newNode=new Node(data);
    size++;
    if(head==null){
        head=tail=newNode;
        return;
    }
    tail.next=newNode;
    tail=newNode;
}
public void addAtMiddle(int data,int index)
{
    Node newNode= new Node(data);
    if(head==null){
        head=tail=newNode;
        size++;
        return;
    }
    Node temp=head;
    // int i=0;
    for(int i=0;i<index-2;i++){
        temp=temp.next;
    }
    newNode.next=temp.next;
    temp.next=newNode;
    size++;
    return;
}
public void printll()
{
    Node temp=head;
    if(head==null){
        System.out.println("Linked LISt is empty");
        return;
    }
    else{
        while(temp!=null){
            System.out.print(temp.data+"-->");
            temp=temp.next;
        }
        System.out.print("null");
        System.out.println();
    }
}
public int removeFirst()
{
    if(head==null){
        System.out.println("The linkedLISt is empty");
        return Integer.MIN_VALUE;
    }
    else if(size==1){
        int val=tail.data;
        head=tail=null;
        size=0;
        return val;
    }
    int val=head.data;
    head=head.next;
    return val;
}
public static Node middleNode(Node head){
    Node slow=head;
    Node fast=head;
    while(fast!=null && fast.next!=null){
        slow=slow.next;
        fast=fast.next.next;
    }
    return slow;
}
public int removeLast()
{
    if(head==null){
        System.out.println("Linked List is empty");
        return Integer.MIN_VALUE;
    }
    else if(size==1){
        int val=head.data;
        head=tail=null;
        size=0;
        return val;
    }
    Node prev=head;
    for(int i=0;i<size-1;i++){
        prev=prev.next;
    }
    int val=prev.next.data;
    prev=tail;
    prev.next=null;
    return val;
}
public static void main(String args[]){
    // Scanner sc=new Scanner(System.in);
    LinkedList2 ll= new LinkedList2();
    // int item;
    //     while (true){
    //     System.out.println("Enter your choice");
    //     System.out.println("1. addFirst");
    //     System.out.println("2. addLast");
    //     System.out.println("3. exit");
    //     int ch=sc.nextInt();
    //     switch(ch){
    //         case 1: item=sc.nextInt();
    //                 ll.addFirst(item);1
    //                 break;
            
    //         case 2: item=sc.nextInt();
    //                 ll.addLast(item);
    //                 break;
            
    //         case 3: System.exit(0);

    //         default: System.out.println("Enter a valid choice");
    //                 break;
    ll.addFirst(10);
    ll.addFirst(20);
    ll.printll();
    //System.out.println("The removed element is: "+ll.removeFirst());
    //ll.printll();
    // ll.removeLast();
    ll.printll();
    ll.addFirst(15);
    ll.addLast(23);
    ll.addAtMiddle(12,3);
    ll.printll();
    System.out.println(ll.middleNode(head));
    }
}

