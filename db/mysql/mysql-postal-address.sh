#!/bin/sh

DIR="$( cd "$( dirname "${BASH_SOURCE[0]}" )" && pwd )"
bin=${DIR}/../bin
lib=${DIR}/../lib

echo '
{
    "type": "jdbc",
    "jdbc": {
        "url": "jdbc:mysql://localhost:3306/paf?verifyServerCertificate=false",
        "user": "paf",
        "password": "password",
        "locale": "en_US",
        "sql": "SELECT dp.delivy_point_id as delivy_point_id, dp.postal_delivery_nbr as postal_delivery_nbr, dpg.postal_delivery_type as postal_delivery_type, dp.lot_nbr as lot_nbr, dp.floor_level_type as floor_level_type, dp.floor_level_nbr as floor_level_nbr, dp.flat_unit_type as flat_unit_type, dp.flat_unit_nbr as flat_unit_nbr, CONVERT(dp.house_nbr_1, UNSIGNED INTEGER) as house_nbr_1, dp.house_nbr_sfx_1 as house_nbr_sfx_1, dpg.street_name as street_name, dpg.street_type as street_type, l.locality_name as locality_name, l.postcode as postcode, l.state as state FROM delivery_point dp INNER JOIN delivery_point_group dpg ON dp.delivy_point_group_id = dpg.delivy_point_group_id INNER JOIN locality l ON l.locality_id = dpg.locality_id",
        "elasticsearch": {
            "cluster": "elasticsearch",
            "host": "localhost",
            "port": 9300
        },
        "index": "postaladdress",
        "type": "postaladdress",
        "index_settings": {
            "index": {
                "number_of_shards": 2
            }
        },
        "type_mapping": {
            "postaladdress": {
                "_source": {
                    "enabled": true
                },
                "properties": {
                    "delivy_point_id": {
                        "type": "string"
                    },
                    "postal_delivery_nbr": {
                        "type": "string"
                    },
                    "postal_delivery_type": {
                        "type": "string"
                    },
                    "lot_nbr": {
                        "type": "string"
                    },
                    "floor_level_type": {
                        "type": "string"
                    },
                    "floor_level_nbr": {
                        "type": "string"
                    },
                    "flat_unit_type": {
                        "type": "string"
                    },
                    "flat_unit_nbr": {
                        "type": "string"
                    },
                    "house_nbr_1": {
                        "type": "string"
                    },
                    "house_nbr_sfx_1": {
                        "type": "string"
                    },
                    "street_name": {
                        "type": "string"
                    },
                    "street_type": {
                        "type": "string"
                    },
                    "locality_name": {
                        "type": "string"
                    },
                    "postcode": {
                        "type": "string"
                    },
                    "state": {
                        "type": "string"
                    }
                }
            }
        }
    }
}
' | java \
    -cp "${lib}/*" \
    -Dlog4j.configurationFile=${bin}/log4j2.xml \
    org.xbib.tools.Runner \
    org.xbib.tools.JDBCImporter

curl -XGET 'localhost:9200/postaladdress/_refresh'
