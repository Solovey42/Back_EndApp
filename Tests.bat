echo off 
cd/d %~dp0/out
cls
echo Test 1.1 
app.jar
echo %ErrorLevel%
echo Test 1.2 
app.jar -h
echo %ErrorLevel%
echo Test 1.3 
app.jar -q
echo %ErrorLevel%	
echo Test 2.1
app.jar -login vasya -pass 123	
echo %ErrorLevel%
echo Test 2.2 
app.jar -pass 123 -login vasya	
echo %ErrorLevel%
echo Test 2.3 
app.jar -login VASYA -pass 123	
echo %ErrorLevel%
echo Test 2.4 
app.jar -login asd -pass 123	
echo %ErrorLevel%
echo Test 2.5 
app.jar -login admin -pass 1234	
echo %ErrorLevel%
echo Test 2.6 
app.jar -login admin -pass admin	
echo %ErrorLevel%
echo Test 3.1 
app.jar -login vasya -pass 123 -role READ -res A
echo %ErrorLevel%	
echo Test 3.2 
app.jar -login vasya -pass 123 -role DELETE -res A
echo %ErrorLevel%	
echo Test 3.3 
app.jar -login vasya -pass 123 -role WRITE -res A
echo %ErrorLevel%	
echo Test 3.4 
app.jar -login vasya -pass 123 -role READ -res A.B	
echo %ErrorLevel%
echo Test 3.5
app.jar -login admin -pass admin -role WRITE -res A.B.C	 
echo %ErrorLevel%
echo Test 3.6 
app.jar -login vasya -pass 1234 -role DELETE -res A
echo %ErrorLevel%	
echo Test 3.7 
app.jar -login vasya -pass 123 -role WRITE -res A.B.C	
echo %ErrorLevel%
echo Test 3.8 
app.jar -login admin -pass admin -role READ	
echo %ErrorLevel%
echo Test 3.9 
app.jar -login admin -pass admin -role EXECUTE -res A	
echo %ErrorLevel%
echo Test 3.10
app.jar -login admin -pass admin -role WRITE -res A.A	
echo %ErrorLevel%
echo Test 4.1 
app.jar -login vasya -pass 123 -role READ -res A -ds 2020-01-01 -de 2020-02-01 -vol 20	
echo %ErrorLevel%
echo Test 4.2 
app.jar -login vasya -pass 123 -role READ -res A -ds 2020-01-01 -de 2020-15-01 -vol 20	
echo %ErrorLevel%
echo Test 4.3 
app.jar -login vasya -pass 123 -role READ -res A -ds 2020-01-01 -de asd -vol 20	
echo %ErrorLevel%
echo Test 4.4
app.jar -login vasya -pass 123 -role READ -res A -ds 2020-01-01 -de 2020-02-01 -vol asd	
echo %ErrorLevel%
echo Test 4.5
app.jar -login vasya -pass 123 -role READ -res A -ds 2020-01-01 -de 2020-02-01 -vol -1	
echo %ErrorLevel%
PAUSE