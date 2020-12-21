import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;
import java.util.*;
import java.net.*;

class OTP implements ActionListener
{
	JFrame fr;
	JTextArea t2;
	JTextField t1,t3;
	JLabel l1,l2,l3;
	JButton b1,b2,b3;
	
	int otp;
	public OTP()
	{
		Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
		fr = new JFrame("OTP Generator");
		fr.setBounds((dim.width-700)/2,(dim.height-400)/2,700,400);
		fr.getContentPane().setBackground(Color.black);
		fr.setLayout(null);
			
		l1 = new JLabel("Enter Mobile No.");
		l1.setBounds(20,20,180,30);
		l1.setFont(new Font("verdana",Font.BOLD,18));
		l1.setForeground(Color.white);
		fr.add(l1);
		
		t1 = new JTextField();
		t1.setBounds(20,60,280,30);
		t1.setFont(new Font("verdana",Font.BOLD,18));
		t1.setForeground(Color.green);
		fr.add(t1);
		
		l2 = new JLabel("Enter Name");
		l2.setBounds(20,100,250,30);
		l2.setFont(new Font("verdana",Font.BOLD,18));
		l2.setForeground(Color.white);
		fr.add(l2);
		
		t2 = new JTextArea();
		t2.setBounds(20,140,280,90);
		t2.setFont(new Font("verdana",Font.BOLD,18));
		t2.setForeground(Color.green);
		fr.add(t2);
		
		b1 = new JButton("<html><u>Send</u></html>");
		b1.setBounds(210,250,90,30);
		b1.setBackground(Color.green);
		b1.setForeground(Color.white);
		b1.setFont(new Font("verdana",Font.BOLD,18));
		b1.addActionListener(this);
		b1.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		fr.add(b1);
		
		b2 = new JButton("<html><u>Reset</u></html>");
		b2.setBounds(110,250,90,30);
		b2.setForeground(Color.white);
		b2.setBackground(Color.green);
		b2.setFont(new Font("verdana",Font.BOLD,18));
		b2.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		b2.addActionListener(this);
		fr.add(b2);
		
		l3 = new JLabel("Verify OTP No.");
		l3.setBounds(330,20,180,30);
		l3.setFont(new Font("verdana",Font.BOLD,18));
		l3.setForeground(Color.white);
		fr.add(l3);
		
		t3 = new JTextField();
		t3.setBounds(330,60,280,30);
		t3.setFont(new Font("verdana",Font.BOLD,18));
		t3.setForeground(Color.green);
		fr.add(t3);
		
		b3 = new JButton("<html><u>Verify</u></html>");
		b3.setBounds(520,110,90,30);
		b3.setBackground(Color.green);
		b3.setForeground(Color.white);
		b3.setFont(new Font("verdana",Font.BOLD,18));
		b3.addActionListener(this);
		b3.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		fr.add(b3);
		
		fr.setVisible(true);
		fr.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
	public void actionPerformed(ActionEvent ae)
	{
		if(ae.getSource()==b1)
		{
			try {
			// Construct data
			String apiKey = "apikey=" + "PrrZB41DBZc-P8q9TeFyHwFmdHTN7Jb2U8DCsV92zT";
			Random rand = new Random();
			otp = rand.nextInt(999999);
			String name = t2.getText();
			String message = "&message=" + "Hey "+name+"Your OTP is  "+otp;
			String sender = "&sender=" + "TXTLCL";
			String numbers = "&numbers=" + "91" +t1.getText();;
			
			// Send data
			HttpURLConnection conn = (HttpURLConnection) new URL("https://api.textlocal.in/send/?").openConnection();
			String data = apiKey + numbers + message + sender;
			conn.setDoOutput(true);
			conn.setRequestMethod("POST");
			conn.setRequestProperty("Content-Length", Integer.toString(data.length()));
			conn.getOutputStream().write(data.getBytes("UTF-8"));
			final BufferedReader rd = new BufferedReader(new InputStreamReader(conn.getInputStream()));
			final StringBuffer stringBuffer = new StringBuffer();
			String line;
			while ((line = rd.readLine()) != null) {
				stringBuffer.append(line);
			}
			rd.close();
			JOptionPane.showMessageDialog(fr,"OTP send Successfully...");
			//return stringBuffer.toString();
		} catch (Exception e) {
			System.out.println("Error SMS "+e);
			JOptionPane.showMessageDialog(fr,"Error to Generate OTP");
			//return "Error "+e;
		}
		}
		if(ae.getSource()==b2)
		{
			t1.setText("");
			t2.setText("");
			t3.setText("");
			t1.requestFocus();
		}
		if(ae.getSource()==b3)
		{
			if(t3.getText().trim().length()==0)
			{
				JOptionPane.showMessageDialog(fr,"Please enter OTP to Verify");
			}
			else
			{
				if(Integer.parseInt(t3.getText())==otp)
				{
					JOptionPane.showMessageDialog(fr,"Login Successfully");
				}
				else
				{
					JOptionPane.showMessageDialog(fr,"Wrong OTP");
				}
			}	
		}
	}
	
	public static void main(String args[])
	{
		new OTP();
	}
}