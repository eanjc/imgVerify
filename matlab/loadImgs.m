grayimglist={};
aftrnimglist={};
for i =1 : 500
    str=sprintf('C:/文件/毕业设计/code/imgVerify/img/source/sourceimg%d.jpg',i);
    str2=sprintf('C:/文件/毕业设计/test/18MF-9/MF%d.jpg',i);
    img=imread(str);
    img2=imread(str2);
    grayimg=rgb2gray(img);
    %bwimg=rgb2gray(img2);
    grayimglist{i}=grayimg;
    aftrnimglist{i}=img2;
end;
