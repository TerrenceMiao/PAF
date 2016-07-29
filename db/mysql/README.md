### Allow "root" access MySQL database from anywhere

```
mysql> GRANT ALL PRIVILEGES ON *.* TO 'root'@'%' IDENTIFIED BY 'password';
```

### Set up MySQL database and user

```
terrence@Silencer ~/Projects/PAF/db/mysql
20:57:56 127 𝜆 /usr/local/mysql/bin/mysql -h localhost -u root < create_database.sql

terrence@Silencer ~/Projects/PAF/db/mysql
20:57:56 127 𝜆 /usr/local/mysql/bin/mysql -h localhost -u root < create_user.sql
```
##### try 127.0.0.1 instead of localhost in case of error like:
ERROR 2002 (HY000): Can't connect to local MySQL server through socket '/var/run/mysql.sock'

### Set up PAF (Postal Address File) tables 

```
terrence@Silencer ~/Projects/PAF/db/mysql
20:57:56 127 𝜆 /usr/local/mysql/bin/mysql -h localhost -u paf -ppassword -D paf < create_tables.sql
```

### Query Delivery Point ID (DPID) by Postal Address

Example, looking address **"18 Sandlewood Lane, Point Cook, VIC 3030"**

```
mysql> SELECT * FROM locality WHERE locality_name = "POINT COOK";
+------+------------------+-------------+---------------+----------+-------+--------------+
| id   | record_actn_code | locality_id | locality_name | postcode | state | locality_did |
+------+------------------+-------------+---------------+----------+-------+--------------+
| 6868 | I                | 00012220    | POINT COOK    | 3030     | VIC   |              |
+------+------------------+-------------+---------------+----------+-------+--------------+
1 row in set (0.09 sec)

mysql> SELECT * FROM delivery_point_group WHERE locality_id = "00012220" AND street_name = "Sandlewood" AND street_type = "LANE";
+--------+------------------+-----------------------+-------------+-------------+-------------+------------+----------------------+------------------------+
| id     | record_actn_code | delivy_point_group_id | locality_id | street_name | street_type | street_sfx | postal_delivery_type | delivy_point_group_did |
+--------+------------------+-----------------------+-------------+-------------+-------------+------------+----------------------+------------------------+
| 339694 | I                | 00783106              | 00012220    | SANDLEWOOD  | LANE        |            |                      |                        |
+--------+------------------+-----------------------+-------------+-------------+-------------+------------+----------------------+------------------------+
1 row in set (0.71 sec)

mysql> SELECT DISTINCT CONCAT(UCASE(MID(street_type,1,1)),LCASE(MID(street_type,2))) AS street_type FROM delivery_point_group WHERE  street_type != '' ORDER BY street_type;
+-------------+
| street_type |
+-------------+
| Lane        |
+-------------+
| Rd          |
+-------------+
| St          |
+-------------+
3 row in set (0.012 sec)

mysql> SELECT * FROM synonym WHERE locality_id = "00012220";
+------+------------------+---------+-------------+-----------------+----------+----------------+
| id   | record_actn_code | type_id | locality_id | synonym         | postcode | type_actn_code |
+------+------------------+---------+-------------+-----------------+----------+----------------+
| 3107 | I                | LOC     | 00012220    | RAAF POINT COOK | 3030     | V              |
| 3108 | I                | LOC     | 00012220    | SANCTUARY LAKES | 3030     | U              |
| 3109 | I                | LOC     | 00012220    | PT COOK RAAF    | 3030     | V              |
| 3110 | I                | LOC     | 00012220    | POINT COOK RAAF | 3030     | V              |
| 3111 | I                | LOC     | 00012220    | PT COOK         | 3030     | U              |
+------+------------------+---------+-------------+-----------------+----------+----------------+
5 rows in set (0.01 sec)

mysql> SELECT * FROM bordering_locality WHERE parent_locality_id = "00012220";
+-------+------------------+--------------------+-----------------------+-------------------------+--------------------+
| id    | record_actn_code | parent_locality_id | bordering_locality_id | bordering_locality_name | bordering_postcode |
+-------+------------------+--------------------+-----------------------+-------------------------+--------------------+
| 37908 | I                | 00012220           | 00002956              | LAVERTON                | 3028               |
| 37909 | I                | 00012220           | 00005758              | WERRIBEE                | 3030               |
| 37910 | I                | 00012220           | 00011653              | HOPPERS CROSSING        | 3029               |
| 37911 | I                | 00012220           | 00016719              | ALTONA MEADOWS          | 3028               |
| 37912 | I                | 00012220           | 00022010              | SEABROOK                | 3028               |
| 37913 | I                | 00012220           | 00023513              | WERRIBEE SOUTH          | 3030               |
| 37914 | I                | 00012220           | 00026849              | WILLIAMS LANDING        | 3027               |
+-------+------------------+--------------------+-----------------------+-------------------------+--------------------+
7 rows in set (0.11 sec)

mysql> SELECT * FROM street_alternate WHERE delivy_point_group_id = "00783106";
Empty set (0.00 sec)

mysql> SELECT * FROM delivery_point WHERE delivy_point_group_id = "00783106" and house_nbr_1 = "00018";
+---------+------------------+-----------------+-----------------------+-------------+-----------------+-------------+-----------------+----------------+---------------+------------------+-----------------+---------+---------------------+-------------------------+-------------------------+-------------------+
| id      | record_actn_code | delivy_point_id | delivy_point_group_id | house_nbr_1 | house_nbr_sfx_1 | house_nbr_2 | house_nbr_sfx_2 | flat_unit_type | flat_unit_nbr | floor_level_type | floor_level_nbr | lot_nbr | postal_delivery_nbr | postal_delivery_nbr_pfx | postal_delivery_nbr_sfx | primary_point_ind |
+---------+------------------+-----------------+-----------------------+-------------+-----------------+-------------+-----------------+----------------+---------------+------------------+-----------------+---------+---------------------+-------------------------+-------------------------+-------------------+
| 4217837 | I                | 51123887        | 00783106              | 00018       |                 | 00000       |                 |                |               |                  |                 |         | 00000               |                         |                         | R                 |
+---------+------------------+-----------------+-----------------------+-------------+-----------------+-------------+-----------------+----------------+---------------+------------------+-----------------+---------+---------------------+-------------------------+-------------------------+-------------------+
1 row in set (8.77 sec)
```

Example, looking address **"Flat 18 / 10 - 12 Anderson Road, Thornbury, VIC 3071"**

```
mysql> SELECT * FROM locality WHERE locality_name = "Thornbury";
+------+------------------+-------------+---------------+----------+-------+--------------+
| id   | record_actn_code | locality_id | locality_name | postcode | state | locality_did |
+------+------------------+-------------+---------------+----------+-------+--------------+
| 2536 | I                | 00006251    | THORNBURY     | 3071     | VIC   |              |
+------+------------------+-------------+---------------+----------+-------+--------------+
1 row in set (0.00 sec)

mysql> SELECT * FROM delivery_point_group WHERE locality_id = "00006251" AND street_name = "Anderson" AND street_type = "RD";
+--------+------------------+-----------------------+-------------+-------------+-------------+------------+----------------------+------------------------+
| id     | record_actn_code | delivy_point_group_id | locality_id | street_name | street_type | street_sfx | postal_delivery_type | delivy_point_group_did |
+--------+------------------+-----------------------+-------------+-------------+-------------+------------+----------------------+------------------------+
| 265894 | I                | 00625292              | 00006251    | ANDERSON    | RD          |            |                      |                        |
+--------+------------------+-----------------------+-------------+-------------+-------------+------------+----------------------+------------------------+
1 row in set (0.00 sec)

mysql> SELECT * FROM delivery_point WHERE delivy_point_group_id = "00625292" AND house_nbr_1 = "00010" AND delivery_point.house_nbr_2 = "00012" AND flat_unit_type = "U" AND flat_unit_nbr = "8";
+---------+------------------+-----------------+-----------------------+-------------+-----------------+-------------+-----------------+----------------+---------------+------------------+-----------------+---------+---------------------+-------------------------+-------------------------+-------------------+
| id      | record_actn_code | delivy_point_id | delivy_point_group_id | house_nbr_1 | house_nbr_sfx_1 | house_nbr_2 | house_nbr_sfx_2 | flat_unit_type | flat_unit_nbr | floor_level_type | floor_level_nbr | lot_nbr | postal_delivery_nbr | postal_delivery_nbr_pfx | postal_delivery_nbr_sfx | primary_point_ind |
+---------+------------------+-----------------+-----------------------+-------------+-----------------+-------------+-----------------+----------------+---------------+------------------+-----------------+---------+---------------------+-------------------------+-------------------------+-------------------+
| 5817114 | I                | 59134819        | 00625292              | 00010       |                 | 00012       |                 | U              | 8             |                  |                 |         | 00000               |                         |                         |                   |
+---------+------------------+-----------------+-----------------------+-------------+-----------------+-------------+-----------------+----------------+---------------+------------------+-----------------+---------+---------------------+-------------------------+-------------------------+-------------------+
1 row in set (0.00 sec)
```


### Query Postal Address by Delivery Point ID (DPID)

Example, looking for DPID **"45535128"** Postal Address

```
mysql> SELECT * FROM delivery_point WHERE delivy_point_id = "45535128";
+---------+------------------+-----------------+-----------------------+-------------+-----------------+-------------+-----------------+----------------+---------------+------------------+-----------------+---------+---------------------+-------------------------+-------------------------+-------------------+
| id      | record_actn_code | delivy_point_id | delivy_point_group_id | house_nbr_1 | house_nbr_sfx_1 | house_nbr_2 | house_nbr_sfx_2 | flat_unit_type | flat_unit_nbr | floor_level_type | floor_level_nbr | lot_nbr | postal_delivery_nbr | postal_delivery_nbr_pfx | postal_delivery_nbr_sfx | primary_point_ind |
+---------+------------------+-----------------+-----------------------+-------------+-----------------+-------------+-----------------+----------------+---------------+------------------+-----------------+---------+---------------------+-------------------------+-------------------------+-------------------+
| 3104647 | I                | 45535128        | 00592628              | 00035       |                 | 00000       |                 |                |               |                  |                 |         | 00000               |                         |                         | R                 |
+---------+------------------+-----------------+-----------------------+-------------+-----------------+-------------+-----------------+----------------+---------------+------------------+-----------------+---------+---------------------+-------------------------+-------------------------+-------------------+
1 row in set (0.00 sec)

mysql> SELECT * FROM delivery_point_group WHERE delivy_point_group_id = "00592628";
+--------+------------------+-----------------------+-------------+-------------+-------------+------------+----------------------+------------------------+
| id     | record_actn_code | delivy_point_group_id | locality_id | street_name | street_type | street_sfx | postal_delivery_type | delivy_point_group_did |
+--------+------------------+-----------------------+-------------+-------------+-------------+------------+----------------------+------------------------+
| 239730 | I                | 00592628              | 00011762    | BRADSHAW    | ST          |            |                      |                        |
+--------+------------------+-----------------------+-------------+-------------+-------------+------------+----------------------+------------------------+
1 row in set (0.00 sec)

mysql> SELECT * FROM locality WHERE locality_id = "00011762";
+------+------------------+-------------+---------------+----------+-------+--------------+
| id   | record_actn_code | locality_id | locality_name | postcode | state | locality_did |
+------+------------------+-------------+---------------+----------+-------+--------------+
| 6503 | I                | 00011762    | KINGSBURY     | 3083     | VIC   |              |
+------+------------------+-------------+---------------+----------+-------+--------------+
1 row in set (0.00 sec)
```

So Postal Address **"35 Bradshaw St, Kingsbury VIC 3083"** matches DPID **"45535128"**


### Query ALL Postal Addresses

```
mysql> SELECT dp.delivy_point_id, dp.postal_delivery_nbr, dpg.postal_delivery_type, dp.lot_nbr, dp.floor_level_type, dp.floor_level_nbr, dp.flat_unit_type, dp.flat_unit_nbr, dp.house_nbr_1,
              dpg.street_name, dpg.street_type, l.locality_name, l.postcode, l.state FROM delivery_point dp
   INNER JOIN delivery_point_group dpg ON dp.delivy_point_group_id = dpg.delivy_point_group_id
   INNER JOIN locality l ON l.locality_id = dpg.locality_id
     ORDER BY l.state, l.locality_name, dpg.street_name, dpg.street_type, dp.house_nbr_1, dp.flat_unit_type, dp.flat_unit_nbr, dp.lot_nbr, dpg.postal_delivery_type, dp.postal_delivery_nbr;
```