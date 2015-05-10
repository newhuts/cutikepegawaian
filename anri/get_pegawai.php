<?php
// array for JSON response
$response = array();
// include db connect class
require_once __DIR__ . '/db_connect.php';
// connecting to db
$db = new DB_CONNECT();
// check for post data
if (isset($_GET["username"])&& isset($_GET["password"])) {
    $us = $_GET['username'];
    $pw = $_GET['password'];
    // get a product from products table
    $result = mysql_query("SELECT * FROM pegawai WHERE username = '$us' and password = '$pw' " );
    if (!empty($result)) {
        // check for empty result
        if (mysql_num_rows($result) > 0) {
            $result = mysql_fetch_array($result);
            $pegawai = array();
            $pegawai["pin"] = $result["pin"];
            $pegawai["nama"] = $result["nama"];
            $pegawai["username"] = $result["username"];
            $pegawai["password"] = $result["password"];
            $pegawai["tahunlalu"] = $result["tahunlalu"];
            $pegawai["tahunini"] = $result["tahunini"];
            $pegawai["total"] = $result["total"];
            $pegawai["role"] = $result["role"];
            // success
            $response["success"] = 1;
            // user node
            $response["pegawai"] = array();
            array_push($response["pegawai"], $pegawai);
            // echoing JSON response
            echo json_encode($response);
        } else {
            // no product found
            $response["success"] = 0;
            $response["message"] = "Tidak ada Pegawai";
            // echo no users JSON
            echo json_encode($response);
        }
    } else {
        // no product found
        $response["success"] = 0;
        $response["message"] = "pegawai tidak ditemukan";
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