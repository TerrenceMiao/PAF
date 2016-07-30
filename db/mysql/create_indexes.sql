use paf;

# LOCALITY
CREATE UNIQUE INDEX locality_id_index on locality(locality_id) USING HASH;
CREATE INDEX locality_name_index on locality(locality_name) USING HASH;
CREATE INDEX postcode_index on locality(postcode) USING HASH;
CREATE INDEX state_index on locality(state) USING HASH;

# DELIVERY_POINT_GROUP
CREATE UNIQUE INDEX delivy_point_group_id_index on delivery_point_group(delivy_point_group_id) USING HASH;
CREATE INDEX locality_id_index on delivery_point_group(locality_id) USING HASH;
CREATE INDEX street_name_index on delivery_point_group(street_name) USING HASH;
CREATE INDEX street_type_index on delivery_point_group(street_type) USING HASH;
CREATE INDEX postal_delivery_type_index on delivery_point_group(postal_delivery_type) USING HASH;

# SYNONYM
CREATE INDEX locality_id_index on synonym(locality_id) USING HASH;

# BORDERING_LOCALITY
CREATE INDEX parent_locality_id_index on bordering_locality(parent_locality_id) USING HASH;

# DELIVERY_POINT
CREATE UNIQUE INDEX delivy_point_id_index on delivery_point(delivy_point_id) USING HASH;
CREATE INDEX delivy_point_group_id_index on delivery_point(delivy_point_group_id) USING HASH;
CREATE INDEX house_nbr_1_index on delivery_point(house_nbr_1) USING HASH;
CREATE INDEX house_nbr_sfx_1_index on delivery_point(house_nbr_sfx_1) USING HASH;
CREATE INDEX flat_unit_type_index on delivery_point(flat_unit_type) USING HASH;
CREATE INDEX flat_unit_nbr_index on delivery_point(flat_unit_nbr) USING HASH;
CREATE INDEX lot_nbr_index on delivery_point(lot_nbr) USING HASH;
CREATE INDEX postal_delivery_nbr_index on delivery_point(postal_delivery_nbr) USING HASH;