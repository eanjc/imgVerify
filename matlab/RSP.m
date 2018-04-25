%img 47 192 241 
simg={};
aft_rsp={};
simg{1}=grayimglist{47};
simg{2}=grayimglist{192};
simg{3}=grayimglist{241};
aft_rsp{1}=imread('C:\文件\毕业设计\test\9R\RSP47.jpg');
aft_rsp{2}=imread('C:\文件\毕业设计\test\9R\RSP192.jpg');
aft_rsp{3}=imread('C:\文件\毕业设计\test\9R\RSP241.jpg');
gl=double(80/255);
img_bw={};
db_aft_rsp={};
for i=1:3
    img_bw{i}=im2bw(simg{i},gl);
    db_aft_rsp{i}=im2double(aft_rsp{i});
end
img_min={}; %图像相减
figure;
for i=1:3
    str=sprintf('C:/文件/毕业设计/matlab/RSP_P%d.bmp',i);
    img_min{i}=img_bw{i}-db_aft_rsp{i};
    subplot(2,3,i);
    %imwrite(img_min{i},str);
    imshow(img_min{i});
    subplot(2,3,i+3);
    imshow(db_aft_rsp{i});
end

