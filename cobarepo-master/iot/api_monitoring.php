<?php

include("config.php");

    $status= $_GET['status'];
    
	// buat query update
	$sql = "UPDATE lampu SET status_aktual='$status' WHERE id_lampu='1'";
	$query = mysqli_query($db, $sql);
	
	// apakah query update berhasil?
	if( $query ) {
		// kalau berhasil alihkan ke halaman list-siswa.php
		//header('Location: identitas_masjid.php');
	} else {
		// kalau gagal tampilkan pesan
		die("Gagal menyimpan perubahan...");
	}
	
	


?>
