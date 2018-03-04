# MyPractise
单元练习app、demo

因为后面项目数量可能会累积，因此需要的时候使用稀疏检出获得特定的项目  
git init  
git remote add origin xxxx.git  
git config core.sparsecheckout true  
echo 项目名称 > .git/info/sparse-checkout  
git pull origin master  

或者直接在ProjectList.txt中注释不需要的项目后运行pull.sh脚本。需要先复制ProjectList.txt和pull.sh到本地，适用于同时clone多个项目。  
git init  
git remote add origin xxxx.git  
在ProjectList.txt中注释不需要的项目  
./pull.sh  
 
