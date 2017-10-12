package com.dolphin.mylearn.suanfa;

/**
 * Created by jichuan.wang on 2017/10/12.
 */
public class AvlTree {
    static class Node{
        private Node left;
        private Node right;
        private int value;
        private int height;

        public Node getLeft() {
            return left;
        }

        public void setLeft(Node left) {
            this.left = left;
        }

        public Node getRight() {
            return right;
        }

        public void setRight(Node right) {
            this.right = right;
        }

        public int getValue() {
            return value;
        }

        public void setValue(int value) {
            this.value = value;
        }

        public int getHeight() {
            return height;
        }

        public void setHeight(int height) {
            this.height = height;
        }
    }

    public static void main(String[] args){

    }
    public static Node insert(Node node,int value){
        if(node == null){
            node = createNode(value,null,null);
        }else if(value < node.getValue()){
            node.setLeft(insert(node.getLeft(),value));
            if(height(node.getLeft()) - height(node.getRight()) ==2){
                if(value < node.getLeft().getValue()){

                }
            }
        }
        return null;
    }
    public static int height(Node node){
        return node == null ? 0: node.getHeight();
    }
    public static Node createNode(int value,Node left,Node right){
        Node node = new Node();
        node.setValue(value);
        node.setLeft(left);
        node.setRight(right);
        node.setHeight(0);
        return node;
    }
}
