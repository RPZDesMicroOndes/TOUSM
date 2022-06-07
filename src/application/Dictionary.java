package application;

import java.text.Normalizer;
import java.util.ArrayList;

public class Dictionary {

    private ArrayList<Word> dictionary;

    public Dictionary() {

        this.dictionary = new ArrayList<>();

        initDictionary();
        
    }

    public ArrayList<Word> getDictionary() {

        return dictionary;

    }

    public void addWord(Word word) throws IllegalArgumentException{

        String wordText = word.getword();
        if(wordText != null && wordText.length() >= 6 && wordText.length() <= 9){

            this.dictionary.add(word);

        }else{

            throw new IllegalArgumentException("application - Dictionary - addWord - The word don't respect the words settings.");
            
        }

    }

    public void initDictionary() throws IllegalArgumentException{

        ArrayList<String> wordList = RWFile.readFile("../data/liste_francais.txt");
        for (String word : wordList) {

            if(word.length() >= 6 && word.length() <= 9 && !word.contains("-") && !word.contains(" ") && !word.contains(".")){

                word = word.toUpperCase();

                Word wordToAdd = new Word(word);
                addWord(wordToAdd);

            }
            
        }

    }

    public Word pickRandomWord(){

        int randomIndex = (int) ((Math.random())*(this.dictionary.size()-1));
        return this.dictionary.get(randomIndex);

    }

}
