import java.util.Stack;

/*��ʽ��Ķ���*/
public class question {

	   
	char opr[] = {'+','-','*','/'};           //���������
	
	
	number oprd[] = new number[100];          //�洢������������
	char op[] = new char [100];               //�洢�����������
	number result = new number();             //������
	String res;                               //���������ַ�����ʽ
	
	
	/*��ʽ��Ĺ��캯���������ʼ�������������������������*/
	public void initialize (int r, int l) {              //����r���������ķ�Χ, l���������ĸ���
		
		int i,j;
		
		//���������ʽ
		for (i = 0; i < l; i++) {                        //�������������
			oprd[i]  =  new number();
			oprd[i].d  =  (int) (Math.random() * r) + 1;               //����
			oprd[i].n  =  (int) (Math.random() * r) + 1;               //��ĸ
			if (oprd[i].d > oprd[i].n)  {                              //������Ӵ��ڷ�ĸ��������������
				oprd[i].d /= oprd[i].n;
				oprd[i].n = 1;
			}
			yf(oprd[i]);                                               //Լ��
		}

		for (i = 0; i < l-1; i++) {                      //������������
			op[i]  =  opr[(int) (Math.random() * 4)];
		}
		op[l-1] = '=';
		
		//�������ʽ�Ľ��
		Stack <Character> operator = new Stack <Character>();       //�����ջ
		Stack <number> operand  = new Stack <number>();          //������ջ
		
		number x = new number(), y = new number(), z = new number();
		i = j = 0;
		
		while (true) {                                //ɨ��ջ
			
			operand.push(oprd[i++]);                    //��������ջ

			if (j == l-1)    break;                     //�������������ջ����ֹͣɨ��
			
			//�����ǰ����������ȼ��ϵ�
			while (!operator.isEmpty() && PRI(op[j]) <= PRI(operator.peek()))  {
				y = operand.pop();                      //������������ջ
				x = operand.pop();
				z = solve (x, y, operator.pop());       //�������ջ������
				operand.push(z);                        //�����ջ
			}
			
			operator.push(op[j++]);                     //ֱ����ջ
			
		}
		
		while (operand.size() != 1)  {              //���ջ
			y = operand.pop();                      //������������ջ
			x = operand.pop();
			z = solve (x, y, operator.pop());       //�������ջ������
			operand.push(z);                        //�����ջ
			
		}
		
        result = operand.pop();                     //������ջ��Ԫ��Ϊ���ս��
        
		//������ת�����ַ���
		if (result.n == 0)  res = String.valueOf(0);     //���Ϊ0
		else if (result.d == 1)  res = String.valueOf(result.n);     //���Ϊ����
		else res = String.valueOf(result.n) + '/' + String.valueOf(result.d);    //���Ϊ����
		
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
		
		yf (t1);           //���������Լ��
		return t1;
		
	}
	
	
	/*��������������ȼ�*/
	public int PRI (char c)  {
		if (c == '+' || c == '-')    return 0;
		else if (c == '*' || c == '/')    return 1;
		else    return -1;
	} 
	
	
	/*�����ʽ*/
	public void print (int l) { 
		
		String exp = new String();
		
		for (int i = 0; i < l; i ++)  {
	        if (oprd[i].d == 1)  exp += String.valueOf(oprd[i].n);
			else exp += String.valueOf(oprd[i].n) + '/' + String.valueOf(oprd[i].d);
	        exp += " " + op[i] + " ";
		}

        System.out.println(exp);
		
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
	
	
	/*����С������*/
	public int LCM (int a, int b) {
		
		return a * b / GCD(a,b);
		
	}
	
	
	/*�Է�������Լ��*/
	public void yf (number p) {
		
		int gcd = GCD (p.d, p.n);
		
		p.d /= gcd;
		p.n /= gcd;
		
	}
	
}

