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

        setLayout(null); // Absolutní pozicování

        // Získání velikosti okna
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        int screenWidth = screenSize.width;
        int screenHeight = screenSize.height;

        // Výpočet pozice tlačítek pro umístění do středu obrazovky
        int buttonWidth = 200; // Zmenšíme šířku tlačítek
        int buttonHeight = 80; // Zmenšíme výšku tlačítek
        int buttonX = (screenWidth - buttonWidth) / 2;
        int spustitButtonY = (screenHeight - 3 * buttonHeight) / 2;
        int odejitButtonY = spustitButtonY + 2 * buttonHeight;

        spustitButton.setBounds(buttonX, spustitButtonY, buttonWidth, buttonHeight);
        odejitButton.setBounds(buttonX, odejitButtonY, buttonWidth, buttonHeight);

        // Zvětšení textu v tlačítkách
        Font buttonFont = spustitButton.getFont().deriveFont(Font.PLAIN, 24); // Zvětšíme text
        spustitButton.setFont(buttonFont);
        odejitButton.setFont(buttonFont);

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
