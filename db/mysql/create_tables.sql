USE paf;

CREATE TABLE IF NOT EXISTS code (
    record_actn_code CHAR(1) NOT NULL,
    type_id CHAR(3) NOT NULL,
    type_item CHAR(40) NOT NULL,
    type_item_abbr CHAR(25) NOT NULL,
    type_actn_code CHAR(1) NOT NULL
);

CREATE TABLE IF NOT EXISTS street_alternate (
    record_actn_code CHAR(1) NOT NULL,
    delivy_point_group_id CHAR(8) NOT NULL,
    st_alt_street_name CHAR(30) NOT NULL,
    st_alt_street_type CHAR(4),
    st_alt_street_sfx CHAR(2)
);

CREATE TABLE IF NOT EXISTS synonym (
    record_actn_code CHAR(1) NOT NULL,
    type_id CHAR(3) NOT NULL,
    locality_id CHAR(8) NOT NULL,
    synonym CHAR(46) NOT NULL,
    postcode CHAR(12) NOT NULL,
    type_actn_code CHAR(1) NOT NULL
);

CREATE TABLE IF NOT EXISTS locality (
    record_actn_code CHAR(1) NOT NULL,
    locality_id CHAR(8) NOT NULL,
    locality_name CHAR(46) NOT NULL,
    postcode CHAR(12) NOT NULL,
    state CHAR(3) NOT NULL,
    locality_did CHAR(8)
);

CREATE TABLE IF NOT EXISTS bordering_locality (
    record_actn_code CHAR(1) NOT NULL,
    parent_locality_id CHAR(8) NOT NULL,
    bordering_locality_id CHAR(8) NOT NULL,
    bordering_locality_name CHAR(46) NOT NULL,
    bordering_postcode CHAR(12) NOT NULL
);

CREATE TABLE IF NOT EXISTS building_property_name (
    record_actn_code CHAR(1) NOT NULL,
    delivery_point_id CHAR(8) NOT NULL,
    bldg_prop_name_1 CHAR(30) NOT NULL,
    bldg_prop_name_2 CHAR(30)
);

CREATE TABLE IF NOT EXISTS delivery_point_group (
    record_actn_code CHAR(1) NOT NULL,
    delivery_point_group_id CHAR(8) NOT NULL,
    locality_id CHAR(8) NOT NULL,
    street_name CHAR(30) NOT NULL,
    street_type CHAR(4) NOT NULL,
    street_sfx CHAR(2),
    postal_delivery_type CHAR(11) NOT NULL,
    delivery_point_group_did CHAR(8) NOT NULL
);

CREATE TABLE IF NOT EXISTS delivery_point (
    record_actn_code CHAR(1) NOT NULL,
    delivery_point_id CHAR(8) NOT NULL,
    delivery_point_group_id CHAR(8) NOT NULL,
    house_nbr_1 CHAR(5) NOT NULL,
    house_nbr_sfx_1 CHAR(1),
    house_nbr_2 CHAR(5) NOT NULL,
    house_nbr_sfx_2 CHAR(1),
    flat_unit_type CHAR(7),
    flat_unit_nbr CHAR(7),
    floor_level_type CHAR(2),
    floor_level_nbr CHAR(5),
    lot_nbr CHAR(6),
    postal_delivery_nbr CHAR(5) NOT NULL,
    postal_delivery_nbr_pfx CHAR(3),
    postal_delivery_nbr_sfx CHAR(3),
    primary_point_ind CHAR(1)
);  

