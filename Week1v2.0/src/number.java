/*
 * ��������Ķ���
*/

public class number {
		
	int n;                     //����
	int d;                     //��ĸ
	String str;                //�ַ�����ʽ
	
	/*
	 * ��������Ĺ��캯��
	 * ����rangeΪ���Ӻͷ�ĸ��ȡֵ��Χ
	 */
	public number (int range)    {
		
		d  =  (int) (Math.random() * range + 1.0);               //����
		n  =  (int) (Math.random() * range + 1.0);               //��ĸ

		if (d < n)  {                                            //������Ӵ��ڷ�ĸ��������������
			d = 1;
		}
		
		yf(this);                                                //Լ��
		
		str = new String();
		if (d == 1)    {                                         //������������ַ�����ʽ
			str += n;
		}
		else    {
			str += n + "/" + d;
		}

		
	}
	
	/*
	 * Ĭ�ϵĹ��캯��
	 */
	public number ()  {
		
		str = new String ();
		
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