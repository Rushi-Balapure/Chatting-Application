/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package chatting.application;

/**
 *
 * @author rushi
 */
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.border.*;
import java.util.*;
import java.text.*;
import java.net.*;
import java.io.*;
public class Server  implements ActionListener{
    
    JTextField input_field;
    JPanel panel_mid;
    static Box vertical=Box.createVerticalBox();
    static JFrame f=new JFrame();
    static DataOutputStream dout;
    Server()
    {
        f.setLayout(null);
        JPanel panel_top=new JPanel();
        panel_top.setBackground(new Color(7,94,84));
        panel_top.setBounds(0,0,450,70);
        panel_top.setLayout(null);
        f.add(panel_top);
        
        ImageIcon arrow1=new ImageIcon(ClassLoader.getSystemResource("icons/3.png"));
        Image arrow2=arrow1.getImage().getScaledInstance(25, 25, Image.SCALE_DEFAULT);
        ImageIcon arrow3=new ImageIcon(arrow2);
        JLabel back=new JLabel(arrow3);
        back.setBounds(5,20,25,25);
        panel_top.add(back);
        
        back.addMouseListener(new MouseAdapter(){
            public void mouseClicked(MouseEvent ae)
            {
                System.exit(0);
            }
        });
        
        ImageIcon profile1=new ImageIcon(ClassLoader.getSystemResource("icons/1.png"));
        Image profile2=profile1.getImage().getScaledInstance(50, 50, Image.SCALE_DEFAULT);
        ImageIcon profile3=new ImageIcon(profile2);
        JLabel profile=new JLabel(profile3);
        profile.setBounds(40,10,50,50);
        panel_top.add(profile);
        
        ImageIcon video1=new ImageIcon(ClassLoader.getSystemResource("icons/video.png"));
        Image video2=video1.getImage().getScaledInstance(30, 30, Image.SCALE_DEFAULT);
        ImageIcon video3=new ImageIcon(video2);
        JLabel video=new JLabel(video3);
        video.setBounds(300,20,30,30);
        panel_top.add(video);
        
        
        ImageIcon phone1=new ImageIcon(ClassLoader.getSystemResource("icons/phone.png"));
        Image phone2=phone1.getImage().getScaledInstance(35, 30, Image.SCALE_DEFAULT);
        ImageIcon phone3=new ImageIcon(phone2);
        JLabel phone=new JLabel(phone3);
        phone.setBounds(360,20,30,30);
        panel_top.add(phone);
       
        ImageIcon more1=new ImageIcon(ClassLoader.getSystemResource("icons/3icon.png"));
        Image more2=more1.getImage().getScaledInstance(10, 25, Image.SCALE_DEFAULT);
        ImageIcon more3=new ImageIcon(more2);
        JLabel more=new JLabel(more3);
        more.setBounds(420,20,10,25);
        panel_top.add(more);
        
        JLabel name=new JLabel("Gaitonde");
        name.setBounds(110,15,100,18);
        name.setForeground(Color.WHITE);
        name.setFont(new Font("SAN_SERIF",Font.BOLD,18));
        panel_top.add(name);
        
        JLabel status=new JLabel("Active now");
        status.setBounds(110,35,100,18);
        status.setForeground(Color.WHITE);
        status.setFont(new Font("SAN_SERIF",Font.BOLD,14));
        panel_top.add(status);
        
        panel_mid=new JPanel();
        panel_mid.setBounds(5,75,425,490);
        f.add(panel_mid);
        
        
        input_field=new JTextField();
        input_field.setBounds(5,575,310,40);
        input_field.setFont(new Font("SAN_SERIF",Font.BOLD,16));
        f.add(input_field);
        
        JButton send=new JButton("Send");
        send.setBounds(320,575,110,40);
        send.setBackground(new Color(7,94,84));
        send.setForeground(Color.WHITE);
        send.addActionListener(this);
        send.setFont(new Font("SAN_SERIF",Font.BOLD,16));
        f.add(send);
        
        
        
        f.setSize(450,700);
        f.setLocation(200,50);
        f.getContentPane().setBackground(Color.WHITE);
        f.setVisible(true);
    }
    
    public void actionPerformed(ActionEvent ae)
    {try{
        String text_input=input_field.getText();
        JPanel p2=formatLabel(text_input);
        panel_mid.setLayout(new BorderLayout());
        JPanel right=new JPanel(new BorderLayout());
        right.add(p2,BorderLayout.LINE_END);
        vertical.add(right);
        vertical.add(Box.createVerticalStrut(15));
        panel_mid.add(vertical,BorderLayout.PAGE_START);
        
        
        dout.writeUTF(text_input);
        input_field.setText("");
        f.repaint();
        f.invalidate();
        f.validate();
    }
    catch(Exception e)
    {
        e.printStackTrace();
    }
    }
    
    public static JPanel formatLabel(String output)
    {
        JPanel panel=new JPanel();
        panel.setLayout(new BoxLayout(panel,BoxLayout.Y_AXIS));
        JLabel out=new JLabel("<html><p style=\"width:150px\">"+output+"</p></html>");
        
        out.setFont(new Font("Tahoma",Font.PLAIN,16));
        out.setBackground(new Color(37,211,102));
        out.setOpaque(true);
        
        out.setBorder(new EmptyBorder(15,15,15,50));
        panel.add(out);
        
        Calendar cal=Calendar.getInstance();
        SimpleDateFormat sdf=new SimpleDateFormat("HH:MM");
        JLabel time=new JLabel();
        time.setText(sdf.format(cal.getTime()));
        panel.add(time);
        return panel;
    }
    public static void main(String args[])
    {
        new Server();
        try{
            int port=6001;
            ServerSocket skt=new ServerSocket(port);
            while(true)
            {
                Socket s=skt.accept();
                DataInputStream din=new DataInputStream(s.getInputStream());
                dout=new DataOutputStream(s.getOutputStream());
                
                while(true)
                {
                    String msg=din.readUTF();
                    JPanel npanel=formatLabel(msg);
                    JPanel left=new JPanel(new BorderLayout());
                    left.add(npanel,BorderLayout.LINE_START);
                    vertical.add(left);
                    f.validate();
                }
            }
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
    }
}
