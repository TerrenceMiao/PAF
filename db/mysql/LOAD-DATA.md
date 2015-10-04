### Load data into MySQL by using LOAD DATA INFILE for fixed width data files

sql> use paf;

sql> LOAD DATA INFILE '/Users/terrence/Projects/PAF/data/PAF_V2016-0_CODE.dat' INTO TABLE code FIELDS TERMINATED BY '' LINES TERMINATED BY '\r\n'
[2015-10-04 20:35:49] 637 rows affected in 10ms

sql> LOAD DATA INFILE '/Users/terrence/Projects/PAF/data/PAF_V2016-0_STREET_ALTERNATE.dat' INTO TABLE street_alternate FIELDS TERMINATED BY '' LINES TERMINATED BY '\r\n'
[2015-10-04 20:35:50] 589 rows affected in 240ms

sql> LOAD DATA INFILE '/Users/terrence/Projects/PAF/data/PAF_V2016-0_SYNONYM.dat' INTO TABLE synonym FIELDS TERMINATED BY '' LINES TERMINATED BY '\r\n'
[2015-10-04 20:35:50] 6366 rows affected in 82ms

sql> LOAD DATA INFILE '/Users/terrence/Projects/PAF/data/PAF_V2016-0_LOCALITY.dat' INTO TABLE locality FIELDS TERMINATED BY '' LINES TERMINATED BY '\r\n'
[2015-10-04 20:35:50] 15790 rows affected in 117ms

sql> LOAD DATA INFILE '/Users/terrence/Projects/PAF/data/PAF_V2016-0_BORDERING_LOCALITY.dat' INTO TABLE bordering_locality FIELDS TERMINATED BY '' LINES TERMINATED BY '\r\n'
[2015-10-04 20:35:50] 78852 rows affected in 464ms

sql> LOAD DATA INFILE '/Users/terrence/Projects/PAF/data/PAF_V2016-0_BLDG_PROP_NAME.dat' INTO TABLE building_property_name FIELDS TERMINATED BY '' LINES TERMINATED BY '\r\n'
[2015-10-04 20:35:51] 197457 rows affected in 784ms

sql> LOAD DATA INFILE '/Users/terrence/Projects/PAF/data/PAF_V2016-0_GROUP.dat' INTO TABLE delivery_point_group FIELDS TERMINATED BY '' LINES TERMINATED BY '\r\n'
[2015-10-04 20:35:53] 459033 rows affected in 2s 200ms

sql> LOAD DATA INFILE '/Users/terrence/Projects/PAF/data/PAF_V2016-0_POINT.dat' INTO TABLE delivery_point FIELDS TERMINATED BY '' LINES TERMINATED BY '\r\n'
[2015-10-04 20:37:16] 13592848 rows affected in 1m 22s 521ms


