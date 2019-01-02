#!/bin/bash

HOST_DIR=/tmp/hadoop-cluster-master/
CONTAINER_DIR=/tmp/host-bind/
mkdir -p $HOST_DIR
docker run --rm -it \
  --network hadoop-cluster-network \
  --name hadoop-cluster-client \
  --mount type=bind,source=$HOST_DIR,target=$CONTAINER_DIR \
  --workdir $CONTAINER_DIR \
  hadoopcluster_master-service \
  bash
