/*
 * 操作数类的定义
*/

public class number {
		
	int n;                     //分子
	int d;                     //分母
	String str;                //字符串形式
	
	/*
	 * 操作数类的构造函数
	 * 参数range为分子和分母的取值范围
	 */
	public number (int range)    {
		
		d  =  (int) (Math.random() * range + 1.0);               //分子
		n  =  (int) (Math.random() * range + 1.0);               //分母

		if (d < n)  {                                            //如果分子大于分母，则当做整数处理
			d = 1;
		}
		
		yf(this);                                                //约分
		
		str = new String();
		if (d == 1)    {                                         //生成这个数的字符串形式
			str += n;
		}
		else    {
			str += n + "/" + d;
		}

		
	}
	
	/*
	 * 默认的构造函数
	 */
	public number ()  {
		
		str = new String ();
		
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