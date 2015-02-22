package touch_anywhere;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import java.awt.GridLayout;

import javax.swing.JButton;

import com.leapmotion.leap.*;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class Main extends JFrame {

	private JPanel calibrationScreen;
	private JPanel touchScreen;
	
	public JLabel lblTouchRespond;
		
	public LeapListener listener;
	public Controller controller;
	public SurfaceCalibrator calibrator;
	public SurfaceChecker checker;
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					//Create the jframe
					Main frame = new Main();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public Main() {
		
		//Initialize stuff
		controller = new Controller();
		calibrator = new SurfaceCalibrator();
		checker = new SurfaceChecker(calibrator);
		listener = new LeapListener(checker, this);
		
		controller.addListener(listener);
		
		
		setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		calibrationScreen = new JPanel();
		calibrationScreen.setVisible(true);
		calibrationScreen.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(calibrationScreen);
		calibrationScreen.setLayout(new GridLayout(0, 1, 0, 0));
		
		//Define universal Exit button
		JButton btnExit = new JButton("Exit");
		btnExit.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				exitCleanly();
			}
		});
				
		//Define label inside Touchscreen screen
		lblTouchRespond = new JLabel("None");
		lblTouchRespond.setFont(new Font("Tahoma", Font.PLAIN, 46));
		
		//Touchscreen to use laters
		touchScreen = new JPanel();
		touchScreen.setBorder(new EmptyBorder(5, 5, 5, 5));
		touchScreen.setLayout(new GridLayout(0, 1, 0, 0));
		
		
		//Calibrate a new box-shaped button
		JButton btnCalibrate = new JButton("Make a Button");
		btnCalibrate.addMouseListener(new MouseAdapter() {
			private String buttonName;

			@Override
			public void mouseClicked(MouseEvent arg0) {
				
				JOptionPane.showMessageDialog(getParent(), "Touch the top left corner");
				Vector topLeft = calibrator.calibratePos(controller.frame());
				
				JOptionPane.showMessageDialog(getParent(), "Touch the bottom right corner");
				Vector bottomRight = calibrator.calibratePos(controller.frame());
				
				buttonName = JOptionPane.showInputDialog(getParent(), "Button name:");
				
				calibrator.addButton(topLeft, bottomRight, buttonName);
				checker.update(calibrator); //update checker
				listener.checker = checker; //update listener
			}
		});
		btnCalibrate.setFont(new Font("Tahoma", Font.PLAIN, 25));
		btnCalibrate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			}
		});
		calibrationScreen.add(btnCalibrate);
		
		
		JButton btnReady = new JButton("Ready");
		btnReady.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				
				calibrationScreen.setVisible(false);
				setContentPane(touchScreen);
				touchScreen.add(lblTouchRespond);
				touchScreen.add(btnExit);

				listener.readyToCheck = true;
			}
		});
		btnReady.setFont(new Font("Tahoma", Font.PLAIN, 46));
		calibrationScreen.add(btnReady);
		calibrationScreen.add(btnExit);
	}
	
	
	//First remove the listener from the controller, then exit
	public void exitCleanly() {
		controller.removeListener(listener);
		System.out.println("Exiting...");
		System.exit(0);
	}
		
}
