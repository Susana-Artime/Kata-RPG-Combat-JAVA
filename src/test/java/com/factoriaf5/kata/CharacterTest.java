package com.factoriaf5.kata;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

public class CharacterTest {

    private Character meleeCharacter;
    private Character rangedCharacter;
    private ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    private PrintStream originalOut = System.out;

    @BeforeEach
    public void setUp() {
        
        System.setOut(new PrintStream(outContent));
        meleeCharacter = new Character(true);
        rangedCharacter = new Character(false);
    }


    @Test
    public void testCharacterCreation() {

        assertThat(meleeCharacter.getHealth(), is(1000));
        assertThat(meleeCharacter.getLevel(), is(1));
        assertThat(meleeCharacter.isAlive(), is(true));
        assertThat(meleeCharacter.getAttackRange(), is(2));
        assertThat(meleeCharacter.isMelee(), is(true));


        assertThat(rangedCharacter.getHealth(), is(1000));
        assertThat(rangedCharacter.getLevel(), is(1));
        assertThat(rangedCharacter.isAlive(), is(true));
        assertThat(rangedCharacter.getAttackRange(), is(20));
        assertThat(rangedCharacter.isMelee(), is(false));
    }

        
    @Test
    public void testDealDamageAndDeath() {

        rangedCharacter.dealDamage(meleeCharacter, 100, 1);
        assertThat(rangedCharacter.getHealth(), is(1000));

        meleeCharacter.dealDamage(rangedCharacter, 300, 2);
        assertThat(rangedCharacter.getHealth(), is(700));

        meleeCharacter.dealDamage(rangedCharacter, 800, 2);
        assertThat(rangedCharacter.getHealth(), is(0));
        assertThat(rangedCharacter.isAlive(), is(false));
    }

    @Test
    public void testCannotDealDamageToSelf() {

        meleeCharacter.dealDamage(meleeCharacter, 500, 0);
        assertThat(meleeCharacter.getHealth(), is(1000));

        rangedCharacter.dealDamage(rangedCharacter, 500, 0);
        assertThat(rangedCharacter.getHealth(), is(1000));
    }


    @Test
    public void testHealSelfAndHealthLimit() {

    meleeCharacter.dealDamage(rangedCharacter, 500, 2);
    assertThat(rangedCharacter.getHealth(), is(500));

    
    rangedCharacter.heal(200);
    assertThat(rangedCharacter.getHealth(), is(700));

    
    rangedCharacter.heal(400);
    assertThat(rangedCharacter.getHealth(), is(1000));
    }

    

    @Test
    public void testHealDoesNotChangeHealthOfDeadCharacter() {

        meleeCharacter.dealDamage(rangedCharacter, 1000, 2);
        assertThat(rangedCharacter.isAlive(), is(false));
        rangedCharacter.heal(500);
        assertThat(rangedCharacter.getHealth(), is(0));

          
        rangedCharacter.dealDamage(meleeCharacter, 1000, 20);
        assertThat(meleeCharacter.isAlive(), is(false));
        meleeCharacter.heal(500);
        assertThat(meleeCharacter.getHealth(), is(0));
    }

    
    @Test
    public void testCannotHealDeadCharacter() {
        meleeCharacter.dealDamage(rangedCharacter, 1000, 2);
        assertThat(rangedCharacter.isAlive(), is(false));

        rangedCharacter.heal(200);
        assertThat(rangedCharacter.getHealth(), is(0));
    }

    
    @Test
    public void testDamageModificationByLevelDifference() {

        
        meleeCharacter.dealDamage(rangedCharacter, 200, 2);
        assertThat(rangedCharacter.getHealth(), is(800));
    
        
        rangedCharacter.setLevel(meleeCharacter.getLevel() + 5);
    

        meleeCharacter.dealDamage(rangedCharacter, 100, 2);
        assertThat(rangedCharacter.getHealth(), is(750));

    }

    @Test
    public void testAttackRange() {
        meleeCharacter.dealDamage(rangedCharacter, 200, 3);
        assertThat(rangedCharacter.getHealth(), is(1000));

        rangedCharacter.dealDamage(meleeCharacter, 300, 15);
        assertThat(meleeCharacter.getHealth(), is(700));
    }

    @Test
    public void testDamageIncreaseForLowerLevelTarget() {
        meleeCharacter.setLevel(6);
        rangedCharacter.setLevel(1);
    
        meleeCharacter.dealDamage(rangedCharacter, 200, 2);
        assertThat(rangedCharacter.getHealth(), is(700));
    }

    @Test
    public void testReceiveDamageWhenAlive() {

        meleeCharacter.receiveDamage(300);
        assertThat(meleeCharacter.getHealth(), is(700));
        assertThat(meleeCharacter.isAlive(), is(true));
        
        rangedCharacter.receiveDamage(300);
        assertThat(rangedCharacter.getHealth(), is(700));
        assertThat(rangedCharacter.isAlive(), is(true));
    }

    @Test
    public void testReceiveDamageWhenDead() {

        meleeCharacter.receiveDamage(1000);
        assertThat(meleeCharacter.getHealth(), is(0));
        assertThat(meleeCharacter.isAlive(), is(false));

        meleeCharacter.receiveDamage(100);
        assertThat(meleeCharacter.getHealth(), is(0));
        assertThat(meleeCharacter.isAlive(), is(false));

        rangedCharacter.receiveDamage(1000);
        assertThat(rangedCharacter.getHealth(), is(0));
        assertThat(rangedCharacter.isAlive(), is(false));

        rangedCharacter.receiveDamage(100);
        assertThat(rangedCharacter.getHealth(), is(0));
        assertThat(rangedCharacter.isAlive(), is(false));
    }
    

    @Test
    public void testSetLevel() {
        meleeCharacter.setLevel(5);
        assertThat(meleeCharacter.getLevel(), is(5));
    }

    @Test
    public void testSetHealth() {
        meleeCharacter.setHealth(800);
        assertThat(meleeCharacter.getHealth(), is(800));
    }

    @Test
    public void testSetAlive() {
        meleeCharacter.setAlive(false);
        assertThat(meleeCharacter.isAlive(), is(false));
    }
    @Test
    public void testSetAttackRange() {
        meleeCharacter.setAttackRange(5);
        assertThat(meleeCharacter.getAttackRange(), is(5));
    }

    
    @Test
    public void testIsMelee() {
        assertThat(meleeCharacter.isMelee(), is(true));
        assertThat(rangedCharacter.isMelee(), is(false));
    }

   
    @Test
    public void testSetMelee() {
        rangedCharacter.setMelee(true);
        assertThat(rangedCharacter.isMelee(), is(true));
        assertThat(rangedCharacter.getAttackRange(), is(2));

        meleeCharacter.setMelee(false);
        assertThat(meleeCharacter.isMelee(), is(false));
        assertThat(meleeCharacter.getAttackRange(), is(20));
    }
    @Test
    public void testDisplayStatus() {
        meleeCharacter.displayStatus();
        
        String output = outContent.toString().trim();
        assertThat(output, containsString("Health: 1000"));
        assertThat(output, containsString("Level: 1"));
        assertThat(output, containsString("Alive: true"));
        assertThat(output, containsString("attackRange: 2"));
        assertThat(output, containsString("isMelee= true"));

        
        outContent.reset();
        
        rangedCharacter.displayStatus();

        output = outContent.toString().trim();
        assertThat(output, containsString("Health: 1000"));
        assertThat(output, containsString("Level: 1"));
        assertThat(output, containsString("Alive: true"));
        assertThat(output, containsString("attackRange: 20"));
        assertThat(output, containsString("isMelee= false"));
    }

    @AfterEach
    public void tearDown() {
        
        System.setOut(originalOut);
    }
}

