databaseChangeLog {
  changeSet(id: '1450008653190-1', author: 'terrence (generated)') {
    createTable(tableName: 'bordering_locality') {
      column(name: 'id', type: 'INT', autoIncrement: true) {
        constraints(primaryKey: true)
      }
      column(name: 'record_actn_code', type: 'CHAR(1)') {
        constraints(nullable: false)
      }
      column(name: 'parent_locality_id', type: 'CHAR(8)') {
        constraints(nullable: false)
      }
      column(name: 'bordering_locality_id', type: 'CHAR(8)') {
        constraints(nullable: false)
      }
      column(name: 'bordering_locality_name', type: 'CHAR(46)') {
        constraints(nullable: false)
      }
      column(name: 'bordering_postcode', type: 'CHAR(12)') {
        constraints(nullable: false)
      }
    }
  }

  changeSet(id: '1450008653190-2', author: 'terrence (generated)') {
    createTable(tableName: 'building_property_name') {
      column(name: 'id', type: 'INT', autoIncrement: true) {
        constraints(primaryKey: true)
      }
      column(name: 'record_actn_code', type: 'CHAR(1)') {
        constraints(nullable: false)
      }
      column(name: 'delivy_point_id', type: 'CHAR(8)') {
        constraints(nullable: false)
      }
      column(name: 'bldg_prop_name_1', type: 'CHAR(30)') {
        constraints(nullable: false)
      }
      column(name: 'bldg_prop_name_2', type: 'CHAR(30)')
    }
  }

  changeSet(id: '1450008653190-3', author: 'terrence (generated)') {
    createTable(tableName: 'code') {
      column(name: 'id', type: 'INT', autoIncrement: true) {
        constraints(primaryKey: true)
      }
      column(name: 'record_actn_code', type: 'CHAR(1)') {
        constraints(nullable: false)
      }
      column(name: 'type_id', type: 'CHAR(3)') {
        constraints(nullable: false)
      }
      column(name: 'type_item', type: 'CHAR(40)') {
        constraints(nullable: false)
      }
      column(name: 'type_item_abbr', type: 'CHAR(25)') {
        constraints(nullable: false)
      }
      column(name: 'type_actn_code', type: 'CHAR(1)') {
        constraints(nullable: false)
      }
    }
  }

  changeSet(id: '1450008653190-4', author: 'terrence (generated)') {
    createTable(tableName: 'delivery_point') {
      column(name: 'id', type: 'INT', autoIncrement: true) {
        constraints(primaryKey: true)
      }
      column(name: 'record_actn_code', type: 'CHAR(1)') {
        constraints(nullable: false)
      }
      column(name: 'delivy_point_id', type: 'CHAR(8)') {
        constraints(nullable: false)
      }
      column(name: 'delivy_point_group_id', type: 'CHAR(8)') {
        constraints(nullable: false)
      }
      column(name: 'house_nbr_1', type: 'CHAR(5)') {
        constraints(nullable: false)
      }
      column(name: 'house_nbr_sfx_1', type: 'CHAR(1)')
      column(name: 'house_nbr_2', type: 'CHAR(5)') {
        constraints(nullable: false)
      }
      column(name: 'house_nbr_sfx_2', type: 'CHAR(1)')
      column(name: 'flat_unit_type', type: 'CHAR(7)')
      column(name: 'flat_unit_nbr', type: 'CHAR(7)')
      column(name: 'floor_level_type', type: 'CHAR(2)')
      column(name: 'floor_level_nbr', type: 'CHAR(5)')
      column(name: 'lot_nbr', type: 'CHAR(6)')
      column(name: 'postal_delivery_nbr', type: 'CHAR(5)') {
        constraints(nullable: false)
      }
      column(name: 'postal_delivery_nbr_pfx', type: 'CHAR(3)')
      column(name: 'postal_delivery_nbr_sfx', type: 'CHAR(3)')
      column(name: 'primary_point_ind', type: 'CHAR(1)')
    }
  }

  changeSet(id: '1450008653190-5', author: 'terrence (generated)') {
    createTable(tableName: 'delivery_point_group') {
      column(name: 'id', type: 'INT', autoIncrement: true) {
        constraints(primaryKey: true)
      }
      column(name: 'record_actn_code', type: 'CHAR(1)') {
        constraints(nullable: false)
      }
      column(name: 'delivy_point_group_id', type: 'CHAR(8)') {
        constraints(nullable: false)
      }
      column(name: 'locality_id', type: 'CHAR(8)') {
        constraints(nullable: false)
      }
      column(name: 'street_name', type: 'CHAR(30)') {
        constraints(nullable: false)
      }
      column(name: 'street_type', type: 'CHAR(4)') {
        constraints(nullable: false)
      }
      column(name: 'street_sfx', type: 'CHAR(2)')
      column(name: 'postal_delivery_type', type: 'CHAR(11)') {
        constraints(nullable: false)
      }
      column(name: 'delivy_point_group_did', type: 'CHAR(8)') {
        constraints(nullable: false)
      }
    }
  }

  changeSet(id: '1450008653190-6', author: 'terrence (generated)') {
    createTable(tableName: 'locality') {
      column(name: 'id', type: 'INT', autoIncrement: true) {
        constraints(primaryKey: true)
      }
      column(name: 'record_actn_code', type: 'CHAR(1)') {
        constraints(nullable: false)
      }
      column(name: 'locality_id', type: 'CHAR(8)') {
        constraints(nullable: false)
      }
      column(name: 'locality_name', type: 'CHAR(46)') {
        constraints(nullable: false)
      }
      column(name: 'postcode', type: 'CHAR(12)') {
        constraints(nullable: false)
      }
      column(name: 'state', type: 'CHAR(3)') {
        constraints(nullable: false)
      }
      column(name: 'locality_did', type: 'CHAR(8)')
    }
  }

  changeSet(id: '1450008653190-7', author: 'terrence (generated)') {
    createTable(tableName: 'street_alternate') {
      column(name: 'id', type: 'INT', autoIncrement: true) {
        constraints(primaryKey: true)
      }
      column(name: 'record_actn_code', type: 'CHAR(1)') {
        constraints(nullable: false)
      }
      column(name: 'delivy_point_group_id', type: 'CHAR(8)') {
        constraints(nullable: false)
      }
      column(name: 'st_alt_street_name', type: 'CHAR(30)') {
        constraints(nullable: false)
      }
      column(name: 'st_alt_street_type', type: 'CHAR(4)')
      column(name: 'st_alt_street_sfx', type: 'CHAR(2)')
    }
  }

  changeSet(id: '1450008653190-8', author: 'terrence (generated)') {
    createTable(tableName: 'synonym') {
      column(name: 'id', type: 'INT', autoIncrement: true) {
        constraints(primaryKey: true)
      }
      column(name: 'record_actn_code', type: 'CHAR(1)') {
        constraints(nullable: false)
      }
      column(name: 'type_id', type: 'CHAR(3)') {
        constraints(nullable: false)
      }
      column(name: 'locality_id', type: 'CHAR(8)') {
        constraints(nullable: false)
      }
      column(name: 'synonym', type: 'CHAR(46)') {
        constraints(nullable: false)
      }
      column(name: 'postcode', type: 'CHAR(12)') {
        constraints(nullable: false)
      }
      column(name: 'type_actn_code', type: 'CHAR(1)') {
        constraints(nullable: false)
      }
    }
  }

  changeSet(id: '1450008653190-9', author: 'terrence (generated)') {
    addUniqueConstraint(columnNames: 'delivy_point_group_id', constraintName: 'delivy_point_group_id_index', tableName: 'delivery_point_group')
  }

  changeSet(id: '1450008653190-10', author: 'terrence (generated)') {
    addUniqueConstraint(columnNames: 'delivy_point_id', constraintName: 'delivy_point_id_index', tableName: 'delivery_point')
  }

  changeSet(id: '1450008653190-11', author: 'terrence (generated)') {
    addUniqueConstraint(columnNames: 'locality_id', constraintName: 'locality_id_index', tableName: 'locality')
  }

  changeSet(id: '1450008653190-12', author: 'terrence (generated)') {
    createIndex(indexName: 'delivy_point_group_id_index', tableName: 'delivery_point') {
      column(name: 'delivy_point_group_id')
    }
  }

  changeSet(id: '1450008653190-13', author: 'terrence (generated)') {
    createIndex(indexName: 'locality_id_index', tableName: 'delivery_point_group') {
      column(name: 'locality_id')
    }
  }

  changeSet(id: '1450008653190-14', author: 'terrence (generated)') {
    createIndex(indexName: 'locality_id_index', tableName: 'synonym') {
      column(name: 'locality_id')
    }
  }

  changeSet(id: '1450008653190-15', author: 'terrence (generated)') {
    createIndex(indexName: 'locality_name_index', tableName: 'locality') {
      column(name: 'locality_name')
    }
  }

  changeSet(id: '1450008653190-16', author: 'terrence (generated)') {
    createIndex(indexName: 'parent_locality_id_index', tableName: 'bordering_locality') {
      column(name: 'parent_locality_id')
    }
  }

  changeSet(id: '1450008653190-17', author: 'terrence (generated)') {
    createIndex(indexName: 'postcode_index', tableName: 'locality') {
      column(name: 'postcode')
    }
  }

}
