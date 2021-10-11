echo 'create database bookish' | mysql -h db -P 3306 -u root -prootpw
while read -r line
do
	echo 'USE bookish;'$line | mysql -h db -P 3306 -u root -prootpw
done < ./DBSchema.sql
