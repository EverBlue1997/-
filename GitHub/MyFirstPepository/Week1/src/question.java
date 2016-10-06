import java.util.Stack;

/*算式类的定义*/
public class question {

	   
	char opr[] = {'+','-','*','/'};           //四则运算符
	
	
	number oprd[] = new number[100];          //存储运算数的数组
	char op[] = new char [100];               //存储运算符的数组
	number result = new number();             //运算结果
	String res;                               //运算结果的字符串形式
	
	
	/*算式类的构造函数，随机初始化运算数和算符，并计算出结果*/
	public void initialize (int r, int l) {              //参数r是运算数的范围, l是运算数的个数
		
		int i,j;
		
		//随机生成算式
		for (i = 0; i < l; i++) {                        //随机生成运算数
			oprd[i]  =  new number();
			oprd[i].d  =  (int) (Math.random() * r) + 1;               //分子
			oprd[i].n  =  (int) (Math.random() * r) + 1;               //分母
			if (oprd[i].d > oprd[i].n)  {                              //如果分子大于分母，则当做整数处理
				oprd[i].d /= oprd[i].n;
				oprd[i].n = 1;
			}
			yf(oprd[i]);                                               //约分
		}

		for (i = 0; i < l-1; i++) {                      //随机生成运算符
			op[i]  =  opr[(int) (Math.random() * 4)];
		}
		op[l-1] = '=';
		
		//计算该算式的结果
		Stack <Character> operator = new Stack <Character>();       //运算符栈
		Stack <number> operand  = new Stack <number>();          //运算数栈
		
		number x = new number(), y = new number(), z = new number();
		i = j = 0;
		
		while (true) {                                //扫描栈
			
			operand.push(oprd[i++]);                    //运算数入栈

			if (j == l-1)    break;                     //所有运算符已入栈，则停止扫描
			
			//如果当前运算符的优先级较低
			while (!operator.isEmpty() && PRI(op[j]) <= PRI(operator.peek()))  {
				y = operand.pop();                      //两个运算数出栈
				x = operand.pop();
				z = solve (x, y, operator.pop());       //运算符出栈，计算
				operand.push(z);                        //结果入栈
			}
			
			operator.push(op[j++]);                     //直接入栈
			
		}
		
		while (operand.size() != 1)  {              //清除栈
			y = operand.pop();                      //两个运算数出栈
			x = operand.pop();
			z = solve (x, y, operator.pop());       //运算符出栈，计算
			operand.push(z);                        //结果入栈
			
		}
		
        result = operand.pop();                     //运算数栈底元素为最终结果
        
		//运算结果转换成字符串
		if (result.n == 0)  res = String.valueOf(0);     //结果为0
		else if (result.d == 1)  res = String.valueOf(result.n);     //结果为整数
		else res = String.valueOf(result.n) + '/' + String.valueOf(result.d);    //结果为分数
		
	}
	
	
	/*求两运算数表达式的值*/
	public number solve (number x, number y, char op)  {
		
		number t1 = new number(), t2 = new number();
        int lcm = LCM (x.d, y.d);
        
		t1.n = x.n * lcm / x.d;            //将两数通分
		t2.n = y.n * lcm / y.d;
		t1.d = t2.d = lcm;

		switch (op) {                      //计算结果
		
		    case '+':
		    	t1.n += t2.n;
		    	break;
		    	
		    case '-':
		    	t1.n -= t2.n;
		    	break;
		    	
		    case '*':
		    	t1.n = x.n * y.n;
		    	t1.d = x.d * y.d;
		    	break;
		    	
		    case '/':
		    	t1.n = x.n * y.d;
		    	t1.d = x.d * y.n;
		    	break;
		    	
		    default:
		    	break;
		    	
		}
		
		yf (t1);           //将结果进行约分
		return t1;
		
	}
	
	
	/*计算运算符的优先级*/
	public int PRI (char c)  {
		if (c == '+' || c == '-')    return 0;
		else if (c == '*' || c == '/')    return 1;
		else    return -1;
	} 
	
	
	/*输出算式*/
	public void print (int l) { 
		
		String exp = new String();
		
		for (int i = 0; i < l; i ++)  {
	        if (oprd[i].d == 1)  exp += String.valueOf(oprd[i].n);
			else exp += String.valueOf(oprd[i].n) + '/' + String.valueOf(oprd[i].d);
	        exp += " " + op[i] + " ";
		}

        System.out.println(exp);
		
	}
	
	
	/*求最大公约数*/
	public int GCD (int a, int b) {
		
		int gcd = 1;
		
		for (int i = 1; i <= a; i ++) {
			if (a % i == 0 && b % i == 0) {
				gcd = i;
			}
		}
		
		return gcd;
	}
	
	
	/*求最小公倍数*/
	public int LCM (int a, int b) {
		
		return a * b / GCD(a,b);
		
	}
	
	
	/*对分数进行约分*/
	public void yf (number p) {
		
		int gcd = GCD (p.d, p.n);
		
		p.d /= gcd;
		p.n /= gcd;
		
	}
	
}

