package application;

public class Word {

    private String word;

    public Word (String word) throws IllegalArgumentException{

        if(word != null && word.length() >= 6 && word.length() <= 9){

            this.word = word;

        }else{

            throw new IllegalArgumentException("application - Word - Word - The word don't respect the words settings.");
            
        }

    }

    public String getWord() {

        return word;

    }
    
}
