
import java.awt.*;
import java.awt.geom.*;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;
import javax.swing.*;


public class san extends JPanel {

	static int Num = 0,Weight=0;				//Num:����������Weight:������
	static Scanner input=new Scanner(System.in);
	static int weight[][];						//���ݼ��ж������������һά��ʾ�ڼ��飬�ڶ�ά��ʾ����ĵ�һ��Ԫ��
	static int value[][];						//���ݼ��ж���ļ۸�
	static int row=1,col=1;						//���ݼ����鱳����������ÿ����������Ʒ����
	static int dp[];							//dp���
	static int res;								//���Ž�
	static double run_time;						//��������ʱ��
	static int ans[];
	static int remain_capacity;
	static int data[];//�����Ӧ�����ݣ���integer

	
	//��ȡ���ݼ���Ч����
	public static void read_file_data(String file_name) {
		try {
			int t;
            BufferedReader in = new BufferedReader(new FileReader(file_name));
            if(file_name=="C:\\Users\\����\\Desktop\\idkp1-10.txt") {
            	System.out.println("��ȡ���ݣ�ѡ��һ�飩(0-10):");
            }
            else {
            	System.out.println("��ȡ���ݣ�ѡ��һ�飩(1-10):");
            }
            System.out.print("���������ѡ��:");
            t=input.nextInt();
            if(t>10||t<0) {
            	System.out.println("�������ݲ�����");
            	return;
            }
            else {
            	String str;
	            while ((str = in.readLine()) != null) {
	            	if((str.length()==6&&str.charAt(4)==t+'0')||(str.length()==7&&str.charAt(4)=='1'&&str.charAt(5)=='0'&&t==10)) {
	                	int x=0;
	                	String str1 = in.readLine();
	                	while(str1=="")
	                		str1 = in.readLine();
	                	int i=0;
	                	//�����������
	                	for(;i<str1.length();i++) {
	                		char ch = str1.charAt(i);
	                		if(ch>='0'&&ch<='9') {
	                			break;
	                		}
	                	}
	                	//��ȡÿ�鱳���ĸ���
	                	for(;i<str1.length();i++) {
	                		char ch = str1.charAt(i);
	                		if(ch>='0'&&ch<='9') {
	                			x=x*10+ch-'0';
	                		}
	                		else {
	                			break;
	                		}
	                	}
	                	//�����������
	                	for(;i<str1.length();i++) {
	                		char ch = str1.charAt(i);
	                		if(ch>='0'&&ch<='9') {
	                			break;
	                		}
	                	}
	                	//��ȡ�м��鱳��
	                	for(;i<str1.length();i++) {
	                		char ch = str1.charAt(i);
	                		if(ch>='0'&&ch<='9') {
	                			Num= Num*10+ch-'0';
	                		}
	                		else {
	                			break;
	                		}
	                	}
	                	//�����������
	                	for(;i<str1.length();i++) {
	                		char ch = str1.charAt(i);
	                		if(ch>='0'&&ch<='9') {
	                			break;
	                		}
	                	}
	                	//��ȡ��������
	                	for(;i<str1.length();i++) {
	                		char ch = str1.charAt(i);
	                		if(ch>='0'&&ch<='9') {
	                			Weight=Weight*10+ch-'0';
	                		}
	                		else {
	                			break;
	                		}
	                	}
	                	//��ȡ������ֵ
	                	String profit;
	                	value=new int[10000][5];
	                	int temp_num=0;
	                	profit=in.readLine();
	                	while(profit.isEmpty())
	                		profit=in.readLine();
	                	profit=in.readLine();
	                	while(profit.isEmpty())
	                		profit=in.readLine();
	                	for(i=0;i<profit.length();i++) {
	                		char ch = profit.charAt(i);
	                		if(ch>='0'&&ch<='9') {
	                			temp_num=temp_num*10+ch-'0';
	                		}
	                		else {
	                			value[row][col++]=temp_num;
	                			if(col>3) {
	                				row++;col=1;
	                			}
	                			temp_num=0;
	                		}
	                	}
	                	//��ȡ��������
	                	String Weight1;
	                	weight=new int[10000][5];
	                	row=1;col=1;
	                	temp_num=0;
	                	Weight1=in.readLine();
	                	while(Weight1.isEmpty())
	                		Weight1=in.readLine();
	                	Weight1=in.readLine();
	                	while(Weight1.isEmpty())
	                		Weight1=in.readLine();
	                	for(i=0;i<Weight1.length();i++) {
	                		char ch = Weight1.charAt(i);
	                		if(ch>='0'&&ch<='9') {
	                			temp_num=temp_num*10+ch-'0';
	                		}
	                		else {
	                			weight[row][col++]=temp_num;
	                			if(col>3) {
	                				row++;col=1;
	                			}
	                			temp_num=0;
	                		}
	                	}
	                }
	            }
            }
        } catch (IOException e) {
        	System.out.printf("���ļ�������");
        }
	}
	
	
	//ѡȡ���ݼ�
	public static void read_file() {
		int _;
		System.out.println("��ȡ���������ļ���һ����:");
		System.out.println("1:idkp1-10.txt�ļ�\t2:sdkp1-10.txt�ļ�\t3:udkp1-10.txt�ļ�\t4:wdkp1-10.txt�ļ�\t");
		System.out.print("���������ѡ��:");
		_=input.nextInt();
		switch(_) {
		case 1:
			read_file_data("C:\\Users\\����\\Desktop\\idkp1-10.txt");
			break;
		case 2:
			read_file_data("C:\\Users\\����\\Desktop\\sdkp1-10.txt");
			break;
		case 3:
			read_file_data("C:\\Users\\����\\Desktop\\udkp1-10.txt");
			break;
		case 4:
			read_file_data("C:\\Users\\����\\Desktop\\wdkp1-10.txt");
			break;
		default:System.out.println("");return;
		}
    	//������д��data
		data=new int[10000];
    	int i,j;
    	int count=1;
    	for(i=0;i<row;i++)
    	{
    		for(j=1;j<4;j++)
    		{
    			data[count]=weight[i][j]/10;
    			count++;
    		}
    	}
    	for(i=0;i<row;i++)
    	{
    		for(j=1;j<4;j++)
    		{
    			data[count]=value[i][j]/10;
    			count++;
    		}
    	}
	}


	final int PAD = 2;


    protected  void paintComponent(Graphics g) {
    	
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D)g;
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
                RenderingHints.VALUE_ANTIALIAS_ON);
        int w = getWidth();
        int h = getHeight();
        // Draw ordinate.
        g2.draw(new Line2D.Double(PAD, PAD, PAD, h-PAD));
        // Draw abcissa.
        g2.draw(new Line2D.Double(PAD, h-PAD, w-PAD, h-PAD));
        double xInc = (double)(w - 2*PAD)/(data.length-1);
        double scale = (double)(h - 2*PAD)/getMax();
        // Mark data points.
        g2.setPaint(Color.red);
        for(int i = 0; i < data.length; i++) {
            double x = PAD + i*xInc;
            double y = h - PAD - scale*data[i];
            g2.fill(new Ellipse2D.Double(x-2, y-2, 4, 4));
        }
    }

    private int getMax() {
        int max = -Integer.MAX_VALUE;
        for(int i = 0; i < data.length; i++) {
            if(data[i] > max)
                max = data[i];
        }
        return max;
    }

    public static void main(String[] args) {
    	read_file();
        JFrame f = new JFrame();
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        f.add(new san());
        f.setSize(500,500);
        f.setLocation(400,400);
        f.setVisible(true);

    }
}
