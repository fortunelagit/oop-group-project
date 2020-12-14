Sprite.java
Permainan ini memiliki pesawat luar angkasa dan alien. Kita bisa memindahkan pesawat ruang angkasa di papan dengan menggunakan tombol kursor. Rudal yang menghancurkan alien diluncurkan dengan tombol spasi.

SpaceShip.java
fungsi getBounds() persegi panjang berlari dari gambar sprite. Kita membutuhkan batasan dalam deteksi tabrakan.

Semua rudal yang ditembakkan oleh pesawat ruang angkasa itu disimpan dalam missiles list.
Saat kita menembakkan rudal, Missileobject baru ditambahkan ke missiles list. Itu dipertahankan dalam daftar sampai bertabrakan dengan alien atau keluar dari jendela.

Board.java
initAliens()Methods menciptakan daftar objek alien. Alien mengambil posisi awal mereka dari pos-array.

Di dalam paintComponent()methods ini, kita menggambar sprite game atau menulis pesan game over. Ini tergantung pada ingame variabelnya.
The drawObjects()methods menarik sprite permainan di jendela. Pertama, kita menggambar sprite kerajinan.
Dalam lingkaran ini kita menggambar semua alien; mereka ditarik hanya jika belum pernah dihancurkan sebelumnya. Ini diperiksa dengan isVisible() method.

Di sudut kiri atas jendela, kita menggambarkan berapa banyak alien yang tersisa.

The drawGameOver()menarik permainan selama pesan di tengah jendela. Pesan tersebut ditampilkan di akhir permainan, baik saat kita menghancurkan semua kapal alien maupun saat kita bertabrakan dengan salah satunya.

Setiap peristiwa aksi mewakili satu siklus permainan. Logika permainan difaktorkan ke dalam metode tertentu. Misalnya, updateMissiles() menggerakkan semua rudal yang tersedia.

Di dalam updateAliens()method ini, pertama-tama kami memeriksa apakah ada objek asing yang tersisa di aliensdaftar. Permainan selesai jika daftarnya kosong. Jika tidak kosong, kita pergi melalui daftar dan memindahkan semua itemnya. Alien yang dihancurkan dihapus dari daftar.

The checkCollisions()Method pemeriksaan untuk kemungkinan tabrakan. Pertama, kita memeriksa apakah objek kerajinan bertabrakan dengan salah satu objek alien. Kami mendapatkan persegi panjang objek dengan getBounds()method. intersects()Method pemeriksaan jika dua persegi panjang berpotongan.

Alien.java
Kode ini memeriksa tabrakan antara rudal dan alien.

Missile.java
Alien kembali ke layar di sisi kanan setelah mereka menghilang di sisi kiri.

CollisionEx.java
Rudal hanya bergerak ke satu arah. Mereka menghilang setelah mencapai batas jendela kanan.


