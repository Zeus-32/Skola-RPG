import javax.swing.*;

public class RPGHra extends JFrame {
    private String selectedCharacter;

    public RPGHra() {
        super("RPG Hra");

        MainMenu mainmenu = new MainMenu(this);
        getContentPane().add(mainmenu);

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        setVisible(true);
    }

    public void zobrazVyberPostavy() {
        getContentPane().removeAll();
        VyberPostavy vyberPostavy = new VyberPostavy(this);
        getContentPane().add(vyberPostavy);
        revalidate();
        repaint();
    }

    public void startGame() {
        getContentPane().removeAll();
        Game gamePanel = new Game(selectedCharacter);
        getContentPane().add(gamePanel);
        revalidate();
        repaint();
        gamePanel.requestFocusInWindow();
    }

    public void setSelectedCharacter(String character) {
        this.selectedCharacter = character;
    }
}
