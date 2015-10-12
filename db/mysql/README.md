### Set up MySQL database and user

```
terrence@Silencer ~/Projects/PAF/db/mysql
20:57:56 127 𝜆 /usr/local/mysql/bin/mysql -h localhost -u root < create_database.sql

terrence@Silencer ~/Projects/PAF/db/mysql
20:57:56 127 𝜆 /usr/local/mysql/bin/mysql -h localhost -u root < create_user.sql
```

### Set up PAF (Postal Address File) tables 

```
terrence@Silencer ~/Projects/PAF/db/mysql
20:57:56 127 𝜆 /usr/local/mysql/bin/mysql -h localhost -u paf -ppassword -D paf < create_tables.sql
```

### Query Delivery Point ID (DPID) by Postal Address

Example, looking address "18 Sandlewood Lane, Point Cook, VIC 3030"

```
mysql> SELECT * FROM locality WHERE locality_name = "POINT COOK";
+------------------+-------------+---------------+----------+-------+--------------+
| record_actn_code | locality_id | locality_name | postcode | state | locality_did |
+------------------+-------------+---------------+----------+-------+--------------+
| I                | 00012220    | POINT COOK    | 3030     | VIC   |              |
+------------------+-------------+---------------+----------+-------+--------------+
1 row in set (0.01 sec)

mysql> SELECT * FROM delivery_point_group WHERE locality_id = "00012220" AND street_name = "Sandlewood" AND street_type = "LANE";
+------------------+-----------------------+-------------+-------------+-------------+------------+----------------------+------------------------+
| record_actn_code | delivy_point_group_id | locality_id | street_name | street_type | street_sfx | postal_delivery_type | delivy_point_group_did |
+------------------+-----------------------+-------------+-------------+-------------+------------+----------------------+------------------------+
| I                | 00783106              | 00012220    | SANDLEWOOD  | LANE        |            |                      |                        |
+------------------+-----------------------+-------------+-------------+-------------+------------+----------------------+------------------------+
1 row in set (0.19 sec)

mysql> SELECT * FROM synonym WHERE locality_id = "00012220";
+------------------+---------+-------------+-----------------+----------+----------------+
| record_actn_code | type_id | locality_id | synonym         | postcode | type_actn_code |
+------------------+---------+-------------+-----------------+----------+----------------+
| I                | LOC     | 00012220    | RAAF POINT COOK | 3030     | V              |
| I                | LOC     | 00012220    | SANCTUARY LAKES | 3030     | U              |
| I                | LOC     | 00012220    | PT COOK RAAF    | 3030     | V              |
| I                | LOC     | 00012220    | POINT COOK RAAF | 3030     | V              |
| I                | LOC     | 00012220    | PT COOK         | 3030     | U              |
+------------------+---------+-------------+-----------------+----------+----------------+
5 rows in set (0.00 sec)

mysql> SELECT * FROM bordering_locality WHERE parent_locality_id = "00012220";
+------------------+--------------------+-----------------------+-------------------------+--------------------+
| record_actn_code | parent_locality_id | bordering_locality_id | bordering_locality_name | bordering_postcode |
+------------------+--------------------+-----------------------+-------------------------+--------------------+
| I                | 00012220           | 00002956              | LAVERTON                | 3028               |
| I                | 00012220           | 00005758              | WERRIBEE                | 3030               |
| I                | 00012220           | 00011653              | HOPPERS CROSSING        | 3029               |
| I                | 00012220           | 00016719              | ALTONA MEADOWS          | 3028               |
| I                | 00012220           | 00022010              | SEABROOK                | 3028               |
| I                | 00012220           | 00023513              | WERRIBEE SOUTH          | 3030               |
| I                | 00012220           | 00026849              | WILLIAMS LANDING        | 3027               |
+------------------+--------------------+-----------------------+-------------------------+--------------------+
7 rows in set (0.03 sec)

mysql> SELECT * FROM street_alternate WHERE delivy_point_group_id = "00783106";
Empty set (0.00 sec)

mysql> SELECT * FROM delivery_point WHERE delivy_point_group_id = "00783106" and house_nbr_1 = "00018";
+------------------+-----------------+-----------------------+-------------+-----------------+-------------+-----------------+----------------+---------------+------------------+-----------------+---------+---------------------+-------------------------+-------------------------+-------------------+
| record_actn_code | delivy_point_id | delivy_point_group_id | house_nbr_1 | house_nbr_sfx_1 | house_nbr_2 | house_nbr_sfx_2 | flat_unit_type | flat_unit_nbr | floor_level_type | floor_level_nbr | lot_nbr | postal_delivery_nbr | postal_delivery_nbr_pfx | postal_delivery_nbr_sfx | primary_point_ind |
+------------------+-----------------+-----------------------+-------------+-----------------+-------------+-----------------+----------------+---------------+------------------+-----------------+---------+---------------------+-------------------------+-------------------------+-------------------+
| I                | 51123887        | 00783106              | 00018       |                 | 00000       |                 |                |               |                  |                 |         | 00000               |                         |                         | R                 |
+------------------+-----------------+-----------------------+-------------+-----------------+-------------+-----------------+----------------+---------------+------------------+-----------------+---------+---------------------+-------------------------+-------------------------+-------------------+
1 row in set (8.56 sec)
```


### Query Postal Address by Delivery Point ID (DPID)

Example, looking for DPID "45535128"'s Postal Address

```
mysql> SELECT * FROM delivery_point WHERE delivy_point_id = "45535128";
+------------------+-----------------+-----------------------+-------------+-----------------+-------------+-----------------+----------------+---------------+------------------+-----------------+---------+---------------------+-------------------------+-------------------------+-------------------+
| record_actn_code | delivy_point_id | delivy_point_group_id | house_nbr_1 | house_nbr_sfx_1 | house_nbr_2 | house_nbr_sfx_2 | flat_unit_type | flat_unit_nbr | floor_level_type | floor_level_nbr | lot_nbr | postal_delivery_nbr | postal_delivery_nbr_pfx | postal_delivery_nbr_sfx | primary_point_ind |
+------------------+-----------------+-----------------------+-------------+-----------------+-------------+-----------------+----------------+---------------+------------------+-----------------+---------+---------------------+-------------------------+-------------------------+-------------------+
| I                | 45535128        | 00592628              | 00035       |                 | 00000       |                 |                |               |                  |                 |         | 00000               |                         |                         | R                 |
+------------------+-----------------+-----------------------+-------------+-----------------+-------------+-----------------+----------------+---------------+------------------+-----------------+---------+---------------------+-------------------------+-------------------------+-------------------+
1 row in set (8.31 sec)

mysql> SELECT * FROM delivery_point_group WHERE delivy_point_group_id = "00592628";
+------------------+-----------------------+-------------+-------------+-------------+------------+----------------------+------------------------+
| record_actn_code | delivy_point_group_id | locality_id | street_name | street_type | street_sfx | postal_delivery_type | delivy_point_group_did |
+------------------+-----------------------+-------------+-------------+-------------+------------+----------------------+------------------------+
| I                | 00592628              | 00011762    | BRADSHAW    | ST          |            |                      |                        |
+------------------+-----------------------+-------------+-------------+-------------+------------+----------------------+------------------------+
1 row in set (0.18 sec)

mysql> SELECT * FROM locality WHERE locality_id = "00011762";
+------------------+-------------+---------------+----------+-------+--------------+
| record_actn_code | locality_id | locality_name | postcode | state | locality_did |
+------------------+-------------+---------------+----------+-------+--------------+
| I                | 00011762    | KINGSBURY     | 3083     | VIC   |              |
+------------------+-------------+---------------+----------+-------+--------------+
1 row in set (0.01 sec)
```
