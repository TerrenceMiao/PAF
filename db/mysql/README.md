### Allow "root" access MySQL database from anywhere

```
mysql> GRANT ALL PRIVILEGES ON *.* TO 'root'@'%' IDENTIFIED BY 'Welcome1';
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
    SELECT dp.delivy_point_id as delivy_point_id,
           dp.postal_delivery_nbr as postal_delivery_nbr, dpg.postal_delivery_type as postal_delivery_type,
           dp.lot_nbr as lot_nbr, dp.floor_level_type as floor_level_type, dp.floor_level_nbr as floor_level_nbr, dp.flat_unit_type as flat_unit_type, dp.flat_unit_nbr as flat_unit_nbr,
           dp.house_nbr_1 as house_nbr_1, dp.house_nbr_sfx_1 as house_nbr_sfx_1,
           dpg.street_name as street_name, dpg.street_type as street_type, l.locality_name as locality_name, l.postcode as postcode, l.state as state
      FROM delivery_point dp
INNER JOIN delivery_point_group dpg ON dp.delivy_point_group_id = dpg.delivy_point_group_id
INNER JOIN locality l ON l.locality_id = dpg.locality_id
  ORDER BY l.state, l.locality_name, dpg.street_name, dpg.street_type, dp.house_nbr_1, dp.house_nbr_sfx_1, dp.flat_unit_type, dp.flat_unit_nbr, dp.lot_nbr, dpg.postal_delivery_type, dp.postal_delivery_nbr;
```

### Data in bird view

[![asciicast](https://asciinema.org/a/bVpQJFri2a8o1l0DFS5fIaW1L.png)](https://asciinema.org/a/bVpQJFri2a8o1l0DFS5fIaW1L)

* Tables

```
mysql> SELECT COUNT(*) FROM bordering_locality;
+----------+
| COUNT(*) |
+----------+
|    78852 |
+----------+
1 row in set (0.02 sec)

mysql> SELECT COUNT(*) FROM building_property_name;
+----------+
| COUNT(*) |
+----------+
|   197457 |
+----------+
1 row in set (0.06 sec)

mysql> SELECT COUNT(*) FROM code;
+----------+
| COUNT(*) |
+----------+
|      945 |
+----------+
1 row in set (0.00 sec)

mysql> SELECT COUNT(*) FROM delivery_point;
+----------+
| COUNT(*) |
+----------+
| 13592848 |
+----------+
1 row in set (2.13 sec)

mysql> SELECT COUNT(*) FROM delivery_point_group;
+----------+
| COUNT(*) |
+----------+
|   459033 |
+----------+
1 row in set (0.08 sec)

mysql> SELECT COUNT(*) FROM locality;
+----------+
| COUNT(*) |
+----------+
|    15790 |
+----------+
1 row in set (0.00 sec)

mysql> SELECT COUNT(*) FROM street_alternate;
+----------+
| COUNT(*) |
+----------+
|      589 |
+----------+
1 row in set (0.00 sec)

mysql> SELECT COUNT(*) FROM synonym;
+----------+
| COUNT(*) |
+----------+
|     6366 |
+----------+
1 row in set (0.01 sec)
```

* Views

```
mysql> SELECT COUNT(*) FROM address_view;
+----------+
| COUNT(*) |
+----------+
| 13592848 |
+----------+
1 row in set (4.51 sec)

mysql> SELECT COUNT(*) FROM building_property_name_view;
+----------+
| COUNT(*) |
+----------+
|   196694 |
+----------+
1 row in set (0.05 sec)

mysql> SELECT COUNT(*) FROM compact_address_view;
+----------+
| COUNT(*) |
+----------+
|     7861 |
+----------+
1 row in set (0.00 sec)

mysql> SELECT COUNT(*) FROM delivery_point_group_view;
+----------+
| COUNT(*) |
+----------+
|   459033 |
+----------+
1 row in set (0.16 sec)

mysql> SELECT COUNT(*) FROM locality_view;
+----------+
| COUNT(*) |
+----------+
|    15790 |
+----------+
1 row in set (0.01 sec)
```

### Fun Queries
```
mysql> SELECT full_address FROM address_view WHERE full_address LIKE "35 Bradshaw ST%";
+------------------------------------+
| full_address                       |
+------------------------------------+
| 35 BRADSHAW ST MORDIALLOC VIC 3195 |
| 35 BRADSHAW ST ESSENDON VIC 3040   |
| 35 BRADSHAW ST KINGSBURY VIC 3083  |
| 35 BRADSHAW ST EDEN HILL WA 6054   |
| 35 BRADSHAW ST FRANKSTON VIC 3199  |
+------------------------------------+
5 rows in set (11.76 sec)

mysql> SELECT DISTINCT state FROM address_view WHERE state <> '' ORDER BY state;
+-------+
| state |
+-------+
| ACT   |
| NSW   |
| NT    |
| QLD   |
| SA    |
| TAS   |
| VIC   |
| WA    |
+-------+
8 rows in set (0.00 sec)

mysql> SELECT DISTINCT street_sfx FROM address_view WHERE street_sfx <> '' ORDER BY street_sfx;
+------------+
| street_sfx |
+------------+
| CN         |
| E          |
| EX         |
| LR         |
| N          |
| NE         |
| S          |
| SE         |
| SW         |
| W          |
+------------+
10 rows in set (9.55 sec)

mysql> SELECT DISTINCT flat_unit_type FROM address_view WHERE flat_unit_type <> '' ORDER BY flat_unit_type;
+----------------+
| flat_unit_type |
+----------------+
| CTGE           |
| DUP            |
| FY             |
| HSE            |
| KSK            |
| MSNT           |
| OFF            |
| PTHS           |
| REAR           |
| RM             |
| SE             |
| SHED           |
| SHOP           |
| SITE           |
| SL             |
| STU            |
| TNHS           |
| U              |
| VLLA           |
| WARD           |
+----------------+
20 rows in set (9.88 sec)

mysql> SELECT DISTINCT floor_level_type FROM address_view WHERE floor_level_type <> '' ORDER BY floor_level_type;

+------------------+
| floor_level_type |
+------------------+
| B                |
| G                |
| L                |
| LG               |
| M                |
| UG               |
+------------------+
6 rows in set (9.07 sec)

mysql> SELECT DISTINCT postal_delivery_type FROM address_view WHERE postal_delivery_type <> '' ORDER BY postal_delivery_type;
+----------------------+
| postal_delivery_type |
+----------------------+
| CARE PO              |
| CMA                  |
| CMB                  |
| CPA                  |
| GPO BOX              |
| LOCKED BAG           |
| MS                   |
| PO BOX               |
| PRIVATE BAG          |
| RMB                  |
| RMS                  |
| RSD                  |
+----------------------+
12 rows in set (8.91 sec)

```

### References
* Australian Antarctic Territory (AAT), https://en.wikipedia.org/wiki/Australian_Antarctic_Territory

* Address - road suffix, street suffix code A, http://meteor.aihw.gov.au/content/index.phtml/itemId/429869

| Value   | Meaning    |
|:------- |:---------- |
| CN	  | Central    |
| E	      | East       |
| EX	  | Extension  |
| LR	  | Lower      |
| N	      | North      |
| NE	  | North East |
| NW	  | North West |
| S	      | South      |
| SE	  | South East |
| SW	  | South West |
| UP	  | Upper      |
| W	      | West       |

* Address - building/complex sub-unit type, code AA, http://meteor.aihw.gov.au/content/index.phtml/itemId/429004

| Value   | Meaning                  |
|:------- |:------------------------ |
| ANT	  | Antenna                  |
| APT	  | Apartment                |
| ATM	  | Automated Teller Machine |
| BBQ	  | Barbecue                 |
| BTSD	  | Boatshed                 |
| BLDG	  | Building                 |
| BNGW	  | Bungalow                 |
| CAGE	  | Cage                     |
| CARP	  | Carpark                  |
| CARS	  | Carspace                 |
| CLUB	  | Club                     |
| COOL	  | Coolroom                 |
| CTGE	  | Cottage                  |
| DUPL	  | Duplex                   |
| FCTY	  | Factory                  |
| FLAT	  | Flat                     |
| GRGE	  | Garage                   |
| HALL	  | Hall                     |
| HSE	  | House                    |
| KSK	  | Kiosk                    |
| LSE	  | Lease                    |
| LBBY	  | Lobby                    |
| LOFT	  | Loft                     |
| LOT	  | Lot                      |
| MSNT	  | Maisonette               |
| MBTH	  | Marine Berth             |
| OFFC	  | Office                   |
| RESV	  | Reserve                  |
| ROOM	  | Room                     |
| SHED	  | Shed                     |
| SHOP	  | Shop                     |
| SHRM	  | Showroom                 |
| SIGN	  | Sign                     |
| SITE	  | Site                     |
| STLL	  | Stall                    |
| STOR	  | Store                    |
| STR	  | Strata unit              |
| STU	  | Studio/studio apartment  |
| SUBS	  | Substation               |
| SE	  | Suite                    |
| TNCY	  | Tenancy                  |
| TWR	  | Tower                    |
| TNHS	  | Townhouse                |
| UNIT	  | Unit                     |
| VLT	  | Vault                    |
| VLLA	  | Villa                    |
| WARD	  | Ward                     |
| WHSE	  | Warehouse                |
| WKSH	  | Workshop                 |

* Address - floor/level type, code A, http://meteor.aihw.gov.au/content/index.phtml/itemId/429016/meteorItemView/long

| Value   | Meaning            |
|:------- |:------------------ |
| B	      | Basement           |
| FL	  | Floor              |
| G	      | Ground             |
| L	      | Level              |
| LG	  | Lower ground floor |
| M	      | Mezzanine          |
| OD	  | Observation deck   |
| P	      | Parking            |
| PTHS	  | Penthouse          |
| PLF	  | Platform           |
| PDM	  | Podium             |
| RT	  | Rooftop            |
| SB	  | Sub-basement       |
| UG	  | Upper ground floor |
