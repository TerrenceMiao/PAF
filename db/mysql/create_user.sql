## localhost access ONLY
# create user 'paf'@'localhost' identified by 'password';
# grant all privileges on *.* to 'paf'@'localhost' with grant option;

## Global access
create user 'paf'@'%' identified by 'password';
grant all privileges on *.* to 'paf'@'%' with grant option;
