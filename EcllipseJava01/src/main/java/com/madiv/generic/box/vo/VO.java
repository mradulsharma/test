package com.madiv.generic.box.vo;

public class VO<T> {
    private T t;

    public void set(T t) { this.t = t; }
    public T get() { return t; }
}