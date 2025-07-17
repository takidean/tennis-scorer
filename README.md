# 🎾 Tennis Game Kata

This project implements a simple tennis score computer based on a sequence of points won by players A and B.

---

## 📝 Kata Description

The goal is to simulate a **single tennis game** between two players.

### 🧮 Scoring Rules

- Players start at **0**.
- 1st point → **15**, 2nd → **30**, 3rd → **40**.
- If a player wins a point while at 40 and the opponent has less → **they win**.
- If both players reach 40 → **Deuce**.
- From deuce:
    - The player who wins a point gains **Advantage**.
    - If the same player wins again → **they win**.
    - If the other player wins → back to **Deuce**.

---
---


Quelle est la taille maximale que la chaîne de caractères peut atteindre dans ce kata ? Théoriquement, une String peut contenir jusqu’à 2 milliards de caractères, mais dans le contexte d’un match de tennis, ce serait irréaliste et risqué (risque de OutOfMemoryError).


Q: what design pattern used for this Kata
I used the State design pattern to make the scoring changing and avoid many dependencies and static shared variables
used to make simple architecture to keep open for modification in case we need to develop scorer for all game

Q: Quel est le comportement attendu si la chaîne de caractères reçue contient, par erreur de saisie, d’autres caractères que A ou B (espaces, caractères spéciaux, C, D, etc.) ?
Supposed that extra characters and special characters are typos so add first verification and deleted them
this is done in service class

Q: Que faire si la chaîne contient d’autres occurrences de A ou B après qu’un gagnant a déjà été déterminé selon les règles ?
q:Quelle est la taille maximale que la chaîne de caractères peut atteindre dans ce kata
Supposed that game is in real life conditions => deleted/ stopped the counter if a winner is designed

Q:Why it's coded this way
used Players as Object to be better align with Java principles

Q: why a service class 
In case we will upgrade this kata to a real time game calculator we will keep in the service class a reader
or a consumer/may be we will need a cash or a database to persist data in case we need stateless application.

## ▶️ How to Run

### 1. Compile

bash
mvn clean compile

✅ Tested Scenarios
Score progression for A and B

Deuce / Advantage / Win transitions

Invalid characters ignored

Empty or null input

Long 200-character sequence

---


Sharing here first/Draft version of the  solution:

     Map<String, Integer> map = new HashMap<>();
     boolean isDeuce = false;
     String last = "";

     static void reverse(String input) {
        if (input == null  || input.isEmpty())   {
            return;
        }

        input.replaceAll("[^AB]", "").chars().mapToObj(c -> String.valueOf((char) c))
                .forEach(key -> map.put(key, compute(key)));
 }

    private  Integer compute(String key) {
        int val = map.getOrDefault(key, 0);

        switch (val) {
            case 0 -> {
                logger.info("{} is now 15", key);
                return 15;
            }
            case 15 -> {
                logger.info("{} is now 30", key);
                return 30;
            }
            case 30 -> {
                logger.info("{} is now 40", key);
                last = key;
                return 40;
            }
            case 40 -> {
                if (last.equalsIgnoreCase(key)) {

                    if (!isDeuce) {
                        logger.info("{} is winner", key);
                    }

                    //We can add here also a logger for deuce status but not indicated in the requirement
                    if (isDeuce) {
                        logger.info("deuce");
                    }

                } else {
                    logger.info("{} is advantage", key);
                    isDeuce = false;
                }
                last = key;
                return 40;
            }
            default -> {
                return 0;
            }
        }
    }