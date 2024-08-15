<?php
include 'db_config.php';

// Obtener datos del formulario
$pais = $_POST['pais'];
$oro = $_POST['oro'];
$plata = $_POST['plata'];
$bronce = $_POST['bronce'];

// Crear conexión
$conn = new mysqli(DB_SERVER, DB_USERNAME, DB_PASSWORD, DB_DATABASE);

// Verificar conexión
if ($conn->connect_error) {
    die("Conexión fallida: " . $conn->connect_error);
}

// Preparar y ejecutar la consulta
$sql = "INSERT INTO medallas (pais, oro, plata, bronce) VALUES (?, ?, ?, ?)";
$stmt = $conn->prepare($sql);
$stmt->bind_param("ssss", $pais, $oro, $plata, $bronce);

if ($stmt->execute()) {
    header("Location: index.html");
} else {
    echo "Error: " . $stmt->error;
}

$stmt->close();
$conn->close();
?>