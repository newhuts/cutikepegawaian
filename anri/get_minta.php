<?php
// array for JSON response
$response = array();
// include db connect class
require_once __DIR__ . '/db_connect.php';
// connecting to db
$db = new DB_CONNECT();
if (isset($_GET["pin"])) {
    $pin = $_GET['pin'];
$result = mysql_query("SELECT * FROM permintaan, pegawai, cuti WHERE permintaan.pin=pegawai.pin and 
                            permintaan.id_cuti=cuti.id_cuti and permintaan.pin ='$pin' ORDER BY permintaan.id DESC ") or die(mysql_error());
// check for empty result
if (mysql_num_rows($result) > 0) {
    // looping through all results
    // products node
    $response["permintaan"] = array();
    while ($row = mysql_fetch_array($result)) {
        // temp user array
        $permintaan = array();
            $permintaan["id"] = $row["id"];
            $permintaan["pin"] = $row["pin"];
            $permintaan["nama_cuti"] = $row["nama_cuti"];
            $permintaan["nama"] = $row["nama"];
            $permintaan["tgl_permintaan"] = $row["tgl_permintaan"];
            $permintaan["tgl_awal"] = $row["tgl_awal"];
            $permintaan["tgl_akhir"] = $row["tgl_akhir"];
            $permintaan["jml_hari"] = $row["jml_hari"];
            $permintaan["jenis_cuti"] = $row["jenis_cuti"];
            $permintaan["ket"] = $row["ket"];
            $permintaan["status"] = $row["status"];
        // push single product into final response array
        array_push($response["permintaan"], $permintaan);
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
}
?>
