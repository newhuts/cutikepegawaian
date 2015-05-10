<?php
// array for JSON response
$response = array();
// include db connect class
require_once __DIR__ . '/db_connect.php';
// connecting to db
$db = new DB_CONNECT();
// check for post data
if (isset($_GET["tgl_awal"]) && $_GET["tgl_akhir"]) {
    $awal = $_GET['tgl_awal'];
    $akhir = $_GET['tgl_akhir'];
    // get a product from products table
    $result = mysql_query("SELECT count(*)id from kalender where str_to_date(tanggal,'%Y-%m-%d') >= str_to_Date('$awal','%Y-%m-%d') and str_to_date(tanggal,'%Y-%m-%d') <= str_to_Date('$akhir','%Y-%m-%d') and libur = '0' " );

    if (!empty($result)) {
        // check for empty result
        if (mysql_num_rows($result) > 0) {

            $result = mysql_fetch_array($result);

            $libur = array();
            $libur["id"] = $result["id"];
            // success
            $response["success"] = 1;
            // user node
            $response["permintaan"] = array();
            array_push($response["permintaan"], $libur);
            // echoing JSON response
            echo json_encode($response);
        } else {
            // no product found
            $response["success"] = 0;
            $response["message"] = "Tidak ada Histori";
            // echo no users JSON
            echo json_encode($response);
        }
    } else {
        // no product found
        $response["success"] = 0;
        $response["message"] = "Ohh!. ada yang salah";

        // echo no users JSON
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