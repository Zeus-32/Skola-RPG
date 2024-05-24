import javax.swing.*;

public class RPGHra extends JFrame {
    public RPGHra() {
        super("RPG Hra");

        MainMenu mainmenu = new MainMenu(this);
        getContentPane().add(mainmenu);

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(1920, 1080);
        setVisible(true);
    }


    public void zobrazVyberPostavy() {
        getContentPane().removeAll();
        VyberPostavy vyberPostavy = new VyberPostavy(this);
        getContentPane().add(vyberPostavy);
        revalidate();
        repaint();
    }

    public void Game() {
        getContentPane().removeAll();
        Game gamePanel = new Game();
        getContentPane().add(gamePanel);
        revalidate();
        repaint();
        gamePanel.requestFocusInWindow();
    }
}
