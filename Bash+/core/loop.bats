#!/usr/bin/env bats

@test "Repeat command N times (fixed)" {
  c=$((0))
  for n in {1..5}; do ((c=c+1)); done
  (( $c == 5 ))
}

@test "Repeat command N times (custom)" {
  count=$((0))
  repeat=5
  for n in $(seq $repeat); do 
  	((count=count+1))
  done
  (( $count == $repeat ))
}
