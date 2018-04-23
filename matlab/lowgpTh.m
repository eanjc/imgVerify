function grayth = lowgpTh(img)
%UNTITLED3 此处显示有关此函数的摘要
%   此处显示详细说明
ghis=int32(zeros(1,256));
for y=1:50
    for x=1:200
        ghis(img(y,x)+1)=ghis(img(y,x)+1)+1;
    end
end
list=[];
idx=1;
for i=1:256
    if ghis(i)<10
        list(idx)=i;
        idx=idx+1;
    end

end
grayth=median(list)+1;

