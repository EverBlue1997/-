import java.util.Scanner;

public class Week1 {
	
	public static void main(String[] args) {
		
    	Scanner scan = new Scanner(System.in);
       	
    	int r = Integer.parseInt(args[0]);              //�������ķ�Χ��������������
    	int n = Integer.parseInt(args[1]);              //��ʽ��������������������
    	int l = Integer.parseInt(args[2]);              //�������ĸ�����������������
    	int count = 0;                                  //�û���Ե�����
    	
       	expression e[];                                 //����һ����ʽ����
       	e = new expression[n];    
       	int same = 0;                                   //�ظ���־
    	
    	for (int i = 0; i < n; i ++ ) {
    		
    		do {
    			
            	same = 0;
	    		e[i] = new expression (l, r);
	    		
	    		for (int j = 0; j < i; j ++) {          //�ж���֮ǰ����ʽ�Ƿ��ظ�
	    			if ((e[j].exp).equals(e[i].exp))  {
	    				same = 1;
	    				break;
	    			}
	    		}
	    		
    		} while (same == 1);

    		e[i].print();                                        //����ʽ��ӡ����Ļ��
    		
    		if (scan.nextLine().equals (e[i].value.str))  {      //�����û�����Ĵ𰸲��ж�����              
    			System.out.println("�ش���ȷ!\n");
    			count ++;
    		}
    		else  {
    			System.out.println("�ش����! ��ȷ����: " + e[i].value.str + "\n");
    		}
    		
    	}
    	
    	//�������
    	System.out.println("�������" + count + "����Ŀ���÷�:" + 100 * count / n + "!\n");
    	
    	scan.close();

}

}
