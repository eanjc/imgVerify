%474
imguint8=imread('C:\�ļ�\��ҵ���\test\9R\RSP474.jpg');
img=im2double(imguint8);
L=img(1:16,16:184);
C=img(19:50,52:164);

%line
rs=2;%����
lc=[];
cc=[];
i=1;
for x=1:16
    for y=1:169
        if L(x,y)==0
            r=0;
            for s=1:rs
                if x+s<=16
                    if L(x+s,y)==1
                        r=r+1;
                    end
                end
                if x-s>=1
                    if L(x-s,y)==1
                        r=r+1;
                    end
                end
                if y+s<=169
                    if L(x,y+s)==1
                        r=r+1;
                    end
                end
                if y-s>=1
                    if L(x,y-s)==1
                        r=r+1;
                    end
                end
                if x+s<=16 && y+s<=169
                   if L(x+s,y+s)==1
                       r=r+1;
                   end
                end
                if x+s<=16 && y-s>=1
                   if L(x+s,y-s)==1
                       r=r+1;
                   end
                end
                if x-s>=1 && y+s<=169
                   if L(x-s,y+s)==1
                       r=r+1;
                   end
                end
                if x-s>=1 && y-s>=1
                   if L(x-s,y-s)==1
                       r=r+1;
                   end
                end
            end
            lc(i)=r;
            i=i+1;
        end
    end
end
%figure;
%bar(lc);
lcc=[];
lcmin=min(lc);
lcmax=max(lc);
iddx=1;
for id=1:lcmin
    lcc(id)=1;
end
for pp=lcmin:lcmax
    lcc(pp)=sum(lc>=pp)/i;
end
figure;
plot(lcc);

%charcater
i=1;
for x=1:32
    for y=1:113
        if C(x,y)==0
            r=0;
            for s=1:rs
                if x+s<=32
                    if C(x+s,y)==1
                        r=r+1;
                    end
                end
                if x-s>=1
                    if C(x-s,y)==1
                        r=r+1;
                    end
                end
                if y+s<=113
                    if C(x,y+s)==1
                        r=r+1;
                    end
                end
                if y-s>=1
                    if C(x,y-s)==1
                        r=r+1;
                    end
                end
                if x+s<=32 && y+s<=113
                   if C(x+s,y+s)==1
                       r=r+1;
                   end
                end
                if x+s<=32 && y-s>=1
                   if C(x+s,y-s)==1
                       r=r+1;
                   end
                end
                if x-s>=1 && y+s<=169
                   if C(x-s,y+s)==1
                       r=r+1;
                   end
                end
                if x-s>=1 && y-s>=1
                   if C(x-s,y-s)==1
                       r=r+1;
                   end
                end
            end
            cc(i)=r;
            i=i+1;
        end
    end
end
%figure;
%bar(cc);

ccc=[];
ccmin=min(cc);
ccmax=max(cc);
iddx=1;
for id=1:ccmin
    ccc(id)=1;
end
for pp=ccmin:ccmax
    ccc(pp+1)=sum(cc>=pp)/i;
end
cccx=0:1:ccmax;
figure;
plot(cccx,ccc);
%plot(ccc);
                
ccc=1209;
lll=339;
raterl=[];
raterc=[];
i=1;
tt=20:-1:10;
for t=20:-1:10
    rl=sum((lc>t));
    rc=sum((cc>t));
    raterl(i)=rl/lll;
    raterc(i)=rc/ccc;
    i=i+1;
end
figure;
plot(tt,raterl);
figure;
plot(tt,raterc);
    

                        