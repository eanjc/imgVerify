%from RSP 9R
%img_index 47 148 234
simg={};
simg{1}=imread('C:\�ļ�\��ҵ���\test\9R\RSP47.jpg');
simg{2}=imread('C:\�ļ�\��ҵ���\test\9R\RSP148.jpg');
simg{3}=imread('C:\�ļ�\��ҵ���\test\9R\RSP234.jpg');

%������һ��
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


            
            
