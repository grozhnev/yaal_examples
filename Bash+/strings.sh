#!/usr/bin/env bash

str="feature/test-8"
echo "Replace 8 with 9: ${str/8/9}"
echo "Replace '/' with '-': ${str////-}"
echo "Interval from 8 to the end: ${str:8}"