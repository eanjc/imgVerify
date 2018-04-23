gimg=rgb2gray(sourceimg9);
gth=20:20:180;
glevel=gth/255;
bwssi={};
a=1;
for g = glevel
    bwssi{a}=im2bw(gimg,g);
    a=a+1;
end
figure;
for b=1:8
    subplot(2,4,b);
    imshow(bwssi{b});
    str=sprintf('%s%d','T = ',b*20);
    title(str);
end