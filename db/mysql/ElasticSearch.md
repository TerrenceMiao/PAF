# Postal Address in ElasticSearch

- Install ElasticSearch JDBC
- Copy mysql-postal-address.sh file into /usr/local/elasticsearch-jdbc/bin directory
- Run mysql-postal-address.sh. It takes about 15 minutes import ALL 13 millions addresses into ElasticSearch
- Go to ElasticSearch Head http://localhost:9200/_plugin/head/
- Go to Kibana, ElasticSearch web interface http://localhost:5601/app/kibana. Create and configure an index pattern "postaladdress*". Then search address e.g. **"00018 Balfour Cl Point Cook VIC 3030"**
- Go to Sense http://localhost:5601/app/sense, input Server http://localhost:9200, query address **"00018 Balfour Cl Point Cook VIC 3030"**:

Fuzzy Search:

```
GET /postaladdress/_search
{
  "query": {
    "query_string": {
      "fields": ["house_nbr_1", "street_name", "street_type", "locality_name", "state", "postcode"],
      "query": "00018 Balfour Cl Point Cook VIC 3030"
    }
  }
}
```

Exact Match:

```
GET /postaladdress/_search
{
  "query": {
    "and": [{
      "match": {
        "house_nbr_1": "00018"
      }
    }, {
      "match": {
        "street_name": "BALFOUR"
      }
    }, {
      "match": {
        "street_type": "CL"
      }
    }, {
      "match": {
        "locality_name": "POINT COOK"
      }
    }, {
      "match": {
        "postcode": "3030"
      }
    }, {
      "match": {
        "state": "VIC"
      }
    }]
  }
}
```

Combining Queries:

```
{
  "query": {
    "bool": {
      "must": [
        { "match": { "house_nbr_1": "00018" }}
      ],
      "should": {
        "query_string": {
          "fields": ["street_name", "street_type", "locality_name", "state", "postcode"],
          "query": "Balfour Cl Point Cook VIC 3030"
        }
      }
    }
  }
}
```

or

```
{
  "query": {
    "bool": {
      "must": [
        { "match": { "house_nbr_1": "00018" }},
        { "match": { "postcode": "3030" }}
      ],
      "should": {
        "query_string": {
          "fields": ["street_name", "street_type", "locality_name", "state"],
          "query": "Balfour Cl Point Cook VIC"
        }
      }
    }
  }
}
```

Response from ElasticSearch with matched Postal Address:

```
{
  "took": 23,
  "timed_out": false,
  "_shards": {
    "total": 2,
    "successful": 2,
    "failed": 0
  },
  "hits": {
    "total": 1,
    "max_score": 5.6779156,
    "hits": [
      {
        "_index": "postaladdress",
        "_type": "postaladdress",
        "_id": "AVY8zY0ydD5mzQSfZW_t",
        "_score": 5.6779156,
        "_source": {
          "delivy_point_id": "83983570",
          "postal_delivery_nbr": "00000",
          "postal_delivery_type": "",
          "lot_nbr": "",
          "floor_level_type": "",
          "floor_level_nbr": "",
          "flat_unit_type": "",
          "flat_unit_nbr": "",
          "house_nbr_1": "00018",
          "house_nbr_sfx_1": "",
          "street_name": "BALFOUR",
          "street_type": "CL",
          "locality_name": "POINT COOK",
          "postcode": "3030",
          "state": "VIC"
        }
      }
    ]
  }
}
```

### References
- JDBC importer for Elasticsearch, https://github.com/jprante/elasticsearch-jdbc
- elasticsearch-head, A web front end for an Elasticsearch cluster, https://github.com/mobz/elasticsearch-head
- ElasticSearch 101 – a getting started tutorial, http://joelabrahamsson.com/elasticsearch-101/
- Elasticsearch Cheatsheet, http://elasticsearch-cheatsheet.jolicode.com/