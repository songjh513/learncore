package com.songjh.learncore.common.interview;

import org.springframework.util.CollectionUtils;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

/**
 * Created  by songjh on 2019-07-10 22:18.
 */
public class sortListDemo {

    private static List<Integer> sortList = new LinkedList<>();

    public static void addElement(int e){

        LinkedList linkedList = null;
        if(sortList instanceof LinkedList){
            linkedList = (LinkedList)sortList;
        }

        if(sortList.size()==0){
            linkedList.addFirst(e);
            
        }else if(sortList.size()==1){
            if(e<sortList.get(0)){
                sortList.add(0,e);
            }else {
                sortList.add(e);
            }
        }else{
//            int index =0;
//            for (int i = 0; i < sortList.size()-1; i++) {
//                Integer iv = sortList.get(i);
//                Integer iv2 = sortList.get(i+1);
//
////                System.out.println(iv+"--"+iv2);
//
//                if(e>iv && e<iv2){
//                    index = i+1;
//                    break;
//                }
//            }
            sortList.add(e);
        }

        sortList = linkedList;



    }

    public static void main(String[] args) {
        for (int i=0;i<1000000;i++) {
            addElement(100+i);
            addElement(10+i);
            addElement(400+i);
            addElement(3+i);
        }

        System.out.println(sortList);
    }
}
