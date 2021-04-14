
import java.awt.*;
import java.awt.geom.*;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;
import javax.swing.*;


public class san extends JPanel {

	static int Num = 0,Weight=0;				//Num:背包组数，Weight:总重量
	static Scanner input=new Scanner(System.in);
	static int weight[][];						//数据集中读入的重量，第一维表示第几组，第二维表示该组的第一个元素
	static int value[][];						//数据集中读入的价格
	static int row=1,col=1;						//数据集分组背包个数，及每个背包的物品个数
	static int dp[];							//dp求解
	static int res;								//最优解
	static double run_time;						//程序运行时间
	static int ans[];
	static int remain_capacity;
	static int data[];//输入对应的数据，是integer

	
	//读取数据集有效数据
	public static void read_file_data(String file_name) {
		try {
			int t;
            BufferedReader in = new BufferedReader(new FileReader(file_name));
            if(file_name=="C:\\Users\\戴尔\\Desktop\\idkp1-10.txt") {
            	System.out.println("读取数据（选择一组）(0-10):");
            }
            else {
            	System.out.println("读取数据（选择一组）(1-10):");
            }
            System.out.print("请做出你的选择:");
            t=input.nextInt();
            if(t>10||t<0) {
            	System.out.println("该组数据不存在");
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
	                	//清除垃圾数据
	                	for(;i<str1.length();i++) {
	                		char ch = str1.charAt(i);
	                		if(ch>='0'&&ch<='9') {
	                			break;
	                		}
	                	}
	                	//读取每组背包的个数
	                	for(;i<str1.length();i++) {
	                		char ch = str1.charAt(i);
	                		if(ch>='0'&&ch<='9') {
	                			x=x*10+ch-'0';
	                		}
	                		else {
	                			break;
	                		}
	                	}
	                	//清除垃圾数据
	                	for(;i<str1.length();i++) {
	                		char ch = str1.charAt(i);
	                		if(ch>='0'&&ch<='9') {
	                			break;
	                		}
	                	}
	                	//读取有几组背包
	                	for(;i<str1.length();i++) {
	                		char ch = str1.charAt(i);
	                		if(ch>='0'&&ch<='9') {
	                			Num= Num*10+ch-'0';
	                		}
	                		else {
	                			break;
	                		}
	                	}
	                	//清除垃圾数据
	                	for(;i<str1.length();i++) {
	                		char ch = str1.charAt(i);
	                		if(ch>='0'&&ch<='9') {
	                			break;
	                		}
	                	}
	                	//读取背包容量
	                	for(;i<str1.length();i++) {
	                		char ch = str1.charAt(i);
	                		if(ch>='0'&&ch<='9') {
	                			Weight=Weight*10+ch-'0';
	                		}
	                		else {
	                			break;
	                		}
	                	}
	                	//读取背包价值
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
	                	//读取背包容量
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
        	System.out.printf("该文件不存在");
        }
	}
	
	
	//选取数据集
	public static void read_file() {
		int _;
		System.out.println("读取测试数据文件（一个）:");
		System.out.println("1:idkp1-10.txt文件\t2:sdkp1-10.txt文件\t3:udkp1-10.txt文件\t4:wdkp1-10.txt文件\t");
		System.out.print("请做出你的选择:");
		_=input.nextInt();
		switch(_) {
		case 1:
			read_file_data("C:\\Users\\戴尔\\Desktop\\idkp1-10.txt");
			break;
		case 2:
			read_file_data("C:\\Users\\戴尔\\Desktop\\sdkp1-10.txt");
			break;
		case 3:
			read_file_data("C:\\Users\\戴尔\\Desktop\\udkp1-10.txt");
			break;
		case 4:
			read_file_data("C:\\Users\\戴尔\\Desktop\\wdkp1-10.txt");
			break;
		default:System.out.println("");return;
		}
    	//将数据写入data
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
