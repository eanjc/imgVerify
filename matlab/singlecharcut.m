pathroot='C:\文件\毕业设计\test\PD3-TH10-S45\'; %定义一个相对路径
file_list=dir(fullfile(pathroot));  %找到数据库文件夹下的所有文件
yb=zeros(1,50);
s=size(file_list);
ss=s(1);
for f=1:ss
    if file_list(f).isdir==0
        str=strcat(pathroot,file_list(f).name);
        img=imread(str);
        sss=size(img);
        q=zeros(1,50);
        for y=1:50
            for x=1:sss(2)
                if img(y,x)==0
                    q(y)=q(y)+1;
                end
            end
            if q(y)==0
                yb(y)=yb(y)+1;
            end
        end
    end
end

figure;
plot(yb);
hold on;
plot(2000*0.98);
                