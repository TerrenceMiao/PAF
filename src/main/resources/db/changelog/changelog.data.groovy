databaseChangeLog {
  changeSet(id: '1450138345498-1', author: 'miaot (generated)') {
    insert(tableName: 'person') {
      column(name: 'id', valueNumeric: 1)
      column(name: 'first_name', value: 'Terrence')
      column(name: 'last_name', value: 'Miao')
    }
  }

}
