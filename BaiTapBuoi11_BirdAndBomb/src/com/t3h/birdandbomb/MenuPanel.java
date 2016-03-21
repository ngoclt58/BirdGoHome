package com.t3h.birdandbomb;

import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

public class MenuPanel extends JPanel {
	/** */
	private static final long serialVersionUID = 1L;
	private JLabel labelTitTe, labelBackground;
	private Font font = new Font("Tahoma", Font.BOLD | Font.ITALIC, 40);
	public JButton btPlayGame, btHighScore, btIntroduction, btInformation;

	public MenuPanel() {
		initMenuPanel();
		initComps();
		addComps();
		clickButton();
	}

	private void clickButton() {
		
	}

	private void addComps() {
		add(labelTitTe);
		add(btPlayGame);
		add(btHighScore);
		add(btIntroduction);
		add(btInformation);
		add(labelBackground);
	}

	private void initComps() {
		String title = "Bird Go Home ";
		FontMetrics fontMetrics = getFontMetrics(font);
		int width = fontMetrics.stringWidth(title);
		int height = fontMetrics.getHeight();
		labelTitTe = new JLabel(title);
		labelTitTe.setFont(font);
		labelTitTe.setForeground(Color.BLUE);
		labelTitTe.setBounds((600 - width) / 2, 70, width, height);

		btPlayGame = new JButton("Play Game");
		btPlayGame.setBounds(200, 150, 200, 30);

		btHighScore = new JButton("High Score");
		btHighScore.setBounds(200, 200, 200, 30);

		btIntroduction = new JButton("Introduction");
		btIntroduction.setBounds(200, 250, 200, 30);

		labelBackground = new JLabel();
		ImageIcon imageBackground = new ImageIcon(getClass().getResource(
				"/image/background.png"));
		labelBackground.setIcon(imageBackground);
		labelBackground.setBounds(0, 0, imageBackground.getIconWidth(),
				imageBackground.getIconHeight());
		
		btInformation = new JButton("Contact With Us");
		btInformation.setBounds(200, 300, 200, 30);
	}

	private void initMenuPanel() {
		setLayout(null);
	}

}
