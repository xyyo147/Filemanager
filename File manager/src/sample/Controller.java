package sample;
import javax.swing.*;
import java.awt.*;
import java.awt.Container;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.EventHandler;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.Timer;

import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.event.*;
import javafx.application.Platform;

import javafx.scene.control.CheckMenuItem;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.RadioMenuItem;
import javafx.scene.control.SeparatorMenuItem;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

import static javafx.application.Platform.exit;


public class Controller extends JFrame{
//    GridPane grid=new GridPane(myJFrame backg,BorderPane border);
//    private Container timePanel;
    private JLabel timeLabel;
    private JLabel displayArea;        //显示时间的标签，字符串DTF放进pane的媒介
    private String DTF= "yyyy-MM-dd-HH:mm:ss";      //方法需要的东西
    private String time;            //方法需要的东西
    private int ONE_SECOND =1000;       //方法需要的东西
    public void Controller() {
        myJFrame backg = new myJFrame("background.jpg", 1.0f);  //这里需要改，改成创建新的stage（舞台）、新的Scene（场景）、新的pane（面板布局）
        //与backg有关的东西都需要改，因为这个作为最基础的，所以可能得重写，找一下怎么创建跟这种差不多效果的主屏的CSDN，实在没有可以自己写间接一点，能自己写一点是一点，因为这块
        //在文档里面没有要求，但老师会更喜欢界面友好那种
        Font font = new Font("微软雅黑", Font.PLAIN, 30);//字体形式和颜色
        Container contentPane=backg.getContentPane();//把容器放入背景板
        contentPane.setBackground(Color.CYAN);
        JPanel panel=new JPanel();//新的面板
        panel.setBackground(Color.DARK_GRAY);//界面下面那块灰色长方块
        panel.setLayout(null);//这个方法让其他控件按x，y坐标放入面板
        //以下为弹出菜单部分
        JButton button1=new JButton("退出");
        button1.setBounds(0,0,70,40);//按钮大小
        button1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }//退出
        });
        //以下为时间显示部分
//        timeLabel=new JLabel("时间");
        displayArea=new JLabel();//显示时间的控件
        configTimeArea();   //显示时间需要用的方法，应该可以不改
        displayArea.setBounds(1320,0,263,40);//显示时间放的位置
        displayArea.setForeground(Color.white);//字体颜色
        panel.add(button1);//把按钮放入面板才能看得见
        panel.add(displayArea);//把显示时间的控件放入面板
        panel.setBounds(0,874,1500,50);//面板的位置
        backg.add(panel);//把面板放入最基础的backg
        contentPane.add(panel,BorderLayout.SOUTH);//把面板放在容器的最南边
        //以上为底部面板
        //以下为程序入口
        Container contentPanel=backg.getContentPane();//用于给最基础放东西的容器
        contentPanel.setBackground(Color.BLACK);//容器颜色
        backg.setContentPane(contentPanel);//把容器放入最基础的框架
        ImageIcon icon1=new ImageIcon("D:\\学习文件\\大三上学期\\操作系统实验\\课设\\BEN\\src\\sample\\test.png");//按钮的图标
        JButton enter1=new JButton("程序一",icon1);//按钮
        enter1.setBounds(40, 40, 90, 110);//按钮位置
        enter1.setOpaque(false);//按钮背景透明
        enter1.setContentAreaFilled(false);
//        enter.setBorder(null);
        enter1.setHorizontalTextPosition(SwingConstants.CENTER);//图标和程序名字对其方式，横向
        enter1.setVerticalTextPosition(SwingConstants.BOTTOM);//对齐方式，纵向
        contentPanel.add(enter1);//把按钮放入容器
        enter1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
               // new createFile();不用这个了，方法那边也还是Java Swing 的控件，这个是开新的窗口的方法，尝试用的
            }
        });
        //----------------------------
//        ImageIcon icon2=new ImageIcon("D:\\学习文件\\大三上学期\\操作系统实验\\课设\\BEN\\src\\sample\\test.png");
//        JButton enter2=new JButton("程序一",icon2);
//        enter2.setBounds(40, 190, 90, 110);
//        enter2.setOpaque(false);
//        enter2.setContentAreaFilled(false);
////        enter.setBorder(null);
//        enter2.setHorizontalTextPosition(SwingConstants.CENTER);
//        enter2.setVerticalTextPosition(SwingConstants.BOTTOM);
//        contentPanel.add(enter2);

        backg.setLayout(null);//让backg里面的东西能够以x，y来放位置，而不是按默认布局放（默认可能会看不见，太远了
        backg.setLocation(300,100);
        backg.pack();
        backg.setExtendedState(JFrame.MAXIMIZED_BOTH);//使得最基础的框架backg全屏，max嘛
        backg.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);//按交叉时全部关掉，但看不见交叉了这个相当于没用
        backg.setVisible(true);//使得整个框架看得见
    }
    //以下两个方法是关于搜索现在时间的方法，具体我也没怎么看，反正有J开头的控件都小心一下，看看能不能改
        private void configTimeArea(){
            java.util.Timer timer=new Timer();
            timer.scheduleAtFixedRate(new Controller.JLabelTimerTask(),new Date(),ONE_SECOND);
        }
        protected class JLabelTimerTask extends TimerTask {
            SimpleDateFormat dateFormatter =new SimpleDateFormat(DTF);
            @Override
            public void run(){
                time=dateFormatter.format(Calendar.getInstance().getTime());
                displayArea.setText(time);
            }
        }

}
