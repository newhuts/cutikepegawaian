<?php
// array for JSON response
$response = array();
// check for required fields
if (isset($_POST['id'])) {
    $id = $_POST['id'];
    $hlalu = $_POST['tahunlalu'];
    $hini = $_POST['tahunini'];
    $htotal = $_POST['total'];
    // include db connect class
    require_once __DIR__ . '/db_connect.php';
    // connecting to db
    $db = new DB_CONNECT();
    // mysql update row with matched pid
    $result = mysql_query("UPDATE pegawai, permintaan 
                            SET permintaan.status = 'Accept', pegawai.tahunlalu = $hlalu , 
                            pegawai.tahunini= $hini , pegawai.total = $htotal 
                            WHERE permintaan.pin = pegawai.pin and permintaan.id = $id");
    // check if row inserted or not
    if ($result) {
        // successfully updated
        $response["success"] = 1;
        $response["message"] = "berhasil dirubah.";
        // echoing JSON echo
        echo json_encode($response);
    } else {
    }
} else {
    // required field is missing
    $response["success"] = 0;
    $response["message"] = "Filed tidak lengkap";
    // echoing JSON response
    echo json_encode($response);
}
?>
