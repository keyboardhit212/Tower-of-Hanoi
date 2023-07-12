package com.towerofhanoi;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class TowerOfHanoi implements Runnable {

    private Scanner console = new Scanner(System.in);

    private int numOfDiscs;
    private List<Disc> discs;
    private List<Prong> prongs;
    private Prong startProng;
    private Prong destinationProng;

    public TowerOfHanoi(int numOfDiscs) {
        this.discs = new ArrayList<>();
        this.prongs = new ArrayList<>();
        this.numOfDiscs = numOfDiscs;
        createProngs();
        createDiscs();
        setStartingProng();
        setDestinationProng();
    }

    @Override
    public void run() {
        int moves = 0;
        initializeProngs();
        try {
            while (true) {
                displayProngs(moves);
                checkGameOver();
                move();
                moves++;
                Screen.clear();
            }
        } catch (GameOverException e) {
            System.out.println("Game Over! " + e.getMessage());
            System.out.println("You made " + moves + " moves in total!");
            System.exit(1);
        }
    }

    private void checkGameOver() throws GameOverException {
        checkInvalidProngs();
        checkGameComplete();
    }

    private void initializeProngs() {
        try {
            for (int i = discs.size() - 1; i >= 0; i--) {
                this.startProng.put(discs.get(i));
            }
        } catch (Exception e) {
            System.out.println("Something went wrong!");
            System.exit(1);
        }
    }

    private void createProngs() {
        prongs.add(new Prong("A"));
        prongs.add(new Prong("B"));
        prongs.add(new Prong("C"));
    }

    private void createDiscs() {
        for (int i = 0; i < numOfDiscs; i++) {
            discs.add(new Disc());
        }
    }

    private void displayProngs(int moves) {
        System.out.println("\nNumber of Moves: " + moves);
        System.out.println();
        for (Prong prong : this.prongs) {
            prong.display();
        }
    }

    private void setStartingProng() {
        while (true) {
            System.out.print("Prong Starting Point (A, B, C): ");
            String startingPoint = String.valueOf(console.next().charAt(0)).toUpperCase();
            try {
                this.startProng = selectProng(startingPoint);
                break;
            } catch (ProngNotFoundException e) {
                System.out.println("Invalid Choice!");
            }
        }
    }

    private void setDestinationProng() {
        while (true) {
            System.out.print("Prong Destination Point (A, B, C): ");
            String destinationPoint = String.valueOf(console.next().charAt(0)).toUpperCase();
            try {
                Prong destination = selectProng(destinationPoint);
                if (destination.getName().equals(this.startProng.getName()))
                    throw new ProngNotFoundException();
                this.destinationProng = destination;
                break;
            } catch (ProngNotFoundException e) {
                System.out.println("Start and Destination Prong Cannot Be The Same!");
            }
        }
    }

    private Prong selectProng(String prongLetter) throws ProngNotFoundException {
        for (Prong prong : this.prongs) {
            if (prongLetter.equals(prong.getName()))
                return prong;
        }
        throw new ProngNotFoundException();
    }

    private void transfer(Prong fromProng, Prong destinationProng) throws EmptyProngException{
        if (fromProng.isEmpty())
            throw new EmptyProngException();
        Disc selectedDisc = fromProng.get();
        destinationProng.put(selectedDisc);
    }

    private void move() {
        while (true) {
            try {
                System.out.print("Disk from Prong: ");
                Prong fromProng = selectProng(String.valueOf(console.next().charAt(0)).toUpperCase());
                System.out.print("To Prong: ");
                Prong toProng = selectProng(String.valueOf(console.next().charAt(0)).toUpperCase());
                transfer(fromProng, toProng);
                break;
            } catch (ProngNotFoundException e) {
                System.out.println("Prong Not Found!");
            } catch (EmptyProngException e) {
                System.out.println("Selected Prong Is Empty!");
            }
        }
    }

    private void checkInvalidProngs() throws GameOverException {
        for (Prong prong : prongs) {
            if (prong.isInvalid())
                throw new GameOverException("Invalid Stacking of Discs!");
        }
    }

    private void checkGameComplete() throws GameOverException {
        Prong prong = destinationProng;
        if (!prong.isInvalid() && prong.size() == Disc.getCount())
            throw new GameOverException("Congratulations, you finished the game!");
    }

}
