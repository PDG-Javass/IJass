#!/bin/bash
curl "http://localhost:8080/newgame"
echo
curl "http://localhost:8080/firstPartFold?gameId=0&trump=1"
echo
curl "http://localhost:8080/firstPartFold?gameId=0&playerId=0&cardPlayed=0"
echo
curl "http://localhost:8080/secondPartFold?gameId=0&playerId=0&cardPlayed=0"
