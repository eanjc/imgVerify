set a=0

setlocal EnableDelayedExpansion

FOR %%n IN (*.jpg) DO (

set /A a+=1

ren "%%n" "!a!.jpg"

)