import java.util.Scanner;

public class Week1 {
	
    public static void main (String args[]) {
		
    	Scanner scan = new Scanner(System.in);
       	question q = new question();                    //����һ����ʽ��ʵ��
       	
    	int r = Integer.parseInt(args[0]);              //�������ķ�Χ��������������
    	int n = Integer.parseInt(args[1]);              //��ʽ��������������������
    	int l = Integer.parseInt(args[2]);              //�������ĸ�����������������
    	int count = 0;                                  //�û���Ե�����
    	
    	for (int i = 0; i < n; i ++ ) {
    		
    		q.initialize(r,l);                          //���������ʽ
    		q.print(l);                                 //����ʽ��ӡ����Ļ��
    		
    		if ( scan.nextLine().equals (q.res) )  {      //�����û�����Ĵ𰸲��ж�����              
    			System.out.println("�ش���ȷ!\n");
    			count ++;
    		}
    		else  {
    			System.out.println("�ش����! ��ȷ����: " + q.res + "\n");
    		}
    		
    	}
    	
    	//�������
    	System.out.println("�������" + count + "����Ŀ���÷�:" + 100 * count / n + "!\n");
    	
    	scan.close();

	}

}
