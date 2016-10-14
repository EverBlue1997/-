# include <stdio.h>
# include <stdlib.h>
# include <string>
# include <string.h>


using namespace std;


/*结构体，包括单词以及它出现的次数*/
struct word_count {

	char word[30];           //单词
	int count;               //出现的次数

};

/*判断字符是否是字母*/
bool isAlphabetic (char c)  {

	return ((c >= 'a' && c <= 'z')
	        || (c >= 'A' && c <= 'Z'));

}

/*判断字符是否是数字*/
bool isNumerical (char c)  {

	return (c >= '0' && c <= '9');

}

/*判断字符是否是分隔符*/
bool isSeparator (char c)  {

	return (! isAlphabetic(c) && ! isNumerical(c));

}


/*判断字符串是否是单词*/
bool isWord (char s[])  {

	for (int i = 0; i < 4; i ++) {        //字符串前四位必须是字母
		if (!isAlphabetic (s[i]))  {
			return false;
		}
	}

	return true;

}

/*判断两个单词是否相同（无视大小写）*/
bool isSame (char a[], char b[]) {

	char s1[20], s2[20];
	strcpy (s1, a);
	strcpy (s2, b);
	strupr (s1);
	strupr (s2);
	return (!strcmp (s1, s2));

}

/*统计单词的出现次数,并排序，输出到文件中*/
void count (FILE * in, FILE * out)  {

	char s[30];                            //当前正在处理的字符串
	char c = ' ';                          //当前字符
	word_count w[20000];                   //word_count数组，存放出现的单词及出现次数
	int countUpNow = 0, i, j, k;           //countUpNow记录目前已有的单词数
	bool haveRecord;                       //记录一个单词之前是否有出现过


	//统计单词的出现次数
	while (! feof(in))  {

		memset (s, 0, sizeof(s));
		haveRecord = false;
		k = 0;
		c = ' ';

		while (isSeparator (c) && !feof(in))  {           //跳过分隔符
			c = fgetc (in);
		}

		while (! isSeparator (c) && !feof(in))  {         //读入一个字符串
			s[k++] = c;
			c = fgetc (in);
		}

		if (isWord (s))  {                                //如果是一个单词

			for (i = 0; i < countUpNow; i ++)  {          //查找之前该单词是否出现过

				if (isSame (s, w[i].word))  {             //若出现过

					w[i].count ++;                        //频数+1
					if (s < w[i].word) {                  //如果该单词的字典序较小
						strcpy (w[i].word, s);            //替换之前的单词
					}
					haveRecord = true;
					break;
				}

			}

			if (! haveRecord)  {                          //若没有出现过

				w[countUpNow].count = 1;
				strcpy (w[countUpNow].word, s);
				countUpNow ++;

			}

		}

	}


	//按照次数和字典序进行排序
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


	//结果输出到目标文件中
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

    //打开需要进行词频统计的文件
    FILE * in = fopen (argv[0], "a+");
    if (! in)  {
	    printf ("Failed to open the input file!\n");
	    exit (1);
	}

    //打开输出文件
    FILE * out = fopen ("result.txt", "a+");
    if (! out)  {
	    printf ("Failed to open the output file!\n");
	    exit (1);
	}

	//调用count函数进行词频统计
	count (in, out);

    return 0;

}








