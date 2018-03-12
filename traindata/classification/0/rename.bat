set a=0

setlocal EnableDelayedExpansion

FOR %%n IN (*.txt) DO (

set /A a+=1

ren "%%n" "!a!.txt"

)