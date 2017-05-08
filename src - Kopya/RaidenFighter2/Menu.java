package RaidenFighter2;

import java.awt.Dimension;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;
import java.util.NavigableMap;
import java.util.TreeMap;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JTextArea;
import javax.swing.JTextField;

public class Menu extends JMenuBar implements ActionListener {

	private static final long serialVersionUID = 1L;

	
	JMenu File;
	JMenu Help;
	JMenuItem ScoreBoard;
	JMenuItem Credits;
	JMenuItem Register;
	JMenuItem Play_Game;
	JMenuItem Quit;
	 
	JFrame mainFrame;
	JLabel gifLabel;
	HashMap<String,PlayerInfo> DataMap = new HashMap<String,PlayerInfo>();
	
	
	PlayerInfo myPlayer;
	
	
	Menu(JFrame mainFrame,int width,int height){
		 
		this.mainFrame = mainFrame;
		
		java.net.URL url = this.getClass().getResource("MainGif.gif");
		ImageIcon gifIcon = new ImageIcon(url);
		gifIcon.setImage(gifIcon.getImage().getScaledInstance(width,height,Image.SCALE_DEFAULT));
		gifLabel = new JLabel(gifIcon);
		mainFrame.getContentPane().add(gifLabel);
		
		readUserData();
		
		
		initMenu();

		
	}
	
	public void initMenu(){
		File = new JMenu("File");
		Help = new JMenu("Help");
		ScoreBoard = new JMenuItem("Scoreboard");
		Quit = new JMenuItem("Quit");
		Credits = new JMenuItem("Credits");
		Register = new JMenuItem("Register");
		Play_Game = new JMenuItem("Play Game");
		
		Credits.addActionListener(this);
		Register.addActionListener(this);
		Play_Game.addActionListener(this);
		Quit.addActionListener(this);
		ScoreBoard.addActionListener(this);
		
		File.add(Register);
		File.add(Play_Game);
		File.add(ScoreBoard);
		File.add(Quit);
		
		Help.add(Credits);
		
		this.add(File);
		this.add(Help);
	}



	@Override
	public void actionPerformed(ActionEvent e) {

		if(e.getSource() == Credits){
			JOptionPane.showMessageDialog(null,"Onur Berk Töre\n20140702018\nYeditepe University","Credits",JOptionPane.INFORMATION_MESSAGE);
		
		}
		if(e.getSource() == Register){
			register();
			
		}
		if(e.getSource() == Play_Game){
			playGame();
		}
		
		
		if(e.getSource() == ScoreBoard){
			highScores();
		}
		
		
		if(e.getSource() == Quit){
			System.exit(0);
		}
		
	}
	
	public void register(){
		JTextField username = new JTextField();
		JTextField password  = new JPasswordField();
		
		Object[] message = {
				
				"Username:",username,
				"Password:",password
		};
		
		int option = JOptionPane.showConfirmDialog(null,message,"Register",JOptionPane.OK_CANCEL_OPTION);
		
		if(option == JOptionPane.OK_OPTION){
			if(DataMap.containsKey(username.getText())){
				JOptionPane.showMessageDialog(null,"This username already taken","Registration Info",JOptionPane.INFORMATION_MESSAGE);
				return;
			}
			
		
			PlayerInfo newPlayer = new PlayerInfo(username.getText(),password.getText());
			DataMap.put(newPlayer.username,newPlayer);
			JOptionPane.showMessageDialog(null,"Registration Succesful","Registration Info",JOptionPane.INFORMATION_MESSAGE);
			saveUsers();
		}
		else {
			JOptionPane.showMessageDialog(null,"Registration Cancelled","Registration Info",JOptionPane.INFORMATION_MESSAGE);
		}
		
	}
	
	public void saveUsers(){
		try{
			FileOutputStream fileOut  = new FileOutputStream("save.dat");
			ObjectOutputStream out = new ObjectOutputStream(fileOut);
			
			out.writeObject(DataMap);
			out.close();
			fileOut.close();
		}catch (IOException e){
			e.printStackTrace();
		}
	}
	 
	public void readUserData(){
		FileInputStream fileIn;
		ObjectInputStream in = null;
		
		try{
			fileIn = new FileInputStream("save.dat");
			in = new ObjectInputStream(fileIn);
			DataMap = (HashMap<String,PlayerInfo>)in.readObject();
			fileIn.close();
			in.close();
		}catch(IOException | ClassNotFoundException e){
			JOptionPane.showMessageDialog(null,"User Data Cannot Read","Error",JOptionPane.INFORMATION_MESSAGE);
		}
		
		
		
	}
	
	public void playGame(){
		
		
		
		boolean findUserName = false;
		JTextField username = new JTextField();
		JTextField password = new JPasswordField();
		Object[] message = {
		    "Username:", username,
		    "Password:", password
		};
		
		int option = JOptionPane.showConfirmDialog(null,message,"Login",JOptionPane.OK_CANCEL_OPTION);
		if(option == JOptionPane.OK_OPTION){
			if(DataMap.containsKey(username.getText())){
				if(DataMap.get(username.getText()).getPassword().equals(password.getText() )  ){				
					myPlayer = DataMap.get(username.getText());
					selectLevel();
					startGame();	
				}
				else{
					JOptionPane.showMessageDialog(null,"Wrong Password","Login Info",JOptionPane.INFORMATION_MESSAGE);
				}
			}
			else{
				JOptionPane.showMessageDialog(null,"User Doesn't Exist","Login Info",JOptionPane.INFORMATION_MESSAGE);
			}
		}
		else{
			JOptionPane.showMessageDialog(null,"Login Cancelled","Login Info",JOptionPane.INFORMATION_MESSAGE);
		}
		
	}
	
	
	public void selectLevel(){
		Object[] options =
			{"Novice",
        	"Intermediate"};
int n = JOptionPane.showOptionDialog(null,"Choose Your Level",
"Level Selection",
JOptionPane.YES_NO_OPTION,
JOptionPane.QUESTION_MESSAGE,
null,     //do not use a custom Icon
options,  //the titles of buttons
options[0]); //default button title

		myPlayer.setLevel(n);
	}
	
	public void startGame(){
		
		mainFrame.remove(this.gifLabel);
	
		mainFrame.repaint();
		
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		
		int width = screenSize.width * 1 / 4;
		
		int height = screenSize.height / 16  * 9 ;
	
		Board myBoard = new Board(width,height,mainFrame);
		
		mainFrame.getContentPane().add(myBoard);
	
		mainFrame.revalidate();
		
		mainFrame.repaint();
		
		myBoard.requestFocusInWindow();
	   
	}
	
	public void savePlayer(int level,int highScore){
		
		myPlayer.setLevel(level);
		myPlayer.setHighScore(highScore);
		saveUsers();
	}
	
	
	public void highScores(){
		
		//Rezillik
	/*
		String HighScores =  "Name\tScores\n";
	
		TreeMap<Integer,String> ScoreMap = new TreeMap<Integer,String>();
		
		for (Entry<String, PlayerInfo> entry : DataMap.entrySet()) {

			
			String key = entry.getKey();
		    
			PlayerInfo value = entry.getValue();

		
		    //HighScores += key + "\t" + value.highScore + '\n';
			ScoreMap.put(value.highScore, value.username);
		}
		
		NavigableMap<Integer, String> nmap = ScoreMap.descendingMap();
		
		
		for (Entry<Integer, String> entry : nmap.entrySet()) {

			
			Integer key = entry.getKey();
		    
			String value = entry.getValue();

		
		    HighScores += key + "\t" + value + '\n';
			
		}		
		JTextArea myTextArea = new JTextArea(HighScores);
		myTextArea.setOpaque(false);
		JOptionPane.showMessageDialog(null,myTextArea,"HighScores",JOptionPane.INFORMATION_MESSAGE);
		
		*/
	
		
		String ScoreBoard =  "Name\tScores\n";
		
		
		for (Entry<String, PlayerInfo> entry : DataMap.entrySet()) {

			
			String key = entry.getKey();
		    
			PlayerInfo value = entry.getValue();

		
		    ScoreBoard += key + "\t" + value.highScore + '\n';
		}
		
				JTextArea myTextArea = new JTextArea(ScoreBoard);
		myTextArea.setOpaque(false);
		JOptionPane.showMessageDialog(null,myTextArea,"ScoreBoard",JOptionPane.INFORMATION_MESSAGE);

		
		
	}

	public PlayerInfo getMyPlayer() {
		return myPlayer;
	}

	public void setMyPlayer(PlayerInfo myPlayer) {
		this.myPlayer = myPlayer;
	}

	
}
