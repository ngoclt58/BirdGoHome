package com.t3h.birdandbomb;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.Random;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

public class GamePanel extends JPanel {
	/** */
	private static final long serialVersionUID = 1L;
	private static final int SPEED_ROCKET = 5;
	private static final int SPEED_BIRD = 20;
	private static final int SPEED_BOMB = 5;
	private static final int SPEED_HUNTER = 5;
	private static final int SPEED_ITEMHEALTH = 2;
	private Sound_sdjv sound;
	public int highScore = 0;
	private boolean bomb2 = false;
	private int countHeart = 3;
	private int score = 0;
	private boolean muscleBird = false;
	private Thread th1, th2;
	private boolean bombMove = true; // go down
	private boolean bombMove2 = true;
	private boolean rocket1 = false, rocket2 = false, rocket2Move = true,
			hunter = false, hunterMove = true, rocket1Move = true;
	private boolean itemHealth = false;
	private int birdMove = 0; // 0: right - 1: left - 2: up - 3:down
	private ImageIcon imageIcon, imageIcon2, imageIcon3, imageIcon4,
			imageIcon5, imageIcon6, imageIcon7, imageIcon8, imageIcon9,
			imageIcon10, imageHeart, imageBackground, imageRocket,
			imageRocketUp, imageRocket2, imageRocket2Up, imageHunter, imageItemHealth;
	private JLabel labelBird, labelStrawberry, labelBomb, labelBomb2,
			labelHome, labelMuscle, labelBackground, labelHeart1, labelHeart2,
			labelHeart3, labelHeart4, labelRocket, labelRocket2, labelHunter, labelItemHealth;
	private JLabel labelScore;

	public GamePanel() {
		initMyPanel();
		initComps();
		addComps();
		bombMove();
		pressKeyBoard();
	}

	private void calculateHeart(JLabel label) {
		if (countHeart > 0) {
			if (countHeart == 4) {
				labelHeart4.setVisible(true);
				labelHeart3.setVisible(true);
				labelHeart2.setVisible(true);
				labelHeart1.setVisible(true);
			}
			if (countHeart == 3) {
				labelHeart4.setVisible(false);
				labelHeart3.setVisible(true);
				labelHeart2.setVisible(true);
				labelHeart1.setVisible(true);
			}
			if (countHeart == 2) {
				labelHeart4.setVisible(false);
				labelHeart3.setVisible(false);
				labelHeart2.setVisible(true);
				labelHeart1.setVisible(true);
			}
			if (countHeart == 1) {
				labelHeart4.setVisible(false);
				labelHeart3.setVisible(false);
				labelHeart2.setVisible(false);
				labelHeart1.setVisible(true);
			}
			labelBird.setLocation(20, 250);
			label.setVisible(true);
		}
		if (countHeart == 0) {
			labelHeart4.setVisible(false);
			labelHeart3.setVisible(false);
			labelHeart2.setVisible(false);
			labelHeart1.setVisible(false);
			labelBird.setIcon(imageIcon8);
			label.setVisible(false);
			JOptionPane
					.showMessageDialog(null, "Game Over");
			createHighScore();

		}
	}
	

	private void pressKeyBoard() {
		KeyAdapter keyAdapter = new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if (e.getKeyCode() == KeyEvent.VK_LEFT) {
					moveLeft();
				}
				if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
					moveRight();
				}
				if (e.getKeyCode() == KeyEvent.VK_UP) {
					moveUp();
				}
				if (e.getKeyCode() == KeyEvent.VK_DOWN) {
					moveDown();
				}
			}
		};
		addKeyListener(keyAdapter);
	}

	private int randFruit(int min, int max) {
		Random r = new Random();
		int range = max - min + 1;
		int randNum = min + r.nextInt(range);
		return randNum;
	}

	private boolean collision(ImageIcon icon, JLabel label) {
		if ((labelBird.getX() + imageIcon.getIconWidth() >= label.getX()
				&& labelBird.getX() + imageIcon.getIconWidth() <= label.getX()
						+ icon.getIconWidth()
				&& labelBird.getY() + imageIcon.getIconHeight() >= label.getY() && labelBird
				.getY() + imageIcon.getIconWidth() <= label.getY()
				+ icon.getIconHeight())
				|| (labelBird.getX() >= label.getX()
						&& labelBird.getX() <= label.getX()
								+ icon.getIconWidth()
						&& labelBird.getY() + imageIcon.getIconHeight() >= label
								.getY() && labelBird.getY()
						+ imageIcon.getIconWidth() <= label.getY()
						+ icon.getIconHeight())
				|| (labelBird.getX() + imageIcon.getIconWidth() >= label.getX()
						&& labelBird.getX() + imageIcon.getIconWidth() <= label
								.getX() + icon.getIconWidth()
						&& labelBird.getY() >= label.getY() && labelBird.getY() <= label
						.getY() + icon.getIconHeight())
				|| (labelBird.getX() >= label.getX()
						&& labelBird.getX() <= label.getX()
								+ icon.getIconWidth()
						&& labelBird.getY() >= label.getY() && labelBird.getY() <= label
						.getY() + icon.getIconHeight())) {
			label.setVisible(false);
			sound = new Sound_sdjv("D:/bum.wav");
			sound.start();
			return true;
		} else
			return false;
	}

	private void bombMove() {
		th1 = new Thread(runnable);
		th1.start();
	}

	private void addComps() {
		add(labelBird);
		add(labelBomb);
		add(labelStrawberry);
		add(labelHome);
		add(labelScore);
		add(labelMuscle);
		add(labelHeart1);
		add(labelHeart2);
		add(labelHeart3);
		add(labelHeart4);
		add(labelBomb2);
		add(labelRocket);
		add(labelRocket2);
		add(labelHunter);
		add(labelItemHealth);
		add(labelBackground);
	}

	private void initComps() {
		labelBird = new JLabel();
		imageIcon = new ImageIcon(getClass().getResource(
				"/image/bird_goright.png"));
		labelBird.setIcon(imageIcon);
		labelBird.setBounds(20, 250, imageIcon.getIconWidth(),
				imageIcon.getIconHeight());

		labelBomb = new JLabel();
		imageIcon2 = new ImageIcon(getClass().getResource("/image/bomb.png"));
		labelBomb.setIcon(imageIcon2);
		labelBomb.setBounds(170, 50, imageIcon2.getIconWidth(),
				imageIcon2.getIconHeight());

		labelBomb2 = new JLabel();
		labelBomb2.setIcon(imageIcon2);
		labelBomb2.setBounds(270, 50, imageIcon2.getIconWidth(),
				imageIcon2.getIconHeight());
		labelBomb2.setVisible(false);

		labelStrawberry = new JLabel();
		imageIcon4 = new ImageIcon(getClass().getResource(
				"/image/strawberry.png"));
		labelStrawberry.setIcon(imageIcon4);
		labelStrawberry.setBounds(300, 250, imageIcon4.getIconWidth(),
				imageIcon4.getIconHeight());

		imageIcon3 = new ImageIcon(getClass().getResource(
				"/image/bird_goleft.png"));

		imageIcon5 = new ImageIcon(getClass().getResource(
				"/image/bird_goup.png"));

		imageIcon6 = new ImageIcon(getClass().getResource(
				"/image/bird_godown.png"));

		imageIcon7 = new ImageIcon(getClass().getResource("/image/home.png"));

		imageIcon8 = new ImageIcon(getClass().getResource("/image/fire.png"));

		imageIcon9 = new ImageIcon(getClass().getResource("/image/muscle.png"));

		imageIcon10 = new ImageIcon(getClass().getResource(
				"/image/strongbird.png"));
		labelMuscle = new JLabel();
		labelMuscle.setIcon(imageIcon9);
		labelMuscle.setBounds(500, 20, imageIcon9.getIconWidth(),
				imageIcon9.getIconHeight());

		labelHome = new JLabel();
		labelHome.setIcon(imageIcon7);
		labelHome.setBounds(400, 350, imageIcon7.getIconWidth(),
				imageIcon7.getIconHeight());

		labelScore = new JLabel("Score : 0");
		labelScore.setBounds(400, 0, 100, 30);

		imageBackground = new ImageIcon(getClass().getResource(
				"/image/background.png"));
		labelBackground = new JLabel();
		labelBackground.setIcon(imageBackground);
		labelBackground.setBounds(0, 0, imageBackground.getIconWidth(),
				imageBackground.getIconHeight());

		imageHeart = new ImageIcon(getClass().getResource("/image/heart.png"));

		labelHeart1 = new JLabel();
		labelHeart1.setIcon(imageHeart);
		labelHeart1.setBounds(20, 10, imageHeart.getIconWidth(),
				imageHeart.getIconHeight());

		labelHeart2 = new JLabel();
		labelHeart2.setIcon(imageHeart);
		labelHeart2.setBounds(50, 10, imageHeart.getIconWidth(),
				imageHeart.getIconHeight());

		labelHeart3 = new JLabel();
		labelHeart3.setIcon(imageHeart);
		labelHeart3.setBounds(80, 10, imageHeart.getIconWidth(),
				imageHeart.getIconHeight());

		labelHeart4 = new JLabel();
		labelHeart4.setIcon(imageHeart);
		labelHeart4.setBounds(110, 10, imageHeart.getIconWidth(),
				imageHeart.getIconHeight());
		labelHeart4.setVisible(false);
		
		labelRocket = new JLabel();
		imageRocket = new ImageIcon(getClass().getResource(
				"/image/rocket_godown.png"));
		imageRocketUp = new ImageIcon(getClass().getResource(
				"/image/rocket_goup.png"));
		labelRocket.setIcon(imageRocket);
		labelRocket.setBounds(550, 0, imageRocket.getIconWidth(),
				imageRocket.getIconHeight());
		labelRocket.setVisible(false);

		labelRocket2 = new JLabel();
		imageRocket2 = new ImageIcon(getClass().getResource(
				"/image/rocket2_godown.png"));
		imageRocket2Up = new ImageIcon(getClass().getResource(
				"/image/rocket2_goup.png"));
		labelRocket2.setIcon(imageRocket);
		labelRocket2.setBounds(0, 0, imageRocket.getIconWidth(),
				imageRocket.getIconHeight());
		labelRocket2.setVisible(false);

		imageHunter = new ImageIcon(getClass().getResource("/image/hunter.png"));
		labelHunter = new JLabel();
		labelHunter.setIcon(imageHunter);
		labelHunter.setBounds(520, 0, imageHunter.getIconWidth(),
				imageHunter.getIconHeight());
		labelHunter.setVisible(false);
		
		imageItemHealth = new ImageIcon(getClass().getResource("/image/itemHealth.png"));
		labelItemHealth = new JLabel();
		labelItemHealth.setIcon(imageItemHealth);
		labelItemHealth.setVisible(false);
		labelItemHealth.setBounds(350, 0, imageItemHealth.getIconWidth(), imageItemHealth.getIconWidth());
		
	}

	private void initMyPanel() {
		// setBackground(Color.BLACK);
		setLayout(null);
	}

	private Runnable runnable = new Runnable() {
		@Override
		public void run() {
			int x = 1;
			while (x > 0) {
				if (bombMove == true) {
					while (labelBomb.getY() < 400) {
						labelBomb.setBounds(170, labelBomb.getY() + SPEED_BOMB,
								imageIcon2.getIconWidth(),
								imageIcon2.getIconHeight());
						// System.out.println("Bomb: " + labelBomb.getX()+"-" +
						// labelBomb.getY());
						try {
							Thread.sleep(50);
						} catch (InterruptedException e) {
							e.printStackTrace();
						}
						if (collision(imageIcon2, labelBomb)
								&& muscleBird == false) {
							countHeart--;
							calculateHeart(labelBomb);
						}
						if (collision(imageIcon2, labelBomb)
								&& muscleBird == true) {
							labelBomb.setVisible(true);
						}
					}
					bombMove = false;
				} else {
					while (labelBomb.getY() > 50) {
						labelBomb.setBounds(170, labelBomb.getY() - SPEED_BOMB,
								imageIcon2.getIconWidth(),
								imageIcon2.getIconHeight());
						// System.out.println("Bomb: " + labelBomb.getX()+"-" +
						// labelBomb.getY());
						try {
							Thread.sleep(50);
						} catch (InterruptedException e) {
							e.printStackTrace();
						}
						if (collision(imageIcon2, labelBomb)
								&& muscleBird == false) {

							countHeart--;
							calculateHeart(labelBomb);
						}
						if (collision(imageIcon2, labelBomb)
								&& muscleBird == true) {
							labelBomb.setVisible(true);
						}
					}
					bombMove = true;
				}
			}
		}
	};

	private Runnable runnable2 = new Runnable() {
		@Override
		public void run() {
			if(score == 15 && itemHealth == false) {
				labelItemHealth.setVisible(true);
				itemHealth = true;
				Thread th7 = new Thread(runnable7);
				th7.start();
			}
			
			if (score == 12 && hunter == false) {
				labelHunter.setVisible(true);
				hunter = true;
				Thread th6 = new Thread(runnable6);
				th6.start();
			}

			if (score == 3 && bomb2 == false) {
				labelBomb2.setVisible(true);
				bomb2 = true;
				Thread th3 = new Thread(runnable3);
				th3.start();
			}
			if (score == 6 && rocket1 == false) {
				labelRocket.setVisible(true);
				rocket1 = true;
				Thread th4 = new Thread(runnable4);
				th4.start();
			}
			if (score == 9 && rocket2 == false) {
				labelRocket2.setVisible(true);
				rocket2 = true;
				Thread th5 = new Thread(runnable5);
				th5.start();
			}
			// if(score==1) {
			// createDynamite();
			// }
			if (birdMove == 0) {
				if (muscleBird == false) {
					labelBird.setIcon(imageIcon);
				}
				labelBird.setBounds(labelBird.getX() + SPEED_BIRD,
						labelBird.getY(), imageIcon.getIconWidth(),
						imageIcon.getIconHeight());
				// System.out.println(labelBird.getX()+"-" + labelBird.getY());
			}
			if (birdMove == 1) {
				if (muscleBird == false) {
					labelBird.setIcon(imageIcon3);
				}
				labelBird.setBounds(labelBird.getX() - SPEED_BIRD,
						labelBird.getY(), imageIcon.getIconWidth(),
						imageIcon.getIconHeight());
				collision(imageIcon4, labelStrawberry);
				// System.out.println(labelBird.getX()+"-" + labelBird.getY());
			}
			if (birdMove == 2) {
				if (muscleBird == false) {
					labelBird.setIcon(imageIcon5);
				}
				labelBird.setBounds(labelBird.getX(), labelBird.getY()
						- SPEED_BIRD, imageIcon.getIconWidth(),
						imageIcon.getIconHeight());
				// System.out.println(labelBird.getX()+"-" + labelBird.getY());
			}
			if (birdMove == 3) {
				if (muscleBird == false) {
					labelBird.setIcon(imageIcon6);
				}
				labelBird.setBounds(labelBird.getX(), labelBird.getY()
						+ SPEED_BIRD, imageIcon.getIconWidth(),
						imageIcon.getIconHeight());
				// System.out.println(labelBird.getX()+"-" + labelBird.getY());
			}
			if (collision(imageIcon4, labelStrawberry)) {
				score++;
				labelScore.setText("Score: " + score);
				labelStrawberry.setLocation(randFruit(0, 500),
						randFruit(0, 400));
				labelStrawberry.setVisible(true);
			}
			if (collision(imageIcon7, labelHome)) {
				JOptionPane.showMessageDialog(null, "Chim về tổ" + "\n"
						+ "Điểm của bạn là: " + score);
				labelHome.setLocation(1000, 100);
			}
			if (collision(imageIcon10, labelMuscle)) {
				muscleBird = true;
				labelBird.setSize(imageIcon10.getIconWidth(),
						imageIcon10.getIconHeight());
				labelBird.setIcon(imageIcon10);
			}
			if (collision(imageHunter, labelHunter) && muscleBird == false) {
				countHeart--;
				calculateHeart(labelHunter);
			}
			if (collision(imageHunter, labelHunter) && muscleBird == true) {
				labelHunter.setVisible(true);
			}
		}

	};

	public void moveRight() {
		birdMove = 0;
		th2 = new Thread(runnable2) {
		};
		th2.start();
	}

	public void moveLeft() {
		birdMove = 1;
		th2 = new Thread(runnable2) {
		};
		th2.start();
	}

	public void moveUp() {
		birdMove = 2;
		th2 = new Thread(runnable2) {
		};
		th2.start();
	}

	public void moveDown() {
		birdMove = 3;
		th2 = new Thread(runnable2) {
		};
		th2.start();
	}

	private Runnable runnable3 = new Runnable() {
		@Override
		public void run() {
			int x = 1;
			while (x > 0) {
				if (bombMove2 == true) {
					while (labelBomb2.getY() < 400) {
						labelBomb2.setBounds(270, labelBomb2.getY()
								+ SPEED_BOMB, imageIcon2.getIconWidth(),
								imageIcon2.getIconHeight());
						// System.out.println("Bomb: " + labelBomb.getX()+"-" +
						// labelBomb.getY());
						try {
							Thread.sleep(50);
						} catch (InterruptedException e) {
							e.printStackTrace();
						}
						if (collision(imageIcon2, labelBomb2)
								&& muscleBird == false) {
							countHeart--;
							calculateHeart(labelBomb2);
						}
						if (collision(imageIcon2, labelBomb2)
								&& muscleBird == true) {
							labelBomb2.setVisible(true);
						}
					}
					bombMove2 = false;
				} else {
					while (labelBomb2.getY() > 50) {
						labelBomb2.setBounds(270, labelBomb2.getY()
								- SPEED_BOMB, imageIcon2.getIconWidth(),
								imageIcon2.getIconHeight());
						// System.out.println("Bomb: " + labelBomb.getX()+"-" +
						// labelBomb.getY());
						try {
							Thread.sleep(50);
						} catch (InterruptedException e) {
							e.printStackTrace();
						}
						if (collision(imageIcon2, labelBomb2)
								&& muscleBird == false) {

							countHeart--;
							calculateHeart(labelBomb2);
						}
						if (collision(imageIcon2, labelBomb2)
								&& muscleBird == true) {
							labelBomb2.setVisible(true);
						}
					}
					bombMove2 = true;
				}

			}
		}
	};

	private Runnable runnable4 = new Runnable() {

		@Override
		public void run() {
			int x = 1;
			while (x > 0) {
				if (rocket1Move == true) {
					labelRocket.setIcon(imageRocket);
					while (labelRocket.getY() < 400) {
						labelRocket.setBounds(
								labelRocket.getX() - SPEED_ROCKET,
								labelRocket.getY() + SPEED_ROCKET,
								imageRocket.getIconWidth(),
								imageRocket.getIconHeight());
						// System.out.println("Bomb: " + labelBomb.getX()+"-" +
						// labelBomb.getY());
						try {
							Thread.sleep(50);
						} catch (InterruptedException e) {
							e.printStackTrace();
						}
						if (collision(imageRocket, labelRocket)
								&& muscleBird == false) {
							countHeart--;
							calculateHeart(labelRocket);
						}
						if (collision(imageRocket, labelRocket)
								&& muscleBird == true) {
							labelRocket.setVisible(true);
						}
					}
					rocket1Move = false;
				} else {
					labelRocket.setIcon(imageRocketUp);
					while (labelRocket.getY() > 20) {
						labelRocket.setBounds(
								labelRocket.getX() + SPEED_ROCKET,
								labelRocket.getY() - SPEED_ROCKET,
								imageRocket.getIconWidth(),
								imageRocket.getIconHeight());
						// System.out.println("Bomb: " + labelBomb.getX()+"-" +
						// labelBomb.getY());
						try {
							Thread.sleep(50);
						} catch (InterruptedException e) {
							e.printStackTrace();
						}
						if (collision(imageRocket, labelRocket)
								&& muscleBird == false) {
							countHeart--;
							calculateHeart(labelRocket);
						}
						if (collision(imageRocket, labelRocket)
								&& muscleBird == true) {
							labelRocket.setVisible(true);
						}
					}
					rocket1Move = true;
				}
			}
		}
	};

	private Runnable runnable5 = new Runnable() {

		@Override
		public void run() {
			int x = 1;
			while (x > 0) {
				if (rocket2Move == true) {
					labelRocket2.setIcon(imageRocket2);
					while (labelRocket2.getY() < 400) {
						labelRocket2.setBounds(labelRocket2.getX()
								+ SPEED_ROCKET, labelRocket2.getY()
								+ SPEED_ROCKET, imageRocket2.getIconWidth(),
								imageRocket2.getIconHeight());
						// System.out.println("Bomb: " + labelBomb.getX()+"-" +
						// labelBomb.getY());
						try {
							Thread.sleep(50);
						} catch (InterruptedException e) {
							e.printStackTrace();
						}
						if (collision(imageRocket2, labelRocket2)
								&& muscleBird == false) {
							countHeart--;
							calculateHeart(labelRocket2);
						}
						if (collision(imageRocket2, labelRocket2)
								&& muscleBird == true) {
							labelRocket2.setVisible(true);
						}
					}
					rocket2Move = false;
				} else {
					labelRocket2.setIcon(imageRocket2Up);
					while (labelRocket2.getY() > 20) {
						labelRocket2.setBounds(labelRocket2.getX()
								- SPEED_ROCKET, labelRocket2.getY()
								- SPEED_ROCKET, imageRocket2.getIconWidth(),
								imageRocket2.getIconHeight());
						// System.out.println("Bomb: " + labelBomb.getX()+"-" +
						// labelBomb.getY());
						try {
							Thread.sleep(50);
						} catch (InterruptedException e) {
							e.printStackTrace();
						}
						if (collision(imageRocket2, labelRocket2)
								&& muscleBird == false) {
							countHeart--;
							calculateHeart(labelRocket2);
						}
						if (collision(imageRocket2, labelRocket2)
								&& muscleBird == true) {
							labelRocket2.setVisible(true);
						}
					}
					rocket2Move = true;
				}

			}
		}
	};

	private Runnable runnable6 = new Runnable() {
		@Override
		public void run() {
			int x = 1;
			while (x > 0) {
				if (hunterMove == true) {
					int ranY = randFruit(0, 400);
					if (ranY >= labelHunter.getY()) {
						while (labelHunter.getY() < ranY) {
							labelHunter.setBounds(labelHunter.getX(),
									labelHunter.getY() + SPEED_HUNTER,
									imageHunter.getIconWidth(),
									imageHunter.getIconHeight());
							try {
								Thread.sleep(50);
							} catch (InterruptedException e) {
								e.printStackTrace();
							}
						}
					} else {
						while (labelHunter.getY() > ranY) {
							labelHunter.setBounds(labelHunter.getX(),
									labelHunter.getY() - SPEED_HUNTER,
									imageHunter.getIconWidth(),
									imageHunter.getIconHeight());
							try {
								Thread.sleep(50);
							} catch (InterruptedException e) {
								e.printStackTrace();
							}
						}
					}
					hunterMove = false;
				} else {
					int ranX = randFruit(0, 550);
					if (ranX >= labelHunter.getX()) {
						while (labelHunter.getX() < ranX) {
							labelHunter.setBounds(labelHunter.getX()
									+ SPEED_HUNTER, labelHunter.getY(),
									imageHunter.getIconWidth(),
									imageHunter.getIconHeight());
							try {
								Thread.sleep(50);
							} catch (InterruptedException e) {
								e.printStackTrace();
							}
						}
					} else {
						while (labelHunter.getX() > ranX) {
							labelHunter.setBounds(labelHunter.getX()
									- SPEED_HUNTER, labelHunter.getY(),
									imageHunter.getIconWidth(),
									imageHunter.getIconHeight());
							try {
								Thread.sleep(50);
							} catch (InterruptedException e) {
								e.printStackTrace();
							}
						}
					}
					hunterMove = true;
				}
			}
		}
	};

	private Runnable runnable7 = new Runnable() {
		
		@Override
		public void run() {
			while(labelItemHealth.getY()<=330) {
				labelItemHealth.setBounds(350, labelItemHealth.getY() + SPEED_ITEMHEALTH , imageItemHealth.getIconWidth(), imageItemHealth.getIconHeight());
				try {
					Thread.sleep(50);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				if (collision(imageItemHealth, labelItemHealth)) {
					countHeart++;
					int birdX = labelBird.getX();
					int birdY = labelBird.getY();
					calculateHeart(labelItemHealth);
					labelItemHealth.setVisible(false);
					labelItemHealth.setLocation(1000, 1000);
					labelBird.setLocation(birdX, birdY);
				}
			}
		}
	};
	
	public void createHighScore() {
		if (score >= highScore) {
			highScore = score;
		}
		System.out.println("HighScore: " + highScore);
	}
}
