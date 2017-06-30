# ElasticSearch Docker Image

1. Source: https://www.elastic.co/guide/en/elasticsearch/reference/current/docker.html

2. Run

```
export ELASTIC_VERSION=5.4.3

docker run -p 9200:9200 \
  -e "http.host=0.0.0.0"  \
  -e "transport.host=127.0.0.1" \
  docker.elastic.co/elasticsearch/elasticsearch:${ELASTIC_VERSION}
```

3. Test
URL: http://localhost:9200
Pass: elastic/changeme