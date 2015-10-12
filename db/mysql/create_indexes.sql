use paf;

CREATE UNIQUE INDEX locality_id_index on locality(locality_id) USING HASH;
CREATE INDEX locality_name_index on locality(locality_name) USING HASH;
CREATE INDEX postcode_index on locality(postcode) USING HASH;

CREATE UNIQUE INDEX delivy_point_group_id_index on delivery_point_group(delivy_point_group_id) USING HASH;
CREATE INDEX locality_id_index on delivery_point_group(locality_id) USING HASH;

CREATE INDEX locality_id_index on synonym(locality_id) USING HASH;

CREATE INDEX parent_locality_id_index on bordering_locality(parent_locality_id) USING HASH;

CREATE UNIQUE INDEX delivy_point_id_index on delivery_point(delivy_point_id) USING HASH;
CREATE INDEX delivy_point_group_id_index on delivery_point(delivy_point_group_id) USING HASH;
