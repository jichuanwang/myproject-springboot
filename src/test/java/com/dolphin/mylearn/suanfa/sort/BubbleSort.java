package com.dolphin.mylearn.suanfa.sort;

import com.dolphin.mylearn.springboot.util.PrintUtil;

/**
 * Created by jichuan.wang on 2017/10/12.
 */
public class BubbleSort {
    public static void main(String[] args){
        int[] a = {2,1,7,11,6,9};
        int length = a.length;
        for(int i= length-1;i>0;i--){
            for(int j=0;j<i;j++){
                if(a[j] > a[j+1]){
                    int temp = a[j];
                    a[j]=a[j+1];
                    a[j+1]= temp;
                }
            }
        }
        for(int i=0;i<a.length;i++){
            PrintUtil.print(a[i]);
        }
    }
}
