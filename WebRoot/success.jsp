<%@page import="com.mysql.jdbc.Driver"%>
<%@page import="java.sql.*"%>
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@page import="java.io.*"%>
<%@page import="java.util.Scanner" %>
<%@page import="org.apache.log4j.Logger" %>
<%@page import="java.io.IOException" %>
<%@page import="java.util.Random" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<%!

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
	static String RES=Integer.toString(res);
	static String RUN_TIME=Double.toString(run_time);
	private static Logger log1=Logger.getLogger(RES);
	private static Logger log2=Logger.getLogger(RUN_TIME);
		public static void read_file_data(String file_name,int t) {
        try {
            BufferedReader in = new BufferedReader(new FileReader(file_name));

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
  
	 %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<body>
 <form action="SuccessServlet" method="GET"> 
  <% 

        //1、显示注册信息       
		out.println("-----------------------1、显示注册信息---------------------------");
		out.println("</br>");//换行标签
		out.println("</br>");//换行标签
        response.setCharacterEncoding("UTF-8");
        request.setCharacterEncoding("UTF-8");
        response.setContentType("text/html");
        
        String tempcount=new String (request.getParameter("tempcount").getBytes("ISO-8859-1"),"UTF-8");
        String gender=new String (request.getParameter("gender").getBytes("ISO-8859-1"),"UTF-8");
        String file_name=new String (request.getParameter("filecount").getBytes("ISO-8859-1"),"UTF-8");

        out.println("备注：1:idkp1-10.txt文件       2:sdkp1-10.txt文件       3:udkp1-10.txt文件       4:wdkp1-10.txt文件");
        out.println("</br>");//换行标签
        out.println("备注：1:动态规划算法       2:回溯算法");
        out.println("</br>");//换行标签
        out.println("您已经提交成功，提交信息为：");
		out.println("</br>");//换行标签
		out.println("文件名："+ file_name);
		out.println("</br>");//换行标签
		out.println("组数："+tempcount);
		out.println("</br>");//换行标签
		out.println("方法名："+gender);
		out.println("</br>");//换行标签
        //2.读取数据
        out.println("</br>");//换行标签
		out.println("------------------------2、读取数据------------------------------");
		out.println("</br>");//换行标签
        int filecount=Integer.parseInt(file_name);
        int Tempcount=Integer.parseInt(tempcount);
   		switch(filecount) {
		case 1:
			read_file_data("C:\\Users\\戴尔\\Desktop\\idkp1-10.txt",Tempcount);
			break;
		case 2:
			read_file_data("C:\\Users\\戴尔\\Desktop\\sdkp1-10.txt",Tempcount);
			break;
		case 3:
			read_file_data("C:\\Users\\戴尔\\Desktop\\udkp1-10.txt",Tempcount);
			break;
		case 4:
			read_file_data("C:\\Users\\戴尔\\Desktop\\wdkp1-10.txt",Tempcount);
			break;
		default:System.out.println("");return;
		}
		out.println("</br>");//换行标签
		out.println("数据读取完成！");
		out.println("</br>");//换行标签
		//3、将D{0-1}KP 实例数据集需存储在数据库
        out.println("</br>");//换行标签
		out.println("------------------3、将D{0-1}KP 实例数据集需存储在数据库---------------------");
		out.println("</br>");//换行标签		
		DriverManager.registerDriver(new com.mysql.jdbc.Driver());
        Connection conn=DriverManager.getConnection("jdbc:mysql://localhost:3306/dpkp01?useUnicode=true&characterEncoding=utf-8", "root", "123456");
        Statement st=conn.createStatement();
       
        String querySql="select * from table_kp01";
        ResultSet rs=st.executeQuery(querySql);
        
        for(int i=1;i<row;i++) {
    		String Row=Integer.toString(i);
    		for(int j=1;j<4;j++) {
	    		String Weight=Integer.toString(weight[i][j]);
			    String Value=Integer.toString(value[i][j]);           	
				String addsql="INSERT INTO table_kp01(tempname,weight,value) VALUES(?,?,?)";
			    PreparedStatement pst=null;
				pst=conn.prepareStatement(addsql);//预编译SQL
		        pst.setString(1, Row);
				pst.setString(2, Weight);
				pst.setString(3, Value);
				pst.executeUpdate();//执行SQl语句	
		    }
		}
		out.println("</br>");//换行标签
	    out.println("数据已成功导入数据库！");
		out.println("</br>");//换行标签
		
		
		//4、调用算法
		out.println("</br>");//换行标签
		out.println("-------------------------4、调用算法-------------------------------");
		out.println("</br>");//换行标签
		out.println("</br>");//换行标签
		int gender1=Integer.parseInt(gender);
		if(gender1==1)
		{
		    out.println("您选择调用的算法是动态规划法！");
		    out.println("</br>");//换行标签
		    long  startTime = System.currentTimeMillis(); 
    		DP();
    		long endTime = System.currentTimeMillis();
    		run_time=(endTime - startTime)/1000.0;
    		log1.debug("最优解:"+res);
    		out.println("最优解:"+res);
    		out.println("</br>");//换行标签
    		log2.debug("运行时间:"+run_time+"s");
    		out.println("运行时间:"+run_time+"s");
		}
		else if(gender1==2)
		{
		    
		}
		out.println("</br>");//换行标签
		out.println("</br>");//换行标签

	  
%>
</form>
  </body>
</html>
