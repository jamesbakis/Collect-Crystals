import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import java.util.Random;
public class KeyListenerTester extends JFrame implements KeyListener {

    JLabel label;
    int gameSize;
    String[][] field;
    int row;
    int column;
    int[] crystalSpawn = new int[2];
    int[] enemySpawn = new int[2];
    int score = 0;
    Enemy[] enemies = new Enemy[1000];
    int enemyCount = 0;
    Timer timer;

    public void getTimer(Timer timer) {
        this.timer = timer;
    }

    public void createField(int gameSize) {
        this.gameSize = gameSize;
        String[][] field = new String[gameSize][gameSize];
        for (int i = 0; i < gameSize; ++i) {
            for (int j = 0; j < gameSize; ++j) {
                field[i][j] = "[  ]";
                // System.out.print(field[i][j]);
            }
            System.out.println();
        }
        
        
        this.field = field;
        spawnCrystal(this.field);
    }

    public void showField(int gameSize) {
        // System.out.println("\n\n\n\n\n\n\n\n\n\n");
        for (int i = 0; i < gameSize; ++i) {
            for (int j = 0; j < gameSize; ++j) {
                System.out.print(field[i][j]);
            }
            System.out.println();
        }
        System.out.println("SCORE: " + score);
    }

    public void startPosition(int row, int column) {
        this.row = row - 1;
        this.column = column -1;
        field[this.row][this.column] = "[o7]";

    }

    public void changePosition(int rowChange, int columnChange) {
        field[this.row][this.column] = "[  ]";
        
        //Left right code
        if (rowChange == 0) {
            if  (columnChange == 1) {
                if (this.column < gameSize - 1) {
                    field[this.row][this.column + columnChange] = "[o7]";
                    this.column += columnChange;
                    if (this.row == crystalSpawn[0] && this.column == crystalSpawn[1]) {
                        score += 1;
                        spawnCrystal(field);
                    }
                } else {
                    field[this.row][0] = "[o7]";
                    this.column = 0;
                    if (this.row == crystalSpawn[0] && this.column == crystalSpawn[1]) {
                        score += 1;
                        spawnCrystal(field);
                    }
                }
            } else {
                if (this.column > 0) {
                    field[this.row][this.column + columnChange] = "[o7]";
                    this.column += columnChange;
                    if (this.row == crystalSpawn[0] && this.column == crystalSpawn[1]) {
                        score += 1;
                        spawnCrystal(field);
                    }
                } else {
                    field[this.row][gameSize - 1] = "[o7]";
                    this.column = gameSize - 1;
                    if (this.row == crystalSpawn[0] && this.column == crystalSpawn[1]) {
                        score += 1;
                        spawnCrystal(field);
                    }
                }
            }
                
        }
        //Up down code
        if (columnChange == 0) {
            if  (rowChange == 1) {
                if (this.row > 0) {
                    field[this.row - rowChange][this.column] = "[o7]";
                    this.row -= rowChange;
                    if (this.row == crystalSpawn[0] && this.column == crystalSpawn[1]) {
                        score += 1;
                        spawnCrystal(field);
                    }
                } else {
                    field[gameSize - 1][this.column] = "[o7]";
                    this.row = gameSize - 1;
                    if (this.row == crystalSpawn[0] && this.column == crystalSpawn[1]) {
                        score += 1;
                        spawnCrystal(field);
                    }
                }
            } else {
                if (this.row < gameSize - 1) {
                    field[this.row - rowChange][this.column] = "[o7]";
                    this.row -= rowChange;
                    if (this.row == crystalSpawn[0] && this.column == crystalSpawn[1]) {
                        score += 1;
                        spawnCrystal(field);
                    }
                } else {
                    field[0][this.column] = "[o7]";
                    this.row = 0;
                    if (this.row == crystalSpawn[0] && this.column == crystalSpawn[1]) {
                        score += 1;
                        spawnCrystal(field);
                    }
                }
                
            }
                
        }
        
    }

    public void spawnCrystal(String[][] field) {
        Random rand = new Random();
        
        do {
            crystalSpawn[0] = rand.nextInt(gameSize);
            crystalSpawn[1] = rand.nextInt(gameSize);
        } while (crystalSpawn[0] == this.row && crystalSpawn[1] == this.column || field[crystalSpawn[0]][crystalSpawn[1]].equals("[<>]"));
        field[crystalSpawn[0]][crystalSpawn[1]] = "[**]";
        // System.out.print("Crystal spawn is at " + crystalSpawn[0] + ", " + crystalSpawn[1]);
    }

    public void spawnEnemy(String[][] field) {
        Enemy enemy = new Enemy(); 
        enemies[enemyCount] = enemy;
        
        Random rand = new Random();
        do {
            enemies[enemyCount].position[0] = rand.nextInt(gameSize);
            enemies[enemyCount].position[1] = rand.nextInt(gameSize);
        } while (field[enemies[enemyCount].position[0]][enemies[enemyCount].position[1]].equals("[**]") || 
        (field[enemies[enemyCount].position[0]][enemies[enemyCount].position[1]].equals("[<>]")) ||
        field[enemies[enemyCount].position[0]][enemies[enemyCount].position[1]].equals("[o7]") );
        
        field[enemy.position[0]][enemy.position[1]] = "[<>]";
        enemyCount += 1;
        // System.out.println(enemies[0]);

    }

    public void updateEnemy() {
        Random rand = new Random();
        System.out.println("Update");
        System.out.println(enemies[0].position[0]);
        for (int i = 0; i < enemyCount; ++i) {
            int move = rand.nextInt(4);
            int restoreRow = enemies[i].position[0];
            int restoreColumn = enemies[i].position[1];
            
            field[enemies[i].position[0]][enemies[i].position[1]] = "[  ]";
            if (enemies[i].position[0] == row && enemies[i].position[1] == column) {
                field[enemies[i].position[0]][enemies[i].position[1]] = "[o7]";    
            }
            
                
                if (move == 0) {
                    if (enemies[i].position[0] == 0) {
                        enemies[i].position[0] = gameSize - 1;
                    } else {
                        enemies[i].position[0] -= 1;
                    }   
                }
        
                if (move == 2) {
                    if (enemies[i].position[0] == gameSize -1) {
                        enemies[i].position[0] = 0;
                    } else {
                        enemies[i].position[0] += 1;
                    }
                }
        
                if (move == 1) {
                    if (enemies[i].position[1] == gameSize -1 ) {
                        enemies[i].position[1] = 0;
                    } else {
                        enemies[i].position[1] += 1;
                    }
                }
        
                if (move == 3) {
                    if (enemies[i].position[1] == 0 ) {
                        enemies[i].position[1] = gameSize -1;
                    } else {
                        enemies[i].position[1] -= 1;
                    }
                }
           if (field[enemies[i].position[0]][enemies[i].position[1]].equals("[<>]") ) {
                        enemies[i].position[0] = restoreRow;
                        enemies[i].position[1] = restoreColumn;              
                }
            
        
            
            field[enemies[i].position[0]][enemies[i].position[1]] = "[<>]";
            if (enemies[i].position[0] == crystalSpawn[0] && enemies[i].position[1] == crystalSpawn[1]) {
                score -= 1;
                spawnCrystal(field);
            }

            if(enemies[i].position[0] == row && enemies[i].position[1] == column) {
                field[row][column] = "[XX]";
                showField(gameSize);
                
                System.out.print("YOU LOSE");
                System.exit(0);
            }
    
        }
        



    }

    public KeyListenerTester(String s) {
        super(s);
        JPanel p = new JPanel();
        label = new JLabel("KEEP THIS OPEN INFRONT OF THE GAME");
        p.add(label);
        add(p);
        label = new JLabel("PRESS ARROW KEYS TO MOVE AROUND");
        p.add(label);
        add(p);
        
        addKeyListener(this);
        setSize(400, 100);
        setVisible(true);

    }

    @Override
    public void keyTyped(KeyEvent e) {

        // if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
        //     System.out.println("Right key typed");
        // }
        // if (e.getKeyCode() == KeyEvent.VK_LEFT) {
        //     System.out.println("Left key typed");
        // }

    }

    @Override
    public void keyPressed(KeyEvent e) {

        if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
            System.out.println("Right key pressed");
            changePosition(0, 1);
            updateEnemy();
            showField(gameSize);
            timer.timeLeft();
        }
        if (e.getKeyCode() == KeyEvent.VK_LEFT) {
            System.out.println("Left key pressed");
            changePosition(0, -1);
            updateEnemy();
            showField(gameSize);
            timer.timeLeft();
        }
        if (e.getKeyCode() == KeyEvent.VK_UP) {
            System.out.println("Up key pressed");
            changePosition(1, 0);
            updateEnemy();
            showField(gameSize);
            timer.timeLeft();
        }
        if (e.getKeyCode() == KeyEvent.VK_DOWN) {
            System.out.println("Down key pressed");
            changePosition(-1, 0);
            updateEnemy();
            showField(gameSize);
            timer.timeLeft();
        }

    }

    @Override
    public void keyReleased(KeyEvent e) {
        // if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
        //     System.out.println("Right key Released");
        // }
        // if (e.getKeyCode() == KeyEvent.VK_LEFT) {
        //     System.out.println("Left key Released");
        // }
    }

    
}