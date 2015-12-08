workd=$0 #full path name of this file
declare -i lworkd=${#workd} #length of name
name=`basename "$0"` #returns just the file name
declare -i lname=${#name} #length of just file name
declare -i ldir=$lworkd-$lname #calc length of dir name
dir=${workd:0:$ldir} #get substring that is just dir name
DB=$dir'Resources/'
JAR=$DB'PoS.jar'
java -jar $JAR
read hi
