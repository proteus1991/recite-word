import java.io.*; 
import java.awt.*;
import java.awt.event.*;
import javax.swing.*; 
import java.util.List;
import java.util.ArrayList;

public class Recite_student3 extends JFrame{ 
	JButton lblWord = new JButton("word");                //�Ľ�һ������Ľ���word��meaning��button��ʾ
	JButton lblMeaning = new JButton("meaning"); 
	public void init() { 
		setSize(400,150);
		lblWord.setSize(400,50);
		lblMeaning.setSize(400,100);
		setLayout(new BorderLayout());
		getContentPane().add(lblWord,BorderLayout.NORTH);              //�Ľ�һ������Ľ���word��meaning������������ʾ
		getContentPane().add(lblMeaning,BorderLayout.CENTER);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setVisible(true);
	} 

	List<String> words = new ArrayList<>();
	List<String> meanings = new ArrayList<>();
	int current = 0;
	int interval=gettime(); //�Ľ���������gettime��������ѡ��Ի����л�ȡʹ����ѡ���ʱ�䣬ʹ�ò�ͬ�ļ��ʱ����ʾ����
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
			String[] ss = line.split("\t");     //�Ľ�������ÿһ�е�word��meaning������ֿ�����ȡword��meaning��ʾ�ڽ����ϣ�����ʾ���꣩��
			words.add( ss[0].toString());
			meanings.add( ss[1].toString());
		} 
		reader.close();
	}
	
	public int gettime(){           //�Ľ���������gettime��������ѡ��Ի����л�ȡʹ����ѡ���ʱ�䣬ʹ�ò�ͬ�ļ��ʱ����ʾ����
		Object[] times ={ "1��","2��","3��","5��"};  
	String option = (String)JOptionPane.showInputDialog(null,"��ѡ�񵥴���ʾ�ļ��ʱ��:\n","ѡ��ʱ��",JOptionPane.PLAIN_MESSAGE,null,times,"1��");
	int inter=Integer.valueOf(option.substring(0,1)).intValue();
	return inter;
	}
	public static void main( String[] args){
		JOptionPane.showMessageDialog(null, "Welcome to WordWorld!","WordWorld",JOptionPane.PLAIN_MESSAGE);	//�Ľ��ģ����ӻ�ӭ���棺
		Recite f = new Recite();
		f.init();
		f.start();
	}
} 
