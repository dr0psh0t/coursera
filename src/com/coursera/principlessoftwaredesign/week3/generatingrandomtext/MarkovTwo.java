package com.coursera.principlessoftwaredesign.week3.generatingrandomtext;

import java.util.ArrayList;
import java.util.Random;

public class MarkovTwo {

    private Random random;
    private String trainingText;

    public MarkovTwo() {
        random = new Random();
    }

    public void setRandom(int seed) {
        random = new Random(seed);
    }

    public void setTraining(String s) {
        //trainingText = s.trim();
        trainingText = s;
    }

    public String getRandomText(int charsLength) {
        StringBuilder sb = new StringBuilder();

        int randomIndex = random.nextInt(trainingText.length() - 3);
        String key = trainingText.substring(randomIndex, randomIndex + 3);
        sb.append(key);

        for (int k = 0; k < charsLength - 3; k++) {
            ArrayList<String> follows = getFollows(key);

            if (follows.size() == 0) {
                break;
            }

            //  get random index from follows ArrayList
            randomIndex = random.nextInt(follows.size());
            //  get the element called next using the random index from follows ArrayList
            String next = follows.get(randomIndex);
            //  append next to stringbuffer
            sb.append(next);

            //System.out.println(next+" = "+follows);
            key = key.substring(1) + next;
            //System.out.println("key="+key);
        }

        return sb.toString();
    }

    public ArrayList<String> getFollows(String key) {

        ArrayList<String> follows = new ArrayList<>();
        int keyIndex = trainingText.indexOf(key);

        while ((keyIndex != -1)) {
            if (keyIndex+key.length() < trainingText.length()) {
                follows.add(trainingText.substring(keyIndex+key.length(), keyIndex+key.length()+1));
                keyIndex = trainingText.indexOf(key, ++keyIndex);
            } else {
                break;
            }
        }

        return follows;
    }
}
