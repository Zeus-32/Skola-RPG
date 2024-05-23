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

        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        int screenWidth = screenSize.width;
        int screenHeight = screenSize.height;

        Font buttonFont = spustitButton.getFont().deriveFont(Font.PLAIN, 24); // Zvětšíme text
        spustitButton.setFont(buttonFont);
        odejitButton.setFont(buttonFont);

        int buttonWidth = 200; // Šířka tlačítek
        int buttonHeight = 80; // Výška tlačítek
        int buttonX = (screenWidth - buttonWidth) / 2;
        int buttonSpacing = 20; // Mezera mezi tlačítky
        int buttonY = (screenHeight - 2 * buttonHeight - buttonSpacing) / 2; // Pozice prvního tlačítka

        // Nastavení pozic tlačítek
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
