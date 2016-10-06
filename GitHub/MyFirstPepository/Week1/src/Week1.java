import java.util.Scanner;

public class Week1 {
	
    public static void main (String args[]) {
		
    	Scanner scan = new Scanner(System.in);
       	question q = new question();                    //创建一个算式的实例
       	
    	int r = Integer.parseInt(args[0]);              //运算数的范围，由命令行输入
    	int n = Integer.parseInt(args[1]);              //算式的数量，由命令行输入
    	int l = Integer.parseInt(args[2]);              //运算数的个数，由命令行输入
    	int count = 0;                                  //用户答对的题数
    	
    	for (int i = 0; i < n; i ++ ) {
    		
    		q.initialize(r,l);                          //随机生成算式
    		q.print(l);                                 //将算式打印到屏幕上
    		
    		if ( scan.nextLine().equals (q.res) )  {      //接受用户输入的答案并判断正误              
    			System.out.println("回答正确!\n");
    			count ++;
    		}
    		else  {
    			System.out.println("回答错误! 正确答案是: " + q.res + "\n");
    		}
    		
    	}
    	
    	//输出分数
    	System.out.println("您答对了" + count + "道题目，得分:" + 100 * count / n + "!\n");
    	
    	scan.close();

	}

}
