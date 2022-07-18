package com.task;

public class Test {
   public static void main(String[] args) {
      Test test = new Test();
      Class<? extends Test> clazz= test.getClass();
      Class<Boolean> a = boolean.class;
   }
}
