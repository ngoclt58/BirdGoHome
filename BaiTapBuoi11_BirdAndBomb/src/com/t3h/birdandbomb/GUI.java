package com.t3h.birdandbomb;

import java.awt.CardLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

public class GUI extends JFrame {
	/** */
	private static final long serialVersionUID = 1L;
	private GamePanel myPanel;
	private MenuPanel menuPanel;
	public static final int WIDTH = 600;
	public static final int HEIGHT = 450;

	public GUI() {
		initGUI();
		initComps();
		addComps();
		music();
		clickButton();
	}

	private void clickButton() {
		menuPanel.btIntroduction.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				JOptionPane
						.showMessageDialog(
								null,
								"Bạn dùng các phím ấn lên, xuống, trái, phải để điều khiển cho chú chim di chuyển"
										+ "\n"
										+ "Bạn ăn hoa quả để được tăng điểm"
										+ "\n"
										+ "Bạn va chạm vào các chướng ngại vật sẽ bị mất đi 1 mạng"
										+ "\n"
										+ "Điểm càng cao thì mức độ khó càng tăng"
										+ "\n" + "Chúc bạn chơi game vui vẻ!");
			}

		});

		menuPanel.btInformation.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				JOptionPane
						.showMessageDialog(
								null,
								"Author: Lê Thanh Ngọc"
										+ "\n"
										+ "Email: lethanhngoc672@gmail.com"
										+ "\n"
										+ "Mọi phản hồi của các bạn sẽ được cập nhật trong các phiên bản sau");
			}
		});

		menuPanel.btPlayGame.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				menuPanel.setVisible(false);
				GUI.this.add(myPanel);
				myPanel.setVisible(true);
				myPanel.requestFocusInWindow();
			}
		});
	}

	private void music() {
		Sound_sdjv sound = new Sound_sdjv("D:/ahihi.wav");
		System.out.println("ahihi");
		sound.start();
	}

	private void initComps() {
		menuPanel = new MenuPanel();
		myPanel = new GamePanel();
		myPanel.setVisible(false);
	}

	private void addComps() {
		add(menuPanel);
		add(myPanel);
	}

	private void initGUI() {
		setTitle("Bird Go Home!");
		setSize(WIDTH, HEIGHT);
		setLocationRelativeTo(null);

		setLayout(new CardLayout());
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
}
