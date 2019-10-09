package org.docksidestage.bizfw.basic.objanimal;

public class PhilippineMonkeyEatingEagle extends Animal implements Flier {

    @Override
    protected String getBarkWord() {
        return "kikikiki-ki-ki-kuk...kuk...kuk...kuh.";
    }

    @Override
    public int numberOfWings() {
        return 2;
    }

    @Override
    public String wingType() {
        return "feather";
    }

    @Override
    public void fly(int distance) {
        for (int i = 1; i <= distance; i++) {
            postProcessMuscularExertion();
        }
    }
}
