git config core.sparsecheckout true
egrep -rv ^\/\/ ProjectList.txt > .git/info/sparse-checkout
git pull origin master