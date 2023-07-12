package com.towerofhanoi;

import java.util.InputMismatchException;
import java.util.Scanner;

public class Main {

    private static Scanner console = new Scanner(System.in);

    public static void main(String[] args) {
        displayBanner();
        int num = setInitialNumberOfDisc();
        TowerOfHanoi game = new TowerOfHanoi(num);
        new Thread(game).start();
    }

    public static void displayBanner() {
        System.out.println("************ Tower of Hanoi ************");
        System.out.println("****************************************");
        System.out.println();
    }

    public static int setInitialNumberOfDisc() {
        int num;
        while (true) {
            try {
                System.out.print("Initial Number of Discs: ");
                num = console.nextInt();
                break;
            } catch (InputMismatchException e) {
                System.out.println("Invalid Input!");
                console.next();
            }
        }
        return num;
    }

}
