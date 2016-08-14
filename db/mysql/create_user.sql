## localhost access ONLY

## PAF (Postal Address File)
create user 'paf'@'localhost' identified by 'password';
grant all privileges on *.* to 'paf'@'localhost' with grant option;

## POL (Post Office Location)
create user 'pol'@'localhost' identified by 'password';
grant all privileges on *.* to 'pol'@'localhost' with grant option;

## Global access
create user 'paf'@'%' identified by 'password';
grant all privileges on *.* to 'paf'@'%' with grant option;

create user 'pol'@'%' identified by 'password';
grant all privileges on *.* to 'pol'@'%' with grant option;
