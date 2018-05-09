%-----------------------------------------
no=125;
uint8si=aftrnimglist{no};
dbsi=im2double(uint8si);
P=[];
for x=1:200
    c=0;
    for y=1:50
        if dbsi(y,x)==0
            c=c+1;
        end
    end
    P(x)=c;
end

figure;
%subplot(1,2,1);
imshow(dbsi);
axis on;
figure;
%subplot(1,2,2);
plot(P);
%-----------------------------------------
tb=zeros(1,200);
for i=1:500
    ig=aftrnimglist{i};
    %pp=[];
    for x=1:200
        c=0;
        for y=1:50
            if ig(y,x)==0;
                c=c+1;
            end
        end
        %pp(x)=c;
        if c==0;
            tb(x)=tb(x)+1;
        end
    end
end
figure;
plot(tb);

[yp,idxp]=findpeaks(tb);
    