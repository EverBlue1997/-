import java.util.Scanner;

public class Week1 {
	
	public static void main(String[] args) {
		
    	Scanner scan = new Scanner(System.in);
       	
    	int r = Integer.parseInt(args[0]);              //运算数的范围，由命令行输入
    	int n = Integer.parseInt(args[1]);              //算式的数量，由命令行输入
    	int l = Integer.parseInt(args[2]);              //运算数的个数，由命令行输入
    	int count = 0;                                  //用户答对的题数
    	
       	expression e[];                                 //创建一个算式数组
       	e = new expression[n];    
       	int same = 0;                                   //重复标志
    	
    	for (int i = 0; i < n; i ++ ) {
    		
    		do {
    			
            	same = 0;
	    		e[i] = new expression (l, r);
	    		
	    		for (int j = 0; j < i; j ++) {          //判断与之前的算式是否重复
	    			if ((e[j].exp).equals(e[i].exp))  {
	    				same = 1;
	    				break;
	    			}
	    		}
	    		
    		} while (same == 1);

    		e[i].print();                                        //将算式打印到屏幕上
    		
    		if (scan.nextLine().equals (e[i].value.str))  {      //接受用户输入的答案并判断正误              
    			System.out.println("回答正确!\n");
    			count ++;
    		}
    		else  {
    			System.out.println("回答错误! 正确答案是: " + e[i].value.str + "\n");
    		}
    		
    	}
    	
    	//输出分数
    	System.out.println("您答对了" + count + "道题目，得分:" + 100 * count / n + "!\n");
    	
    	scan.close();

}

}
