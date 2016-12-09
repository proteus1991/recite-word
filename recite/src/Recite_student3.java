import java.io.*; 
import java.awt.*;
import java.awt.event.*;
import javax.swing.*; 
import java.util.List;
import java.util.ArrayList;

public class Recite_student3 extends JFrame{ 
	JButton lblWord = new JButton("word");                //改进一：界面改进，word和meaning用button显示
	JButton lblMeaning = new JButton("meaning"); 
	public void init() { 
		setSize(400,150);
		lblWord.setSize(400,50);
		lblMeaning.setSize(400,100);
		setLayout(new BorderLayout());
		getContentPane().add(lblWord,BorderLayout.NORTH);              //改进一：界面改进，word和meaning分上下两行显示
		getContentPane().add(lblMeaning,BorderLayout.CENTER);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setVisible(true);
	} 

	List<String> words = new ArrayList<>();
	List<String> meanings = new ArrayList<>();
	int current = 0;
	int interval=gettime(); //改进三：增加gettime方法，从选择对话框中获取使用者选择的时间，使用不同的间隔时间显示单词
	public void start() {
		new Thread(()->{
			try{
				readAll();
			}catch(IOException ex){}
			new javax.swing.Timer(interval*1000,(e)->{
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
			String[] ss = line.split("\t");     //改进二：将每一行的word，meaning和音标分开，读取word和meaning显示在界面上（不显示音标）；
			words.add( ss[0].toString());
			meanings.add( ss[1].toString());
		} 
		reader.close();
	}
	
	public int gettime(){           //改进三：增加gettime方法，从选择对话框中获取使用者选择的时间，使用不同的间隔时间显示单词
		Object[] times ={ "1秒","2秒","3秒","5秒"};  
	String option = (String)JOptionPane.showInputDialog(null,"请选择单词显示的间隔时间:\n","选择时间",JOptionPane.PLAIN_MESSAGE,null,times,"1秒");
	int inter=Integer.valueOf(option.substring(0,1)).intValue();
	return inter;
	}
	public static void main( String[] args){
		JOptionPane.showMessageDialog(null, "Welcome to WordWorld!","WordWorld",JOptionPane.PLAIN_MESSAGE);	//改进四：增加欢迎界面：
		Recite f = new Recite();
		f.init();
		f.start();
	}
} 
