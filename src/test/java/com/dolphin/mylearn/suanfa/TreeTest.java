package com.dolphin.mylearn.suanfa;

import com.dolphin.mylearn.springboot.util.PrintUtil;

import javax.xml.soap.Node;
import java.util.Random;

/**
 * Created by jichuan.wang on 2017/10/9.
 */
public class TreeTest {
    static class BinarySearchTree{
        public TreeNode getRoot() {
            return root;
        }

        public void setRoot(TreeNode root) {
            this.root = root;
        }

        public TreeNode root;
        public boolean insert(int value){
            TreeNode node = new TreeNode(value);
            return insert(node);
        }
        public TreeNode find(int key){
            TreeNode head = this.root;
            while (head != null){
                if(head.getId() == key){
                    return head;
                }else if(head.getId() < key){
                    head = head.getRight();
                }else {
                    head = head.getLeft();
                }
            }
            return null;
        }
        public void sort(TreeNode node){
            if(node.getLeft() != null){
                sort(node.getLeft());

            }

            PrintUtil.print(node.getId());
            if(node.getRight() !=null){
                sort(node.getRight());

            }
        }
        public boolean insert(TreeNode node){
            if(root == null){
                root = node;
                return true;
            }
            //树中不允许插入重复的数据项
            TreeNode head = root;
            while (head != null){
                if(node.getId() >head.getId()){
                    if(head.getRight() == null){
                        head.setRight(node);
                        return true;
                    }
                    head = head.getRight();
                }else if(node.getId() <head.getId()){
                    if(head.getLeft() == null){
                        head.setLeft(node);
                        return true;
                    }
                    head = head.getLeft();
                }
            }
            return false;
        }
    }
   static class TreeNode{
       private int id;
       private TreeNode left;
       private TreeNode right;

       public int getId() {
           return id;
       }

       public void setId(int id) {
           this.id = id;
       }

       public TreeNode getLeft() {
           return left;
       }

       public void setLeft(TreeNode left) {
           this.left = left;
       }

       public TreeNode getRight() {
           return right;
       }

       public void setRight(TreeNode right) {
           this.right = right;
       }

      public TreeNode(int value){
           this.id = value;
      }
   }
   public static void main(String[] args){
        BinarySearchTree binarySearchTree = new BinarySearchTree();
        binarySearchTree.insert(3);
        TreeNode node = new TreeNode(8);
        binarySearchTree.insert(node);
        PrintUtil.print(node);
        binarySearchTree.insert(29);
        binarySearchTree.insert(22);
        TreeNode treeNode = binarySearchTree.find(219);
        PrintUtil.print(treeNode);
   }
}
