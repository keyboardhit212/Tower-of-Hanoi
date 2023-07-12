package com.towerofhanoi;

import java.util.EmptyStackException;
import java.util.Stack;

public class Prong {

    private String name;
    private Stack<Disc> stack;

    public Prong(String name) {
        this.name = name;
        this.stack = new Stack<>();
    }

    public String getName() {
        return new String(this.name);
    }

    public void put(Disc disc) {
        this.stack.push(disc);
    }

    public Disc get() throws EmptyProngException{
        try {
            return this.stack.pop();
        } catch (EmptyStackException e) {
            throw new EmptyProngException();
        }
    }

    public Disc get(int num) throws EmptyProngException {
        try {
            return this.stack.get(num);
        } catch (EmptyStackException e) {
            throw new EmptyProngException();
        }
    }

    public void display() {
        System.out.println(this.getName());
        System.out.println(paint());
    }

    public boolean isEmpty() {
        return this.stack.isEmpty();
    }

    public int size() {
        return this.stack.size();
    }

    public boolean isInvalid() {
        try {
            Disc initialDisc = stack.get(0);
            for (Disc disc : stack) {
                if (disc.getNumber() > initialDisc.getNumber())
                    return true;
                else
                    initialDisc = disc;
            }
        } catch (IndexOutOfBoundsException e) {
            return false;
        }
        return false;
    }

    private String paint() {
        StringBuilder figure = new StringBuilder();
        for (int i = Disc.getCount() - 1; i >= 0; i--) {
            try {
                Disc disc = this.stack.get(i);
                String formatted = String.format("%s%s|%s", getSpaces(disc.getNumber()), disc, disc);
                figure.append(formatted);
            } catch (ArrayIndexOutOfBoundsException e) {
                String formatted = String.format("%s%s|%s", getSpaces(0), "", "");
                figure.append(formatted);
            }
            figure.append("\n");
        }
        return figure.toString();
    }

    private String getSpaces(int num) {
        StringBuilder spaces = new StringBuilder();
        for (int i = num; i < Disc.getCount(); i++) {
            spaces.append(" ");
        }
        return spaces.toString();
    }

}
