
 
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Random;
import java.util.Scanner;
 
public class GA2 {
 
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
	static int v[];
	static int b[];
	
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
	private int pb;// �����ݻ�
	private int LL; // Ⱦɫ�峤��
	private int scale;// ��Ⱥ��ģ
	private int MAX_GEN; // ���д���
 
	private int bestT;// ��ѳ��ִ���
	private int bestLength; // ��ѱ����ֵ
	private int[] bestTour; // ��ѱ���
 
	// ��ʼ��Ⱥ��������Ⱥ��������ʾ��Ⱥ��ģ��һ�д���һ�����壬��Ⱦɫ�壬�б�ʾȾɫ�����Ƭ��
	private int[][] oldPopulation;
	private int[][] newPopulation;// �µ���Ⱥ���Ӵ���Ⱥ
	private int[] fitness;// ��Ⱥ��Ӧ�ȣ���ʾ��Ⱥ�и����������Ӧ��
 
	private float[] Pi;// ��Ⱥ�и���������ۼƸ���
	private float Pc;// �������
	private float Pm;// �������
	private int t;// ��ǰ����
	private Random random;
	

 
	// ��Ⱥ��ģ��Ⱦɫ�峤��,�������������ʣ�������
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
 
	// ��ʼ����Ⱥ
	void initGroup() {
		int k, i;
		for (k = 0; k < scale; k++)// ��Ⱥ��
		{
			// 01����
			for (i = 0; i < LL; i++) {
				oldPopulation[k][i] = random.nextInt(65535) % 2;
			}
		}
	}
 
	public int evaluate(int[] chromosome) {
		// 010110
		int vv = 0;
		int bb = 0;
		// Ⱦɫ�壬��ʼ����,����1,����2...����n
		for (int i = 0; i < LL; i++) {
			if (chromosome[i] == 1) {
				vv += v[i];
				bb += b[i];
			}
		}

		if (bb > Weight) {
			// �����������
			return 0;
		} else {
			return vv;
		}
	}
 
	// ������Ⱥ�и���������ۻ����ʣ�ǰ�����Ѿ�����������������Ӧ��fitness[max]����Ϊ����ѡ�����һ���֣�Pi[max]
	void countRate() {
		int k;
		double sumFitness = 0;// ��Ӧ���ܺ�
 
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
 
	// ��ѡĳ����Ⱥ����Ӧ����ߵĸ��壬ֱ�Ӹ��Ƶ��Ӵ���
	// ǰ�����Ѿ�����������������Ӧ��Fitness[max]
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
			bestT = t;// ��õ�Ⱦɫ����ֵĴ���;
			for (i = 0; i < LL; i++) {
				bestTour[i] = oldPopulation[maxid][i];
			}
		}
 
		// ����Ⱦɫ�壬k��ʾ��Ⱦɫ������Ⱥ�е�λ�ã�kk��ʾ�ɵ�Ⱦɫ������Ⱥ�е�λ��
		copyGh(0, maxid);// ��������Ⱥ����Ӧ����ߵ�Ⱦɫ��k���Ƶ�����Ⱥ�У����ڵ�һλ0
	}
 
	// ����Ⱦɫ�壬k��ʾ��Ⱦɫ������Ⱥ�е�λ�ã�kk��ʾ�ɵ�Ⱦɫ������Ⱥ�е�λ��
	public void copyGh(int k, int kk) {
		int i;
		for (i = 0; i < LL; i++) {
			newPopulation[k][i] = oldPopulation[kk][i];
		}
	}
 
	// ����ѡ�������ѡ
	public void select() {
		int k, i, selectId;
		float ran1;
		for (k = 1; k < scale; k++) {
			ran1 = (float) (random.nextInt(65535) % 1000 / 1000.0);
			// System.out.println("����"+ran1);
			// ������ʽ
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
		// ��ѡĳ����Ⱥ����Ӧ����ߵĸ���
		selectBestGh();
		// ����ѡ�������ѡscale-1����һ������
		select();
		float r;
 
		// ���淽��
		for (k = 0; k < scale; k = k + 2) {
			r = random.nextFloat();// /��������
			// System.out.println("������..." + r);
			if (r < Pc) {
				// System.out.println(k + "��" + k + 1 + "���н���...");
				OXCross(k, k + 1);// ���н���
			} else {
				r = random.nextFloat();// /��������
				// System.out.println("������1..." + r);
				// ����
				if (r < Pm) {
					// System.out.println(k + "����...");
					OnCVariation(k);
				}
				r = random.nextFloat();// /��������
				// System.out.println("������2..." + r);
				// ����
				if (r < Pm) {
					// System.out.println(k + 1 + "����...");
					OnCVariation(k + 1);
				}
			}
 
		}
 
	}
	
 
	// ���㽻������
	void OXCross(int k1, int k2) {
		int i, j, flag;
		int ran1, ran2, temp = 0;
 
		ran1 = random.nextInt(65535) % LL;
		ran2 = random.nextInt(65535) % LL;
 
		while (ran1 == ran2) {
			ran2 = random.nextInt(65535) % LL;
		}
		if (ran1 > ran2)// ȷ��ran1<ran2
		{
			temp = ran1;
			ran1 = ran2;
			ran2 = temp;
		}
		flag = ran2 - ran1 + 1;// ����
		for (i = 0, j = ran1; i < flag; i++, j++) {
			temp = newPopulation[k1][j];
			newPopulation[k1][j] = newPopulation[k2][j];
			newPopulation[k2][j] = temp;
		}
 
	}
 
	// ��ζԻ���������
	public void OnCVariation(int k) {
		int ran1, ran2, temp;
		int count;// �Ի�����
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
 
		// ��ʼ����Ⱥ
		initGroup();
		// �����ʼ����Ⱥ��Ӧ�ȣ�Fitness[max]
		for (k = 0; k < scale; k++) {
			fitness[k] = evaluate(oldPopulation[k]);
			// System.out.println(fitness[k]);
		}
 
		// �����ʼ����Ⱥ�и���������ۻ����ʣ�Pi[max]
		countRate();
		System.out.println("��ʼ��Ⱥ...");
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
			// ������ȺnewGroup���Ƶ�����ȺoldGroup�У�׼����һ������
			for (k = 0; k < scale; k++) {
				for (i = 0; i < LL; i++) {
					oldPopulation[k][i] = newPopulation[k][i];
				}
			}
			// ������Ⱥ��Ӧ��
			for (k = 0; k < scale; k++) {
				fitness[k] = evaluate(oldPopulation[k]);
			}
			// ������Ⱥ�и���������ۻ�����
			countRate();
		}
 
		System.out.println("�����Ⱥ...");
		for (k = 0; k < scale; k++) {
			for (i = 0; i < LL; i++) {
				System.out.print(oldPopulation[k][i] + ",");
			}
			System.out.println();
			System.out.println("---" + fitness[k] + " " + Pi[k]);
		}
 
		System.out.println("��ѱ�����ִ�����");
		System.out.println(bestT);
		System.out.println("��ѱ����ֵ");
		System.out.println(bestLength);
		System.out.println("��ѱ��룺");
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
	    read_file();			//��ȡ�ı���Ч����
		GA2 ga = new GA2(20, 50, 500, 0.8f, 0.9f);
		ga.init();
		ga.solve();
	}
}