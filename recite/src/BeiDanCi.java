import java.io.*; 
import java.awt.*;
import java.awt.event.*;
import javax.swing.*; 
import java.util.List;
import java.util.ArrayList;

public class BeiDanCi extends JFrame{
 public BeiDanCi() {
 } 
 JLabel lblWord = new JLabel("word"); 
 JLabel lblMeaning = new JLabel("meaning"); 
 public void init() { 
  setSize( 600,400 );
  
  setTitle("»¶Ó­ÄúÀ´±³µ¥´Ê£¡");
  
  //make the frame show up in the center of the screen
  setLocationRelativeTo(null);
  
  //change frame background color
  getContentPane().setBackground(new Color(150,250,200));
  
  getContentPane().setLayout(new FlowLayout());
  getContentPane().add(lblWord);
  getContentPane().add(lblMeaning);
  setDefaultCloseOperation(EXIT_ON_CLOSE);
  
  //add a border of magenta color
  getRootPane().setBorder(BorderFactory.createMatteBorder(5, 5, 5, 5, Color.MAGENTA));
  
  
  
  setVisible(true);
 } 

 List<String> words = new ArrayList<>();
 List<String> meanings = new ArrayList<>();
 int current = 0;
 String mySubStrg = "";
 public void start() {
  new Thread(()->{
   try{
    readAll();
   }catch(IOException ex){}
   new javax.swing.Timer(3000,(e)->{
    lblWord.setText( words.get(current) + "  " );
    lblWord.setForeground(Color.BLUE);
    lblWord.setFont(new Font("Serif", Font.PLAIN, 30));
    
    lblMeaning.setText( meanings.get(current) );
    lblMeaning.setForeground(Color.RED);
    lblMeaning.setFont(new Font("Serif", Font.PLAIN, 24));
    
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
   int lstTab = line.lastIndexOf("\t"); 
   words.add( line.substring(0, idx ));
   meanings.add( line.substring(idx+1, lstTab));
  } 
  reader.close();
 }

 public static void main( String[] args){
  BeiDanCi f = new BeiDanCi();
  f.init();
  f.start();
  
 }
}
