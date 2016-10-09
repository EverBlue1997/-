/*
 * 表达式类的定义
 */

public class expression {

	String  exp;         //表达式的字符串形式
	number  value;       //表达式的值
	char    op = ' ';    //两个子表达式中间的算符

	
	/*
	 * 表达式类的构造函数
	 * 参数length为表达式的长度, range为运算数的范围
	 */
	public expression (int length, int range)  {
		
		exp = new String ();
		value = new number();
		
		char opr[] = {'+','-','*','/'};           //四则运算符
		
		if (length == 1)    {       //递归边界，表达式长度为1
			
			number num = new number (range);
			
			exp += num.str;
			
			value = num;
			
		}
		
		else if (length == 2)    {  //递归边界，表达式长度为2
			
			number n1 = new number (range);
			number n2 = new number (range);
			op = opr[(int) (Math.random() * 4)];
			
			exp += n1.str + " " + op + " " + n2.str;
			
			value = solve (n1, n2, op);
			
		}
		
		else    {                   //如果表达式的长度大于2，则要递归生成表达式
			
			int temp = (int) (Math.random() * (length-1)) + 1;         
			op = opr[(int) (Math.random() * 4)];
			
			//将表达式分解为两个子表达式，子表达式的长度随机生成
			expression e1 = new expression (temp, range);
			expression e2 = new expression (length - temp, range);
			
			//将子表达式拼接在一起
			
			if (temp == 1                   //如果子表达式长度为1，或者长度为2且算符为乘除，则不用加括号
					|| (temp == 2 && (e1.op == '*' || e1.op == '/')))  {				
				exp += e1.exp;				
			}
			else  {
				exp += "(" + e1.exp + ")";
			}
			
			exp += " " + op + " ";
			
			if (length - temp == 1          //如果子表达式长度为1，或者长度为2且算符为乘除，则不用加括号
					|| (length - temp == 2 && (e2.op == '*' || e2.op == '/')))  {				
				exp += e2.exp;				
			}
			else  {
				exp += "(" + e2.exp + ")";
			}
			
			//计算结果
			value = solve (e1.value, e2.value, op);
			
		}
		
	}
	
	/*
	 * 默认的构造函数
	 */
	public expression ()  { 
		
		exp = new String();
		value = new number();
		
	}
	
	/*输出表达式*/
	public void print ()  {
		
		System.out.println(exp + " = ");
		
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

		yf (t1);                 //将结果进行约分
		
		if (t1.d == 1)    {      //生成结果的字符串形式
			t1.str += t1.n;
		}
		else    {
			t1.str += t1.n + "/" + t1.d;
		}
		
		return t1;
		
	}
	
	/*求最小公倍数*/
	public int LCM (int a, int b) {
		
		return a * b / GCD(a,b);
		
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
	
	/*对分数进行约分*/
	public void yf (number p) {
		
		int gcd = GCD (p.d, p.n);
		
		p.d /= gcd;
		p.n /= gcd;
		
	}
	
}
