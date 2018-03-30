package com.hsy.record.test;

/**
 * Created by developer2 on 2018/3/27.
 */
public class Box<T> {

    private T t;

    public T getT() {
        return t;
    }

    public void setT(T t) {
        this.t = t;
    }

    public static void main(String[] args){
        Box<Integer> a = new Box<>();
    }
}
