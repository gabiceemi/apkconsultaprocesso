<?php
header('Content-Type: text/html; charset=utf-8');
$conex = pg_connect("host=localhost port=5432 dbname=postgres user=postgres password=0433vek* ") or die ("Nao foi possivel conectar ao servidor POSTGRESQL");
pg_query($conex, "SET NAMES 'utf8'");
?>
