
import java.util.Scanner;
import java.time.LocalTime;
public class Main {
    public static void main(String[] args) {
        KeyListenerTester game = new KeyListenerTester("Crystal Game");
        
        Scanner scnr = new Scanner(System.in);
        
        System.out.println("Enter a number for the game size (ZERO FOR DEFAULT GAME)");
        int gameSize = scnr.nextInt();
        
        Timer timer = new Timer();
        game.getTimer(timer);
        
        if (gameSize == 0) {
            gameSize = 7;
            int adversaries = 3;
            game.createField(gameSize);
            game.startPosition(3, 3);
            for (int i = 0; i < adversaries; ++i) {
                game.spawnEnemy(game.field);
            }
            
            game.showField(gameSize);
        } else {
            System.out.println("Enter a number of adversaries");
            int adversaries = scnr.nextInt();
            game.createField(gameSize);
            game.startPosition(gameSize/2, gameSize/2);
            if (adversaries > (gameSize * gameSize) - 2){
                adversaries = (gameSize * gameSize) - 2;   
            }
            for (int i = 0; i < adversaries; ++i) {
                game.spawnEnemy(game.field);
            }
            game.showField(gameSize);
 
        }
     
        scnr.close();
    }

    
}


