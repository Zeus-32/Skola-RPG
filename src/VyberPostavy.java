import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class VyberPostavy extends JPanel implements ActionListener {
    private RPGHra rpgHra;

    public VyberPostavy(RPGHra rpgHra) {
        this.rpgHra = rpgHra; // Uložení instance RPGHra
        setLayout(null); // Absolutní pozicování

        // Pevně nastavená velikost obrazovky
        int screenWidth = 1920;  // Šířka obrazovky
        int screenHeight = 1080; // Výška obrazovky

        // Vytvoření tlačítek
        JButton valecnikButton = new JButton("Válečník");
        JButton kouzelnikButton = new JButton("Kouzelník");

        // Přidání ActionListeneru
        valecnikButton.addActionListener(this);
        kouzelnikButton.addActionListener(this);

        // Zvětšení textu v tlačítcích
        Font buttonFont = valecnikButton.getFont().deriveFont(Font.PLAIN, 24); // Zvětšíme text
        valecnikButton.setFont(buttonFont);
        kouzelnikButton.setFont(buttonFont);

        // Výpočet pozice tlačítek pro umístění do středu obrazovky
        int buttonWidth = 200; // Šířka tlačítek
        int buttonHeight = 80; // Výška tlačítek
        int buttonX = (screenWidth - buttonWidth) / 2;
        int buttonSpacing = 20; // Mezera mezi tlačítky
        int buttonY = (screenHeight - 2 * buttonHeight - buttonSpacing) / 2; // Pozice prvního tlačítka

        // Nastavení pozic tlačítek
        valecnikButton.setBounds(buttonX, buttonY, buttonWidth, buttonHeight);
        kouzelnikButton.setBounds(buttonX, buttonY + buttonHeight + buttonSpacing, buttonWidth, buttonHeight);

        // Přidání tlačítek na panel
        add(valecnikButton);
        add(kouzelnikButton);
    }

    public void actionPerformed(ActionEvent e) {
        if (e.getActionCommand().equals("Válečník")){
            //vymyslet to abych si vybral postavu
        }
        else if (e.getActionCommand().equals("Kouzelník")) {
            //vymyslet to abych si vybral postavu
        }
        rpgHra.Game();
    }
}
