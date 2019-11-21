<?php
include('config.php');

$id_processo = $_GET['id'];

if ($rs = pg_query($conex, "SELECT andamento.\"_ID\", andamento.\"idProcesso\", andamento.\"dtAndamento\", andamento.\"ocorrencia\", departamento.\"nomeDepartamento\", andamento.\"despachoProcesso\"
FROM andamento
INNER JOIN departamento
ON andamento.\"idDepto\" = departamento.\"_ID\"
WHERE andamento.\"idProcesso\" = $id_processo
ORDER BY andamento.\"_ID\" ASC")) {
    while($row = pg_fetch_assoc($rs)){
        $json[] = $row;
    }
    header('Content-type: application/json');
    //var_dump($json);
    echo json_encode($json, JSON_PRETTY_PRINT);
    $rs = null;
    pg_close($conex);

}

?>