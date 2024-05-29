import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class VyberPostavy extends JPanel implements ActionListener {
    private RPGHra rpgHra;

    public VyberPostavy(RPGHra rpgHra) {
        this.rpgHra = rpgHra;
        setLayout(null);

        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        int screenWidth = screenSize.width;
        int screenHeight = screenSize.height;

        JButton valecnikButton = new JButton("Válečník");
        JButton kouzelnikButton = new JButton("Kouzelník");

        valecnikButton.addActionListener(this);
        kouzelnikButton.addActionListener(this);

        Font buttonFont = valecnikButton.getFont().deriveFont(Font.PLAIN, 24);
        valecnikButton.setFont(buttonFont);
        kouzelnikButton.setFont(buttonFont);

        int buttonWidth = 200;
        int buttonHeight = 80;
        int buttonX = (screenWidth - buttonWidth) / 2;
        int buttonSpacing = 20;
        int buttonY = (screenHeight - 2 * buttonHeight - buttonSpacing) / 2;

        valecnikButton.setBounds(buttonX, buttonY, buttonWidth, buttonHeight);
        kouzelnikButton.setBounds(buttonX, buttonY + buttonHeight + buttonSpacing, buttonWidth, buttonHeight);

        add(valecnikButton);
        add(kouzelnikButton);
    }

    public void actionPerformed(ActionEvent e) {
        if (e.getActionCommand().equals("Válečník")) {
            rpgHra.setSelectedCharacter("valecnik");
        } else if (e.getActionCommand().equals("Kouzelník")) {
            rpgHra.setSelectedCharacter("kouzelnik");
        }
        rpgHra.startGame();
    }
}
