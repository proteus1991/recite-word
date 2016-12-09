import java.io.*; 
import java.awt.*;
import java.awt.event.*;

import javax.swing.*; 

import java.util.List;
import java.util.ArrayList;

class A{ 
static int speed = 1000 ; 
} 

public class Recite extends JFrame{ 
	JLabel lblWord = new JLabel("word"); 
	JLabel lblMeaning = new JLabel("meaning"); 
	JButton btn1 = new JButton("Âý·Å");
	JButton btn2 = new JButton("¿ì·Å");

	public void init() { 
		setSize( 400,100 );
		setLayout(new FlowLayout());
		getContentPane().add(lblWord);
		getContentPane().add(lblMeaning);
		add(btn1);
		btn1.addActionListener( new BtnActionAdapter1() );
		add(btn2);
		btn2.addActionListener( new BtnActionAdapter2() );
		setSize( 400,100 );
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setVisible(true);
	} 
	class BtnActionAdapter2 implements ActionListener
	{
	public void actionPerformed( ActionEvent e ){
		if(e.getSource() == btn1){
			A b=new A();
			b.speed=b.speed+500;}
	}
	}
	
	class BtnActionAdapter1 implements ActionListener
	{
	public void actionPerformed( ActionEvent e ){
		//A b=new A();
		//b.speed=b.speed-500;
		if(e.getSource() == btn1){
			A b=new A();
			b.speed=b.speed-500;
		}
	}
	
	}
	
	List<String> words = new ArrayList<>();
	List<String> meanings = new ArrayList<>();
	int current = 0;
	public void start() {
		new Thread(()->{
			try{
				readAll();
			}catch(IOException ex){}
			new javax.swing.Timer(new A().speed,(e)->{
				lblWord.setText( words.get(current) );
				lblMeaning.setText( meanings.get(current) );
				current++;
			}).start();
		}).start();
	}

	public void readAll( ) throws IOException{
		String fileName = "College_Grade4.txt";
		String charset = "GB2312";
		BufferedReader reader = new BufferedReader(
			new InputStreamReader(
				new FileInputStream(fileName), charset)); 
		String line; 
		while ((line = reader.readLine()) != null) { 
			line = line.trim();
			if( line.length() == 0 ) continue;
			int idx = line.indexOf("\t");
			words.add( line.substring(0, idx ));
			int length=line.length();
			String Str=new StringReverse().swapWords(line);
			int idx1 = Str.indexOf("\t");
			meanings.add( line.substring(idx+1,length-idx1));
		} 
		reader.close();
	}

	public static void main( String[] args){
		Recite f = new Recite();
		f.init();
		f.start();
	}
} 
