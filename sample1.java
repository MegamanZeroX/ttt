package work1;
import javax.swing.*;
import java.math.*;
import java.io.*;
import javax.imageio.*;

public class sample1 {
	static char[][] mp = new char[3][3];
	static int[] x = {0,0,1,1,1};
	static int[] y = {0,1,0,1,-1};
	static void init()//�������ݳ�ʼ��
	{
		for(int i=0;i<3;i++)
			for(int j=0;j<3;j++)
				mp[i][j] = (char)(i*3+j+1+(int)('0'));
	}
	static String show()//����չʾ
	{
		String rowLine = "-----------------";
		String res = rowLine;
		for(int i=1;i<=6;i++)
		{
			if(i%2 == 0)
				res = res + "\n" + rowLine;
			else{
				res = res + "\n" +"|" ;
				for(int j=0;j<3;j++)
					res = res + "  " + mp[i/2][j] + "  " + "|";
			}
		}
		
		return res;
	}
	static boolean dfs(int i,int j,int cnt,int dir,char type)//����������������ͬ�����ӵĺ���
	{
		if(cnt == 3) return true;
		else {
			int nextx = i+x[dir];	int nexty = j+y[dir];
			if(nextx>2||nextx<0||nexty>2||nexty<0||mp[nextx][nexty]!=type)
				return false;
			return dfs(nextx,nexty,cnt+1,dir,type);
		}
	}
	static int check()	//ÿ��һ��������֤������
	{
		int res = 0;
		for(int i=0;i<3;i++)
		{
			for(int j=0;j<3;j++)
			{
				if(mp[i][j] == '*'){//��ҷ���֤���ҳ�ÿһ����ҵ����ӽ����ĸ�������֤
					boolean flag = dfs(i,j,1,1,mp[i][j])||dfs(i,j,1,2,mp[i][j])||dfs(i,j,1,3,mp[i][j])||dfs(i,j,1,4,mp[i][j]);
					if(flag) return 1;
				}
				else if(mp[i][j] == 'O'){//���Է���֤���ҳ�ÿһ�����Ե����ӽ����ĸ�������֤
					boolean flag = dfs(i,j,1,1,mp[i][j])||dfs(i,j,1,2,mp[i][j])||dfs(i,j,1,3,mp[i][j])||dfs(i,j,1,4,mp[i][j]);
					if(flag) return 2;
				}
			}
		}
		return res;
	}
	public static void main(String[] args)
	{
		init();

		int result = 0,cnt = 0;
		while(result == 0)
		{
			String chess = show();
			chess = chess +"\n" +  "ѡ��һ�����ֱ������";
			String player = JOptionPane.showInputDialog(chess);
			System.out.println(player.charAt(0));
			int c = (int)(player.charAt(0)-'0') - 1;
			if(c==-1){break;}
			if(c<0||c>8||mp[c/3][c%3]>'9'||mp[c/3][c%3]<'1'){//�ж��Ƿ���Է�����
				continue;
			}
			mp[c/3][c%3] = '*';	cnt++;
			result = check();
			if (result==1){
				chess = show();
				chess = chess + "\n"+"��Ӯ��\n";
				JOptionPane.showMessageDialog(null,chess);
				break;
			}
			if(cnt==9){//�������һ����δӮ�������޿յأ�ƽ��
				chess = show();
				chess = chess +"\n"+ "ƽ��\n";
				JOptionPane.showMessageDialog(null,chess);
				break;
			}
			c =((int)(Math.random()*8));
			System.out.printf("rand  %d\n", c);
			while(mp[c/3][c%3] == 'O' || mp[c/3][c%3] == '*')
				c = ((int)(Math.random()*8));
			mp[c/3][c%3] = 'O';	cnt++;
			result = check();
			if(result==2){
				chess = show();
				chess = chess +"\n"+ "������\n";
				JOptionPane.showMessageDialog(null,chess);
				break;
			}
			
		}
		
	}
}


