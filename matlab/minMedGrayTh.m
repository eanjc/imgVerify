function grayth= minMedGrayTh(img,s,e)
%minMedGrayTh 此处显示有关此函数的摘要
%   此处显示详细说明
ghis=int32(zeros(1,256));
for y=1:50
    for x=1:200
        ghis(img(y,x)+1)=ghis(img(y,x)+1)+1;
    end
end
gg=int32(zeros(1,e-s+1));
for k=s:e
    gg(k-s+1)=ghis(k);
end
minvalue=min(gg);
indexofmin=find(gg==minvalue);
grayth=median(indexofmin)-1+s;

end

