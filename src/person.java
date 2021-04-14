
import java.io.*;
import java.util.Scanner;



public class person  {
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
	}
	
    
    //数据排序
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
    		System.out.println("第"+i+"组数据:"+"("+value[i][1]+","+weight[i][1]+")\t("+value[i][2]+","+weight[i][2]+")\t("+value[i][3]+","+weight[i][3]+")\n\t价值重量比:"+value[i][3]/(double)weight[i][3]+"");
    	}
    }	
    
    //动态规划法求解
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
    		
    		System.out.println("最优解:"+res);
    		System.out.println("运行时间:"+run_time+"s");
    }

    //将答案写入txt文件
    
    static void write_to_txt() throws FileNotFoundException {
    	PrintStream ps = new PrintStream("res.txt");
    	ps.println("最优解:"+res);
    	ps.println("运行时间:"+run_time+"s");
    	ps.println("背包最终的剩余容量:"+remain_capacity);
    	ps.close();
    }
    
    static void get_remain_capacity() {
    	System.out.println("背包最终的剩余容量:"+remain_capacity);
    }
    
	public static void main(String[] args) throws FileNotFoundException {
		read_file();			//读取文本有效数据
		data_sort();			//数据排序
		solve();				//动态规划算法
		write_to_txt();			//存储结果到txt
		get_remain_capacity();	//获取得到最优解后，背包剩余的容量
	}
}