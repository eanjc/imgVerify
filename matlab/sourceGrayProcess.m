%样本集灰度的叠加
N=500;
sourceOverlay=zeros(50,200);
for i=1:N
    doubleimg=im2double(grayimglist{i});
    sourceOverlay=sourceOverlay+doubleimg;
end
sourceOverlay=sourceOverlay/N;
u8SO=im2uint8(sourceOverlay);
figure('Name','像素叠加平均 double');
title('像素叠加平均 double');
imshow(sourceOverlay);
figure('Name','像素叠加平均 uint8');
title('像素叠加平均 uint8');
imshow(u8SO);

%样本集背景位置灰度值采样
%采集位置 line4,5,6
%结果为平均值


%grayimglist{2}(line,2)
px=1:1:200;
gl=int32(zeros(4,200));

for i=1:N
    currget=grayimglist{i}; 
    u=int32(currget);
    for line=4:7
        if line<7
            for x=1:200
                gl(line-3,x)=gl(line-3,x)+u(line,x);
            end
        else
            for x=1:200
                gl(line-3,x)=gl(line-3,x)+u(49,x);
            end    
        end

    end
                        
end
gl=floor(gl/N);
figure('Name','第四五六行灰度平均值');
plot(px,gl(1,:),'r',px,gl(2,:),'g',px,gl(3,:),'b',px,gl(4,:),'k');
legend('line4','line5','line6','line49');

%OSTU算法获取的阈值
ostugrayth=zeros(1,500);
intostuth=int32(zeros(1,500));
for i=1:N
    ostugrayth(i)=graythresh(grayimglist{i});
    intostuth(i)=floor(ostugrayth(i)*255);
end

figure('Name','OSTU算法阈值');
plot(intostuth);

%最小灰度值中位数法
minmedgrayth=zeros(1,500);
intmmth=int32(zeros(1,500));
lgpth=zeros(1,500);
intlgpth=int32(zeros(1,500));
for i=1:N
    %minmedgrayth(i)=minMedGrayTh(grayimglist{i},15,185);
    %intmmth(i)=floor(minmedgrayth(i));
    lgpth(i)=lowgpTh(grayimglist{i});
    intlgpth(i)=floor(lgpth(i));
end
medth=median(intmmth)
avgth=mean(intmmth)
%figure('Name','最小中位数算法阈值');
%plot(intmmth);
figure('Name','二值化阈值');
xx=1:1:500;
plot(xx,intostuth,'r',xx,intlgpth,'b');
legend('OSTU','MedianLowGrayPoint');

%采样进行灰度二值化分析
cn=180; %样本编号
ssi=grayimglist{cn};
hssi=imhist(ssi);
figure;
subplot(1,2,1);
title('原始样本');
imshow(ssi);
subplot(1,2,2);
title('灰度直方图');
imhist(ssi);
gth=20:20:180;
glevel=gth/255;
bwssi={};
a=1;
for g = glevel
    bwssi{a}=im2bw(ssi,g);
    a=a+1;
end
figure;
for b=1:8
    subplot(2,4,b);
    imshow(bwssi{b});
    str=sprintf('%s%d','T = ',b*20);
    title(str);
end

