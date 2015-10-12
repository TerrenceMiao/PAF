use paf;

DROP INDEX locality_id_index on locality;
DROP INDEX locality_name_index on locality;
DROP INDEX postcode_index on locality;

DROP INDEX delivy_point_group_id_index on delivery_point_group;
DROP INDEX locality_id_index on delivery_point_group;

DROP INDEX locality_id_index on synonym;

DROP INDEX parent_locality_id_index on bordering_locality;

DROP INDEX delivy_point_id_index on delivery_point;
DROP INDEX delivy_point_group_id_index on delivery_point;
