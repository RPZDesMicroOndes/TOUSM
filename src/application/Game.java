package application;

public class Game {

    private static final String COLOR_RESET = "\u001B[40m";
    private static final String COLOR_MISPLACED = "\u001B[43m";
    private static final String COLOR_WELLPLACED = "\u001B[42m";

    private int attemptNumber;
    private static final int NB_ATTEMPT = 6;
    private char[] wordHint;
    private char[] wordToFind;
    private String[][] attempts;
    private Dictionary dictionary;
    private Word word;

    public Game(Dictionary dictionary) throws IllegalArgumentException{
        
        if(dictionary != null){

            this.dictionary = dictionary;
            this.word = this.dictionary.pickRandomWord();

            this.wordHint = new char[this.word.getword().length()];
            this.wordHint[0] = this.word.getword().charAt(0);

            this.wordToFind = this.word.getword().toCharArray();
            System.out.println(this.word.getword() + " " + this.wordToFind.length);
            this.attempts = new String[this.NB_ATTEMPT][this.wordToFind.length];
            System.out.println(this.attempts.length + " " + this.attempts[0].length);
            initBoard();

            play();
            
        }else{

            throw new IllegalArgumentException("application - Game - Game - The dictionary can't be null");

        }
    

    }

    public void initBoard(){

        for(int i = 0; i < this.attempts.length;i++){

            for(int j = 0; j < attempts[0].length;j++){

                if(i == 0){

                    if(j == 0){

                        this.attempts[i][j] = "\u001B[42m" + this.wordHint[j] + COLOR_RESET;

                    }else{

                        this.attempts[i][j] = ".";

                    }
                    
                }else{

                    this.attempts[i][j] = ".";

                }
            
            }

            System.out.println("");

        }

    }

    public void play(){

        this.attemptNumber = 1;
        boolean wordFound = false;
        String attempt = "";
        boolean inDico = false;

        displayAttempts();

        while(attemptNumber <= this.NB_ATTEMPT && !wordFound){
            do {

                inDico = false;
                attempt = SimpleInput.getString("Your guess ?\n");
                Word attemptWord = new Word(attempt);
                if(this.dictionary.getDictionary().contains(attemptWord)){
                    inDico = true;
                }
                
            } while (attempt.length() == this.wordToFind.length && !attempt.contains(" ") && !attempt.contains("-") && !attempt.contains(".") && attempt.charAt(0) != this.wordToFind[0] && !inDico);
            checkWord(attempt.toCharArray());
            String wordToFind = String.valueOf(this.wordToFind);
            System.out.println(wordToFind);
            refreshAttempts(checkWord(attempt.toCharArray()));
            if(wordToFind.equals(attempt)){

                wordFound = true;

            }else{

                attemptNumber++;

            }
            

        }

    }

    public String[] checkWord(char[] attempt){

        String[] attemptColored = new String[attempt.length];

        for(int i = 0; i < attempt.length; i++){

            if(this.wordToFind[i] == attempt[i]){

                attemptColored[i] =  COLOR_WELLPLACED + attempt[i] + COLOR_RESET;                

            }else{

                attemptColored[i] =  COLOR_RESET + attempt[i] + COLOR_RESET;

            }

        }

        return attemptColored; 

    }

    public void refreshAttempts(String[] attempt){

        for(int j = 0; j < attempts[0].length;j++){

            this.attempts[this.attemptNumber-1][j] = attempt[j];
        
        }

        displayAttempts();

    }

    public int numberOccurence(char c){

        int nbOccurence = 0;

        for(int i = 0;i < this.wordToFind.length;i++){

            if(this.wordToFind[i] == c){

                nbOccurence++;

            }

        }

        return nbOccurence;

    }

    public void displayAttempts(){

        for(int i = 0; i < attempts.length;i++){

            for(int j = 0; j < attempts[0].length;j++){

                System.out.print(attempts[i][j] + " ");
            
            }

            System.out.println("");

        }



    }

}
