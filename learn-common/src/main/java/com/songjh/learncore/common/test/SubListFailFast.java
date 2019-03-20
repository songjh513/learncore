package com.songjh.learncore.common.test;


import java.util.ArrayList;
import java.util.List;

/**
 * Created  by songjh on 2019-03-17 21:33.
 */
public class SubListFailFast {
    public static void main(String[] args) {
        ArrayList<String> masterList = new ArrayList<String>();
        masterList.add("one");
        masterList.add("two");
        masterList.add("three");
        masterList.add("four");
        masterList.add("five");

        List<String> branchList = masterList.subList(0, 3);

//        masterList.remove(0);
//        masterList.add("ten");
//        masterList.clear();

        branchList.clear();
        branchList.add("six");
        branchList.add("seven");
        branchList.remove(0);

        for (String t : branchList) {
            System.out.println(t);
        }

        System.out.println(masterList);
    }
}
