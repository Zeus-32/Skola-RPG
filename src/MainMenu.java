import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class MainMenu extends JPanel implements ActionListener {
    private RPGHra rpgHra;
    private JButton spustitButton;
    private JButton odejitButton;

    public MainMenu(RPGHra rpgHra) {
        this.rpgHra = rpgHra;

        spustitButton = new JButton("Spustit");
        spustitButton.addActionListener(this);

        odejitButton = new JButton("Odejít");
        odejitButton.addActionListener(this);

        setLayout(null);

        int screenWidth = 1920;
        int screenHeight = 1080;

        Font buttonFont = spustitButton.getFont().deriveFont(Font.PLAIN, 24);
        spustitButton.setFont(buttonFont);
        odejitButton.setFont(buttonFont);

        int buttonWidth = 200;
        int buttonHeight = 80;
        int buttonX = (screenWidth - buttonWidth) / 2;
        int buttonSpacing = 20;
        int buttonY = (screenHeight - 2 * buttonHeight - buttonSpacing) / 2;

        spustitButton.setBounds(buttonX, buttonY, buttonWidth, buttonHeight);
        odejitButton.setBounds(buttonX, buttonY + buttonHeight + buttonSpacing, buttonWidth, buttonHeight);

        add(spustitButton);
        add(odejitButton);
    }

    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == spustitButton) {
            rpgHra.zobrazVyberPostavy();
        } else if (e.getSource() == odejitButton) {
            System.exit(0);
        }
    }
}
