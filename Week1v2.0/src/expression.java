/*
 * ���ʽ��Ķ���
 */

public class expression {

	String  exp;         //���ʽ���ַ�����ʽ
	number  value;       //���ʽ��ֵ
	char    op = ' ';    //�����ӱ��ʽ�м�����

	
	/*
	 * ���ʽ��Ĺ��캯��
	 * ����lengthΪ���ʽ�ĳ���, rangeΪ�������ķ�Χ
	 */
	public expression (int length, int range)  {
		
		exp = new String ();
		value = new number();
		
		char opr[] = {'+','-','*','/'};           //���������
		
		if (length == 1)    {       //�ݹ�߽磬���ʽ����Ϊ1
			
			number num = new number (range);
			
			exp += num.str;
			
			value = num;
			
		}
		
		else if (length == 2)    {  //�ݹ�߽磬���ʽ����Ϊ2
			
			number n1 = new number (range);
			number n2 = new number (range);
			op = opr[(int) (Math.random() * 4)];
			
			exp += n1.str + " " + op + " " + n2.str;
			
			value = solve (n1, n2, op);
			
		}
		
		else    {                   //������ʽ�ĳ��ȴ���2����Ҫ�ݹ����ɱ��ʽ
			
			int temp = (int) (Math.random() * (length-1)) + 1;         
			op = opr[(int) (Math.random() * 4)];
			
			//�����ʽ�ֽ�Ϊ�����ӱ��ʽ���ӱ��ʽ�ĳ����������
			expression e1 = new expression (temp, range);
			expression e2 = new expression (length - temp, range);
			
			//���ӱ��ʽƴ����һ��
			
			if (temp == 1                   //����ӱ��ʽ����Ϊ1�����߳���Ϊ2�����Ϊ�˳������ü�����
					|| (temp == 2 && (e1.op == '*' || e1.op == '/')))  {				
				exp += e1.exp;				
			}
			else  {
				exp += "(" + e1.exp + ")";
			}
			
			exp += " " + op + " ";
			
			if (length - temp == 1          //����ӱ��ʽ����Ϊ1�����߳���Ϊ2�����Ϊ�˳������ü�����
					|| (length - temp == 2 && (e2.op == '*' || e2.op == '/')))  {				
				exp += e2.exp;				
			}
			else  {
				exp += "(" + e2.exp + ")";
			}
			
			//������
			value = solve (e1.value, e2.value, op);
			
		}
		
	}
	
	/*
	 * Ĭ�ϵĹ��캯��
	 */
	public expression ()  { 
		
		exp = new String();
		value = new number();
		
	}
	
	/*������ʽ*/
	public void print ()  {
		
		System.out.println(exp + " = ");
		
	}
	
	/*�������������ʽ��ֵ*/
	public number solve (number x, number y, char op)  {
		
		number t1 = new number(), t2 = new number();
        int lcm = LCM (x.d, y.d);
        
		t1.n = x.n * lcm / x.d;            //������ͨ��
		t2.n = y.n * lcm / y.d;
		t1.d = t2.d = lcm;

		switch (op) {                      //������
		
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

		yf (t1);                 //���������Լ��
		
		if (t1.d == 1)    {      //���ɽ�����ַ�����ʽ
			t1.str += t1.n;
		}
		else    {
			t1.str += t1.n + "/" + t1.d;
		}
		
		return t1;
		
	}
	
	/*����С������*/
	public int LCM (int a, int b) {
		
		return a * b / GCD(a,b);
		
	}
	
	/*�����Լ��*/
	public int GCD (int a, int b) {
		
		int gcd = 1;
		
		for (int i = 1; i <= a; i ++) {
			if (a % i == 0 && b % i == 0) {
				gcd = i;
			}
		}
		
		return gcd;
	}
	
	/*�Է�������Լ��*/
	public void yf (number p) {
		
		int gcd = GCD (p.d, p.n);
		
		p.d /= gcd;
		p.n /= gcd;
		
	}
	
}
