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


Sharing here first version of the personnal solution:

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