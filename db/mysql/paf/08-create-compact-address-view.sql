USE paf;

DROP TABLE IF EXISTS `compact_address_view`;
CREATE TABLE compact_address_view LIKE address_view;

-- Single house number
INSERT INTO compact_address_view
  SELECT *
  FROM address_view
  WHERE TRIM(LEADING '0' FROM house_nbr_1) <> ''
  LIMIT 100;

-- house number 1 & 2 not null
INSERT INTO compact_address_view
  SELECT *
  FROM address_view
  WHERE TRIM(LEADING '0' FROM house_nbr_1) <> '' AND TRIM(LEADING '0' FROM house_nbr_2) <> ''
  LIMIT 100;

-- has Floor level
INSERT INTO compact_address_view
  SELECT *
  FROM address_view
  WHERE TRIM(LEADING '0' FROM house_nbr_1) <> '' AND floor_level <> ''
  LIMIT 100;

-- has UNIT/FLAT number
INSERT INTO compact_address_view
  SELECT *
  FROM address_view
  WHERE TRIM(LEADING '0' FROM house_nbr_1) <> '' AND flat_unit_nbr <> ''
  LIMIT 100;

-- has both FLOOR level & UNIT/FLAT number
INSERT INTO compact_address_view
  SELECT *
  FROM address_view
  WHERE TRIM(LEADING '0' FROM house_nbr_1) <> '' AND flat_unit_nbr <> '' AND floor_level <> ''
  LIMIT 100;

-- postal delivery type - CARE PO
INSERT INTO compact_address_view
  SELECT *
  FROM address_view
  WHERE TRIM(LEADING '0' FROM postal_delivery_nbr) <> '' AND postal_delivery_type = 'CARE PO'
  LIMIT 10;

-- postal delivery type - community mail agent
INSERT INTO compact_address_view
  SELECT *
  FROM address_view
  WHERE TRIM(LEADING '0' FROM postal_delivery_nbr) <> '' AND postal_delivery_type = 'CMA'
  LIMIT 10;

-- postal delivery type - community mail bag
INSERT INTO compact_address_view
  SELECT *
  FROM address_view
  WHERE TRIM(LEADING '0' FROM postal_delivery_nbr) <> '' AND postal_delivery_type = 'CMB'
  LIMIT 10;

-- postal delivery type - community postal agent
INSERT INTO compact_address_view
  SELECT *
  FROM address_view
  WHERE TRIM(LEADING '0' FROM postal_delivery_nbr) <> '' AND postal_delivery_type = 'CPA'
  LIMIT 10;

-- postal delivery type - GPO Box
INSERT INTO compact_address_view
  SELECT *
  FROM address_view
  WHERE TRIM(LEADING '0' FROM postal_delivery_nbr) <> '' AND postal_delivery_type = 'GPS BOX'
  LIMIT 10;

-- postal delivery type - Locked Bag
INSERT INTO compact_address_view
  SELECT *
  FROM address_view
  WHERE TRIM(LEADING '0' FROM postal_delivery_nbr) <> '' AND postal_delivery_type = 'LOCKED BOX'
  LIMIT 10;

-- postal delivery type - Mail Service
INSERT INTO compact_address_view
  SELECT *
  FROM address_view
  WHERE TRIM(LEADING '0' FROM postal_delivery_nbr) <> '' AND postal_delivery_type = 'MS'
  LIMIT 10;

-- postal delivery type - PO Box
INSERT INTO compact_address_view
  SELECT *
  FROM address_view
  WHERE TRIM(LEADING '0' FROM postal_delivery_nbr) <> '' AND postal_delivery_type = 'PO BOX'
  LIMIT 10;

-- postal delivery type - Private bag
INSERT INTO compact_address_view
  SELECT *
  FROM address_view
  WHERE TRIM(LEADING '0' FROM postal_delivery_nbr) <> '' AND postal_delivery_type = 'PRIVATE BAG'
  LIMIT 10;

-- postal delivery type - rural mail box
INSERT INTO compact_address_view
  SELECT *
  FROM address_view
  WHERE TRIM(LEADING '0' FROM postal_delivery_nbr) <> '' AND postal_delivery_type = 'RMB'
  LIMIT 10;

-- postal delivery type - Roadside mail service
INSERT INTO compact_address_view
  SELECT *
  FROM address_view
  WHERE TRIM(LEADING '0' FROM postal_delivery_nbr) <> '' AND postal_delivery_type = 'RMS'
  LIMIT 10;

-- postal delivery type - Roadside delivery
INSERT INTO compact_address_view
  SELECT *
  FROM address_view
  WHERE TRIM(LEADING '0' FROM postal_delivery_nbr) <> '' AND postal_delivery_type = 'RSD'
  LIMIT 10;

-- States - AAT
INSERT INTO compact_address_view
  SELECT *
  FROM address_view
  WHERE state = 'AAT'
  LIMIT 100;

-- States - ACT
INSERT INTO compact_address_view
  SELECT *
  FROM address_view
  WHERE state = 'ACT'
  LIMIT 100;

-- States - NSW
INSERT INTO compact_address_view
  SELECT *
  FROM address_view
  WHERE state = 'NSW'
  LIMIT 100;

-- States - NT
INSERT INTO compact_address_view
  SELECT *
  FROM address_view
  WHERE state = 'NT'
  LIMIT 100;

-- States - QLD
INSERT INTO compact_address_view
  SELECT *
  FROM address_view
  WHERE state = 'QLD'
  LIMIT 100;

-- States - SA
INSERT INTO compact_address_view
  SELECT *
  FROM address_view
  WHERE state = 'SA'
  LIMIT 100;

-- States - TAS
INSERT INTO compact_address_view
  SELECT *
  FROM address_view
  WHERE state = 'TAS'
  LIMIT 100;

-- States - VIC
INSERT INTO compact_address_view
  SELECT *
  FROM address_view
  WHERE state = 'VIC'
  LIMIT 100;

-- States - WA
INSERT INTO compact_address_view
  SELECT *
  FROM address_view
  WHERE state = 'WA'
  LIMIT 100;

-- street addresses
INSERT INTO compact_address_view
  SELECT *
  FROM address_view
  WHERE TRIM(LEADING '0' FROM house_nbr_1) <> '' AND street <> ''
  LIMIT 1000;

-- street addresses with alternatives
INSERT INTO compact_address_view
  SELECT *
  FROM address_view
  WHERE TRIM(LEADING '0' FROM house_nbr_1) <> '' AND street <> '' AND alt_street <> ''
  LIMIT 1000;

-- street addresses with street suffix
INSERT INTO compact_address_view
  SELECT ad.*
  FROM address_view ad
    LEFT JOIN delivery_point_group_view dpg ON ad.delivy_point_group_id = dpg.delivy_point_group_id
  WHERE TRIM(LEADING '0' FROM house_nbr_1) <> '' AND dpg.street_sfx <> ''
        AND dpg.street_name <> '' AND dpg.street_type <> ''
  LIMIT 1000;

-- alternate street addresses with street suffix
INSERT INTO compact_address_view
  SELECT ad.*
  FROM address_view ad
    LEFT JOIN delivery_point_group_view dpg ON ad.delivy_point_group_id = dpg.delivy_point_group_id
  WHERE ad.alt_street <> ''
  LIMIT 1000;

-- locality synonym
INSERT INTO compact_address_view
  SELECT *
  FROM address_view
  WHERE locality_synonym <> '' AND locality_synonym IS NOT NULL
  LIMIT 1000;

-- bordering locality names
INSERT INTO compact_address_view
  SELECT *
  FROM address_view
  WHERE bordering_locality_names <> '' AND bordering_locality_names IS NOT NULL
  LIMIT 1000;

-- building property names
INSERT INTO compact_address_view
  SELECT *
  FROM address_view
  WHERE bldg_prop_name_1 LIKE '%,%' OR bldg_prop_name_2 LIKE '%,%'
  LIMIT 1100;

-- same street names with different suffixes
INSERT INTO compact_address_view
  SELECT *
  FROM address_view
  WHERE street = "LIVERPOOL ST"
  LIMIT 2;

INSERT INTO compact_address_view
  SELECT *
  FROM address_view
  WHERE street = "LIVERPOOL RD"
  LIMIT 2;

INSERT INTO compact_address_view
  SELECT *
  FROM address_view
  WHERE street = "NORTH LIVERPOOL RD"
  LIMIT 2;

INSERT INTO compact_address_view
  SELECT *
  FROM address_view
  WHERE street = "LIVERPOOL DR"
  LIMIT 2;

INSERT INTO compact_address_view
  SELECT *
  FROM address_view
  WHERE street = "SOUTH LIVERPOOL RD"
  LIMIT 2;

INSERT INTO compact_address_view
  SELECT *
  FROM address_view
  WHERE street = "LIVERPOOL LANE"
  LIMIT 2;

INSERT INTO compact_address_view
  SELECT *
  FROM address_view
  WHERE street = "OLD LIVERPOOL RD"
  LIMIT 2;

INSERT INTO compact_address_view
  SELECT *
  FROM address_view
  WHERE street = "LIVERPOOL CRES"
  LIMIT 2;

INSERT INTO compact_address_view
  SELECT *
  FROM address_view
  WHERE street = "LIVERPOOL CT"
  LIMIT 2;

INSERT INTO compact_address_view
  SELECT *
  FROM address_view
  WHERE street = "LIVERPOOL PL"
  LIMIT 2;

INSERT INTO compact_address_view
  SELECT *
  FROM address_view
  WHERE street = "LORD LIVERPOOL DR"
  LIMIT 1;

INSERT INTO compact_address_view
  SELECT *
  FROM address_view
  WHERE street = "LIVERPOOL CCT"
  LIMIT 1;

-- House number, flat number is empty, only have the street name.
INSERT INTO compact_address_view
  SELECT *
  FROM address_view
  WHERE street <> '' AND house_nbr = '' AND flat_unit = ''
  LIMIT 5;

-- Flat unit type coverage
INSERT INTO compact_address_view
  SELECT *
  FROM address_view
  WHERE flat_unit_type = 'WARD'
  LIMIT 1;

INSERT INTO compact_address_view
  SELECT *
  FROM address_view
  WHERE flat_unit_type = 'SHED'
  LIMIT 1;

INSERT INTO compact_address_view
  SELECT *
  FROM address_view
  WHERE flat_unit_type = 'CTGE'
  LIMIT 1;

INSERT INTO compact_address_view
  SELECT *
  FROM address_view
  WHERE flat_unit_type = 'SL'
  LIMIT 1;

INSERT INTO compact_address_view
  SELECT *
  FROM address_view
  WHERE flat_unit_type = 'PTHS'
  LIMIT 1;

INSERT INTO compact_address_view
  SELECT *
  FROM address_view
  WHERE flat_unit_type = 'MSNT'
  LIMIT 1;

-- Floor level coverage
INSERT INTO compact_address_view
  SELECT *
  FROM address_view
  WHERE floor_level_type = 'B'
  LIMIT 1;

INSERT INTO compact_address_view
  SELECT *
  FROM address_view
  WHERE floor_level_type = 'G'
  LIMIT 1;

INSERT INTO compact_address_view
  SELECT *
  FROM address_view
  WHERE floor_level_type = 'UG'
  LIMIT 1;

INSERT INTO compact_address_view
  SELECT *
  FROM address_view
  WHERE floor_level_type = 'M'
  LIMIT 1;

INSERT INTO compact_address_view
  SELECT *
  FROM address_view
  WHERE floor_level_type = 'LG'
  LIMIT 1;

-- Insert addresses which cause incorrect index search
-- U 502 710 STATION ST BOX HILL VIC 3128
INSERT INTO compact_address_view
  SELECT *
  FROM address_view
  WHERE delivy_point_id IN (46652459, 56205809, 61838320, 54596590, 50125456, 50303593, 49835749, 50592112, 63336822);

-- SHOP 4A UG 108 BOURKE ST MELBOURNE VIC 3000
INSERT INTO compact_address_view
  SELECT *
  FROM address_view
  WHERE delivy_point_id IN (67451371, 64107706, 75148360, 88745091, 84643146, 71205778, 70793133, 72688798, 92048669);

-- SHOP 19 1 MAIN ST BOX HILL VIC 3128
INSERT INTO compact_address_view
  SELECT *
  FROM address_view
  WHERE delivy_point_id IN (61838320, 54596590);

-- 111 BOURKE ADDRESSES
INSERT INTO compact_address_view
  SELECT *
  FROM address_view
  WHERE full_address LIKE '111 BOURKE%';

-- 29 PITT ST SOUTH OAKLEIGH VIC which causes the sorting issue in rank
INSERT INTO compact_address_view
  SELECT *
  FROM address_view
  WHERE delivy_point_id = 34163460
        OR delivy_point_id = 55680446
        OR delivy_point_id = 66251880
        OR delivy_point_id = 95038617
        OR delivy_point_id = 78802841
        OR delivy_point_id = 31532856
        OR delivy_point_id = 32025380
        OR delivy_point_id = 99483715;

-- Special format of addresses
INSERT INTO compact_address_view
  SELECT *
  FROM address_view
  WHERE full_address LIKE "%O'Reilly%"
  LIMIT 2;

INSERT INTO compact_address_view
  SELECT *
  FROM address_view
  WHERE full_address LIKE "%Lancaster-Mooroopna%"
  LIMIT 2;

INSERT INTO compact_address_view
  SELECT *
  FROM address_view
  WHERE full_address = "765 BOURKE-MILPARINKA RD BOURKE NSW 2840"
  LIMIT 1;