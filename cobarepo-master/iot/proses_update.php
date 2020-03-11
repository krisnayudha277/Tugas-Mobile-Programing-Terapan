<?php

include("config.php");

        $status= $_GET['status'];
    
        
	$sql = "UPDATE lampu SET status='$status' WHERE id_lampu='1'";
	$query = mysqli_query($db, $sql);
	
	// apakah query update berhasil?
	if( $query ) {
		// kalau berhasil alihkan ke halaman index.php
		header('Location: index.php');
	} else {
		// kalau gagal tampilkan pesan
		die("Gagal menyimpan perubahan...");
	}
	
	


?>