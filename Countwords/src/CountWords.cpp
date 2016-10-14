# include <stdio.h>
# include <stdlib.h>
# include <string>
# include <string.h>


using namespace std;


/*�ṹ�壬���������Լ������ֵĴ���*/
struct word_count {

	char word[30];           //����
	int count;               //���ֵĴ���

};

/*�ж��ַ��Ƿ�����ĸ*/
bool isAlphabetic (char c)  {

	return ((c >= 'a' && c <= 'z')
	        || (c >= 'A' && c <= 'Z'));

}

/*�ж��ַ��Ƿ�������*/
bool isNumerical (char c)  {

	return (c >= '0' && c <= '9');

}

/*�ж��ַ��Ƿ��Ƿָ���*/
bool isSeparator (char c)  {

	return (! isAlphabetic(c) && ! isNumerical(c));

}


/*�ж��ַ����Ƿ��ǵ���*/
bool isWord (char s[])  {

	for (int i = 0; i < 4; i ++) {        //�ַ���ǰ��λ��������ĸ
		if (!isAlphabetic (s[i]))  {
			return false;
		}
	}

	return true;

}

/*�ж����������Ƿ���ͬ�����Ӵ�Сд��*/
bool isSame (char a[], char b[]) {

	char s1[20], s2[20];
	strcpy (s1, a);
	strcpy (s2, b);
	strupr (s1);
	strupr (s2);
	return (!strcmp (s1, s2));

}

/*ͳ�Ƶ��ʵĳ��ִ���,������������ļ���*/
void count (FILE * in, FILE * out)  {

	char s[30];                            //��ǰ���ڴ�����ַ���
	char c = ' ';                          //��ǰ�ַ�
	word_count w[20000];                   //word_count���飬��ų��ֵĵ��ʼ����ִ���
	int countUpNow = 0, i, j, k;           //countUpNow��¼Ŀǰ���еĵ�����
	bool haveRecord;                       //��¼һ������֮ǰ�Ƿ��г��ֹ�


	//ͳ�Ƶ��ʵĳ��ִ���
	while (! feof(in))  {

		memset (s, 0, sizeof(s));
		haveRecord = false;
		k = 0;
		c = ' ';

		while (isSeparator (c) && !feof(in))  {           //�����ָ���
			c = fgetc (in);
		}

		while (! isSeparator (c) && !feof(in))  {         //����һ���ַ���
			s[k++] = c;
			c = fgetc (in);
		}

		if (isWord (s))  {                                //�����һ������

			for (i = 0; i < countUpNow; i ++)  {          //����֮ǰ�õ����Ƿ���ֹ�

				if (isSame (s, w[i].word))  {             //�����ֹ�

					w[i].count ++;                        //Ƶ��+1
					if (s < w[i].word) {                  //����õ��ʵ��ֵ����С
						strcpy (w[i].word, s);            //�滻֮ǰ�ĵ���
					}
					haveRecord = true;
					break;
				}

			}

			if (! haveRecord)  {                          //��û�г��ֹ�

				w[countUpNow].count = 1;
				strcpy (w[countUpNow].word, s);
				countUpNow ++;

			}

		}

	}


	//���մ������ֵ����������
	word_count temp;

	for (i = 0; i < countUpNow; i ++) {

		for (j = 0; j < i; j ++)  {
			if (w[j].count < w[i].count)  {
				temp = w[j];
				w[j] = w[i];
				w[i] = temp;
			}
			else if (w[j].count == w[i].count && w[j].word > w[i].word) {
				temp = w[j];
				w[j] = w[i];
				w[i] = temp;
			}
		}

	}


	//��������Ŀ���ļ���
	for (i = 0; i < countUpNow; i ++) {

		itoa (w[i].count, s, 20);
		fputs ("<", out);
		fputs (w[i].word, out);
		fputs (">: ", out);
		fputs (s, out);
		fputc ('\n', out);

	}

}



int main (int argc, char * argv[])  {

    //����Ҫ���д�Ƶͳ�Ƶ��ļ�
    FILE * in = fopen (argv[0], "a+");
    if (! in)  {
	    printf ("Failed to open the input file!\n");
	    exit (1);
	}

    //������ļ�
    FILE * out = fopen ("result.txt", "a+");
    if (! out)  {
	    printf ("Failed to open the output file!\n");
	    exit (1);
	}

	//����count�������д�Ƶͳ��
	count (in, out);

    return 0;

}








