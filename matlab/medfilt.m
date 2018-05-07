imguint8=imread('C:\文件\毕业设计\test\9R\RSP474.jpg');
img=im2double(imguint8);
L=img(1:16,16:184);
C=img(19:50,52:164);
%H=5 W=3
RES=[];
RESS=[];
ratel=[];
idx=1;
idy=1;
for y=3:14
    for x=2:168
        T=[];
        idxx=1;
        for i=-2:1:2
            for j=-1:1:1
                T(idxx)=L(y+i,x+j);
                idxx=idxx+1;
            end
        end
        TS=sort(T);
        for i=1:15
            RES(idx,i)=TS(i);
        end
        if L(y,x)==0
            for i=1:15
                RESS(idy,i)=TS(i);
            end
            idy=idy+1;
        end
        idx=idx+1;
    end
end

for i=1:15
    c=0;
    for j=1:idy-1
        if RESS(j,i)==1
            c=c+1;
        end
    end
    ratel(i)=c/idy;
end

figure;
subplot(1,3,1);
imagesc(RES,[0,1]);
subplot(1,3,2);
imagesc(RESS,[0,1]);
subplot(1,3,3)
plot(ratel);

%-----------------------
REC=[];
RECC=[];
idx=1;
idy=1;
ratec=[];
for y=3:30
    for x=2:112
        T=[];
        idxx=1;
        for i=-2:1:2
            for j=-1:1:1
                T(idxx)=C(y+i,x+j);
                idxx=idxx+1;
            end
        end
        TS=sort(T);
        for i=1:15
            REC(idx,i)=TS(i);
        end
        if C(y,x)==0
            for i=1:15
                RECC(idy,i)=TS(i);
            end
            idy=idy+1;
        end
        idx=idx+1;
    end
        idx=idx+1;
    
end

for i=1:15
    c=0;
    for j=1:idy-1
        if RECC(j,i)==1
            c=c+1;
        end
    end
    ratec(i)=c/idy;
end

figure;
subplot(1,3,1);
imagesc(REC,[0,1]);
subplot(1,3,2);
imagesc(RECC,[0,1]);
subplot(1,3,3);
plot(ratec);




                
            
        
        