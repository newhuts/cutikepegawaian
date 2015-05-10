  <?php
// array for JSON response
$response = array();
// check for required fields
if (isset($_POST['pin']) && isset($_POST['id_cuti']) && isset($_POST['tgl_awal']) && isset($_POST['tgl_akhir']) && isset($_POST['jml_hari'])) { 
    $pin = $_POST['pin'];
    $id_cuti = $_POST['id_cuti'];
    $tgl_awal = $_POST['tgl_awal'];
    $tgl_akhir = $_POST['tgl_akhir'];
    $jml_hari = $_POST['jml_hari'];
    $ket = $_POST['ket'];
    $sta = $_POST['status'];
    // include db connect class
    require_once __DIR__ . '/db_connect.php';
    // connecting to db
    $db = new DB_CONNECT();
    // mysql inserting a new row
    $result = mysql_query("INSERT INTO permintaan(pin, id_cuti,tgl_awal,tgl_akhir, jml_hari, ket, status) VALUES('$pin', '$id_cuti', '$tgl_awal', '$tgl_akhir', '$jml_hari','$ket', '$sta')");
    // check if row inserted or not
    if ($result) {
        // successfully inserted into database
        $response["success"] = 1;
        $response["message"] = "permintaan berhasil dibuat.";
        // echoing JSON response
        echo json_encode($response);
    } else {
        // failed to insert row
        $response["success"] = 0;
        $response["message"] = "Ohh!. ada yang salah.";
        // echoing JSON response
        echo json_encode($response);
    }
} else {
    // required field is missing
    $response["success"] = 0;
    $response["message"] = "Field tidak lengkap";
    // echoing JSON response
    echo json_encode($response);
}
?>