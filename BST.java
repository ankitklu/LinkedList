import java.util.*;

public class BST{
    static class Node{
        int data;
        Node left;
        Node right;
        Node(int data){
            this.data=data;
        }

    }
    public static Node insert(Node root, int val){
        if(root==null){
            root=new Node(val);
            return root;
        }
        if(val>root.data){
            root.right=insert(root.right,val);
        }
        else{
            root.left=insert(root.left,val);
        }
        return root;
    }
    public static boolean search(Node root, int key){
        if(root==null){
            return false;
        }
        if(root.data==key){
            return true;
        }
        if(key>root.data){
            return search(root.right,key);
        }
        if(key<root.data){
            return search(root.left,key);
        }
        return false;
    }
    public static void inOrder(Node root){
        if(root==null){
            return;
        }
        inOrder(root.left);
        System.out.print(root.data+ " ");
        inOrder(root.right);

    }
    public static Node delete(Node root, int val){
        if(root.data>val){
            root.left=delete(root.left,val);
        }
        if(root.data<val){
            root.right=delete(root.right,val);
        }
        else{
            //if no child
        if(root.left==null&& root.right==null){
            return null;
        }
        //one child
        if(root.left==null){
            return root.right;
        }
        else if(root.right==null){
            return root.left;
        }

        //if two child
        //find inOrder Successor
        Node IS=inOrderSuccessor(root.right);             //finding inOrderSuccessor
        root.data=IS.data;                                //replace the data
        root.right=delete(root.left,IS.data);             //deleteing the inOrdeerSuccessor
        }
        return root;

    }
    public static Node inOrderSuccessor(Node root){
        while(root.left!=null){
            root=root.left;
        }
        return root;
    }
    public static void printRange(Node root,int k1, int k2){
        if(root==null){
            return ;
        }
        if(root.data>=k1 && root.data<=k2){
            printRange(root.left,k1,k2);
            System.out.print(root.data+" ");
            printRange(root.right, k1, k2);
        }
        else if(root.data>k1){
            printRange(root.right, k1, k2);
        }
        else if(root.data<k2){
            printRange(root.left, k1, k2);
        }
        
    }
    public static Node balancedBST(int arr[], int start, int end){
        if(start>end){
            return null;
        }
        int  mid=(start+end)/2;
        Node root=new Node(arr[mid]);
        root.left=balancedBST(arr, start, mid-1);
        root.right=balancedBST(arr, mid+1, end);
        return root;
    }
    static class Info2{
        boolean isValid;
        int size;
        int max;
        int min;
        public Info2(boolean info2, int size, int max, int min){
            this.isValid=isValid;
            this.size=size;
            this.max=max;
            this.min=min;
        }
    }
    public static int maxBST=0;

    public static Info2 largestBST(Node root){
        if(root==null){
            return new Info2(true,0,Integer.MIN_VALUE,Integer.MAX_VALUE);
        }
        Info2 leftInfo=largestBST(root.left);
        Info2 rightInfo=largestBST(root.right);
        int size=leftInfo.size+rightInfo.size+1;
        int min=Math.min(root.data,Math.min(leftInfo.min,rightInfo.min));
        int max=Math.max(root.data,Math.max(leftInfo.max,rightInfo.max));

        if(root.data<=leftInfo.max || root.data >=rightInfo.min){
            return new Info2(false,size,max,min);
        }
        if(leftInfo.isValid && rightInfo.isValid){
            maxBST=Math.max(maxBST,size);
            return new Info2(true,size,max,min);
        }
        return new Info2(false,size,max,min);
    }
    public static void getInOrder(Node root, ArrayList<Integer>list){
        if(root==null){
            return;
        }
        getInOrder(root.left, list);
        list.add(root.data);
        getInOrder(root.right, list);
    }
    public static Node balancedBST(ArrayList<Integer>list, int start,int end){
        if(start>end){
            return null;
        }
        int mid=(start+end)/2;
        Node root=new Node(list.get(mid));
        root.left=balancedBST(list, start, mid-1);
        root.right=balancedBST(list, mid+1, end);
        return root;
    }

    public static Node mergeBSts(Node root1, Node root2){
        ArrayList<Integer>inO=new ArrayList<>();
        getInOrder(root1, inO);
        ArrayList<Integer>inO2=new ArrayList<>();
        getInOrder(root2,inO2);

        ArrayList<Integer>list=new ArrayList<>();
        int i=0,j=0;
        while(i<inO.size() && j<inO2.size()){
            if(inO.get(i)<=inO2.get(j)){
                list.add(inO.get(i));
                i++;
            }else{
                list.add(inO.get(j));
                j++;
                
            }
        }
        while(i<inO.size()){
            list.add(inO.get(i));
            i++;
        }
        while(j<inO2.size()){
            list.add(inO2.get(j));
            j++;
        }
        return balancedBST(list,0,list.size());
    }

    public static void printPath(ArrayList<Integer>list){
        for(int i=0;i<list.size();i++){
            System.out.print(list.get(i)+"->");
        }
        System.out.println("Null");
    }
    public static void rootToleaf(Node root, ArrayList<Integer>list){
        if(root==null){
            return;
        }
        list.add(root.data);
        if(root.left==null && root.right==null){
            printPath(list);
        }
        rootToleaf(root.left, list);
        rootToleaf(root.right, list);
        list.remove(list.size()-1);
    }
    public static void main(String args[]){
        // int values[]={5,1,3,4,2,7};
        // Node root=null;
        // for(int i=0;i<values.length;i++){
        //     root=insert(root, values[i]);
        // }
        // inOrder(root);

        Node root=new Node(5);
        root.left=new Node(3);
        root.right=new Node(7);
        root.left.left=new Node(2);
        root.left.right=new Node(4);
        root.right.left=new Node(6);
        root.right.right=new Node(8);

        // System.out.println(search(root,4));
        // printRange(root, 5, 8);
        
        rootToleaf(root,new ArrayList<>());
    }
}