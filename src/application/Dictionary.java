package application;

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

        String wordText = word.getWord();
        if(wordText != null && wordText.length() >= 6 && wordText.length() <= 9){

            this.dictionary.add(word);

        }else{

            throw new IllegalArgumentException("application - Dictionary - addWord - The word don't respect the words settings.");
            
        }

    }

    public void testDictionary(){

        for(Word word : this.dictionary){

            System.out.println(word.getWord());

        }

    }

    public void initDictionary() throws IllegalArgumentException{

        ArrayList<String> wordList = RWFile.readFile("data/liste_francais_v2.txt");
        for (String word : wordList) {

            if(word.length() >= 6 && word.length() <= 9 && !word.contains("-") && !word.contains(" ") && !word.contains(".")){

                word = word.toUpperCase();

                Word wordToAdd = new Word(word);
                addWord(wordToAdd);

            }
            
        }

    }

    public Word pickRandomWord() {

        int randomIndex = (int) (Math.random() * dictionary.size());
        return dictionary.get(randomIndex);

    }

    public void testPickRandomWord() {

        Word word = pickRandomWord();
        System.out.println(word.getWord());

    }

    public boolean inDictionary(String word) {

        boolean inDictionary = false;

        for(Word w : dictionary){

            if(w.getWord().equals(word)){

                inDictionary = true;

            }

        }

        return inDictionary;

    }

}
