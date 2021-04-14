
 
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Random;
import java.util.Scanner;
 
public class GA2 {
 
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
	static int v[];
	static int b[];
	
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
		int count=1;
		v=new int[1000];
		b=new int[1000];
		int k;
    	for(k=0;k<row;k++)
    	{
    		for(int j=1;j<4;j++)
    		{
    			v[count]=value[k][j];
    			b[count]=weight[k][j];
    			count++;
    		}
    	}
	}
	private int pb;// 背包容积
	private int LL; // 染色体长度
	private int scale;// 种群规模
	private int MAX_GEN; // 运行代数
 
	private int bestT;// 最佳出现代数
	private int bestLength; // 最佳编码价值
	private int[] bestTour; // 最佳编码
 
	// 初始种群，父代种群，行数表示种群规模，一行代表一个个体，即染色体，列表示染色体基因片段
	private int[][] oldPopulation;
	private int[][] newPopulation;// 新的种群，子代种群
	private int[] fitness;// 种群适应度，表示种群中各个个体的适应度
 
	private float[] Pi;// 种群中各个个体的累计概率
	private float Pc;// 交叉概率
	private float Pm;// 变异概率
	private int t;// 当前代数
	private Random random;
	

 
	// 种群规模，染色体长度,最大代数，交叉率，变异率
	public GA2(int s, int l, int g, float c, float m) {
		scale = s;
		LL = l;
		MAX_GEN = g;
		Pc = c;
		Pm = m;
	}
 
	private void init() throws IOException {
		bestLength = 0;
		bestTour = new int[LL];
		bestT = 0;
		t = 0;
 
		newPopulation = new int[scale][LL];
		oldPopulation = new int[scale][LL];
		fitness = new int[scale];
		Pi = new float[scale];
 
		random = new Random(System.currentTimeMillis());
	}
 
	// 初始化种群
	void initGroup() {
		int k, i;
		for (k = 0; k < scale; k++)// 种群数
		{
			// 01编码
			for (i = 0; i < LL; i++) {
				oldPopulation[k][i] = random.nextInt(65535) % 2;
			}
		}
	}
 
	public int evaluate(int[] chromosome) {
		// 010110
		int vv = 0;
		int bb = 0;
		// 染色体，起始城市,城市1,城市2...城市n
		for (int i = 0; i < LL; i++) {
			if (chromosome[i] == 1) {
				vv += v[i];
				bb += b[i];
			}
		}

		if (bb > Weight) {
			// 超出背包体积
			return 0;
		} else {
			return vv;
		}
	}
 
	// 计算种群中各个个体的累积概率，前提是已经计算出各个个体的适应度fitness[max]，作为赌轮选择策略一部分，Pi[max]
	void countRate() {
		int k;
		double sumFitness = 0;// 适应度总和
 
		int[] tempf = new int[scale];
 
		for (k = 0; k < scale; k++) {
			tempf[k] = fitness[k];
			sumFitness += tempf[k];
		}
 
		Pi[0] = (float) (tempf[0] / sumFitness);
		for (k = 1; k < scale; k++) {
			Pi[k] = (float) (tempf[k] / sumFitness + Pi[k - 1]);
		}
	}
 
	// 挑选某代种群中适应度最高的个体，直接复制到子代中
	// 前提是已经计算出各个个体的适应度Fitness[max]
	public void selectBestGh() {
		int k, i, maxid;
		int maxevaluation;
 
		maxid = 0;
		maxevaluation = fitness[0];
		for (k = 1; k < scale; k++) {
			if (maxevaluation < fitness[k]) {
				maxevaluation = fitness[k];
				maxid = k;
			}
		}
 
		if (bestLength < maxevaluation) {
			bestLength = maxevaluation;
			bestT = t;// 最好的染色体出现的代数;
			for (i = 0; i < LL; i++) {
				bestTour[i] = oldPopulation[maxid][i];
			}
		}
 
		// 复制染色体，k表示新染色体在种群中的位置，kk表示旧的染色体在种群中的位置
		copyGh(0, maxid);// 将当代种群中适应度最高的染色体k复制到新种群中，排在第一位0
	}
 
	// 复制染色体，k表示新染色体在种群中的位置，kk表示旧的染色体在种群中的位置
	public void copyGh(int k, int kk) {
		int i;
		for (i = 0; i < LL; i++) {
			newPopulation[k][i] = oldPopulation[kk][i];
		}
	}
 
	// 赌轮选择策略挑选
	public void select() {
		int k, i, selectId;
		float ran1;
		for (k = 1; k < scale; k++) {
			ran1 = (float) (random.nextInt(65535) % 1000 / 1000.0);
			// System.out.println("概率"+ran1);
			// 产生方式
			for (i = 0; i < scale; i++) {
				if (ran1 <= Pi[i]) {
					break;
				}
			}
			selectId = i;
			copyGh(k, selectId);
		}
	}
 
	public void evolution() {
		int k;
		// 挑选某代种群中适应度最高的个体
		selectBestGh();
		// 赌轮选择策略挑选scale-1个下一代个体
		select();
		float r;
 
		// 交叉方法
		for (k = 0; k < scale; k = k + 2) {
			r = random.nextFloat();// /产生概率
			// System.out.println("交叉率..." + r);
			if (r < Pc) {
				// System.out.println(k + "与" + k + 1 + "进行交叉...");
				OXCross(k, k + 1);// 进行交叉
			} else {
				r = random.nextFloat();// /产生概率
				// System.out.println("变异率1..." + r);
				// 变异
				if (r < Pm) {
					// System.out.println(k + "变异...");
					OnCVariation(k);
				}
				r = random.nextFloat();// /产生概率
				// System.out.println("变异率2..." + r);
				// 变异
				if (r < Pm) {
					// System.out.println(k + 1 + "变异...");
					OnCVariation(k + 1);
				}
			}
 
		}
 
	}
	
 
	// 两点交叉算子
	void OXCross(int k1, int k2) {
		int i, j, flag;
		int ran1, ran2, temp = 0;
 
		ran1 = random.nextInt(65535) % LL;
		ran2 = random.nextInt(65535) % LL;
 
		while (ran1 == ran2) {
			ran2 = random.nextInt(65535) % LL;
		}
		if (ran1 > ran2)// 确保ran1<ran2
		{
			temp = ran1;
			ran1 = ran2;
			ran2 = temp;
		}
		flag = ran2 - ran1 + 1;// 个数
		for (i = 0, j = ran1; i < flag; i++, j++) {
			temp = newPopulation[k1][j];
			newPopulation[k1][j] = newPopulation[k2][j];
			newPopulation[k2][j] = temp;
		}
 
	}
 
	// 多次对换变异算子
	public void OnCVariation(int k) {
		int ran1, ran2, temp;
		int count;// 对换次数
		count = random.nextInt(65535) % LL;
 
		for (int i = 0; i < count; i++) {
 
			ran1 = random.nextInt(65535) % LL;
			ran2 = random.nextInt(65535) % LL;
			while (ran1 == ran2) {
				ran2 = random.nextInt(65535) % LL;
			}
			temp = newPopulation[k][ran1];
			newPopulation[k][ran1] = newPopulation[k][ran2];
			newPopulation[k][ran2] = temp;
		}
	}
 
	public void solve() {
		int i;
		int k;
 
		// 初始化种群
		initGroup();
		// 计算初始化种群适应度，Fitness[max]
		for (k = 0; k < scale; k++) {
			fitness[k] = evaluate(oldPopulation[k]);
			// System.out.println(fitness[k]);
		}
 
		// 计算初始化种群中各个个体的累积概率，Pi[max]
		countRate();
		System.out.println("初始种群...");
		for (k = 0; k < scale; k++) {
			for (i = 0; i < LL; i++) {
				System.out.print(oldPopulation[k][i] + ",");
			}
			System.out.println();
			System.out.println("----" + fitness[k] + " " + Pi[k]);
		}
		//evolution();
 
		for (t = 0; t < MAX_GEN; t++) {
			evolution();
			// 将新种群newGroup复制到旧种群oldGroup中，准备下一代进化
			for (k = 0; k < scale; k++) {
				for (i = 0; i < LL; i++) {
					oldPopulation[k][i] = newPopulation[k][i];
				}
			}
			// 计算种群适应度
			for (k = 0; k < scale; k++) {
				fitness[k] = evaluate(oldPopulation[k]);
			}
			// 计算种群中各个个体的累积概率
			countRate();
		}
 
		System.out.println("最后种群...");
		for (k = 0; k < scale; k++) {
			for (i = 0; i < LL; i++) {
				System.out.print(oldPopulation[k][i] + ",");
			}
			System.out.println();
			System.out.println("---" + fitness[k] + " " + Pi[k]);
		}
 
		System.out.println("最佳编码出现代数：");
		System.out.println(bestT);
		System.out.println("最佳编码价值");
		System.out.println(bestLength);
		System.out.println("最佳编码：");
		for (i = 0; i < LL; i++) {
			System.out.print(bestTour[i] + ",");
		}
 
	}
 
	/**
	 * @param args
	 * @throws IOException
	 */
	public static void main(String[] args) throws IOException {
		System.out.println("Start....");
	    read_file();			//读取文本有效数据
		GA2 ga = new GA2(20, 50, 500, 0.8f, 0.9f);
		ga.init();
		ga.solve();
	}
}