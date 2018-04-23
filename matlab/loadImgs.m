grayimglist={};
for i =1 : 500
    str=sprintf('C:/文件/毕业设计/code/imgVerify/img/source/sourceimg%d.jpg',i);
    img=imread(str);
    grayimg=rgb2gray(img);
    grayimglist{i}=grayimg;
end;
