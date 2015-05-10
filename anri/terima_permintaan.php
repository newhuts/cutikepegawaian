<?php
// array for JSON response
$response = array();
// include db connect class
require_once __DIR__ . '/db_connect.php';
// connecting to db
$db = new DB_CONNECT();
// get all products from products table
$result = mysql_query("SELECT * FROM permintaan, pegawai, cuti WHERE permintaan.pin=pegawai.pin and 
                            permintaan.id_cuti=cuti.id_cuti and permintaan.status ='Pending' ORDER BY permintaan.id DESC ") or die(mysql_error());
// check for empty result
if (mysql_num_rows($result) > 0) {
    // looping through all results
    // products node
    $response["permintaan"] = array();
    while ($row = mysql_fetch_array($result)) {
        // temp user array
        $terima = array();
            $terima["id"] = $row["id"];
            $terima["pin"] = $row["pin"];
            $terima["nama_cuti"] = $row["nama_cuti"];
            $terima["nama"] = $row["nama"];
            $terima["tgl_permintaan"] = $row["tgl_permintaan"];
            $terima["tgl_awal"] = $row["tgl_awal"];
            $terima["tgl_akhir"] = $row["tgl_akhir"];
            $terima["jml_hari"] = $row["jml_hari"];
            $terima["jenis_cuti"] = $row["jenis_cuti"];
            $terima["ket"] = $row["ket"];
            $terima["status"] = $row["status"];
        // push single product into final response array
        array_push($response["permintaan"], $terima);
    }
    // success
    $response["success"] = 1;
    // echoing JSON response
    echo json_encode($response);
} else {
    // no products found
    $response["success"] = 0;
    $response["message"] = "tidak ada permintaan";
    // echo no users JSON
    echo json_encode($response);
}
?>
