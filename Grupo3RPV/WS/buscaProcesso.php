<?php
include('config.php');


$id_processo = $_GET['id'];
$nr_documento = $_GET['documento'];

if($id_processo == '' || $nr_documento == ''){
    echo 'ERRO 404';
} else {
    if($rs = pg_query($conex, "SELECT processo.\"_ID\", processo.\"numProcesso\", processo.\"dtProcesso\", processo.\"obsProcesso\", processo.\"horaProcesso\",
req.\"nomeCidadao\" as requerente, req.\"cpfOUcnpj\" as docRequerente, tit.\"nomeCidadao\" as titular, tit.\"cpfOUcnpj\" as docTitular, departamento.\"nomeDepartamento\", funcionario.\"nomeFuncionario\", tipo.\"nomeTipo\"
FROM processo
INNER JOIN andamento
ON processo.\"_ID\" =  andamento.\"idProcesso\"
INNER JOIN departamento
ON andamento.\"idDepto\" = departamento.\"_ID\"
INNER JOIN cidadao req 
ON processo.\"idRequerente\" = req.\"_ID\"
INNER JOIN cidadao tit 
ON processo.\"idTitular\" = tit.\"_ID\"
INNER JOIN funcionario
ON processo.\"idFuncionario\" = funcionario.\"_ID\"
INNER JOIN tipo
ON processo.\"idTipo\" = tipo.\"_ID\"
WHERE processo.\"_ID\" = '$id_processo'
AND req.\"cpfOUcnpj\" = '$nr_documento'
AND processo.\"dtProcesso\" = andamento.\"dtAndamento\";")) {

        while ($row = pg_fetch_assoc($rs)) {
            $json = $row;
        }
        header('Content-type: application/json');
        //var_dump($json);
        echo json_encode($json, JSON_PRETTY_PRINT);
        $json = null;
        $rs = null;
        pg_close($conex);
    } else {
        echo 'A BUSCA NAO RETORNOU RESULTADOS';
    }
}

?>