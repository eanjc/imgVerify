%from RSP 9R
%img_index 47 148 234
simg={};
simg{1}=imread('C:\文件\毕业设计\test\9R\RSP47.jpg');
simg{2}=imread('C:\文件\毕业设计\test\9R\RSP148.jpg');
simg{3}=imread('C:\文件\毕业设计\test\9R\RSP234.jpg');

%采样第一张
line_img_1=simg{1}(1:10,12:185);
ca_img_1=simg{1}(11:50,56:165);

DL=[];
DC=[];

idlx=1;
idcx=1;
for y=1:174
    for x=1:10
        if line_img_1(x,y)==0
            k=0;
            while (x+k<=10 & line_img_1(x+k,y)==0)
                k=k+1;
            end
            DL(idlx)=k;
            idlx=idlx+1;
        end
    end
end

for y=1:110
    for x=1:40
        if ca_img_1(x,y)==0
            k=0;
            while (x+k<=40 & ca_img_1(x+k,y)==0)
                k=k+1;
            end
            DC(idcx)=k;
            idcx=idcx+1;
        end
    end
end

figure;
bar(DL);
figure;
bar(DC);

DLMIN=min(DL);
DLMAX=max(DL);
DLL=[];
idx=1;
for p=DLMIN:DLMAX
    DLL(idx)=sum(DL>=p)/idlx;
    idx=idx+1;
end
figure;
plot(DLL);

DCMIN=min(DC);
DCMAX=max(DC);
DCC=[];
idx=1;
for p=DCMIN:DCMAX
    DCC(idx)=sum(DC>=p)/idcx;
    idx=idx+1;
end
figure;
plot(DCC);            
            
