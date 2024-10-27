package com.factoriaf5.kata;
 
public final class App {
    private App() {
    }

    public static void main(String[] args) {
        
        Character hero = new Character();
        Character villain = new Character();
        
        
        villain.setLevel(6);
        hero.setLevel(1);

        
        hero.dealDamage(villain, 200);
        villain.displayStatus();

        
        villain.heal();
        villain.displayStatus();

        
        hero.setLevel(8);

        
        hero.dealDamage(villain, 200);
        villain.displayStatus();

        
        villain.dealDamage(hero, 1000);
        hero.displayStatus();
        villain.heal();
    }

}

