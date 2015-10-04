### Set up MySQL database and user

```
terrence@Silencer ~/Projects/PAF/db/mysql
20:57:56 127 𝜆 /usr/local/mysql/bin/mysql -h localhost -u root < create_database.sql

terrence@Silencer ~/Projects/PAF/db/mysql
20:57:56 127 𝜆 /usr/local/mysql/bin/mysql -h localhost -u root < create_user.sql


### Set up PAF (Postal Address File) tables 

terrence@Silencer ~/Projects/PAF/db/mysql
20:57:56 127 𝜆 /usr/local/mysql/bin/mysql -h localhost -u paf -ppassword -D paf < create_tables.sql
```
