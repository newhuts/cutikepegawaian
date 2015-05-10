<?php
// array for JSON response
$response = array();
// include db connect class
require_once __DIR__ . '/db_connect.php';
// connecting to db
$db = new DB_CONNECT();
// check for post data
if (isset($_GET["id"])) {
    $id = $_GET['id'];
    // get a product from products table
    $result = mysql_query("SELECT * FROM permintaan, pegawai, cuti WHERE permintaan.pin=pegawai.pin and 
                            permintaan.id_cuti=cuti.id_cuti and permintaan.id ='$id' " );

    if (!empty($result)) {
        // check for empty result
        if (mysql_num_rows($result) > 0) {
            $result = mysql_fetch_array($result);
            $detail = array();
            $detail["id"] = $result["id"];
            $detail["pin"] = $result["pin"];
            $detail["nama"] = $result["nama"];
            $detail["tahunlalu"] = $result["tahunlalu"];
            $detail["tahunini"] = $result["tahunini"];
            $detail["total"] = $result["total"];
            $detail["tgl_permintaan"] = $result["tgl_permintaan"];
            $detail["tgl_awal"] = $result["tgl_awal"];
            $detail["tgl_akhir"] = $result["tgl_akhir"];
            $detail["jml_hari"] = $result["jml_hari"];
            $detail["id_cuti"] = $result["id_cuti"];
            $detail["jenis_cuti"] = $result["jenis_cuti"];
            $detail["nama_cuti"] = $result["nama_cuti"];
            $detail["ket"] = $result["ket"];
            $detail["status"] = $result["status"];
            // success
            $response["success"] = 1;
            // user node
            $response["permintaan"] = array();
            array_push($response["permintaan"], $detail);
            // echoing JSON response
            echo json_encode($response);
        } else {
            // no product found
            $response["success"] = 0;
            $response["message"] = "Tidak ada permintaan";
            // echo no users JSON
            echo json_encode($response);
        }
    } else {
        // no product found
        $response["success"] = 0;
        $response["message"] = "ohh!! ada yang salah";
        // echo no users JSON
        echo json_encode($response);
    }
} else {
    // required field is missing
    $response["success"] = 0;
    $response["message"] = "field tidak lengkap";
    // echoing JSON response
    echo json_encode($response);
}
?>