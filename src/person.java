
import java.io.*;
import java.util.Scanner;



public class person  {
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
	}
	
    
    //��������
    public static void data_sort() {
    	for(int i=1;i<row;i++) {
    		for(int j=i+1;j<row;j++) {
    			double x=value[i][3]/(double)weight[i][3];
    			double y=value[j][3]/(double)weight[j][3];
    			if(x<y) {
    				int a;
    				a=value[i][1];
    				value[i][1]=value[j][1];
    				value[j][1]=a;
    				a=value[i][2];
    				value[i][2]=value[j][2];
    				value[j][2]=a;
    				a=value[i][3];
    				value[i][3]=value[j][3];
    				value[j][3]=a;
    				
    				a=weight[i][1];
    				weight[i][1]=weight[j][1];
    				weight[j][1]=a;
    				a=weight[i][2];
    				weight[i][2]=weight[j][2];
    				weight[j][2]=a;
    				a=weight[i][3];
    				weight[i][3]=weight[j][3];
    				weight[j][3]=a;
    			}
    		}
    	}
    	for(int i=1;i<row;i++) {
    		System.out.println("��"+i+"������:"+"("+value[i][1]+","+weight[i][1]+")\t("+value[i][2]+","+weight[i][2]+")\t("+value[i][3]+","+weight[i][3]+")\n\t��ֵ������:"+value[i][3]/(double)weight[i][3]+"");
    	}
    }	
    
    //��̬�滮�����
    public static void DP(){
    	dp=new int[1000000];
    	for(int i=1;i<row;i++) {
    		for(int j=Weight;j>=0;j--) {
    			for(int k=1;k<=3;k++) {
    				if(weight[i][k]>j) {
    					continue;
    				}
    				if(dp[j]<dp[j-weight[i][k]]+value[i][k]) {
    					dp[j]=dp[j-weight[i][k]]+value[i][k];
    				}
    			}
    		}
    	}
    	for(int i=Weight-1;i>=0;i--) {
    		if(dp[i]==dp[i+1]) {
    			remain_capacity++;
    		}
    		else {
    			break;
    		}
    	}
    	res=dp[Weight];
    }
  
    public static void solve() {
    		long  startTime = System.currentTimeMillis(); 
    		DP();
    		long endTime = System.currentTimeMillis();
    		run_time=(endTime - startTime)/1000.0;
    		
    		System.out.println("���Ž�:"+res);
    		System.out.println("����ʱ��:"+run_time+"s");
    }

    //����д��txt�ļ�
    
    static void write_to_txt() throws FileNotFoundException {
    	PrintStream ps = new PrintStream("res.txt");
    	ps.println("���Ž�:"+res);
    	ps.println("����ʱ��:"+run_time+"s");
    	ps.println("�������յ�ʣ������:"+remain_capacity);
    	ps.close();
    }
    
    static void get_remain_capacity() {
    	System.out.println("�������յ�ʣ������:"+remain_capacity);
    }
    
	public static void main(String[] args) throws FileNotFoundException {
		read_file();			//��ȡ�ı���Ч����
		data_sort();			//��������
		solve();				//��̬�滮�㷨
		write_to_txt();			//�洢�����txt
		get_remain_capacity();	//��ȡ�õ����Ž�󣬱���ʣ�������
	}
}