package util;

import java.awt.image.BufferedImage;
import java.lang.reflect.Array;
import java.util.ArrayList;

public class characterDivide {
	
	BufferedImage rawimg;
	ArrayList<BufferedImage> divided;
	ArrayList<Integer> zero;
	ArrayList<Integer> heightZero;
	
	public characterDivide(BufferedImage img)
	{
		rawimg=img;
	}
	
	public void scanWideZero()
	{
		zero=new ArrayList<Integer>();
		imgScan demo=new imgScan(rawimg);
		int rawdata[]=demo.widthScan();
		for(int i=45;i<165;i++)//有效字符区间
		{
			if(rawdata[i]==0)
				zero.add(i);
		}
	}
	
	public void scanHeightZero()
	{
		heightZero=new ArrayList<Integer>();
		imgScan demo=new imgScan(rawimg);
		int rawdata[]=demo.heightScan();
		for(int i=0;i<rawdata.length;i++)
		{
			if(rawdata[i]==0)
				heightZero.add(i);
		}
	}
	
	
	
	public ArrayList<Integer> getHeightZero() {
		return heightZero;
	}

	public void setHeightZero(ArrayList<Integer> heightZero) {
		this.heightZero = heightZero;
	}
	
	public void hand_cut_image(int cutLocation[])
	{
		divided=new ArrayList<BufferedImage>();
		for(int i=0;i<4;i++)
		{
			BufferedImage bfimg=new BufferedImage(cutLocation[i+1]-cutLocation[i]+1, 50, BufferedImage.TYPE_BYTE_BINARY);
			int dx=0;
			for(int x=cutLocation[i];x<=cutLocation[i+1];x++)
			{
				for(int y=0;y<rawimg.getHeight();y++)
				{
					bfimg.setRGB(dx, y, rawimg.getRGB(x, y));
				}
				dx++;
			}
			divided.add(bfimg);
		}
		
		
	}

	public void directDivide()//52,82,106,131,165
	{
		divided=new ArrayList<BufferedImage>();
		BufferedImage dx1=new BufferedImage(31, 50, BufferedImage.TYPE_BYTE_BINARY);
		int dx1r=0;
		for(int x=52;x<=82;x++)
		{
			for(int y=0;y<rawimg.getHeight();y++)
			{
				dx1.setRGB(dx1r, y, rawimg.getRGB(x, y));
			}
			dx1r++;
		}
		divided.add(dx1);
		
		BufferedImage dx2=new BufferedImage(25, 50, BufferedImage.TYPE_BYTE_BINARY);
		int dx2r=0;
		for(int x=82;x<=106;x++)
		{
			for(int y=0;y<rawimg.getHeight();y++)
			{
				dx2.setRGB(dx2r, y, rawimg.getRGB(x, y));
			}
			dx2r++;
		}
		divided.add(dx2);
		
		BufferedImage dx3=new BufferedImage(26, 50, BufferedImage.TYPE_BYTE_BINARY);
		int dx3r=0;
		for(int x=106;x<=131;x++)
		{
			for(int y=0;y<rawimg.getHeight();y++)
			{
				dx3.setRGB(dx3r, y, rawimg.getRGB(x, y));
			}
			dx3r++;
		}
		divided.add(dx3);
		
		BufferedImage dx4=new BufferedImage(35, 50, BufferedImage.TYPE_BYTE_BINARY);
		int dx4r=0;
		for(int x=131;x<=165;x++)
		{
			for(int y=0;y<rawimg.getHeight();y++)
			{
				dx4.setRGB(dx4r, y, rawimg.getRGB(x, y));
			}
			dx4r++;
		}
		divided.add(dx4);
			
	}
	
	public void direct_fixed_Divide()//28per 52-164
	{
		divided=new ArrayList<BufferedImage>();
		BufferedImage dx1=new BufferedImage(29, 50, BufferedImage.TYPE_BYTE_BINARY);
		int dx1r=0;
		for(int x=52;x<=80;x++)
		{
			for(int y=0;y<rawimg.getHeight();y++)
			{
				dx1.setRGB(dx1r, y, rawimg.getRGB(x, y));
			}
			dx1r++;
		}
		divided.add(dx1);
		
		BufferedImage dx2=new BufferedImage(29, 50, BufferedImage.TYPE_BYTE_BINARY);
		int dx2r=0;
		for(int x=80;x<=108;x++)
		{
			for(int y=0;y<rawimg.getHeight();y++)
			{
				dx2.setRGB(dx2r, y, rawimg.getRGB(x, y));
			}
			dx2r++;
		}
		divided.add(dx2);
		
		BufferedImage dx3=new BufferedImage(29, 50, BufferedImage.TYPE_BYTE_BINARY);
		int dx3r=0;
		for(int x=108;x<=136;x++)
		{
			for(int y=0;y<rawimg.getHeight();y++)
			{
				dx3.setRGB(dx3r, y, rawimg.getRGB(x, y));
			}
			dx3r++;
		}
		divided.add(dx3);
		
		BufferedImage dx4=new BufferedImage(29, 50, BufferedImage.TYPE_BYTE_BINARY);
		int dx4r=0;
		for(int x=136;x<=164;x++)
		{
			for(int y=0;y<rawimg.getHeight();y++)
			{
				dx4.setRGB(dx4r, y, rawimg.getRGB(x, y));
			}
			dx4r++;
		}
		divided.add(dx4);
			
	}
	
	public ArrayList<BufferedImage> getDivided()
	{
		return divided;
	}
	
	public void projctionDivede(int th)//投影分割
	{
		divided=new ArrayList<BufferedImage>();
		//获取原始数据
		ArrayList<Integer> location_e=new ArrayList<Integer>();
		ArrayList<Integer> location_s=new ArrayList<Integer>();
		//处理得切分数据
		int divide_s[]=new int[4];
		int divide_e[]=new int [4];
		int key[]={52,82,106,131,165}; //切分准轴
		imgScan demo=new imgScan(rawimg);
		int px[]=demo.widthScan();
		for(int x=45;x<160;x++)
		{
			if(px[x-1]==0&&px[x]!=0)
			{
				location_s.add(x-1);
			}
			
			if(px[x+1]==0&&px[x]!=0)
			{
				location_e.add(x+1);
			}
		}
		
		//处理切分轴
		//开始端
		boolean f_s_1=true;
		for(int i=52-th;i<=52+th;i++)
		{
			
			if(location_s.contains(i))
			{
				divide_s[0]=i;
				f_s_1=false;
				break;
			}
		}
		if(f_s_1)
			divide_s[0]=52;
//---------------------------------
		boolean f_s_2=true;
		for(int i=82-th;i<=82+th;i++)
		{
			
			if(location_s.contains(i))
			{
				divide_s[1]=i;
				f_s_2=false;
				break;
			}
		}
		if(f_s_2)
			divide_s[1]=82;
//--------------------------------
		boolean f_s_3=true;
		for(int i=106-th;i<=106+th;i++)
		{
			
			if(location_s.contains(i))
			{
				divide_s[2]=i;
				f_s_3=false;
				break;
			}
		}
		if(f_s_3)
			divide_s[2]=106;
//-----------------------------------
		boolean f_s_4=true;
		for(int i=131-th;i<=131+th;i++)
		{
			
			if(location_s.contains(i))
			{
				divide_s[3]=i;
				f_s_4=false;
				break;
			}
		}
		if(f_s_4)
			divide_s[3]=131;
		
		//结束端
		boolean f_e_1=true;
		for(int i=82-th;i<=82+th;i++)
		{
			
			if(location_e.contains(i))
			{
				divide_e[0]=i;
				f_e_1=false;
				break;
			}
		}
		if(f_e_1)
			divide_e[0]=82;
//--------------------------------------------------
		boolean f_e_2=true;
		for(int i=106-th;i<=106+th;i++)
		{
			
			if(location_e.contains(i))
			{
				divide_e[1]=i;
				f_e_2=false;
				break;
			}
		}
		if(f_e_2)
			divide_e[1]=106;
//-------------------------------------------------
		boolean f_e_3=true;
		for(int i=131-th;i<=131+th;i++)
		{
			
			if(location_e.contains(i))
			{
				divide_e[2]=i;
				f_e_3=false;
				break;
			}
		}
		if(f_e_3)
			divide_e[2]=131;
//------------------------------------------------
		boolean f_e_4=true;
		for(int i=165-th;i<=165+th;i++)
		{
			
			if(location_e.contains(i))
			{
				divide_e[3]=i;
				f_e_4=false;
				break;
			}
		}
		if(f_e_4)
			divide_e[3]=165;
		
//-----------------------------------------
		//切割图片生成
		for(int i=0;i<4;i++)
		{
			int rpx=0;
			BufferedImage dp=new BufferedImage(divide_e[i]-divide_s[i]+1, rawimg.getHeight(), BufferedImage.TYPE_BYTE_BINARY);
			for(int x=divide_s[i];x<=divide_e[i];x++)
			{
				for(int y=0;y<rawimg.getHeight();y++)
				{
					dp.setRGB(rpx, y, rawimg.getRGB(x, y));
				}
				rpx++;
			}
			divided.add(dp);
		}
		
		
		
		
		//for test
		/*
		System.out.println("location_e");
		for(int l:location_e)
		{
			System.out.print(l+" ");			
		}
		System.out.println("");
		System.out.println("location_s");
		for(int l:location_s)
		{
			System.out.print(l+" ");
			
		}
		System.out.println("");
		System.out.println("divide_s");
		for(int l:divide_s)
		{
			System.out.print(l+" ");
			
		}
		System.out.println("");
		System.out.println("divide_e");
		for(int l:divide_e)
		{
			System.out.print(l+" ");
			
		}
		System.out.println("");
		*/
		
	}
	
	public void projctionDivede(int th,int keys[])//投影分割
	{
		divided=new ArrayList<BufferedImage>();
		//获取原始数据
		ArrayList<Integer> location_e=new ArrayList<Integer>();
		ArrayList<Integer> location_s=new ArrayList<Integer>();
		//ArrayList<Integer>zeros=new ArrayList<Integer>();
		//处理得切分数据
		int divide_s[]=new int[4];
		int divide_e[]=new int [4];
		//int key[]={52,82,106,131,165}; //切分准轴
		imgScan demo=new imgScan(rawimg);
		int px[]=demo.widthScan();
		for(int x=45;x<160;x++)
		{
			//if(px[x]==0)
			//	zeros.add(x);
			if(px[x-1]==0&&px[x]!=0)
			{
				location_s.add(x-1);
			}
			
			if(px[x+1]==0&&px[x]!=0)
			{
				location_e.add(x+1);
			}
		}
		
		//处理切分轴
		//开始端
		boolean f_s_1=true;
		/*
		 * for(int i=0;i<=th;i++)
		 * {
		 *     if(location_s.contains(keys[0]-i)
		 *     {
		 *         	divide_s[0]=i;
				    f_s_1=false;
				    break;
				}
				
		 * }
		 */
		for(int i=keys[0]+th;i>=keys[0]-th;i--)
		{
			
			if(location_s.contains(i)&&(px[i+1]==0||px[i-1]==0))
			{
				divide_s[0]=i;
				f_s_1=false;
				break;
			}
		}
		if(f_s_1)
		{
			if(px[keys[0]]==0)
			{
				divide_s[0]=keys[0];
			}
			else
			{
				int t=1;
				int min=px[keys[0]];
				int x=keys[0];
				while(t<th)
				{
					if(px[keys[0]-t]==0)
					{
						divide_s[0]=keys[0]-t;
						break;
					}
					else
					{
						if((px[keys[0]-t]<min))
						{
							min=(px[keys[0]-t]);
							x=keys[0]-t;
						}

					}
					if(px[keys[0]+t]==0)
					{
						divide_s[0]=keys[0]+t;
						break;
					}
					else
					{
						if((px[keys[0]+t]<min))
						{
							min=(px[keys[0]+t]);
							x=keys[0]+t;
						}

					}
					t++;
					
				}
				if(t>=th)
				{
					if(min<4)
					{
						divide_s[0]=x;
					}
					else
					{
						divide_s[0]=keys[0];
					}
				}

					
			}
		}
		//if(f_s_1)   修正前代码
		//divide_s[0]=keys[0];
//---------------------------------
		boolean f_s_2=true;
		for(int i=keys[1]+th;i>=keys[1]-th;i--)
		{
			
			if(location_s.contains(i)&&(px[i+1]==0||px[i-1]==0))
			{
				divide_s[1]=i;
				f_s_2=false;
				break;
			}
		}
		if(f_s_2)
		{
			if(px[keys[1]]==0)
			{
				divide_s[1]=keys[1];
			}
			else
			{
				int t=1;
				int min=px[keys[1]];
				int x=keys[1];
				while(t<th)
				{
					if(px[keys[1]-t]==0)
					{
						divide_s[1]=keys[1]-t;
						break;
					}
					else
					{
						if((px[keys[1]-t]<min))
						{
							min=(px[keys[1]-t]);
							x=keys[1]-t;
						}

					}
					if(px[keys[1]+t]==0)
					{
						divide_s[1]=keys[1]+t;
						break;
					}
					else
					{
						if((px[keys[1]+t]<min))
						{
							min=(px[keys[1]+t]);
							x=keys[1]+t;
						}

					}
					t++;
					
				}
				if(t>=th)
				{
					if(min<4)
					{
						divide_s[1]=x;
					}
					else
					{
						divide_s[1]=keys[1];
					}
				}
			}
		}
//--------------------------------
		boolean f_s_3=true;
		for(int i=keys[2]+th;i>=keys[2]-th;i--)
		{
			
			if(location_s.contains(i)&&(px[i+1]==0||px[i-1]==0))
			{
				divide_s[2]=i;
				f_s_3=false;
				break;
			}
		}
		if(f_s_3)
		{
			if(px[keys[2]]==0)
			{
				divide_s[2]=keys[2];
			}
			else
			{
				int t=1;
				int min=px[keys[2]];
				int x=keys[2];
				while(t<th)
				{
					if(px[keys[2]-t]==0)
					{
						divide_s[2]=keys[2]-t;
						break;
					}
					else
					{
						if((px[keys[2]-t]<min))
						{
							min=(px[keys[2]-t]);
							x=keys[2]-t;
						}

					}
					if(px[keys[2]+t]==0)
					{
						divide_s[2]=keys[2]+t;
						break;
					}
					else
					{
						if((px[keys[2]+t]<min))
						{
							min=(px[keys[2]+t]);
							x=keys[2]+t;
						}

					}
					t++;
					
				}
				if(t>=th)
				{
					if(min<4)
					{
						divide_s[2]=x;
					}
					else
					{
						divide_s[2]=keys[2];
					}
				}
			}
		}
//-----------------------------------
		boolean f_s_4=true;
		for(int i=keys[3]+th;i>=keys[3]-th;i--)
		{
			
			if(location_s.contains(i)&&(px[i+1]==0||px[i-1]==0))
			{
				divide_s[3]=i;
				f_s_4=false;
				break;
			}
		}
		if(f_s_4)
		{
			if(px[keys[3]]==0)
			{
				divide_s[3]=keys[3];
			}
			else
			{
				int t=1;
				int min=px[keys[3]];
				int x=keys[3];
				while(t<th)
				{
					if(px[keys[3]-t]==0)
					{
						divide_s[3]=keys[3]-t;
						break;
					}
					else
					{
						if((px[keys[3]-t]<min))
						{
							min=(px[keys[3]-t]);
							x=keys[3]-t;
						}

					}
					if(px[keys[3]+t]==0)
					{
						divide_s[3]=keys[3]+t;
						break;
					}
					else
					{
						if((px[keys[3]+t]<min))
						{
							min=(px[keys[3]+t]);
							x=keys[3]+t;
						}

					}
					t++;
					
				}
				if(t>=th)
				{
					if(min<4)
					{
						divide_s[3]=x;
					}
					else
					{
						divide_s[3]=keys[3];
					}
				}
			}
		}
		
		//结束端
		boolean f_e_1=true;
		for(int i=keys[1]-th;i<=keys[1]+th;i++)
		{
			
			if(location_e.contains(i)&&(px[i+1]==0||px[i-1]==0))
			{
				divide_e[0]=i;
				f_e_1=false;
				break;
			}
		}
		if(f_e_1)
		{
			if(px[keys[1]]==0)
			{
				divide_e[0]=keys[1];
			}
			else
			{
				int t=1;
				int min=px[keys[1]];
				int x=keys[1];
				while(t<th)
				{
					if(px[keys[1]-t]==0)
					{
						divide_e[0]=keys[1]-t;
						break;
					}
					else
					{
						if((px[keys[1]-t]<min))
						{
							min=(px[keys[1]-t]);
							x=keys[1]-t;
						}

					}
					if(px[keys[1]+t]==0)
					{
						divide_e[0]=keys[1]+t;
						break;
					}
					else
					{
						if((px[keys[1]+t]<min))
						{
							min=(px[keys[1]+t]);
							x=keys[1]+t;
						}

					}
					t++;
					
				}
				if(t>=th)
				{
					if(min<4)
					{
						divide_e[0]=x;
					}
					else
					{
						divide_e[0]=keys[1];
					}
				}
			}
		}
//--------------------------------------------------
		boolean f_e_2=true;
		for(int i=keys[2]-th;i<=keys[2]+th;i++)
		{
			
			if(location_e.contains(i)&&(px[i+1]==0||px[i-1]==0))
			{
				divide_e[1]=i;
				f_e_2=false;
				break;
			}
		}
		if(f_e_2)
		{
			if(px[keys[2]]==0)
			{
				divide_e[1]=keys[2];
			}
			else
			{
				int t=1;
				int min=px[keys[2]];
				int x=keys[2];
				while(t<th)
				{
					if(px[keys[2]-t]==0)
					{
						divide_e[1]=keys[2]-t;
						break;
					}
					else
					{
						if((px[keys[2]-t]<min))
						{
							min=(px[keys[2]-t]);
							x=keys[2]-t;
						}

					}
					if(px[keys[2]+t]==0)
					{
						divide_e[1]=keys[2]+t;
						break;
					}
					else
					{
						if((px[keys[2]+t]<min))
						{
							min=(px[keys[2]+t]);
							x=keys[2]+t;
						}

					}
					t++;
					
				}
				if(t>=th)
				{
					if(min<4)
					{
						divide_e[1]=x;
					}
					else
					{
						divide_e[1]=keys[2];
					}
				}
			}
		}
//-------------------------------------------------
		boolean f_e_3=true;
		for(int i=keys[3]-th;i<=keys[3]+th;i++)
		{
			
			if(location_e.contains(i)&&(px[i+1]==0||px[i-1]==0))
			{
				divide_e[2]=i;
				f_e_3=false;
				break;
			}
		}
		if(f_e_3)
		{
			if(px[keys[3]]==0)
			{
				divide_e[2]=keys[3];
			}
			else
			{
				int t=1;
				int min=px[keys[3]];
				int x=keys[3];
				while(t<th)
				{
					if(px[keys[3]-t]==0)
					{
						divide_e[2]=keys[3]-t;
						break;
					}
					else
					{
						if((px[keys[3]-t]<min))
						{
							min=(px[keys[3]-t]);
							x=keys[3]-t;
						}

					}
					if(px[keys[3]+t]==0)
					{
						divide_e[2]=keys[3]+t;
						break;
					}
					else
					{
						if((px[keys[3]+t]<min))
						{
							min=(px[keys[3]+t]);
							x=keys[3]+t;
						}

					}
					t++;
					
				}
				if(t>=th)
				{
					if(min<4)
					{
						divide_e[2]=x;
					}
					else
					{
						divide_e[2]=keys[3];
					}
				}
			}
		}
//------------------------------------------------
		boolean f_e_4=true;
		for(int i=keys[4]-th;i<=keys[4]+th;i++)
		{
			
			if(location_e.contains(i)&&(px[i+1]==0||px[i-1]==0))
			{
				divide_e[3]=i;
				f_e_4=false;
				break;
			}
		}
		if(f_e_4)
		{
			if(px[keys[4]]==0)
			{
				divide_e[3]=keys[4];
			}
			else
			{
				int t=1;
				int min=px[keys[4]];
				int x=keys[4];
				while(t<th)
				{
					if(px[keys[4]-t]==0)
					{
						divide_e[3]=keys[4]-t;
						break;
					}
					else
					{
						if((px[keys[4]-t]<min))
						{
							min=(px[keys[4]-t]);
							x=keys[4]-t;
						}

					}
					if(px[keys[4]+t]==0)
					{
						divide_e[3]=keys[4]+t;
						break;
					}
					else
					{
						if((px[keys[4]+t]<min))
						{
							min=(px[keys[4]+t]);
							x=keys[4]+t;
						}

					}
					t++;
					
				}
				if(t>=th)
				{
					if(min<4)
					{
						divide_e[3]=x;
					}
					else
					{
						divide_e[3]=keys[4];
					}
				}
			}
		}
		
//-----------------------------------------
		//切割图片生成
		for(int i=0;i<4;i++)
		{
			int rpx=0;
			BufferedImage dp=new BufferedImage(divide_e[i]-divide_s[i]+1, rawimg.getHeight(), BufferedImage.TYPE_BYTE_BINARY);
			for(int x=divide_s[i];x<=divide_e[i];x++)
			{
				for(int y=0;y<rawimg.getHeight();y++)
				{
					dp.setRGB(rpx, y, rawimg.getRGB(x, y));
				}
				rpx++;
			}
			divided.add(dp);
		}
	}

	//for test
	public void printZero()
	{
		for(int l:zero)
		{
			System.out.println(l);
		}
	}
	
	public ArrayList<Integer> getZero()
	{
		return zero;
	}
	
	
	public BufferedImage heightDivide(int sth,int eth)
	{
		int keys[]={9,46};
		int location_start=keys[0];
		int location_end=keys[1];
		imgScan demo=new imgScan(rawimg);
		int rawdata[]=demo.heightScan();
		
		for(int y=5;y<14;y++)
		{
			if(rawdata[y-1]==0&&rawdata[y]!=0)
				location_start=y-1;
		}
		
		for(int y=43;y<48;y++)
		{
			if(rawdata[y-1]!=0&&rawdata[y]==0)
				location_end=y;
		}
		
		boolean sf=true;
		for(int i=keys[0]-sth;i<=keys[0]+sth;i++)
		{
			if(i==location_start)
			{
				sf=false;
				break;
			}
		}
		
		boolean ef=true;
		for(int i=keys[1]-eth;i<=keys[1]+eth;i++)
		{
			if(i==location_end)
			{
				ef=false;
				break;
			}
		}
		
		if(sf)
			location_start=keys[0];
		if(ef)
			location_end=keys[1];
		
		BufferedImage result=new BufferedImage(rawimg.getWidth(), location_end-location_start+1, BufferedImage.TYPE_BYTE_BINARY);
		
		int rpy=0;
		for(int y=location_start;y<=location_end;y++)
		{
			for(int x=0;x<rawimg.getWidth();x++)
			{
				result.setRGB(x, rpy, rawimg.getRGB(x, y));
			}
			rpy++;
		}

		
		return result;
		
	}
	
	public BufferedImage heightDivide(int sth,int eth,int keys[])
	{
		//int keys[]={9,46};
		int location_start=keys[0];
		int location_end=keys[1];
		imgScan demo=new imgScan(rawimg);
		int rawdata[]=demo.heightScan();
		
		for(int y=5;y<14;y++)
		{
			if(rawdata[y-1]==0&&rawdata[y]!=0)
				location_start=y-1;
		}
		
		for(int y=43;y<48;y++)
		{
			if(rawdata[y-1]!=0&&rawdata[y]==0)
				location_end=y;
		}
		
		boolean sf=true;
		for(int i=keys[0]-sth;i<=keys[0]+sth;i++)
		{
			if(i==location_start)
			{
				sf=false;
				break;
			}
		}
		
		boolean ef=true;
		for(int i=keys[1]-eth;i<=keys[1]+eth;i++)
		{
			if(i==location_end)
			{
				ef=false;
				break;
			}
		}
		
		if(sf)
			location_start=keys[0];
		if(ef)
			location_end=keys[1];
		
		BufferedImage result=new BufferedImage(rawimg.getWidth(), location_end-location_start+1, BufferedImage.TYPE_BYTE_BINARY);
		
		int rpy=0;
		for(int y=location_start;y<=location_end;y++)
		{
			for(int x=0;x<rawimg.getWidth();x++)
			{
				result.setRGB(x, rpy, rawimg.getRGB(x, y));
			}
			rpy++;
		}

		
		return result;
		
	}
	
	

}
