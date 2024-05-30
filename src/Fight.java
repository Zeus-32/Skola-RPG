import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Fight extends JFrame implements ActionListener {
    private Player player;
    private Game game;
    private Enemy enemy;
    private int enemyHealth;
    private int playerHealth;
    private JLabel enemyHealthLabel;
    private JLabel playerHealthLabel;
    private JButton attackButton;

    public Fight(Player player, Game game, Enemy enemy) {
        this.player = player;
        this.game = game;
        this.enemy = enemy;
        this.enemyHealth = 100;
        this.playerHealth = 100;

        setTitle("Fight!");
        setSize(400, 300);
        setLayout(new GridLayout(3, 1));

        enemyHealthLabel = new JLabel("Enemy Health: " + enemyHealth);
        playerHealthLabel = new JLabel("Player Health: " + playerHealth);
        attackButton = new JButton("Attack");

        attackButton.addActionListener(this);

        add(enemyHealthLabel);
        add(playerHealthLabel);
        add(attackButton);

        setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        setLocationRelativeTo(null);
        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == attackButton) {
            enemyHealth -= 10;
            playerHealth -= 5; // Simulate enemy attacking back

            enemyHealthLabel.setText("Enemy Health: " + enemyHealth);
            playerHealthLabel.setText("Player Health: " + playerHealth);

            if (enemyHealth <= 0) {
                JOptionPane.showMessageDialog(this, "You won!");
                enemy.delete();
                dispose(); // Close the window
                game.resumeAfterFight();
            } else if (playerHealth <= 0) {
                JOptionPane.showMessageDialog(this, "You lost!");
                dispose(); // Close the window
                game.resumeAfterFight();
            }
        }
    }
}
