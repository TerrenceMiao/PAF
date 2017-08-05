USE paf;

DROP TABLE IF EXISTS `building_property_name_view`;
CREATE TABLE IF NOT EXISTS `building_property_name_view` AS
    SELECT
        delivy_point_id,
        ANY_VALUE(
            CASE WHEN bldg_prop_name_1 IS NULL OR bldg_prop_name_1 = ''
                THEN ''
            ELSE GROUP_CONCAT(TRIM(bldg_prop_name_1))
            END) AS bldg_prop_name_1,
        ANY_VALUE(
            CASE WHEN bldg_prop_name_2 IS NULL OR bldg_prop_name_2 = ''
                THEN ''
            ELSE GROUP_CONCAT(TRIM(bldg_prop_name_2))
            END) AS bldg_prop_name_2
    FROM building_property_name
    GROUP BY delivy_point_id;

CREATE UNIQUE INDEX delivy_point_id_building_prop_index
    ON building_property_name_view (delivy_point_id) USING HASH;


DROP TABLE IF EXISTS `locality_view`;
CREATE TABLE IF NOT EXISTS `locality_view` AS
    SELECT
        l.locality_id,
        locality_name,
        l.postcode,
        state,
        GROUP_CONCAT(DISTINCT (synonym))                    AS synonym,
        GROUP_CONCAT(DISTINCT (bl.bordering_locality_name)) AS bordering_locality_names
    FROM locality AS l
        LEFT JOIN synonym AS s ON l.locality_id = s.locality_id
        LEFT JOIN bordering_locality bl ON l.locality_id = bl.parent_locality_id
    GROUP BY l.locality_id, l.locality_name, l.postcode, l.state;

CREATE UNIQUE INDEX locality_id_locality_view_index
    ON locality_view (locality_id) USING HASH;
CREATE INDEX state_locality_view_index
    ON locality_view (state) USING HASH;


DROP TABLE IF EXISTS `delivery_point_group_view`;
CREATE TABLE IF NOT EXISTS `delivery_point_group_view` AS
    SELECT
        dpg.delivy_point_group_id,
        dpg.locality_id,
        dpg.street_name,
        dpg.street_type,
        dpg.street_sfx,
        dpg.postal_delivery_type,
        TRIM(CONCAT(dpg.street_name, ' ', dpg.street_type, ' ', dpg.street_sfx))                         AS street,
        TRIM(GROUP_CONCAT(sa.st_alt_street_name, ' ', sa.st_alt_street_type, ' ', sa.st_alt_street_sfx)) AS alt_street
    FROM delivery_point_group dpg
        LEFT JOIN street_alternate sa ON dpg.delivy_point_group_id = sa.delivy_point_group_id
    GROUP BY dpg.delivy_point_group_id, dpg.locality_id, dpg.street_name,
        dpg.street_type, dpg.street_sfx, dpg.postal_delivery_type, street;

CREATE INDEX delivery_point_group_id_dpg_view_index
    ON delivery_point_group_view (delivy_point_group_id) USING HASH;
CREATE INDEX locality_id_dpg_view_index
    ON delivery_point_group_view (locality_id) USING HASH;
