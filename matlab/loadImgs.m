grayimglist={};
for i =1 : 500
    str=sprintf('C:/�ļ�/��ҵ���/code/imgVerify/img/source/sourceimg%d.jpg',i);
    img=imread(str);
    grayimg=rgb2gray(img);
    grayimglist{i}=grayimg;
end;
