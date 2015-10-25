### Load data into MySQL by using LOAD DATA INFILE for fixed width data files

```
sql> use paf;

sql> LOAD DATA INFILE '/Users/terrence/Projects/PAF/data/PAF_V2016-0_CODE.dat' INTO TABLE code  FIELDS TERMINATED BY '' LINES TERMINATED BY '\r\n' (record_actn_code, type_id, type_item, type_item_abbr, type_actn_code)
[2015-10-25 15:01:25] 637 rows affected in 17ms

sql> LOAD DATA INFILE '/Users/terrence/Projects/PAF/data/PAF_V2016-0_STREET_ALTERNATE.dat' INTO TABLE street_alternate FIELDS TERMINATED BY '' LINES TERMINATED BY '\r\n' (record_actn_code, delivy_point_group_id, st_alt_street_name, st_alt_street_type, st_alt_street_sfx)
[2015-10-25 15:01:33] 589 rows affected in 16ms

sql> LOAD DATA INFILE '/Users/terrence/Projects/PAF/data/PAF_V2016-0_SYNONYM.dat' INTO TABLE synonym FIELDS TERMINATED BY '' LINES TERMINATED BY '\r\n' (record_actn_code, type_id, locality_id, synonym, postcode, type_actn_code)
[2015-10-25 15:01:52] 6366 rows affected in 115ms

sql> LOAD DATA INFILE '/Users/terrence/Projects/PAF/data/PAF_V2016-0_LOCALITY.dat' INTO TABLE locality FIELDS TERMINATED BY '' LINES TERMINATED BY '\r\n' (record_actn_code, locality_id, locality_name, postcode, state, locality_did)
[2015-10-25 15:02:30] 15790 rows affected in 194ms

sql> LOAD DATA INFILE '/Users/terrence/Projects/PAF/data/PAF_V2016-0_BORDERING_LOCALITY.dat' INTO TABLE bordering_locality FIELDS TERMINATED BY '' LINES TERMINATED BY '\r\n' (record_actn_code, parent_locality_id, bordering_locality_id, bordering_locality_name, bordering_postcode)
[2015-10-25 15:02:50] 78852 rows affected in 473ms

sql> LOAD DATA INFILE '/Users/terrence/Projects/PAF/data/PAF_V2016-0_BLDG_PROP_NAME.dat' INTO TABLE building_property_name FIELDS TERMINATED BY '' LINES TERMINATED BY '\r\n' (record_actn_code, delivy_point_id, bldg_prop_name_1, bldg_prop_name_2)
[2015-10-25 15:03:13] 197457 rows affected in 907ms

sql> LOAD DATA INFILE '/Users/terrence/Projects/PAF/data/PAF_V2016-0_GROUP.dat' INTO TABLE delivery_point_group FIELDS TERMINATED BY '' LINES TERMINATED BY '\r\n' (record_actn_code, delivy_point_group_id, locality_id, street_name, street_type, street_sfx, postal_delivery_type, delivy_point_group_did)
[2015-10-25 15:03:35] 459033 rows affected in 2s 318ms

sql> LOAD DATA INFILE '/Users/terrence/Projects/PAF/data/PAF_V2016-0_POINT.dat' INTO TABLE delivery_point FIELDS TERMINATED BY '' LINES TERMINATED BY '\r\n' (record_actn_code, delivy_point_id, delivy_point_group_id, house_nbr_1, house_nbr_sfx_1, house_nbr_2, house_nbr_sfx_2, flat_unit_type, flat_unit_nbr, floor_level_type, floor_level_nbr, lot_nbr, postal_delivery_nbr, postal_delivery_nbr_pfx, postal_delivery_nbr_sfx, primary_point_ind)
[2015-10-25 15:05:39] 13592848 rows affected in 1m 29s 258ms
```


### Error "Data Too Long for column xxx" when load data into MySQL on Windows

This is due to Fixed Width LOAD DATA INFILE doesn't work on Windows MySQL.

A workaround solution to this issue is:

```
sql> use paf;

LOAD DATA LOCAL INFILE 'F:\\devprojects\\PAF\\data\\PAF_V2016-0_CODE.dat' INTO TABLE code (@row)
SET record_actn_code = SUBSTR(@row,1,1), type_id = SUBSTR(@row,2,3), type_item = SUBSTR(@row,5,40), type_item_abbr = SUBSTR(@row,45,25), type_actn_code = SUBSTR(@row,70,1);
Query OK, 637 rows affected (0.07 sec)
Records: 637  Deleted: 0  Skipped: 0  Warnings: 0

LOAD DATA LOCAL INFILE 'F:\\devprojects\\PAF\\data\\PAF_V2016-0_STREET_ALTERNATE.dat' INTO TABLE street_alternate (@row)
SET record_actn_code = SUBSTR(@row,1,1), delivy_point_group_id = SUBSTR(@row,2,8), st_alt_street_name = SUBSTR(@row,10,30), st_alt_street_type = SUBSTR(@row,40,4), st_alt_street_sfx = SUBSTR(@row,44,2);
Query OK, 589 rows affected (0.59 sec)
Records: 589  Deleted: 0  Skipped: 0  Warnings: 0

LOAD DATA LOCAL INFILE 'F:\\devprojects\\PAF\\data\\PAF_V2016-0_SYNONYM.dat' INTO TABLE synonym (@row)
SET record_actn_code = SUBSTR(@row,1,1), type_id = SUBSTR(@row,2,3), locality_id = SUBSTR(@row,5,8), synonym = SUBSTR(@row,13,46), postcode = SUBSTR(@row,59,12), type_actn_code = SUBSTR(@row,71,1);
Query OK, 6366 rows affected (0.26 sec)
Records: 6366  Deleted: 0  Skipped: 0  Warnings: 0

LOAD DATA LOCAL INFILE 'F:\\devprojects\\PAF\\data\\PAF_V2016-0_LOCALITY.dat' INTO TABLE locality (@row)
SET record_actn_code = SUBSTR(@row,1,1), locality_id = SUBSTR(@row,2,8), locality_name = SUBSTR(@row,10,46), postcode = SUBSTR(@row,56,12), state = SUBSTR(@row,68,3), locality_did = SUBSTR(@row,71,8);
Query OK, 15790 rows affected (0.56 sec)
Records: 15790  Deleted: 0  Skipped: 0  Warnings: 0

LOAD DATA LOCAL INFILE 'F:\\devprojects\\PAF\\data\\PAF_V2016-0_BORDERING_LOCALITY.dat' INTO TABLE bordering_locality (@row)
SET record_actn_code = SUBSTR(@row,1,1), parent_locality_id = SUBSTR(@row,2,8), bordering_locality_id = SUBSTR(@row,10,8),bordering_locality_name = SUBSTR(@row,18,46), bordering_postcode = SUBSTR(@row,64,12);
Query OK, 78852 rows affected (0.90 sec)
Records: 78852  Deleted: 0  Skipped: 0  Warnings: 0

LOAD DATA LOCAL INFILE 'F:\\devprojects\\PAF\\data\\PAF_V2016-0_BLDG_PROP_NAME.dat' INTO TABLE building_property_name (@row)
SET record_actn_code = SUBSTR(@row,1,1), delivy_point_id = SUBSTR(@row,2,8), bldg_prop_name_1 = SUBSTR(@row,10,30), bldg_prop_name_2 = SUBSTR(@row,40,30);
Query OK, 197457 rows affected (2.87 sec)
Records: 197457  Deleted: 0  Skipped: 0  Warnings: 0

LOAD DATA LOCAL INFILE 'F:\\devprojects\\PAF\\data\\PAF_V2016-0_GROUP.dat' INTO TABLE delivery_point_group (@row)
SET record_actn_code = SUBSTR(@row,1,1), delivy_point_group_id = SUBSTR(@row,2,8), locality_id = SUBSTR(@row,10,8), street_name = SUBSTR(@row,18,30), street_type = SUBSTR(@row,48,4), street_sfx = SUBSTR(@row,52,2), postal_delivery_type = SUBSTR(@row,54,11), delivy_point_group_did = SUBSTR(@row,65,8);
Query OK, 459033 rows affected (5.61 sec)
Records: 459033  Deleted: 0  Skipped: 0  Warnings: 0

LOAD DATA LOCAL INFILE 'F:\\devprojects\\PAF\\data\\PAF_V2016-0_POINT.dat' INTO TABLE delivery_point (@row)
SET record_actn_code = SUBSTR(@row,1,1), delivy_point_id = SUBSTR(@row,2,8), delivy_point_group_id = SUBSTR(@row,10,8), house_nbr_1 = SUBSTR(@row,18,5), house_nbr_sfx_1 = SUBSTR(@row,23,1), house_nbr_2 = SUBSTR(@row,24,5), house_nbr_sfx_2 = SUBSTR(@row,29,1), flat_unit_type = SUBSTR(@row,30,7), flat_unit_nbr = SUBSTR(@row,37,7), floor_level_type = SUBSTR(@row,44,2), floor_level_nbr = SUBSTR(@row,46,5), lot_nbr = SUBSTR(@row,51,6), postal_delivery_nbr = SUBSTR(@row,57,5), postal_delivery_nbr_pfx = SUBSTR(@row,62,3), postal_delivery_nbr_sfx = SUBSTR(@row,65,3), primary_point_ind = SUBSTR(@row,68,1);
Query OK, 13592848 rows affected (3 min 27.19 sec)
Records: 13592848  Deleted: 0  Skipped: 0  Warnings: 0
```
