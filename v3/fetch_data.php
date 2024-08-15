<?php
include 'db_config.php';

// Crear conexión
$conn = new mysqli(DB_SERVER, DB_USERNAME, DB_PASSWORD, DB_DATABASE);

// Verificar conexión
if ($conn->connect_error) {
    die("Conexión fallida: " . $conn->connect_error);
}

// Consultar datos
$sql = "SELECT pais, oro, plata, bronce FROM medallas";
$result = $conn->query($sql);

if ($result->num_rows > 0) {
    while($row = $result->fetch_assoc()) {
        echo "<tr>
                <td>{$row['pais']}</td>
                <td>{$row['oro']}</td>
                <td>{$row['plata']}</td>
                <td>{$row['bronce']}</td>
              </tr>";
    }
} else {
    echo "<tr><td colspan='4' class='text-center'>No se encontraron registros</td></tr>";
}

$conn->close();
?>