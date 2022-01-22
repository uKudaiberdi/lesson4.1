package com.company;

import java.util.Random;

public class Main {
    public static int bossHealth = 700;
    public static int bossDamage = 50;
    public static String bossDefenceType;
    public static int[] heroesHealth = {270, 260, 250,350};
    public static int[] heroesDamage = {15, 20, 25,0};
    public static int medicHeal = 25;
    public static String[] heroesAttackType = {"Physical", "Magical", "Kinetic", "Medic"};
    public static int roundNumber = 0; {
    Random random = new Random();
    int randomHero = random.nextInt(2);
    int randomHeal = random.nextInt(40);
    }
    public static void main(String[] args) {
        printStatistics();
        while (!isGameFinished()) {
            round();
        }
    }

    public static void round() {
        roundNumber++;
        chooseBossDefence();
        bossHits();
        heroesHit();
        medic();
        printStatistics();
    }

    public static void  medic() {
        for (int i = 0; i <heroesHealth.length ; i++) {
            if (heroesHealth[i]!=3 && heroesHealth[i] <100 && heroesHealth[i] >0) {
                heroesHealth[i] += medicHeal;
                break;

            }


        }
    }

    public static void chooseBossDefence() {
        Random random = new Random();
        int randomIndex = random.nextInt(heroesAttackType.length); // 0,1,2
        bossDefenceType = heroesAttackType[randomIndex];
        System.out.println("Boss chose " + bossDefenceType);
    }

    public static void heroesHit() {
        for (int i = 0; i < heroesDamage.length; i++) {
            if (heroesHealth[i] > 0 && bossHealth > 0) {
                if (heroesAttackType[i] == bossDefenceType) {
                    Random random = new Random();
                    int coeff = random.nextInt(8) + 2; // 2,3,4,5,6,7,8,9
                    if (bossHealth - heroesDamage[i] * coeff < 0) {
                        bossHealth = 0;
                    } else {
                        bossHealth = bossHealth - heroesDamage[i] * coeff;
                        // bossHealth -= heroesDamage[i];
                    }
                    System.out.println("Critical damage: " + heroesDamage[i] * coeff);
                } else {
                    if (bossHealth - heroesDamage[i] < 0) {
                        bossHealth = 0;
                    } else {
                        bossHealth = bossHealth - heroesDamage[i];
                        // bossHealth -= heroesDamage[i];
                    }
                }
            }
        }
    }

    public static void bossHits() {
        for (int i = 0; i < heroesHealth.length; i++) {
            if (heroesHealth[i] > 0 && bossHealth > 0) {
                if (heroesHealth[i] - bossDamage < 0) {
                    heroesHealth[i] = 0;
                } else {
                    heroesHealth[i] = heroesHealth[i] - bossDamage;
                    // heroesHealth[i] -= bossDamage;
                }
            }
        }
    }

    public static boolean isGameFinished() {
        if (bossHealth <= 0) {
            System.out.println("Heroes won!!!");
            return true;
        }
        /*if (heroesHealth[0] <= 0 && heroesHealth[1] <= 0 && heroesHealth[2] <= 0) {
            System.out.println("Boss won!!!");
            return true;
        }
        return false;*/
        boolean allHeroesDead = true;
        for (int i = 0; i < heroesHealth.length; i++) {
            if (heroesHealth[i] > 0) {
                allHeroesDead = false;
                break;
            }
        }
        if (allHeroesDead) {
            System.out.println("Boss won!!!");
        }
        return allHeroesDead;
    }

    public static void printStatistics() {
        System.out.println("ROUND " + roundNumber);
        System.out.println("Boss health: " + bossHealth + " [" + bossDamage + "]");
        for (int i = 0; i < heroesHealth.length; i++) {
            System.out.println(heroesAttackType[i] +
                    " health: " + heroesHealth[i] + " ["
                    + heroesDamage[i] + "]");
        }
        System.out.println("______________");
    }
}
