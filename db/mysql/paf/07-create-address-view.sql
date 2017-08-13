USE paf;

DROP TABLE IF EXISTS `address_view`;
CREATE TABLE IF NOT EXISTS address_view AS
    SELECT dp.delivy_point_id                                                AS delivy_point_id,
           dp.delivy_point_group_id                                          AS delivy_point_group_id,
           TRIM(CONCAT(TRIM(LEADING '0' FROM dp.house_nbr_1), dp.house_nbr_sfx_1,
                       CASE WHEN TRIM(LEADING '0' FROM dp.house_nbr_2) = ''
                           THEN ' '
                       ELSE CONCAT('-', TRIM(LEADING '0' FROM dp.house_nbr_2),
                                   dp.house_nbr_sfx_2, ' ')
                       END))                                                 AS house_nbr,
           CONCAT(TRIM(LEADING '0' FROM dp.house_nbr_1),
                  dp.house_nbr_sfx_1)                                        AS house_nbr_1,
           CONCAT(TRIM(LEADING '0' FROM dp.house_nbr_2),
                  dp.house_nbr_sfx_2)                                        AS house_nbr_2,
           dp.lot_nbr                                                        AS lot_nbr,
           TRIM(floor_level_type)                                            AS floor_level_type,
           TRIM(floor_level_nbr)                                             AS floor_level_nbr,
           TRIM(CONCAT(floor_level_type, ' ', floor_level_nbr))              AS floor_level,
           TRIM(flat_unit_type)                                              AS flat_unit_type,
           TRIM(flat_unit_nbr)                                               AS flat_unit_nbr,
           TRIM(CONCAT(flat_unit_type, ' ', flat_unit_nbr))                  AS flat_unit,
           TRIM(CASE WHEN bpn.bldg_prop_name_1 IS NULL OR bpn.bldg_prop_name_1 = ''
               THEN ''
                ELSE TRIM(bpn.bldg_prop_name_1)
                END)                                                         AS bldg_prop_name_1,
           TRIM(CASE WHEN bpn.bldg_prop_name_2 IS NULL OR bpn.bldg_prop_name_2 = ''
               THEN ''
                ELSE TRIM(bpn.bldg_prop_name_2)
                END)                                                         AS bldg_prop_name_2,
           TRIM(CASE WHEN dpg.street IS NULL OR dpg.street = ''
               THEN ''
                ELSE TRIM(dpg.street)
                END)                                                         AS street,
           TRIM(dpg.street_type)                                             AS street_type,
           TRIM(dpg.street_sfx)                                              AS street_sfx,
           TRIM(CASE WHEN dpg.alt_street IS NULL OR dpg.alt_street = ''
               THEN ''
                ELSE TRIM(dpg.alt_street)
                END)                                                         AS alt_street,
           dpg.postal_delivery_type                                          AS postal_delivery_type,
           CONCAT(dp.postal_delivery_nbr_sfx,
                  TRIM(LEADING '0' FROM dp.postal_delivery_nbr),
                  dp.postal_delivery_nbr_pfx)                                AS postal_delivery_nbr,
           l.locality_name                                                   AS locality_name,
           (CASE WHEN l.synonym IS NULL OR l.synonym = ''
               THEN ''
            ELSE TRIM(l.synonym)
            END)                                                             AS locality_synonym,
           (CASE WHEN l.bordering_locality_names IS NULL OR
                      l.bordering_locality_names = ''
               THEN ''
            ELSE TRIM(l.bordering_locality_names)
            END)                                                             AS bordering_locality_names,
           l.postcode                                                        AS postcode,
           l.state                                                           AS state,
           TRIM(CONCAT(
                    CASE WHEN TRIM(LEADING '0' FROM dp.postal_delivery_nbr) = '' OR
                              dp.postal_delivery_nbr IS NULL
                        THEN CONCAT(
                            CASE WHEN TRIM(flat_unit_nbr) = ''
                                THEN ''
                            ELSE CONCAT(flat_unit_type, ' ', flat_unit_nbr,
                                        ' ') END,
                            CASE WHEN
                                TRIM(CONCAT(floor_level_type, ' ', floor_level_nbr))
                                = ''
                                THEN ''
                            WHEN TRIM(floor_level_nbr) = ''
                                THEN CONCAT(floor_level_type, ' ')
                            ELSE CONCAT(floor_level_type, ' ', floor_level_nbr,
                                        ' ') END,
                            CASE WHEN TRIM(lot_nbr) = ''
                              THEN ''
                            ELSE CONCAT(lot_nbr)
                            END,
                            CONCAT(TRIM(LEADING '0' FROM dp.house_nbr_1),
                                   dp.house_nbr_sfx_1,
                                   CASE WHEN TRIM(LEADING '0' FROM dp.house_nbr_2) =
                                             ''
                                       THEN ' '
                                   ELSE CONCAT('-', TRIM(LEADING '0' FROM
                                                         dp.house_nbr_2),
                                               dp.house_nbr_sfx_2, ' ')
                                   END),
                            CASE WHEN dpg.street IS NULL OR dpg.street = ''
                                THEN ''
                            ELSE CONCAT(TRIM(dpg.street), ' ')
                            END)
                    ELSE
                        CONCAT(dpg.postal_delivery_type, ' ',
                               dp.postal_delivery_nbr_sfx,
                               TRIM(LEADING '0' FROM dp.postal_delivery_nbr),
                               dp.postal_delivery_nbr_pfx, ' ')
                    END,
                    TRIM(l.locality_name), ' ', TRIM(l.state), ' ', TRIM(l.postcode)
                ))                                                           AS full_address
       FROM delivery_point dp
           LEFT JOIN building_property_name_view bpn
               ON bpn.delivy_point_id = dp.delivy_point_id
           LEFT JOIN delivery_point_group_view dpg
               ON dp.delivy_point_group_id = dpg.delivy_point_group_id
           LEFT JOIN locality_view l ON l.locality_id = dpg.locality_id;

CREATE INDEX delivy_point_id_address_view_index
    ON address_view (delivy_point_id) USING HASH;
CREATE INDEX state_address_view_index
    ON address_view (state) USING HASH;
